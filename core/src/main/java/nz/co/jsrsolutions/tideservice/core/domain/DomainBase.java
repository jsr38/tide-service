package nz.co.jsrsolutions.tideservice.core.domain;

import javax.persistence.ManyToOne;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(mappedSuperclass = true)
@RooJson(deepSerialize = true)
public class DomainBase {

    @ManyToOne
    private AbstractUser createdBy;

    private DateTime createdDate;

    @ManyToOne
    private AbstractUser lastModifiedBy;

    private DateTime lastModifiedDate;

    @Value("true")
    private Boolean isActive;
}
