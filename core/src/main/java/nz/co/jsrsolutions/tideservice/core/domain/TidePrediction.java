package nz.co.jsrsolutions.tideservice.core.domain;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import nz.co.jsrsolutions.tideservice.core.reference.TidePredictionType;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(sequenceName = "PREDICTION_SEQ")
@RooEquals
@RooJson(deepSerialize = true)
public class TidePrediction extends DomainBase {

    @NotNull
    @Enumerated
    private TidePredictionType tidePredictionType;

    @NotNull
    @DateTimeFormat(style = "M-")
    private DateTime utcTime;

    private Float height;

    @NotNull
    @ManyToOne
    private TidePredictionDay tidePredictionDay;
}
