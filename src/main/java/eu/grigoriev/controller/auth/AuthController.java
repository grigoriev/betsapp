package eu.grigoriev.controller.auth;

import eu.grigoriev.utils.mapping.Mapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(Mapping.AUTH.ROOT)
public class AuthController {

    @RequestMapping(value = Mapping.AUTH.LOGIN, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String login(
            @RequestParam(value = "error", required = false) boolean error,
            ModelMap modelMap
    ) {

        if (error) {
            modelMap.put("error", "Invalid username or password!");
        } else {
            modelMap.put("error", "");
        }

        return "auth/login";
    }
}