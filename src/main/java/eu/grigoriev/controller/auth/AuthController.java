package eu.grigoriev.controller.auth;

import eu.grigoriev.utils.mapping.Mapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(Mapping.AUTH.ROOT)
public class AuthController {

    @RequestMapping(value = Mapping.AUTH.LOGIN, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String login(
            @RequestParam(value = "error", required = false) boolean error,
            @RequestParam(value = "logout", required = false) boolean logout,
            ModelMap modelMap
    ) {

        if (error) {
            modelMap.put("error", "Invalid username or password!");
        } else {
            modelMap.put("error", "");
        }

        if (logout) {
            modelMap.put("message", "Successfully logged out!");
        } else {
            modelMap.put("message", "");
        }

        return "auth/login";
    }

    @RequestMapping(value = Mapping.AUTH.LOGOUT, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/auth/login?logout=true";
    }
}