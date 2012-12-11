package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = SubArea.class)
public interface SubAreaRepository {
}
