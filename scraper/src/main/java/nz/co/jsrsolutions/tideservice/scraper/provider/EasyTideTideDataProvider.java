/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)EasyTideTideDataProvider.java        
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

package nz.co.jsrsolutions.tideservice.scraper.provider;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;

public class EasyTideTideDataProvider implements TideDataProvider {

  @Override
  public Area[] getAreas() throws TideDataProviderException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SubArea[] getSubAreas(Area area) throws TideDataProviderException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Port[] getPorts(Area area, SubArea subArea)
      throws TideDataProviderException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TidePredictionDay[] getTidePredictionDay(Port port)
      throws TideDataProviderException {
    // TODO Auto-generated method stub
    return null;
  }

}
