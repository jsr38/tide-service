package nz.co.jsrsolutions.tideservice.core.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(sequenceName = "PREDICTIONDAY_SEQ")
@RooEquals
@RooJson(deepSerialize = true)
public class TidePredictionDay extends DomainBase {

    @NotNull
    @DateTimeFormat(style = "M-")
    private LocalDate localDate;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TidePrediction> tidePredictions = new HashSet<TidePrediction>();

    @NotNull
    @ManyToOne
    private Port port;
}
