// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import nz.co.jsrsolutions.tideservice.core.domain.AbstractUser;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect AbstractUser_Roo_Equals {
    
    public boolean AbstractUser.equals(Object obj) {
        if (!(obj instanceof AbstractUser)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AbstractUser rhs = (AbstractUser) obj;
        return new EqualsBuilder().append(createdBy, rhs.createdBy).append(createdDate, rhs.createdDate).append(firstName, rhs.firstName).append(id, rhs.id).append(isActive, rhs.isActive).append(lastModifiedBy, rhs.lastModifiedBy).append(lastModifiedDate, rhs.lastModifiedDate).append(lastName, rhs.lastName).append(userName, rhs.userName).isEquals();
    }
    
    public int AbstractUser.hashCode() {
        return new HashCodeBuilder().append(createdBy).append(createdDate).append(firstName).append(id).append(isActive).append(lastModifiedBy).append(lastModifiedDate).append(lastName).append(userName).toHashCode();
    }
    
}