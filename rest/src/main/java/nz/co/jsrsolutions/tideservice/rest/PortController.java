package nz.co.jsrsolutions.tideservice.rest;

import java.util.List;

import nz.co.jsrsolutions.tideservice.core.domain.GeoLocation;
import nz.co.jsrsolutions.tideservice.core.domain.Port;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RooWebJson(jsonObject = Port.class)
@Controller
@RequestMapping("/ports")
public class PortController {
  
  @RequestMapping(value = "?lat={lat}&lng={lng}&n={n}", headers = "Accept=application/json")
  @ResponseBody
  public ResponseEntity<String> listNearestNJson(@PathVariable("lat") Long lat,
                                                  @PathVariable("lng") Long lng,
                                                  @PathVariable("n") Integer n) {
      GeoLocation geoLocation = new GeoLocation();
      geoLocation.setLatitude(lat);
      geoLocation.setLongitude(lng);
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "application/json; charset=utf-8");
      List<Port> result = portService.findNearestNeighbours(geoLocation, n);
      return new ResponseEntity<String>(Port.toJsonArray(result), headers, HttpStatus.OK);
  }
  
}
