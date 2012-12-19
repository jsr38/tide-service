package nz.co.jsrsolutions.tideservice.core.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.repository.SubAreaRepository;
import nz.co.jsrsolutions.tideservice.core.util.PortExternalIdNameDescriptionComparator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class PortServiceImpl implements PortService {
  
  private static final transient Logger mLogger = Logger
      .getLogger(PortServiceImpl.class);
  
  @Autowired
  SubAreaRepository subAreaRepository;
  
  @Override
  @Transactional(readOnly=false)
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
  
}
