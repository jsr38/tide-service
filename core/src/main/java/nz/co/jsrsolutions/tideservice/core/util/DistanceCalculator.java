/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)DistanceCalculator.java        
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

package nz.co.jsrsolutions.tideservice.core.util;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;

public interface DistanceCalculator {

  public abstract double calculate(GeoLocation geoLocation1, GeoLocation geoLocation2);
  
}
