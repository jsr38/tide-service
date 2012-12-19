package nz.co.jsrsolutions.tideservice.core.repository;

import java.util.List;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;

import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = SubArea.class)
public interface SubAreaRepository {
  
  public abstract SubArea findByAreaAndExternalId(@Param("area") Area area, @Param("externalId") String externalId);
  
  public abstract List<SubArea> findByArea(@Param("area") Area area);
}
