package org.unibl.etf.sni.forum.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.unibl.etf.sni.forum.models.dto.Mail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

@Service
public class MailService {

    @Value("${spring.mail.port}")
    private int PORT;
    @Value("${spring.mail.host}")
    private String ADDRESS;

    public MailService() {
    }


    public void sendMail(Mail mail) {

        boolean sent = false;
        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {

            out.writeObject(mail);
            out.flush();

            String result = (String) in.readObject();
            sent = "OK".equals(result);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }

    }
}
