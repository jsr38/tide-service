package nz.co.jsrsolutions.tideservice.core.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;
import nz.co.jsrsolutions.tideservice.core.repository.PortRepository;

import org.springframework.beans.factory.annotation.Autowired;


public class TidePredictionDayServiceImpl implements TidePredictionDayService {

  @Autowired
  PortRepository portRepository;

  @Override
  public Set<TidePredictionDay> updateTidePredictionDays(long portId,
      List<TidePredictionDay> tidePredictionDays) {
    
    final Port port = portRepository.findOne(portId);
    final Set<TidePredictionDay> tidePredictionDaySet = new HashSet<TidePredictionDay>(tidePredictionDays.size()); 
    
    for (TidePredictionDay tidePredictionDay : tidePredictionDays) {
      
      tidePredictionDay.setPort(port);
      tidePredictionDay = tidePredictionDayRepository.save(tidePredictionDay);
      
      tidePredictionDaySet.add(tidePredictionDay);
    }
    
    return tidePredictionDaySet;
    
  }



}
