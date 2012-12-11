package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.TidePrediction;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = TidePrediction.class)
public interface TidePredictionRepository {
}
