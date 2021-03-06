/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)UpdateAllPortsCommand.java        
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
import java.util.Set;
import java.util.concurrent.ExecutorService;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.service.AreaService;
import nz.co.jsrsolutions.tideservice.core.service.PortService;
import nz.co.jsrsolutions.tideservice.core.service.SubAreaService;
import nz.co.jsrsolutions.tideservice.scraper.provider.TideDataProvider;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;

public class UpdateAllPortsCommand implements Command {

  private static final transient Logger mLogger = Logger
      .getLogger(UpdateAllPortsCommand.class);

  private final ExecutorService mExecutorService;

  private final TideDataProvider mTideDataProvider;

  private final AreaService mAreaService;

  private final SubAreaService mSubAreaService;

  private final PortService mPortService;

  public UpdateAllPortsCommand(ExecutorService executorService,
      TideDataProvider tideDataProvider, AreaService areaService,
      SubAreaService subAreaService, PortService portService) {
    mExecutorService = executorService;
    mTideDataProvider = tideDataProvider;
    mAreaService = areaService;
    mSubAreaService = subAreaService;
    mPortService = portService;
  }

  public boolean execute(Context context) throws Exception {

    mLogger.info("Executing: updateallports");

    List<Area> areas = mAreaService.findAllAreas();

    for (Area area : areas) {
      
      List<SubArea> subAreas = mSubAreaService.findSubAreas(area);

      for (SubArea subArea : subAreas) {

        StringBuffer message = new StringBuffer();
        message.append("Querying provider for area [ ");
        message.append(area.getName());
        message.append(" ] subarea [ ");
        message.append(subArea.getName());
        message.append(" ]");
        mLogger.info(message.toString());

        List<Port> ports = mTideDataProvider.getPorts(subArea);

        Set<Port> updatedPorts = mPortService.updatePorts(subArea.getId(), ports);
      }

    }

    return false;

  }
}
