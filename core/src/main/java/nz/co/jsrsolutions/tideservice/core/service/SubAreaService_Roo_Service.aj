// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.service.SubAreaService;

privileged aspect SubAreaService_Roo_Service {
    
    public abstract long SubAreaService.countAllSubAreas();    
    public abstract void SubAreaService.deleteSubArea(SubArea subArea);    
    public abstract SubArea SubAreaService.findSubArea(Long id);    
    public abstract List<SubArea> SubAreaService.findAllSubAreas();    
    public abstract List<SubArea> SubAreaService.findSubAreaEntries(int firstResult, int maxResults);    
    public abstract void SubAreaService.saveSubArea(SubArea subArea);    
    public abstract SubArea SubAreaService.updateSubArea(SubArea subArea);    
}