package nz.co.jsrsolutions.tideservice.core.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(sequenceName = "AREA_SEQ")
@RooEquals
@RooJson(deepSerialize = true)
public class Area extends DomainBase {

  @NotNull
  @Column(unique = true)
  @Size(max = 60)
  private String externalId;

  @Size(max = 60)
  private String name;

  @Size(max = 255)
  private String description;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
  private Set<SubArea> subArea = new HashSet<SubArea>();
}
