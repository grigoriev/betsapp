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

    public class DATA_LOADER {
        public static final String ROOT = "/data/loader";
        public static final String ALL = "/all";
        public static final String CLEAR = "/clear";
        public static final String CUPS = "/cups";
        public static final String TYPES = "/types";
        public static final String TEAMS_NATIONAL = "/teams/national";
        public static final String TEAMS_CLUB = "/teams/club";
        public static final String MATCHES_WC2014 = "/matches/wc2014";
        public static final String GROUPS_WC2014 = "/groups/wc2014";
        public static final String MATCHES_EURO2016 = "/matches/euro2016";
        public static final String GROUPS_EURO2016 = "/groups/euro2016";
    }
}
