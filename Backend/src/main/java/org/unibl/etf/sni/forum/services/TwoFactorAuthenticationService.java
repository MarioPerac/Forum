package org.unibl.etf.sni.forum.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.sni.forum.models.dto.Mail;
import org.unibl.etf.sni.forum.models.entites.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwoFactorAuthenticationService
{
    private MailService mailService;

    public TwoFactorAuthenticationService(MailService mailService){
        this.mailService = mailService;
    }
    private final Map<String, String> userVerificationCodes = new HashMap<>();

    public String generateCode(String username) {
        String code = String.valueOf(new Random().nextInt(9000) + 1000);
        userVerificationCodes.put(username, code);
        return code;
    }

    public boolean verifyCode(String username, String code) {
        String storedCode = userVerificationCodes.get(username);
        return storedCode != null && storedCode.equals(code);
    }

    public void sendMail(UserEntity userEntity, String code){
        new Thread(() -> {
            String fullName = userEntity.getName() +  " " + userEntity.getSurname();
            String subject = "Forum - Two Factor Authentication";
            String htmlContent ="<html>"
                    + "<body>"
                    + "<p>Dear " + fullName + ",</p>"
                    + "<p>This is your code: " + code+"</p>"
                    + "<p>Kind regards,</p>"
                    + "<p>Fit Zone</p>"
                    + "</body>"
                    + "</html>";
            mailService.sendMail(new Mail(userEntity.getMail(), subject, fullName, userEntity.getUsername(), htmlContent));
        }).start();
    }
}
