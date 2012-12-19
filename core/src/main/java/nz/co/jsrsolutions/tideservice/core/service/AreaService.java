package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import java.util.Set;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { nz.co.jsrsolutions.tideservice.core.domain.Area.class,
                            nz.co.jsrsolutions.tideservice.core.domain.SubArea.class})
public interface AreaService {
  
  
  /**
   * 
   * Updates any areas that appear to be different to the supplied
   * Area objects.  Note that the criteria for comparison are
   * fewer than for the implementation of the Area object's
   * compareTo() method.  Each area for comparison is fetched and
   * only the reduced comparison fields are updated.  If an
   * area is not present, then it is inserted.  If an area is
   * present in the persistence layer but not in the supplied
   * set then it is removed from the persistence layer.
   * 
   * @param areas
   * @return
   */
  public abstract List<Area> updateAreas(List<Area> areas);
  
  /**
   * 
   * Updates any subAreas that appear to be different to 
   * those associated with the supplied Area objects.  
   * Note that the criteria for comparison are
   * fewer than for the implementation of the SubArea object's
   * compareTo() method.  Each subArea for comparison is fetched and
   * only the reduced comparison fields are updated.  If an
   * subArea is not present, then it is inserted.  If an subArea is
   * present in the persistence layer but not in the supplied
   * set then it is removed from the persistence layer.
   * 
   * @param subAreas
   * @return
   */
  public abstract Set<SubArea> updateSubAreas(long areaId, List<SubArea> subAreas);
}
