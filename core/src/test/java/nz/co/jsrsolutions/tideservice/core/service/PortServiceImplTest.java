/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)PortServiceImplTest.java        
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

package nz.co.jsrsolutions.tideservice.core.service;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.repository.PortRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author jsr
 *
 */
public class PortServiceImplTest {

  private List<Port> mPortList;
  
  @Mock
  private PortRepository mPortRepository;
  
  @InjectMocks
  private PortService mPortService;
  
  private GeoLocation mSomewhereInTheBahamasGeoLoc;
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    
    mPortService = new PortServiceImpl();
    
    // lat=24770525&lng=-76238646
    mSomewhereInTheBahamasGeoLoc = new GeoLocation();
    mSomewhereInTheBahamasGeoLoc.setLatitude(24770525L);
    mSomewhereInTheBahamasGeoLoc.setLongitude(-76238646L);
    
    mPortList = new ArrayList<Port>();
    
    // Belize City 
    GeoLocation geoLoc1 = new GeoLocation();
    geoLoc1.setLatitude(17497713L);
    geoLoc1.setLongitude(-88186653L);
    Port port1 = mock(Port.class);
    when(port1.getGeoLocation()).thenReturn(geoLoc1);
    when(port1.isGeoCoded()).thenReturn(true);
    when(port1.getId()).thenReturn(1L);
    
    // St. David's Island
    GeoLocation geoLoc2 = new GeoLocation();
    geoLoc2.setLatitude(32364326L);
    geoLoc2.setLongitude(-64671791L);
    Port port2 = mock(Port.class);
    when(port2.getGeoLocation()).thenReturn(geoLoc2);
    when(port2.isGeoCoded()).thenReturn(true);
    when(port2.getId()).thenReturn(2L);
    
    // Barrington Passage, Canada
    GeoLocation geoLoc3 = new GeoLocation();
    geoLoc3.setLatitude(43527233L);
    geoLoc3.setLongitude(-65609275L);
    Port port3 = mock(Port.class);
    when(port3.getGeoLocation()).thenReturn(geoLoc3);
    when(port3.isGeoCoded()).thenReturn(true);
    when(port3.getId()).thenReturn(3L);
    
    // Pelican Harbour, Bahamas
    GeoLocation geoLoc4 = new GeoLocation();
    geoLoc4.setLatitude(25277669L);
    geoLoc4.setLongitude(-76338760L);
    Port port4 = mock(Port.class);
    when(port4.getGeoLocation()).thenReturn(geoLoc4);
    when(port4.isGeoCoded()).thenReturn(true);
    when(port4.getId()).thenReturn(4L);
    
    // Eleuthera Island, Bahamas
    GeoLocation geoLoc5 = new GeoLocation();
    geoLoc5.setLatitude(25136203L);
    geoLoc5.setLongitude(-76143592L);
    Port port5 = mock(Port.class);
    when(port5.getGeoLocation()).thenReturn(geoLoc5);
    when(port5.isGeoCoded()).thenReturn(true);
    when(port5.getId()).thenReturn(5L);
    
    mPortList.add(port1);
    mPortList.add(port2);
    mPortList.add(port3);
    mPortList.add(port4);
    mPortList.add(port5); 
    
    MockitoAnnotations.initMocks(this);
    
    when(mPortRepository.findAll()).thenReturn(mPortList);

    
      
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for {@link nz.co.jsrsolutions.tideservice.core.service.PortServiceImpl#PortServiceImpl()}.
   */
  @Test
  public void testPortServiceImpl() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link nz.co.jsrsolutions.tideservice.core.service.PortServiceImpl#updatePorts(long, java.util.List)}.
   */
  @Test
  public void testUpdatePorts() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link nz.co.jsrsolutions.tideservice.core.service.PortServiceImpl#findNearestNeighbours(nz.co.jsrsolutions.tideservice.core.domain.GeoLocation, int)}.
   */
  @Test
  public void testFindNearestNeighbours() {
    
    List<Port> nearestPorts = mPortService.findNearestNeighbours(mSomewhereInTheBahamasGeoLoc, 3);
    
    assertNotNull(nearestPorts);
    assertEquals(3, nearestPorts.size());
    assertEquals(5L, (long) ((nearestPorts.get(0)).getId()));
  }

}
