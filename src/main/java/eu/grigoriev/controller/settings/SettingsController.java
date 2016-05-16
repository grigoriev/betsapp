package eu.grigoriev.controller.settings;

import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.model.response.SettingsResponse;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PreAuthorize(SecurityRules.ALLOWED_FOR_ADMIN_ROLE)
@RequestMapping(Mapping.SETTINGS.ROOT)
public class SettingsController extends AbstractController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public SettingsResponse index() {
        SettingsResponse settingsResponse = new SettingsResponse();
        return settingsResponse;
    }
}
