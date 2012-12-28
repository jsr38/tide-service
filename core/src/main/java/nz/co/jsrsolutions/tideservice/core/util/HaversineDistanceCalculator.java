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

  @SuppressWarnings("unused")
  private static final double EARTH_MAJOR_AXIS_RADIUS = 6378137.0; // (m)

  @SuppressWarnings("unused")
  private static final double EARTH_MINOR_AXIS_RADIUS = 6356752.314245; // (m)

  private static final double EARTH_AVERAGE_RADIUS = 6372800; // (m)

  private static final double PI_OVER_180;

  static {
    PI_OVER_180 = Math.PI / 180.0f;
  }

  /**
   * @brief Computes the arc, in radian, between two WGS-84 positions.
   * 
   *        -- 6372.8 km is an approximation of the radius of the average
   *        circumference (i.e., the average great-elliptic or great-circle
   *        radius), where the boundaries are the meridian (6367.45 km) and the
   *        equator (6378.14 km).
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

    final double lat1Radians = geoLocation1.getLatitude() * PI_OVER_180 / 1e6; // scaled
                                                                               // as
                                                                               // per
                                                                               // GeoLocation
                                                                               // storage
                                                                               // type
                                                                               // (long
                                                                               // *
                                                                               // 1e6)
    final double lat2Radians = geoLocation2.getLatitude() * PI_OVER_180 / 1e6;
    final double lng1Radians = geoLocation1.getLongitude() * PI_OVER_180 / 1e6;
    final double lng2Radians = geoLocation2.getLongitude() * PI_OVER_180 / 1e6;

    final double haversinLatDiff = Math.pow(
        Math.sin((lat2Radians - lat1Radians) / 2.0f), 2.0f);
    final double haversinLngDiff = Math.pow(
        Math.sin((lng2Radians - lng1Radians) / 2.0f), 2.0f);

    final double sqrtH = Math.sqrt(haversinLatDiff + Math.cos(lat1Radians)
        * Math.cos(lat2Radians) * haversinLngDiff);

    final double d = 2.0f * EARTH_AVERAGE_RADIUS * Math.asin(sqrtH);

    return d / 1e3; // Return the result in km
  }

}
