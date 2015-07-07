package eu.grigoriev.utils.security;

public abstract class SecurityRules {
    public static final String ALLOWED_FOR_USER_ROLE = "hasRole('" + Roles.USER + "')";
    public static final String ALLOWED_FOR_LOGGED_IN = "isAuthenticated()";
}
