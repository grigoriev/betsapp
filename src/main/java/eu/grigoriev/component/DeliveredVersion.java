package eu.grigoriev.component;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class DeliveredVersion {

    private Logger logger = Logger.getLogger(DeliveredVersion.class);

    private String version;

    public String getVersion() {
        if (version == null) {
            version = readVersion();
        }
        return version;
    }

    public String readVersion() {
        ClassPathResource classPathResource = new ClassPathResource("version.properties", DeliveredVersion.class.getClassLoader());
        Properties properties = new Properties();
        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {
            logger.error("", e);
        }
        return "Bets App v" + properties.getProperty(BUILD.VERSION) + ", assembled at " + properties.getProperty(BUILD.TIME);
    }

    private class BUILD {
        public static final String VERSION = "bets.build.version";
        public static final String TIME = "bets.build.time";
    }
}
