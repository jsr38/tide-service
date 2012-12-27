/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)UpdateAllTidePredictionsCommand.java        
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;
import nz.co.jsrsolutions.tideservice.core.service.PortService;
import nz.co.jsrsolutions.tideservice.core.service.TidePredictionDayService;
import nz.co.jsrsolutions.tideservice.scraper.provider.TideDataProvider;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;

public class UpdateAllTidePredictionsCommand implements Command {

  private static final transient Logger mLogger = Logger
      .getLogger(UpdateAllTidePredictionsCommand.class);

  private final ExecutorService mExecutorService;

  private final TideDataProvider mTideDataProvider;

  private final PortService mPortService;
  
  private final TidePredictionDayService mTidePredictionDayService;

  public UpdateAllTidePredictionsCommand(ExecutorService executorService,
      TideDataProvider tideDataProvider, PortService portService, TidePredictionDayService tidePredictionDayService) {
    mExecutorService = executorService;
    mTideDataProvider = tideDataProvider;
    mPortService = portService;
    mTidePredictionDayService = tidePredictionDayService;
  }

  public boolean execute(Context context) throws Exception {

    mLogger.info("Executing: updatealltidepredictions");

    List<Port> ports = mPortService.findAllPorts();
//    List<Port> ports = new ArrayList<Port>(1);
//    ports.add(mPortService.findPort(4268L));
    for (Port port : ports) {

      StringBuffer message = new StringBuffer();
      message.append("Querying provider for port [ ");
      message.append(port.getExternalId());
      message.append(" ]  [ ");
      message.append(port.getName());
      message.append(" ]");
      mLogger.info(message.toString());

      List<TidePredictionDay> tidePredictionDays = mTideDataProvider.getTidePredictionDay(port);

      Set<TidePredictionDay> tidePredictionDaySet = mTidePredictionDayService.updateTidePredictionDays(port.getId(), tidePredictionDays);

    }

    return false;

  }
}
