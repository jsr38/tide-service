package nz.co.jsrsolutions.tideservice.rest;

import nz.co.jsrsolutions.tideservice.core.domain.SubArea;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = SubArea.class)
@Controller
@RequestMapping("/subareas")
public class SubAreaController {
}
