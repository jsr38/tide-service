// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.Client;
import nz.co.jsrsolutions.tideservice.core.service.ClientService;

privileged aspect ClientService_Roo_Service {
    
    public abstract long ClientService.countAllClients();    
    public abstract void ClientService.deleteClient(Client client);    
    public abstract Client ClientService.findClient(Long id);    
    public abstract List<Client> ClientService.findAllClients();    
    public abstract List<Client> ClientService.findClientEntries(int firstResult, int maxResults);    
    public abstract void ClientService.saveClient(Client client);    
    public abstract Client ClientService.updateClient(Client client);    
}