package nz.co.jsrsolutions.tideservice.rest;

import nz.co.jsrsolutions.tideservice.core.domain.Area;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Area.class)
@Controller
@RequestMapping("/areas")
public class AreaController {
}
