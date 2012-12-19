package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { nz.co.jsrsolutions.tideservice.core.domain.SubArea.class })
public interface SubAreaService {
  
  public abstract List<SubArea> findSubAreas(Area area);
  
}
