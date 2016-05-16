package eu.grigoriev.controller;

import eu.grigoriev.component.DeliveredVersion;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.Roles;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
@PreAuthorize(SecurityRules.ALLOWED_FOR_USER_AND_ADMIN_ROLES)
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
