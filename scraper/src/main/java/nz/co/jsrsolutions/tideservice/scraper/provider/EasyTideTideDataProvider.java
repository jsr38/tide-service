/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)EasyTideTideDataProvider.java        
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import nz.co.jsrsolutions.tideservice.core.domain.Port;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import nz.co.jsrsolutions.tideservice.core.domain.TidePredictionDay;
import nz.co.jsrsolutions.util.UriBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class EasyTideTideDataProvider implements TideDataProvider {

  private static final transient Logger mLogger = Logger
      .getLogger(EasyTideTideDataProvider.class);

  private final HttpClient mHttpClient;
  private final UriBuilder mUriBuilder;
  private final String mScheme;
  private final String mHost;
  private final String mPath;
  private final String mGetAreasUrlSuffix;
  private final String mGetSubAreasUrlSuffix;
  private final String mGetPortsUrlSuffix;
  private static final String mPostbackRegexString = new String(
      "javascript:__doPostBack\\('(.*)',.*");
  private static final Pattern mPostbackRegexPattern = Pattern
      .compile(mPostbackRegexString);
  private static final String mPortIdRegexString = new String(
      ".*PortID=(.*)");
  private static final Pattern mPortIdRegexPattern = Pattern
      .compile(mPortIdRegexString);

  public EasyTideTideDataProvider(String scheme, String host, String path,
      String getAreasUrlSuffix, String getSubAreasUrlSuffix,
      String getPortsUrlSuffix, long timeout) {
    mScheme = scheme;
    mHost = host;
    mPath = path;
    mGetAreasUrlSuffix = getAreasUrlSuffix;
    mGetSubAreasUrlSuffix = getSubAreasUrlSuffix;
    mGetPortsUrlSuffix = getPortsUrlSuffix;
    mHttpClient = new DefaultHttpClient();
    mUriBuilder = new UriBuilder(mScheme, mHost, mPath);
  }

  @Override
  public List<Area> getAreas() throws TideDataProviderException {

    HttpPost postRequest = null;
    try {

      final EasyTidePageState pageState = getAreasPage();

      postRequest = new EasyTideAreaButtonClickHttpPost(
          mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
          pageState.getEventValidation());
      HttpResponse response = mHttpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataProviderException(
            "Couldn't simulate click on area button...");
      }

      Parser parser = getHtmlParser(response);

      NodeList list = new NodeList();
      NodeFilter filter = new AndFilter(new TagNameFilter("OPTION"),
          new HasParentFilter(new HasAttributeFilter("ID",
              "PortSelectionAreas_ddlAreas")));

      for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
        e.nextNode().collectInto(list, filter);

      }

      EntityUtils.consume(response.getEntity());

      Node[] nodes = list.toNodeArray();
      ArrayList<Area> areas = new ArrayList<Area>(list.size());
      for (int i = 0; i < list.size(); ++i) {
        mLogger.debug(nodes[i]);
        TagNode tagNode = (TagNode) nodes[i];
        String externalId = tagNode.getAttribute("VALUE");
        String name = tagNode.getChildren().elementAt(0).getText();
        Area area = new Area();
        area.setExternalId(externalId);
        area.setName(name);
        area.setDescription(name);
        areas.add(area);
      }

      return areas;

    } catch (URISyntaxException e) {
      throw new TideDataProviderException(e);
    } catch (ClientProtocolException e) {
      throw new TideDataProviderException(e);
    } catch (IOException e) {
      throw new TideDataProviderException(e);
    } catch (ParserException e) {
      throw new TideDataProviderException(e);
    } finally {

      if (postRequest != null) {
        postRequest.releaseConnection();
      }
    }

  }

  @Override
  public List<SubArea> getSubAreas(Area area) throws TideDataProviderException {

    HttpPost postRequest = null;
    try {

      EasyTidePageState pageState = getAreasPage();

      postRequest = new EasyTideAreaButtonClickHttpPost(
          mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
          pageState.getEventValidation());
      HttpResponse response = mHttpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataProviderException(
            "Couldn't simulate click on area button...");
      }
      pageState = getPageState(getHtmlParser(response));
      EntityUtils.consume(response.getEntity());
      if (postRequest != null) {
        postRequest.releaseConnection();
      }

      postRequest = new EasyTideAreaSelectClickHttpPost(
          mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
          pageState.getEventValidation(), area.getExternalId());
      response = mHttpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataProviderException(
            "Couldn't simulate click on area select...");
      }

      Parser parser = getHtmlParser(response);

      NodeList list = new NodeList();
      NodeFilter filter = new AndFilter(new TagNameFilter("OPTION"),
          new HasParentFilter(new HasAttributeFilter("ID",
              "PortSelectionAreas_ddlRegions")));

      for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
        e.nextNode().collectInto(list, filter);

      }

      EntityUtils.consume(response.getEntity());

      Node[] nodes = list.toNodeArray();
      ArrayList<SubArea> subAreas = new ArrayList<SubArea>(list.size());
      for (int i = 0; i < list.size(); ++i) {
        mLogger.debug(nodes[i]);
        TagNode tagNode = (TagNode) nodes[i];
        String externalId = tagNode.getAttribute("VALUE");
        String name = tagNode.getChildren().elementAt(0).getText();
        SubArea subArea = new SubArea();
        subArea.setExternalId(externalId);
        subArea.setName(name);
        subArea.setDescription(name);
        subAreas.add(subArea);
      }

      return subAreas;

    } catch (URISyntaxException e) {
      throw new TideDataProviderException(e);
    } catch (ClientProtocolException e) {
      throw new TideDataProviderException(e);
    } catch (IOException e) {
      throw new TideDataProviderException(e);
    } catch (ParserException e) {
      throw new TideDataProviderException(e);
    } finally {

      if (postRequest != null) {
        postRequest.releaseConnection();
      }
    }

  }

  @Override
  public List<Port> getPorts(SubArea subArea) throws TideDataProviderException {

    HttpPost postRequest = null;
    try {

      // Initial GET
      EasyTidePageState pageState = getAreasPage();

      // Select area tab POST
      postRequest = new EasyTideAreaButtonClickHttpPost(
          mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
          pageState.getEventValidation());
      HttpResponse response = mHttpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataProviderException(
            "Couldn't simulate click on area button...");
      }
      pageState = getPageState(getHtmlParser(response));
      EntityUtils.consume(response.getEntity());
      if (postRequest != null) {
        postRequest.releaseConnection();
      }

      // Select area POST
      postRequest = new EasyTideAreaSelectClickHttpPost(
          mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
          pageState.getEventValidation(), subArea.getArea().getExternalId());
      response = mHttpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataProviderException(
            "Couldn't simulate click on area select...");
      }
      pageState = getPageState(getHtmlParser(response));
      EntityUtils.consume(response.getEntity());
      if (postRequest != null) {
        postRequest.releaseConnection();
      }

      // Select region POST
      postRequest = new EasyTideRegionSelectClickHttpPost(
          mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
          pageState.getEventValidation(), subArea.getArea().getExternalId(),
          subArea.getExternalId());
      response = mHttpClient.execute(postRequest);

      if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
        throw new TideDataProviderException(
            "Couldn't simulate click on show ports button...");
      }
      Parser parser = getHtmlParser(response);
      pageState = getPageState(parser);

      EntityUtils.consume(response.getEntity());
      if (postRequest != null) {
        postRequest.releaseConnection();
      }
      
      // ......
      ArrayList<Port> ports = new ArrayList<Port>();
      
      // Parse the pager links from the port selection table
      NodeList pagerNodeList = new NodeList();
      NodeFilter filter = new AndFilter(new TagNameFilter("A"),
          new HasParentFilter(new HasAttributeFilter("ID", "PSL_dlPager"), true));

      parser.reset();
      for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
        e.nextNode().collectInto(pagerNodeList, filter);
      }
      
      // remove the first pager link because we
      // assume (!) that we already have the
      // links for the first page
      if (pagerNodeList.size() > 1) {
        // throw away the result
        pagerNodeList.remove(0);
      }
      
      // Do this for each page of port links
      do {
        // Parse the links from the port selection table
        NodeList list = new NodeList();
        filter = new AndFilter(new TagNameFilter("A"),
            new HasParentFilter(new HasAttributeFilter("ID", "PSL_dl"), true));

        parser.reset();
        for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
          e.nextNode().collectInto(list, filter);
        }

        Node[] nodes = list.toNodeArray();
        ports.ensureCapacity(ports.size() + list.size());

        for (int i = 0; i < list.size(); ++i) {

          mLogger.debug(nodes[i]);
          final TagNode tagNode = (TagNode) nodes[i];
          final String link = tagNode.getAttribute("HREF");
          final Matcher matcher = mPostbackRegexPattern.matcher(link);

          String controlId;
          if (matcher.matches()) {
            controlId = matcher.group(1);
          } else {
            throw new TideDataProviderException(
                "Couldn't match control Id when retrieving port link: "
                    .concat(tagNode.toString()));
          }

          String name = tagNode.getChildren().elementAt(0).getText();
          Port port = new Port();

          port.setName(name);
          port.setDescription(name);

          // Send the port link POST and interpret the response (HTTP 302)
          postRequest = new EasyTidePortLinkClickHttpPost(
              mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
              pageState.getEventValidation(),
              subArea.getArea().getExternalId(), subArea.getExternalId(),
              controlId);
          response = mHttpClient.execute(postRequest);

          if (response.getStatusLine().getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY) {
            throw new TideDataProviderException(
                "Couldn't simulate click on port link for port: ".concat(name));
          }
          // pageState = getPageState(getHtmlParser(response));
          String location = response.getFirstHeader("Location").getValue();
          Matcher externalIdMatcher = mPortIdRegexPattern.matcher(location);
          
          if (externalIdMatcher.matches()) {
            port.setExternalId(externalIdMatcher.group(1));
          } else {
            throw new TideDataProviderException(
                "Couldn't match portId from location: "
                    .concat(location));
          }

          EntityUtils.consume(response.getEntity());
          if (postRequest != null) {
            postRequest.releaseConnection();
          }

          ports.add(port);
        }
        
        if (pagerNodeList.size() > 0) {
          // Get the next page of port results
          final TagNode tagNode = (TagNode)pagerNodeList.remove(0);
          final String link = tagNode.getAttribute("HREF");
          final Matcher matcher = mPostbackRegexPattern.matcher(link);

          String controlId;
          if (matcher.matches()) {
            controlId = matcher.group(1);
          } else {
            throw new TideDataProviderException(
                "Couldn't match control Id when retrieving port link: "
                    .concat(tagNode.toString()));
          }
          // Select next page POST
          postRequest = new EasyTidePageLinkClickHttpPost(
              mUriBuilder.build(mGetAreasUrlSuffix), pageState.getViewState(),
              pageState.getEventValidation(), subArea.getArea().getExternalId(),
              subArea.getExternalId(), controlId);
          response = mHttpClient.execute(postRequest);

          if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new TideDataProviderException(
                "Couldn't simulate click on port page link...");
          }
          parser = getHtmlParser(response);
          pageState = getPageState(parser);

          EntityUtils.consume(response.getEntity());
          if (postRequest != null) {
            postRequest.releaseConnection();
          }
          
        }
        
      } while (pagerNodeList.size() > 0);

      return ports;

    } catch (URISyntaxException e) {
      throw new TideDataProviderException(e);
    } catch (ClientProtocolException e) {
      throw new TideDataProviderException(e);
    } catch (IOException e) {
      throw new TideDataProviderException(e);
    } catch (ParserException e) {
      throw new TideDataProviderException(e);
    } finally {

      if (postRequest != null) {
        postRequest.releaseConnection();
      }
    }

  }

  @Override
  public List<TidePredictionDay> getTidePredictionDay(Port port)
      throws TideDataProviderException {
    // TODO Auto-generated method stub
    return null;
  }

  private Parser getHtmlParser(HttpResponse response) throws ParseException,
      IOException, ParserException {

    final HttpEntity entity = response.getEntity();
    // do something useful with the response body
    // and ensure it is fully consumed
    final String documentString = EntityUtils.toString(entity);

    return new Parser(documentString);

  }

  private EasyTidePageState getAreasPage() throws TideDataProviderException {

    HttpGet getRequest = null;
    try {

      getRequest = new EasyTideHttpGet(mUriBuilder.build(mGetAreasUrlSuffix));
      HttpResponse response = mHttpClient.execute(getRequest);

      Parser parser = getHtmlParser(response);

      return getPageState(parser);

    } catch (URISyntaxException e) {
      throw new TideDataProviderException(e);
    } catch (ClientProtocolException e) {
      throw new TideDataProviderException(e);
    } catch (IOException e) {
      throw new TideDataProviderException(e);
    } catch (ParserException e) {
      throw new TideDataProviderException(e);
    } finally {

      if (getRequest != null) {
        getRequest.releaseConnection();
      }

    }

  }

  private EasyTidePageState getPageState(Parser parser) throws ParserException,
      TideDataProviderException, UnsupportedEncodingException {

    NodeList list = new NodeList();
    NodeFilter filter = new OrFilter(new AndFilter(new TagNameFilter("INPUT"),
        new HasAttributeFilter("ID", "__VIEWSTATE")), new AndFilter(
        new TagNameFilter("INPUT"), new HasAttributeFilter("ID",
            "__EVENTVALIDATION")));

    for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
      e.nextNode().collectInto(list, filter);
    }

    if (list.size() != 2) {
      throw new TideDataProviderException(
          "Unable to find exactly one __VIEWSTATE tag and one __EVENTVALIDATION tag");
    }

    String viewState = null;
    String eventValidation = null;
    Node[] nodes = list.toNodeArray();
    for (Node node : nodes) {
      mLogger.debug(node);
      InputTag inputTag = (InputTag) node;
      if (inputTag.getAttribute("ID").compareTo("__VIEWSTATE") == 0) {
        viewState = inputTag.getAttribute("VALUE");
      }
      if (inputTag.getAttribute("ID").compareTo("__EVENTVALIDATION") == 0) {
        eventValidation = inputTag.getAttribute("VALUE");
      }
    }

    String decodedViewState = new String(
        DatatypeConverter.parseBase64Binary(viewState), "UTF-8");
    mLogger.debug(decodedViewState);

    return new EasyTidePageState(viewState, eventValidation);

  }

}
