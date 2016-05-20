package eu.grigoriev.utils.mapping;

public class Mapping {

    public static class INDEX {
        public static final String ROOT = "/";
    }

    public static class AUTH {
        public static final String ROOT = "/auth";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
    }

    public static class SETTINGS {
        public static final String ROOT = "/settings";
    }

    public static class TEST {
        public static final String ROOT = "/test";
        public static final String ADD_TEST_USER = "/add/test/user";
    }

    public static class ADMIN {
        public static final String ROOT = "/admin";
        public static final String USER_ADD = "/user/add";
        public static final String USER_EDIT = "/user/edit";
    }

    public class INITIAL_DATA {
        public static final String ROOT = "/initial/data";
        public static final String ALL = "/all";
        public static final String WC2014 = "/wc2014";
        public static final String EURO2016 = "/euro2016";
    }

    public static class INITIAL_DEPLOYMENT {
        public static final String ROOT = "/initial/deployment";
        public static final String ADD_USERS = "/add/users";
    }
}
