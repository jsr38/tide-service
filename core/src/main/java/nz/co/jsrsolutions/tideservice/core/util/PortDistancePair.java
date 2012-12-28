/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)PortDistancePair.java        
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

import nz.co.jsrsolutions.tideservice.core.domain.Port;

public class PortDistancePair implements Comparable<PortDistancePair> {

  private final double mDistance;
  
  private final Port mPort;
  
  public PortDistancePair(Port port, double distance) {
    mPort = port;
    mDistance = distance;
  }
  
  @Override
  public int compareTo(PortDistancePair arg0) {
    int result = Double.compare(mDistance, arg0.mDistance);
    if (result != 0) {
      return result;
    }
    // unlikely, but to ensure consistency with equals() (ish), 
    // TODO:  Do this properly
    return mPort.getId().compareTo(arg0.getPort().getId());
  }

  public double getDistance() {
    return mDistance;
  }

  public Port getPort() {
    return mPort;
  }

  
  
}
