package eu.grigoriev.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Value("${" + PROPERTIES.MYSQL.JDBC_DRIVER_CLASS_NAME + "}")
    private String mysqlJdbcDriverClassName;
    @Value("${" + PROPERTIES.MYSQL.JDBC_URL + "}")
    private String mysqlJdbcUrl;
    @Value("${" + PROPERTIES.MYSQL.JDBC_USERNAME + "}")
    private String mysqlJdbcUsername;
    @Value("${" + PROPERTIES.MYSQL.JDBC_PASSWORD + "}")
    private String mysqlJdbcPassword;

    @Value("${" + PROPERTIES.TEST_MODE + "}")
    private Boolean testMode;

    public String getMysqlJdbcUrl() {
        return mysqlJdbcUrl;
    }

    public String getMysqlJdbcUsername() {
        return mysqlJdbcUsername;
    }

    public String getMysqlJdbcPassword() {
        return mysqlJdbcPassword;
    }

    public String getMysqlJdbcDriverClassName() {
        return mysqlJdbcDriverClassName;
    }

    public Boolean isTestMode() {
        return testMode;
    }

    public static class PROPERTIES {

        public static class MYSQL {
            public static final String JDBC_DRIVER_CLASS_NAME = "mysql.jdbc.driverClassName";
            public static final String JDBC_URL = "mysql.jdbc.url";
            public static final String JDBC_USERNAME = "mysql.jdbc.username";
            public static final String JDBC_PASSWORD = "mysql.jdbc.password";
        }

        public static final String TEST_MODE = "test.mode";
    }
}
