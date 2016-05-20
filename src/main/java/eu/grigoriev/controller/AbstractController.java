package eu.grigoriev.controller;

import eu.grigoriev.component.DeliveredVersion;
import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.CupMenuItemEntity;
import eu.grigoriev.persistence.service.CupsRepository;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public abstract class AbstractController {

    @Autowired
    DeliveredVersion deliveredVersion;

    @Autowired
    CupsRepository cupsRepository;

    @ModelAttribute("deliveredVersion")
    public DeliveredVersion modelAttrDeliveredVersion() {
        return deliveredVersion;
    }

    @ModelAttribute("admin")
    public Boolean modelAttrAdmin() {
        return SecurityRules.isAdmin();
    }

    @ModelAttribute("cups")
    public List<CupEntity> modelAttrCups() {
        return cupsRepository.findAll();
    }
}
