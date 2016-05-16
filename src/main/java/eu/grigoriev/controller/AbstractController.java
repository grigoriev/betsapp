package eu.grigoriev.controller;

import eu.grigoriev.component.DeliveredVersion;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public abstract class AbstractController {

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
}
