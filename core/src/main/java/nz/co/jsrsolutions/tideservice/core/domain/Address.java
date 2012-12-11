package nz.co.jsrsolutions.tideservice.core.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Embeddable
@RooJson(deepSerialize = true)
public class Address {

    @NotNull
    @Size(min = 1, max = 50)
    private String addressLine1;

    @Size(min = 1, max = 50)
    private String addressLine2;

    @NotNull
    @Size(max = 30)
    private String city;

    @NotNull
    @Size(max = 30)
    private String postalCode;

    @NotNull
    @Size(max = 30)
    private String country;
}
