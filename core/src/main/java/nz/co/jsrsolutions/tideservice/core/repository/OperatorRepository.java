package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.Operator;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Operator.class)
public interface OperatorRepository {
}
