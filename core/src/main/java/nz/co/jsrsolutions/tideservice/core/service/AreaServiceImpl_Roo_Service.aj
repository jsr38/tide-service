// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.repository.AreaRepository;
import nz.co.jsrsolutions.tideservice.core.service.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AreaServiceImpl_Roo_Service {
    
    declare @type: AreaServiceImpl: @Service;
    
    declare @type: AreaServiceImpl: @Transactional;
    
    @Autowired
    AreaRepository AreaServiceImpl.areaRepository;
    
    public long AreaServiceImpl.countAllAreas() {
        return areaRepository.count();
    }
    
    public void AreaServiceImpl.deleteArea(Area area) {
        areaRepository.delete(area);
    }
    
    public Area AreaServiceImpl.findArea(Long id) {
        return areaRepository.findOne(id);
    }
    
    public List<Area> AreaServiceImpl.findAllAreas() {
        return areaRepository.findAll();
    }
    
    public List<Area> AreaServiceImpl.findAreaEntries(int firstResult, int maxResults) {
        return areaRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void AreaServiceImpl.saveArea(Area area) {
        areaRepository.save(area);
    }
    
    public Area AreaServiceImpl.updateArea(Area area) {
        return areaRepository.save(area);
    }
    
}