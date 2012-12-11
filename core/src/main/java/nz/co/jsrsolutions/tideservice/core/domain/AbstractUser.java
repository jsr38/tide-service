package nz.co.jsrsolutions.tideservice.core.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(identifierType = String.class)
@RooEquals
@RooJson(deepSerialize = true)
public abstract class AbstractUser extends DomainBase {

    @NotNull
    @Size(min = 1, max = 80)
    private String userName;

    @NotNull
    @Size(min = 1, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100)
    private String lastName;
}
