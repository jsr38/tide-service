/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)EasyTideAreaButtonClickHttpPost.java        
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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class EasyTideAreaButtonClickHttpPost extends AbstractEasyTideHttpPost {

  public EasyTideAreaButtonClickHttpPost(URI uri, String viewState, String eventValidation) {
    
    super(uri, viewState);
    
    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("__VIEWSTATE", viewState));
    nvps.add(new BasicNameValuePair("__EVENTVALIDATION", eventValidation));
    //nvps.add(new BasicNameValuePair("__EVENTTARGET", ""));
    //nvps.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
    nvps.add(new BasicNameValuePair("imgSelectAreas.x", "38"));
    nvps.add(new BasicNameValuePair("imgSelectAreas.y", "19"));

    setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
    
  }
  
}
