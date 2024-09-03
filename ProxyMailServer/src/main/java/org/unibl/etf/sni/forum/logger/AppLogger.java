package org.unibl.etf.sni.forum.logger;

import org.unibl.etf.sni.forum.properties.ConfigMailProperties;
import org.unibl.etf.sni.forum.server.ProxyMailServer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {

    private static Logger LOGGER = null;
    private static ConfigMailProperties prop = new ConfigMailProperties();

    public static Logger getLogger() {
        if (LOGGER == null) {
            try {
                LOGGER = Logger.getLogger(ProxyMailServer.class.getName());
                FileHandler fileHandler = new FileHandler(prop.getLogFilePath());
                fileHandler.setFormatter(new SimpleFormatter());
                LOGGER.addHandler(fileHandler);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return LOGGER;
    }
}
