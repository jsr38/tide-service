// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import nz.co.jsrsolutions.tideservice.core.domain.Client;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect Client_Roo_Equals {
    
    public boolean Client.equals(Object obj) {
        if (!(obj instanceof Client)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Client rhs = (Client) obj;
        return new EqualsBuilder().append(createdBy, rhs.createdBy).append(createdDate, rhs.createdDate).append(firstName, rhs.firstName).append(id, rhs.id).append(isActive, rhs.isActive).append(lastModifiedBy, rhs.lastModifiedBy).append(lastModifiedDate, rhs.lastModifiedDate).append(lastName, rhs.lastName).append(userName, rhs.userName).isEquals();
    }
    
    public int Client.hashCode() {
        return new HashCodeBuilder().append(createdBy).append(createdDate).append(firstName).append(id).append(isActive).append(lastModifiedBy).append(lastModifiedDate).append(lastName).append(userName).toHashCode();
    }
    
}