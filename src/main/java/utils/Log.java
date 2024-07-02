package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    protected static Logger LOGGER = getLogger();

    public static Logger getLogger() {
        if(LOGGER == null) {
            LOGGER = LogManager.getLogger();
        }

        return LOGGER;
    }
}