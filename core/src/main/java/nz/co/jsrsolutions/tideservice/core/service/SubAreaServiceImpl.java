package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;


public class SubAreaServiceImpl implements SubAreaService {

  @Override
  public List<SubArea> findSubAreas(Area area) {
    return subAreaRepository.findByArea(area);
  }

}
