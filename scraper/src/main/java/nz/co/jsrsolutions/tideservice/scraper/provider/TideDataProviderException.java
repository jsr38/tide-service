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

package nz.co.jsrsolutions.tideservice.scraper.provider;


import java.lang.String;

public class TideDataProviderException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private String mistake;

  public TideDataProviderException() {
    super();
    mistake = "unknown";
  }
  
  public TideDataProviderException(String err) {
    super(err);
    mistake = err;
  }
  
  public TideDataProviderException(Throwable cause) {
    super(cause);
  }  
  
  public String getError() {
    return mistake;
  }
}
  