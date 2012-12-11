// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.repository.PortRepository;
import nz.co.jsrsolutions.tideservice.core.service.PortServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PortServiceImpl_Roo_Service {
    
    declare @type: PortServiceImpl: @Service;
    
    declare @type: PortServiceImpl: @Transactional;
    
    @Autowired
    PortRepository PortServiceImpl.portRepository;
    
    public long PortServiceImpl.countAllPorts() {
        return portRepository.count();
    }
    
    public void PortServiceImpl.deletePort(Port port) {
        portRepository.delete(port);
    }
    
    public Port PortServiceImpl.findPort(Long id) {
        return portRepository.findOne(id);
    }
    
    public List<Port> PortServiceImpl.findAllPorts() {
        return portRepository.findAll();
    }
    
    public List<Port> PortServiceImpl.findPortEntries(int firstResult, int maxResults) {
        return portRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void PortServiceImpl.savePort(Port port) {
        portRepository.save(port);
    }
    
    public Port PortServiceImpl.updatePort(Port port) {
        return portRepository.save(port);
    }
    
}