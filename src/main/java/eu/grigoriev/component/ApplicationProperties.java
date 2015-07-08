package eu.grigoriev.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Value("${" + PROPERTIES.TEST_MODE + "}")
    private Boolean testMode;

    public Boolean isTestMode() {
        return testMode;
    }

    public static class PROPERTIES {
        public static final String TEST_MODE = "test.mode";
    }
}
