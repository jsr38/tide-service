// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect TidePredictionDay_Roo_Equals {
    
    public boolean TidePredictionDay.equals(Object obj) {
        if (!(obj instanceof TidePredictionDay)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        TidePredictionDay rhs = (TidePredictionDay) obj;
        return new EqualsBuilder().append(createdBy, rhs.createdBy).append(createdDate, rhs.createdDate).append(id, rhs.id).append(isActive, rhs.isActive).append(lastModifiedBy, rhs.lastModifiedBy).append(lastModifiedDate, rhs.lastModifiedDate).append(localDate, rhs.localDate).append(port, rhs.port).isEquals();
    }
    
    public int TidePredictionDay.hashCode() {
        return new HashCodeBuilder().append(createdBy).append(createdDate).append(id).append(isActive).append(lastModifiedBy).append(lastModifiedDate).append(localDate).append(port).toHashCode();
    }
    
}