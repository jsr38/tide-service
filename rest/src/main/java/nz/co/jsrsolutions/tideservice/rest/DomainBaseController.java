package nz.co.jsrsolutions.tideservice.rest;

import nz.co.jsrsolutions.tideservice.core.domain.DomainBase;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = DomainBase.class)
@Controller
@RequestMapping("/domainbases")
public class DomainBaseController {
}
