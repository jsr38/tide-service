package nz.co.jsrsolutions.tideservice.core.domain;

import javax.persistence.Embeddable;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Embeddable
@RooJson(deepSerialize = true)
public class GeoLocation {
  
    private int latitude;
    

    private int longitude;
}
