// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.service.AreaService;

privileged aspect AreaService_Roo_Service {
    
    public abstract long AreaService.countAllSubAreas();    
    public abstract void AreaService.deleteSubArea(SubArea subArea);    
    public abstract SubArea AreaService.findSubArea(Long id);    
    public abstract List<SubArea> AreaService.findAllSubAreas();    
    public abstract List<SubArea> AreaService.findSubAreaEntries(int firstResult, int maxResults);    
    public abstract void AreaService.saveSubArea(SubArea subArea);    
    public abstract SubArea AreaService.updateSubArea(SubArea subArea);    
    public abstract long AreaService.countAllAreas();    
    public abstract void AreaService.deleteArea(Area area);    
    public abstract Area AreaService.findArea(Long id);    
    public abstract List<Area> AreaService.findAllAreas();    
    public abstract List<Area> AreaService.findAreaEntries(int firstResult, int maxResults);    
    public abstract void AreaService.saveArea(Area area);    
    public abstract Area AreaService.updateArea(Area area);    
}
