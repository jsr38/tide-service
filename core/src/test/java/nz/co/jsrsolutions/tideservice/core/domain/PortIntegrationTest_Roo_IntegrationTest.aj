// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.PortDataOnDemand;
import nz.co.jsrsolutions.tideservice.core.domain.PortIntegrationTest;
import nz.co.jsrsolutions.tideservice.core.repository.PortRepository;
import nz.co.jsrsolutions.tideservice.core.service.PortService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PortIntegrationTest_Roo_IntegrationTest {
    
    declare @type: PortIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: PortIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: PortIntegrationTest: @Transactional;
    
    @Autowired
    private PortDataOnDemand PortIntegrationTest.dod;
    
    @Autowired
    PortService PortIntegrationTest.portService;
    
    @Autowired
    PortRepository PortIntegrationTest.portRepository;
    
    @Test
    public void PortIntegrationTest.testCountAllPorts() {
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", dod.getRandomPort());
        long count = portService.countAllPorts();
        Assert.assertTrue("Counter for 'Port' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void PortIntegrationTest.testFindPort() {
        Port obj = dod.getRandomPort();
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Port' failed to provide an identifier", id);
        obj = portService.findPort(id);
        Assert.assertNotNull("Find method for 'Port' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Port' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void PortIntegrationTest.testFindAllPorts() {
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", dod.getRandomPort());
        long count = portService.countAllPorts();
        Assert.assertTrue("Too expensive to perform a find all test for 'Port', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Port> result = portService.findAllPorts();
        Assert.assertNotNull("Find all method for 'Port' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Port' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void PortIntegrationTest.testFindPortEntries() {
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", dod.getRandomPort());
        long count = portService.countAllPorts();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Port> result = portService.findPortEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Port' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Port' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void PortIntegrationTest.testFlush() {
        Port obj = dod.getRandomPort();
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Port' failed to provide an identifier", id);
        obj = portService.findPort(id);
        Assert.assertNotNull("Find method for 'Port' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPort(obj);
        Integer currentVersion = obj.getVersion();
        portRepository.flush();
        Assert.assertTrue("Version for 'Port' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void PortIntegrationTest.testUpdatePortUpdate() {
        Port obj = dod.getRandomPort();
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Port' failed to provide an identifier", id);
        obj = portService.findPort(id);
        boolean modified =  dod.modifyPort(obj);
        Integer currentVersion = obj.getVersion();
        Port merged = (Port)portService.updatePort(obj);
        portRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Port' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void PortIntegrationTest.testSavePort() {
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", dod.getRandomPort());
        Port obj = dod.getNewTransientPort(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Port' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Port' identifier to be null", obj.getId());
        portService.savePort(obj);
        portRepository.flush();
        Assert.assertNotNull("Expected 'Port' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void PortIntegrationTest.testDeletePort() {
        Port obj = dod.getRandomPort();
        Assert.assertNotNull("Data on demand for 'Port' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Port' failed to provide an identifier", id);
        obj = portService.findPort(id);
        portService.deletePort(obj);
        portRepository.flush();
        Assert.assertNull("Failed to remove 'Port' with identifier '" + id + "'", portService.findPort(id));
    }
    
}
