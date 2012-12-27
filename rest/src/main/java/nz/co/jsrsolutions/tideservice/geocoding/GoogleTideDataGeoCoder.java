/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)GoogleTideDataGeoCoder.java        
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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.util.UriBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GoogleTideDataGeoCoder implements TideDataGeoCoder {

  private static final transient Logger mLogger = Logger
      .getLogger(GoogleTideDataGeoCoder.class);

  private final HttpClient mHttpClient;

  private final UriBuilder mUriBuilder;

  private final String mScheme;

  private final String mHost;

  private final String mPath;

  public GoogleTideDataGeoCoder(String scheme, String host, String path,
      long timeout) {
    mScheme = scheme;
    mHost = host;
    mPath = path;
    mHttpClient = new DefaultHttpClient();
    mUriBuilder = new UriBuilder(mScheme, mHost, mPath);
  }

  @Override
  public GeoLocation getGeoLocation(Port port) throws TideDataGeoCoderException {

    URI uri;
    try {

      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append(port.getName());
      stringBuffer.append(", ");
      stringBuffer.append(port.getSubArea().getName());
      uri = mUriBuilder.buildGetGeoLocUri(stringBuffer.toString());
      HttpUriRequest request = new GoogleGeoCoderHttpGet(uri);
      HttpResponse response = mHttpClient.execute(request);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataGeoCoderException("HTTP GET returned: "
            + response.getStatusLine().getStatusCode());
      }

      // read result and parse into XML Document
      HttpEntity entity = response.getEntity();

      Document geocoderResultDocument = DocumentBuilderFactory.newInstance()
          .newDocumentBuilder().parse(entity.getContent());

      // prepare XPath
      XPath xpath = XPathFactory.newInstance().newXPath();

      // extract the result
      NodeList resultNodeList = null;

      // a) Examine the status
      resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/status",
          geocoderResultDocument, XPathConstants.NODESET);
      if (resultNodeList.getLength() != 1) {
        throw new TideDataGeoCoderException("Couldn't parse result document.");
      }
      GoogleGeoCoderResultStatus resultStatus = GoogleGeoCoderResultStatus
          .valueOf(resultNodeList.item(0).getTextContent());
      
      switch (resultStatus) {
      case OK:
        break;
      case ZERO_RESULTS:
        throw new TideDataGeoCoderException("No results found for: " + stringBuffer.toString());
      case OVER_QUERY_LIMIT:
        throw new TideDataGeoCoderException("Over query limit (was 2500 requests per day)");
      case REQUEST_DENIED:
        throw new TideDataGeoCoderException("Request denied (using sensor=true|false ?)");
      case INVALID_REQUEST:
        throw new TideDataGeoCoderException("Invalid request - badly formed query");
      }

      // c) extract the coordinates of the first result
      resultNodeList = (NodeList) xpath.evaluate(
          "/GeocodeResponse/result[1]/geometry/location/*",
          geocoderResultDocument, XPathConstants.NODESET);
      float lat = Float.NaN;
      float lng = Float.NaN;
      for (int i = 0; i < resultNodeList.getLength(); ++i) {
        Node node = resultNodeList.item(i);
        if ("lat".equals(node.getNodeName())) {
          lat = Float.parseFloat(node.getTextContent());
        }
        if ("lng".equals(node.getNodeName())) {
          lng = Float.parseFloat(node.getTextContent());
        }
      }

      GeoLocation geoLocation = new GeoLocation();
      geoLocation.setLatitude((long) (lat * 1e6));
      geoLocation.setLongitude((long) (lng * 1e6));

      return geoLocation;

    } catch (URISyntaxException e) {
      throw new TideDataGeoCoderException(e);
    } catch (ClientProtocolException e) {
      throw new TideDataGeoCoderException(e);
    } catch (IOException e) {
      throw new TideDataGeoCoderException(e);
    } catch (IllegalStateException e) {
      throw new TideDataGeoCoderException(e);
    } catch (SAXException e) {
      throw new TideDataGeoCoderException(e);
    } catch (ParserConfigurationException e) {
      throw new TideDataGeoCoderException(e);
    } catch (XPathExpressionException e) {
      throw new TideDataGeoCoderException(e);
    }

  }

}
