// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.service.PortService;

privileged aspect PortService_Roo_Service {
    
    public abstract long PortService.countAllPorts();    
    public abstract void PortService.deletePort(Port port);    
    public abstract Port PortService.findPort(Long id);    
    public abstract List<Port> PortService.findAllPorts();    
    public abstract List<Port> PortService.findPortEntries(int firstResult, int maxResults);    
    public abstract void PortService.savePort(Port port);    
    public abstract Port PortService.updatePort(Port port);    
}
