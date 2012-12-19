/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)TideDataProvider.java        
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

import java.util.List;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;



/** 
 * 
 * Interface implemented by providers of tide data
 * 
 */ 
public interface TideDataProvider {

  /** 
   * 
   * Gets the available port areas
   * 
   * 
   * @param       
   * @return      
   * @exception   TideDataProviderException
   * 
   */
  public List<Area> getAreas() throws TideDataProviderException;

  /** 
   * 
   * Gets the available port sub areas for a given area
   * 
   * 
   * @param       
   * @return      
   * @exception   TideDataProviderException
   * 
   */
  public List<SubArea> getSubAreas(Area area) throws TideDataProviderException;
  
  /** 
   * 
   * Gets the available ports for a given area and subarea
   * 
   * 
   * @param       
   * @return      
   * @exception   TideDataProviderException
   * 
   */
  public List<Port> getPorts(SubArea subArea) throws TideDataProviderException;

  /** 
   * 
   * Gets the next available predictions for a given port
   * 
   * 
   * @param       
   * @return      
   * @exception   TideDataProviderException
   * 
   */
  public List<TidePredictionDay> getTidePredictionDay(Port port) throws TideDataProviderException;
  
}