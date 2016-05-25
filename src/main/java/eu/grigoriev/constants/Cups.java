package eu.grigoriev.constants;

public class Cups {
    public static class WC2014 {
        public static final String NAME = "WC2014";
        public static final String DISPLAY_NAME = "WC 2014";
        public static final String URL = "/wc2014";
        public static final Integer UTC_OFFSET = -3;

        public static class STAGES extends COMMON_STAGES {
        }

        public static class MENU_ITEMS extends COMMON_MENU_ITEMS {

        }
    }

    public static class EURO2016 {
        public static final String NAME = "EURO2016";
        public static final String DISPLAY_NAME = "Euro 2016";
        public static final String URL = "/euro2016";
        public static final Integer UTC_OFFSET = +2;

        public static class STAGES extends COMMON_STAGES {
        }

        public static class MENU_ITEMS extends COMMON_MENU_ITEMS {
            public static class PARTICIPANTS {
                public static final String NAME = "Participants";
                public static final String URL = "/participants";
            }
        }
    }

    private static class COMMON_STAGES {
        public static class GROUPS_STAGE {
            public static final String NAME = "Group Stage";
            public static final String URL = "/group/stage";
        }
        public static class ROUND_OF_16 {
            public static final String NAME = "1/8";
            public static final String URL = "/1/8";
        }
        public static class QUARTER_FINAL {
            public static final String NAME = "1/4";
            public static final String URL = "/1/4";
        }
        public static class SEMI_FINAL {
            public static final String NAME = "1/2";
            public static final String URL = "/1/2";
        }
        public static class FINAL {
            public static final String NAME = "Final";
            public static final String URL = "/final";
        }
    }

    private static class COMMON_MENU_ITEMS {
        public static class OTHERS {
            public static final String NAME = "Others";
            public static final String URL = "/others";
        }
        public static class SCORES {
            public static final String NAME = "Scores";
            public static final String URL = "/scores";
        }
        public static class SEPARATOR {
            public static final String NAME = "---separator---";
            public static final String URL = "";
        }
        public static class HELP {
            public static final String NAME = "Help";
            public static final String URL = "/help";
        }
    }
}
