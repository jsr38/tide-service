package nz.co.jsrsolutions.tideservice.core.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(sequenceName = "PORT_SEQ")
@RooEquals
@RooJson(deepSerialize = true)
public class Port extends DomainBase {

  @NotNull
  @Column(unique = true)
  @Size(max = 30)
  private String externalId;
  
  @Size(max = 60)
  private String name;

  @Size(max = 255)
  private String description;

  @Embedded
  private GeoLocation geoLocation;

  @NotNull
  @ManyToOne
  private SubArea subArea;


}
