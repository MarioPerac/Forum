package org.unibl.etf.sni.forum.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Siem {

    private static final Logger logger = LoggerFactory.getLogger(Siem.class);

    public void logWarning(String message, Object... args) {
        logger.warn(message, args);
    }
}

