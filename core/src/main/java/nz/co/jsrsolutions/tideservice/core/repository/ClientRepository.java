package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.Client;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Client.class)
public interface ClientRepository {
}
