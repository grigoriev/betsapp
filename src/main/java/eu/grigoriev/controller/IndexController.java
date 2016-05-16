package eu.grigoriev.controller;

import eu.grigoriev.component.DeliveredVersion;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize(SecurityRules.ALLOWED_FOR_USER_OR_ADMIN_ROLES)
@RequestMapping(Mapping.INDEX.ROOT)
public class IndexController {

    @Autowired
    DeliveredVersion deliveredVersion;

    @ModelAttribute("deliveredVersion")
    public DeliveredVersion deliveredVersion() {
        return deliveredVersion;
    }

    @ModelAttribute("admin")
    public Boolean isAdmin() {
        return SecurityRules.isAdmin();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "index/index";
    }
}
