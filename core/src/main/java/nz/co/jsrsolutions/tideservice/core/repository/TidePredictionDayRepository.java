package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TidePredictionDay.class)
public interface TidePredictionDayRepository {
}
