package eu.grigoriev.controller;

import eu.grigoriev.component.DeliveredVersion;
import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.UserEntity;
import eu.grigoriev.persistence.service.CupsRepository;
import eu.grigoriev.persistence.service.UsersRepository;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public abstract class AbstractController {

    @Autowired
    DeliveredVersion deliveredVersion;

    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    UsersRepository usersRepository;

    @ModelAttribute("deliveredVersion")
    public DeliveredVersion modelAttrDeliveredVersion() {
        return deliveredVersion;
    }

    @ModelAttribute("admin")
    public Boolean modelAttrAdmin() {
        return SecurityRules.isAdmin();
    }

    @ModelAttribute("currentUser")
    public UserEntity modelCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null;
        } else {
            Authentication authentication = securityContext.getAuthentication();

            return usersRepository.findByName(authentication.getName());
        }
    }

    @ModelAttribute("cups")
    public List<CupEntity> modelAttrCups() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = usersRepository.findByName(username);
        if (userEntity != null) {
            return userEntity.getCupEntities();
        } else {
            return new ArrayList<>();
        }
    }
}
