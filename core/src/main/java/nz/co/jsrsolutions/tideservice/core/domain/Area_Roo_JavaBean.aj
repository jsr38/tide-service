// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import java.util.Set;
import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;

privileged aspect Area_Roo_JavaBean {
    
    public String Area.getExternalId() {
        return this.externalId;
    }
    
    public void Area.setExternalId(String externalId) {
        this.externalId = externalId;
    }
    
    public String Area.getName() {
        return this.name;
    }
    
    public void Area.setName(String name) {
        this.name = name;
    }
    
    public String Area.getDescription() {
        return this.description;
    }
    
    public void Area.setDescription(String description) {
        this.description = description;
    }
    
    public Set<SubArea> Area.getSubArea() {
        return this.subArea;
    }
    
    public void Area.setSubArea(Set<SubArea> subArea) {
        this.subArea = subArea;
    }
    
}
