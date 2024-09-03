package org.unibl.etf.sni.forum.properties;

import java.io.IOException;
import java.util.Properties;

public class ConfigMailProperties {

    Properties properties = new Properties();

    public ConfigMailProperties() {

        try {
            properties.load(getClass().getResourceAsStream("configMail.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Properties getMailProperties() {
        return properties;
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getProxyServerPort() {
        return properties.getProperty("proxy_server_port");
    }


    public String getLogFilePath() {
        return properties.getProperty("log_file");
    }
}
