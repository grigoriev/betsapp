package eu.grigoriev.controller;

import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize(SecurityRules.ALLOWED_FOR_USER_OR_ADMIN_ROLES)
@RequestMapping(Mapping.INDEX.ROOT)
public class IndexController extends AbstractController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "index/index";
    }
}
