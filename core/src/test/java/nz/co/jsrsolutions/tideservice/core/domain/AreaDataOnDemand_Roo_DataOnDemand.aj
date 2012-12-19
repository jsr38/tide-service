// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import nz.co.jsrsolutions.tideservice.core.domain.AbstractUser;
import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.AreaDataOnDemand;
import nz.co.jsrsolutions.tideservice.core.repository.AreaRepository;
import nz.co.jsrsolutions.tideservice.core.service.AreaService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect AreaDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AreaDataOnDemand: @Component;
    
    private Random AreaDataOnDemand.rnd = new SecureRandom();
    
    private List<Area> AreaDataOnDemand.data;
    
    @Autowired
    AreaService AreaDataOnDemand.areaService;
    
    @Autowired
    AreaRepository AreaDataOnDemand.areaRepository;
    
    public Area AreaDataOnDemand.getNewTransientArea(int index) {
        Area obj = new Area();
        setCreatedBy(obj, index);
        setCreatedDate(obj, index);
        setDescription(obj, index);
        setExternalId(obj, index);
        setIsActive(obj, index);
        setLastModifiedBy(obj, index);
        setLastModifiedDate(obj, index);
        setName(obj, index);
        return obj;
    }
    
    public void AreaDataOnDemand.setCreatedBy(Area obj, int index) {
        AbstractUser createdBy = null;
        obj.setCreatedBy(createdBy);
    }
    
    public void AreaDataOnDemand.setCreatedDate(Area obj, int index) {
        DateTime createdDate = null;
        obj.setCreatedDate(createdDate);
    }
    
    public void AreaDataOnDemand.setDescription(Area obj, int index) {
        String description = "description_" + index;
        if (description.length() > 255) {
            description = description.substring(0, 255);
        }
        obj.setDescription(description);
    }
    
    public void AreaDataOnDemand.setExternalId(Area obj, int index) {
        String externalId = "externalId_" + index;
        if (externalId.length() > 60) {
            externalId = new Random().nextInt(10) + externalId.substring(1, 60);
        }
        obj.setExternalId(externalId);
    }
    
    public void AreaDataOnDemand.setIsActive(Area obj, int index) {
        Boolean isActive = Boolean.TRUE;
        obj.setIsActive(isActive);
    }
    
    public void AreaDataOnDemand.setLastModifiedBy(Area obj, int index) {
        AbstractUser lastModifiedBy = null;
        obj.setLastModifiedBy(lastModifiedBy);
    }
    
    public void AreaDataOnDemand.setLastModifiedDate(Area obj, int index) {
        DateTime lastModifiedDate = null;
        obj.setLastModifiedDate(lastModifiedDate);
    }
    
    public void AreaDataOnDemand.setName(Area obj, int index) {
        String name = "name_" + index;
        if (name.length() > 60) {
            name = name.substring(0, 60);
        }
        obj.setName(name);
    }
    
    public Area AreaDataOnDemand.getSpecificArea(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Area obj = data.get(index);
        Long id = obj.getId();
        return areaService.findArea(id);
    }
    
    public Area AreaDataOnDemand.getRandomArea() {
        init();
        Area obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return areaService.findArea(id);
    }
    
    public boolean AreaDataOnDemand.modifyArea(Area obj) {
        return false;
    }
    
    public void AreaDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = areaService.findAreaEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Area' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Area>();
        for (int i = 0; i < 10; i++) {
            Area obj = getNewTransientArea(i);
            try {
                areaService.saveArea(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            areaRepository.flush();
            data.add(obj);
        }
    }
    
}
