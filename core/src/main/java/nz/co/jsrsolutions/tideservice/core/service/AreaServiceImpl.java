package nz.co.jsrsolutions.tideservice.core.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.repository.SubAreaRepository;
import nz.co.jsrsolutions.tideservice.core.util.AreaExternalIdNameDescriptionComparator;
import nz.co.jsrsolutions.tideservice.core.util.SubAreaExternalIdNameDescriptionComparator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AreaServiceImpl implements AreaService {

  private static final transient Logger mLogger = Logger
      .getLogger(AreaServiceImpl.class);
  
  @Autowired
  SubAreaRepository subAreaRepository;
  
  @Override
  @Transactional(readOnly=false)
  public List<Area> updateAreas(List<Area> areas) {

    List<Area> repoAreas = areaRepository.findAll();

    for (Area area : areas) {

      Area repoArea = areaRepository.findByExternalId(area.getExternalId());

      if (repoArea == null) {
        repoArea = areaRepository.save(area);
      } else {

        Comparator<Area> areaComparator = new AreaExternalIdNameDescriptionComparator();
        if (areaComparator.compare(area, repoArea) != 0) {
          repoArea.setName(area.getName());
          repoArea.setDescription(area.getDescription());
          repoArea = areaRepository.save(repoArea); 
        }
        repoAreas.remove(repoArea);
        // each pertinent field was equal to what we already knew, so no-op
      }

    }

    // The following must be removed as they were not
    // supplied. Could have called removeAll, but our
    // supplied Area objects are not fully populated
    // and therefore equals() and hashCode() contracts
    // are morally violated.
    areaRepository.delete(repoAreas);

    return areaRepository.findAll();
  }

  @Override
  @Transactional(readOnly=false)
  public Set<SubArea> updateSubAreas(long areaId, List<SubArea> subAreas) {

    Area area = areaRepository.findOne(areaId);
    Set<SubArea> repoSubAreas = area.getSubArea();
    Set<SubArea> subAreasForRemoval = new HashSet<SubArea>(repoSubAreas);
    
    for (SubArea subArea : subAreas) {
      
      mLogger.info(subArea.getExternalId());
      SubArea repoSubArea = subAreaRepository.findByAreaAndExternalId(area, subArea.getExternalId());
      
      if (repoSubArea == null) {
        mLogger.info("Add new");
        subArea.setArea(area);
        repoSubAreas.add(subArea);
        
      } else {
        mLogger.info("Update");
        Comparator<SubArea> comparator = new SubAreaExternalIdNameDescriptionComparator();
        
        if (comparator.compare(subArea, repoSubArea) != 0) {
          
          repoSubArea.setName(subArea.getName());
          repoSubArea.setDescription(subArea.getDescription());
          
          repoSubAreas.add(repoSubArea);
          
        }
        subAreasForRemoval.remove(repoSubArea);
        // otherwise no-op, it's identical (for our purposes)
      }
      
    }
    
    repoSubAreas.removeAll(subAreasForRemoval);
    area = areaRepository.save(area);

    return repoSubAreas;
  }
}
