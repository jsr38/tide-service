package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.Area;

import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Area.class)
public interface AreaRepository {
  
  public abstract Area findByExternalId(@Param("externalId") String externalId);
  
}
