package nz.co.jsrsolutions.tideservice.core.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.repository.SubAreaRepository;
import nz.co.jsrsolutions.tideservice.core.util.DistanceCalculator;
import nz.co.jsrsolutions.tideservice.core.util.HaversineDistanceCalculator;
import nz.co.jsrsolutions.tideservice.core.util.PortDistancePair;
import nz.co.jsrsolutions.tideservice.core.util.PortExternalIdNameDescriptionComparator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jsr
 * 
 */
public class PortServiceImpl implements PortService {

  private static final transient Logger mLogger = Logger
      .getLogger(PortServiceImpl.class);

  @Autowired
  private SubAreaRepository subAreaRepository;

  private DistanceCalculator mDistanceCalculator;

  public PortServiceImpl() {
    mDistanceCalculator = new HaversineDistanceCalculator();
  }

  @Override
  @Transactional(readOnly = false)
  public Set<Port> updatePorts(long subAreaId, List<Port> ports) {

    SubArea subArea = subAreaRepository.findOne(subAreaId);
    Set<Port> repoPorts = subArea.getPorts();
    Set<Port> portsForRemoval = new HashSet<Port>(repoPorts);

    for (Port port : ports) {

      mLogger.info(port.getExternalId());
      Port repoPort = portRepository.findByExternalId(port.getExternalId());

      if (repoPort == null) {
        mLogger.info("Add new");
        port.setSubArea(subArea);
        if (port.getGeoLocation() == null) {
          port.setGeoLocation(new GeoLocation());
        }
        repoPorts.add(port);

      } else {
        mLogger.info("Update");
        Comparator<Port> comparator = new PortExternalIdNameDescriptionComparator();

        if (comparator.compare(port, repoPort) != 0) {

          repoPort.setName(subArea.getName());
          repoPort.setDescription(subArea.getDescription());

          repoPorts.add(repoPort);

        }
        portsForRemoval.remove(repoPort);
        // otherwise no-op, it's identical (for our purposes)
      }

    }

    repoPorts.removeAll(portsForRemoval);
    subArea = subAreaRepository.save(subArea);

    return repoPorts;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Port> findNearestNeighbours(GeoLocation geoLocation, int numPorts) {

    List<Port> ports = portRepository.findAll();
    SortedSet<PortDistancePair> portDistancePairs = new TreeSet<PortDistancePair>();

    for (Port port : ports) {
      portDistancePairs.add(new PortDistancePair(port, mDistanceCalculator
          .calculate(geoLocation, port.getGeoLocation())));
    }

    List<Port> nearestPorts = new ArrayList<Port>(numPorts);
    int i = 0;
    for (Iterator<PortDistancePair> iterator = portDistancePairs.iterator(); i < numPorts
        && iterator.hasNext();) {
      nearestPorts.add(iterator.next().getPort());
    }
    return nearestPorts;
  }

}
