package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;

import org.joda.time.LocalDate;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TidePredictionDay.class)
public interface TidePredictionDayRepository {
  
  public abstract TidePredictionDay findByPortAndLocalDate(@Param("port") Port port, @Param("localDate") LocalDate localDate);
  
  
}
