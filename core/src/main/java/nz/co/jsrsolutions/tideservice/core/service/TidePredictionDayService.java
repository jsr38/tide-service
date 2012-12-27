package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import java.util.Set;

import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay.class })
public interface TidePredictionDayService {
  
  /**
   * 
   * Updates any tide prediction days to the latest version.
   * 
   * @param 
   * @return
   */
  public abstract Set<TidePredictionDay> updateTidePredictionDays(long portId, List<TidePredictionDay> tidePredictionDays);
  
}
