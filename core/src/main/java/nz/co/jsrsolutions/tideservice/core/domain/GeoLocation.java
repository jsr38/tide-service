package nz.co.jsrsolutions.tideservice.core.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Embeddable
@RooJson(deepSerialize = true)
public class GeoLocation {

    @NotNull
    private int latitude;

    @NotNull
    private int longitude;
}
