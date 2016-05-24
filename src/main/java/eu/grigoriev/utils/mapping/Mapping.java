package eu.grigoriev.utils.mapping;

import eu.grigoriev.constants.Cups;

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

    public static class ADMIN {
        public static final String ROOT = "/admin";
        public static final String USER_ADD = "/user/add";
        public static final String USER_EDIT = "/user/edit";
    }

    public class INITIAL_DATA {
        public static final String ROOT = "/initial/data";
        public static final String ALL = "/all";
        public static final String WC2014 = Cups.WC2014.URL;
        public static final String EURO2016 = Cups.EURO2016.URL;
    }

    public static class INITIAL_DEPLOYMENT {
        public static final String ROOT = "/initial/deployment";
        public static final String ADD_USERS = "/add/users";
    }

    public class WC2014 {
        public static final String ROOT = Cups.WC2014.URL;
        public static final String GROUP_STAGE = Cups.WC2014.STAGES.GROUPS_STAGE.URL;
        public static final String ROUND_OF_16 = Cups.WC2014.STAGES.ROUND_OF_16.URL;
        public static final String QUARTER_FINAL = Cups.WC2014.STAGES.QUARTER_FINAL.URL;
        public static final String SEMI_FINAL = Cups.WC2014.STAGES.SEMI_FINAL.URL;
        public static final String FINAL = Cups.WC2014.STAGES.FINAL.URL;
    }

    public class EURO2016 {
        public static final String ROOT = Cups.EURO2016.URL;
        public static final String GROUP_STAGE = Cups.EURO2016.STAGES.GROUPS_STAGE.URL;
        public static final String ROUND_OF_16 = Cups.EURO2016.STAGES.ROUND_OF_16.URL;
        public static final String QUARTER_FINAL = Cups.EURO2016.STAGES.QUARTER_FINAL.URL;
        public static final String SEMI_FINAL = Cups.EURO2016.STAGES.SEMI_FINAL.URL;
        public static final String FINAL = Cups.EURO2016.STAGES.FINAL.URL;
    }
}
