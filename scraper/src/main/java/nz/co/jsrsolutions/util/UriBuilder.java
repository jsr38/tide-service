/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)UriBuilder.java        
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

package nz.co.jsrsolutions.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;


public class UriBuilder {

  private URIBuilder mBuilder;
  
  private String mPath;
  
  public UriBuilder(String scheme, String host, String path) {
    mBuilder = new URIBuilder();
    mBuilder.setScheme(scheme);
    mBuilder.setHost(host);
    mPath = path;
  }
  
  public URI build(String urlSuffix) throws URISyntaxException {
    
    mBuilder.setPath(mPath + "/" + urlSuffix);
    
    return mBuilder.build();
  }

}