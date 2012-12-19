/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)UpdateAllSubAreasCommand.java        
 *
 * Copyright (c) 2012 JSR Solutions Limited
 * 4 Viridian Lane, Auckland, 0632.  New Zealand
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of JSR
 * Solutions Limited. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with JSR Solutions Limited.
 */

package nz.co.jsrsolutions.tideservice.scraper.command;

import java.util.List;
import java.util.concurrent.ExecutorService;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.service.AreaService;
import nz.co.jsrsolutions.tideservice.scraper.provider.TideDataProvider;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;

public class UpdateAllSubAreasCommand implements Command {

  private static final transient Logger mLogger = Logger
      .getLogger(UpdateAllSubAreasCommand.class);

  private final ExecutorService mExecutorService;

  private final TideDataProvider mTideDataProvider;

  private final AreaService mAreaService;

  public UpdateAllSubAreasCommand(ExecutorService executorService,
      TideDataProvider tideDataProvider, AreaService areaService) {
    mExecutorService = executorService;
    mTideDataProvider = tideDataProvider;
    mAreaService = areaService;
  }

  public boolean execute(Context context) throws Exception {

    mLogger.info("Executing: updateallsubareas");

    List<Area> serviceAreas = mAreaService.findAllAreas();

    for (Area area : serviceAreas) {

      StringBuffer message = new StringBuffer();
      message.append("Querying provider for subAreas in area: [ ");
      message.append(area.getExternalId());
      message.append(" ] [ ");
      message.append(area.getName());
      message.append(" ]");
      mLogger.info(message.toString());
      
      List<SubArea> providerSubAreas = mTideDataProvider.getSubAreas(area);
      
      for (SubArea subArea : providerSubAreas) {
        
        message = new StringBuffer();
        message.append("Provider has subArea: [ ");
        message.append(subArea);
        message.append(" ]");
  
        mLogger.info(message.toString());     
        
      }
      
      mAreaService.updateSubAreas(area.getId(), providerSubAreas);

    }
    
    
    return false;

  }
}
