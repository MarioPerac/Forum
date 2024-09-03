package org.unibl.etf.sni.forum.server;

import org.unibl.etf.sni.forum.logger.AppLogger;
import org.unibl.etf.sni.forum.properties.ConfigMailProperties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class ProxyMailServer {

    private static int PORT;
    private static ConfigMailProperties prop = new ConfigMailProperties();

    public static void main(String args[]) {
        PORT = Integer.parseInt(prop.getProxyServerPort());

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Proxy Mail server started");
            while (true) {
                Socket socket = server.accept();
                new ProxyMailServerThread(socket).start();
            }
        } catch (IOException e) {
            AppLogger.getLogger().log(Level.SEVERE, e.getMessage());
        }

    }
}
