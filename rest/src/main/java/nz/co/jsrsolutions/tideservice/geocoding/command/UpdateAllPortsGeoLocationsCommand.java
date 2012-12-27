/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)UpdateAllPortsGeoLocationsCommand.java        
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

package nz.co.jsrsolutions.tideservice.geocoding.command;

import java.util.List;
import java.util.concurrent.ExecutorService;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.service.PortService;
import nz.co.jsrsolutions.tideservice.geocoding.TideDataGeoCoder;
import nz.co.jsrsolutions.tideservice.geocoding.TideDataGeoCoderException;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.log4j.Logger;

public class UpdateAllPortsGeoLocationsCommand implements Command {

  private static final transient Logger mLogger = Logger
      .getLogger(UpdateAllPortsGeoLocationsCommand.class);

  private final ExecutorService mExecutorService;

  private final TideDataGeoCoder mTideDataGeoCoder;

  private final PortService mPortService;

  private final long mMaxRequests;

  public UpdateAllPortsGeoLocationsCommand(ExecutorService executorService,
      PortService portService, TideDataGeoCoder tideDataGeoCoder,
      long maxRequests) {
    mExecutorService = executorService;
    mPortService = portService;
    mTideDataGeoCoder = tideDataGeoCoder;
    mMaxRequests = maxRequests;
  }

  public boolean execute(Context context) throws Exception {

    mLogger.info("Executing: updateallportsgeolocs");

    List<Port> ports = mPortService.findAllPorts();

    long numRequests = 0;

    for (Port port : ports) {

      if (!port.isIsGeoCoded()) {

        ++numRequests;
        try {

          GeoLocation geoLoc = mTideDataGeoCoder.getGeoLocation(port);

          StringBuffer message = new StringBuffer();
          message.append("Geocoded port [ ");
          message.append(port.getExternalId());
          message.append(" ] as [ ");
          message.append(geoLoc);
          message.append(" ]");
          mLogger.info(message.toString());

          port.setGeoLocation(geoLoc);
          port.setIsGeoCoded(true);
          mPortService.updatePort(port);
          
        } catch (TideDataGeoCoderException e) {
          mLogger.error("Unable to geocode port " + port.getExternalId(), e);
          //throw new TideDataGeoCoderException(e);
        }

      }
      
      if (numRequests >= mMaxRequests) {
        break;
      }
    }

    return false;

  }
}
