// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import nz.co.jsrsolutions.tideservice.core.domain.Port;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect Port_Roo_Equals {
    
    public boolean Port.equals(Object obj) {
        if (!(obj instanceof Port)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Port rhs = (Port) obj;
        return new EqualsBuilder().append(createdBy, rhs.createdBy).append(createdDate, rhs.createdDate).append(description, rhs.description).append(externalId, rhs.externalId).append(geoLocation, rhs.geoLocation).append(id, rhs.id).append(isActive, rhs.isActive).append(lastModifiedBy, rhs.lastModifiedBy).append(lastModifiedDate, rhs.lastModifiedDate).append(name, rhs.name).append(subArea, rhs.subArea).isEquals();
    }
    
    public int Port.hashCode() {
        return new HashCodeBuilder().append(createdBy).append(createdDate).append(description).append(externalId).append(geoLocation).append(id).append(isActive).append(lastModifiedBy).append(lastModifiedDate).append(name).append(subArea).toHashCode();
    }
    
}
