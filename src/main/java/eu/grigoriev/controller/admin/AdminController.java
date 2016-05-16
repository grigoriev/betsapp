package eu.grigoriev.controller.admin;

import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Mapping.ADMIN.ROOT)
@PreAuthorize(SecurityRules.ALLOWED_FOR_ADMIN_ROLE)
public class AdminController extends AbstractController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = Mapping.ADMIN.USER_ADD, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String userAdd(
            ModelMap modelMap
    ) {

        return "admin/user";
    }

    @RequestMapping(value = Mapping.ADMIN.USER_EDIT, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String userEdit(
            ModelMap modelMap
    ) {

        return "admin/user";
    }
}
