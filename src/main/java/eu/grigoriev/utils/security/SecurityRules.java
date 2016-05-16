package eu.grigoriev.utils.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public abstract class SecurityRules {
    public static final String ALLOWED_FOR_USER_ROLE = "hasRole('" + Roles.USER + "')";
    public static final String ALLOWED_FOR_ADMIN_ROLE = "hasRole('" + Roles.ADMIN + "')";
    public static final String ALLOWED_FOR_USER_OR_ADMIN_ROLES = "hasRole('" + Roles.ADMIN + "') OR hasRole('" + Roles.USER + "')";
    public static final String ALLOWED_FOR_LOGGED_IN = "isAuthenticated()";


    private static boolean hasUserRole(Authentication authentication, String role) {
        if (authentication == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        return userAuthorities.contains(new SimpleGrantedAuthority(role));
    }

    private static boolean hasUserRole(String role) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return false;
        }
        Authentication authentication = securityContext.getAuthentication();
        return SecurityRules.hasUserRole(authentication, role);
    }

    public static boolean isAdmin() {
        return hasUserRole(Roles.ADMIN);
    }

    public static boolean isUser(User user) {
        return hasUserRole(Roles.USER);
    }
}
