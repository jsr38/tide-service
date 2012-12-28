/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)HaversineDistanceCalculatorTest.java        
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

import static org.junit.Assert.assertEquals;
import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jsr
 *
 */
public class HaversineDistanceCalculatorTest {

  DistanceCalculator mDistanceCalculator = new HaversineDistanceCalculator();
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for
   * {@link nz.co.jsrsolutions.tideservice.core.util.HaversineDistanceCalculator#calculate(nz.co.jsrsolutions.tideservice.core.domain.GeoLocation, nz.co.jsrsolutions.tideservice.core.domain.GeoLocation)}
   * .
   * 
   * Copied from:  http://rosettacode.org/wiki/Haversine_formula
   * 
   * Test data for distance between:
   * 
   * Nashville International Airport (BNA) in Nashville, TN, USA: N 36°7.2', W
   * 86°40.2' (36.12, -86.67) Los Angeles International Airport (LAX) in Los
   * Angeles, CA, USA: N 33°56.4', W 118°24.0' (33.94, -118.40)
   * 
   * -- 6371.0 km is the authalic radius based on/extracted from surface area;
   * -- 6372.8 km is an approximation of the radius of the average circumference
   * (i.e., the average great-elliptic or great-circle radius), where the
   * boundaries are the meridian (6367.45 km) and the equator (6378.14 km).
   * 
   * Using either of these values results, of course, in differing distances:
   * 
   * 6371.0 km -> 2886.44444283798329974715782394574671655 km; 
   * 6372.8 km -> 2887.25995060711033944886005029688505340 km; 
   * (results extended for accuracy check: Given that the radii are only 
   * approximations anyways, .01' ≈
   * 1.0621333 km and .001" ≈ .00177 km, practical precision required is
   * certainly no greater than about .0000001——i.e., .1 mm!)
   * 
   * As distances are segments of great circles/circumferences, it is
   * recommended that the latter value (r = 6372.8 km) be used (which most of
   * the given solutions have already adopted, anyways).
   * 
   * The distance we assume to be correct is:  
   */
  @Test
  public void testCalculate() {
    
    final double lat1 = 36.12;
    final double lng1 = -86.67;
    
    final double lat2 = 33.94;
    final double lng2 = -118.40;
    
    final double distance = 2887.25995060711033944886005029688505340f;
    
    // interface for GeoLocation demands a long type for
    // latitudes and longitudes.
    // Scale by 1e6 and round.   
    final GeoLocation nashvilleAirport = new GeoLocation();
    nashvilleAirport.setLatitude((long)(lat1 * 1e6));
    nashvilleAirport.setLongitude((long)(lng1 * 1e6));
    final GeoLocation losAngelesAirport = new GeoLocation();
    losAngelesAirport.setLatitude((long)(lat2 * 1e6));
    losAngelesAirport.setLongitude((long)(lng2 * 1e6));
    
    final double calculatedDistance = mDistanceCalculator.calculate(nashvilleAirport, losAngelesAirport);
    
    assertEquals(distance, calculatedDistance, 1e-4);
  }

}
