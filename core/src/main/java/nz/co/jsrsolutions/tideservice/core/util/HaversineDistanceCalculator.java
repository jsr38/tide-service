/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)HaversineDistanceCalculator.java        
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

public class HaversineDistanceCalculator implements DistanceCalculator {

  private static final double EARTH_MAJOR_AXIS_RADIUS = 6378137.0; // (m)

  private static final double EARTH_MINOR_AXIS_RADIUS = 6356752.314245; // (m)

  private static final double EARTH_GEOMETRIC_MEAN_RADIUS;
  
  private static final double PI_OVER_180;

  static {
    EARTH_GEOMETRIC_MEAN_RADIUS = Math.sqrt(Math.pow(EARTH_MAJOR_AXIS_RADIUS,
        2.0f) + Math.pow(EARTH_MINOR_AXIS_RADIUS, 2.0f));
    PI_OVER_180 = Math.PI / 180.0f;
  }

  /**
   * @brief Computes the arc, in radian, between two WGS-84 positions.
   * 
   *        The result is equal to
   *        <code>Distance(from,to)/EARTH_RADIUS_IN_METERS</code>
   *        <code>= 2*asin(sqrt(h(d/EARTH_RADIUS_IN_METERS )))</code>
   * 
   *        where:
   *        <ul>
   *        <li>d is the distance in meters between 'from' and 'to' positions.</li>
   *        <li>h is the haversine function: <code>h(x)=sin²(x/2)</code></li>
   *        </ul>
   * 
   *        The haversine formula gives:
   *        <code>h(d/R) = h(from.lat-to.lat)+h(from.lon-to.lon)+cos(from.lat)*cos(to.lat)</code>
   * 
   * @sa http://en.wikipedia.org/wiki/Law_of_haversines
   */
  @Override
  public double calculate(GeoLocation geoLocation1, GeoLocation geoLocation2) {

    final double lat1Radians = geoLocation1.getLatitude() * PI_OVER_180;
    final double lat2Radians = geoLocation2.getLatitude() * PI_OVER_180;
    final double lng1Radians = geoLocation1.getLongitude() * PI_OVER_180;
    final double lng2Radians = geoLocation2.getLongitude() * PI_OVER_180;
    
    final double haversinLatDiff = Math.pow(Math.sin((lat2Radians - lat1Radians)/2.0f), 2.0f);
    final double haversinLngDiff = Math.pow(Math.sin((lng2Radians - lng1Radians)/2.0f), 2.0f);
    
    final double sqrtH = Math.sqrt(haversinLatDiff + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversinLngDiff);
    
    final double d = 2.0f * EARTH_GEOMETRIC_MEAN_RADIUS * Math.asin(sqrtH);
    
    return d;
  }

}
