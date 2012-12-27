/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)TideDataProviderException.java        
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

package nz.co.jsrsolutions.tideservice.geocoding;


import java.lang.String;

public class TideDataGeoCoderException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private String mistake;

  public TideDataGeoCoderException() {
    super();
    mistake = "unknown";
  }
  
  public TideDataGeoCoderException(String err) {
    super(err);
    mistake = err;
  }
  
  public TideDataGeoCoderException(Throwable cause) {
    super(cause);
  }  
  
  public String getError() {
    return mistake;
  }
}
  