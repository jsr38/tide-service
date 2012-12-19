package nz.co.jsrsolutions.tideservice.core.service;

import java.util.List;
import java.util.Set;

import nz.co.jsrsolutions.tideservice.core.domain.Port;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { nz.co.jsrsolutions.tideservice.core.domain.Port.class })
public interface PortService {
  
  /**
   * 
   * Updates any ports that appear to be different to 
   * those associated with the supplied Area objects.  
   * Note that the criteria for comparison are
   * fewer than for the implementation of the port object's
   * compareTo() method.  Each port for comparison is fetched and
   * only the reduced comparison fields are updated.  If an
   * port is not present, then it is inserted.  If an port is
   * present in the persistence layer but not in the supplied
   * set then it is removed from the persistence layer.
   * 
   * @param ports
   * @return
   */
  public abstract Set<Port> updatePorts(long subAreaId, List<Port> ports);
  
}
