package com.buyalskaya.fitclub.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

public class MailSender {
    private static final MailSender INSTANCE = new MailSender();
    private static final Logger logger = LogManager.getLogger();
    private static final String MAIL_CONFIG_FILE = "config/mail.properties";
    private static final String USER_NAME = "mail.user.name";
    private static final String USER_PASSWORD = "mail.user.password";
    private Properties properties = new Properties();

    private MailSender() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(MAIL_CONFIG_FILE);
            properties.load(input);
        } catch (IOException e) {
            logger.log(Level.ERROR, "Impossible to send a message. Mail service has incorrect configuration");
        }
    }

    public boolean send(String sendToEmail, String mailSubject, String mailText) {
        boolean isSend = false;
        try {
            MimeMessage message = initMessage(sendToEmail, mailSubject, mailText);
            Transport.send(message);
            isSend = true;
        } catch (AddressException ex) {
            logger.log(Level.ERROR, "Invalid address: " + sendToEmail);
        } catch (MessagingException ex) {
            logger.log(Level.ERROR, "Error generating or sending message: " + ex.getMessage());
        }
        return isSend;
    }

    private MimeMessage initMessage(String sendToEmail, String mailSubject, String mailText) throws MessagingException {
        Session mailSession = createSession();
        mailSession.setDebug(true);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress((String) properties.get(USER_NAME)));
        message.setSubject(mailSubject);
        message.setText(mailText);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        return message;
    }

    private Session createSession() {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication((String) properties.get(USER_NAME),
                        (String) properties.get(USER_PASSWORD));
            }
        });
        return session;
    }

    public static MailSender getINSTANCE() {
        return INSTANCE;
    }
}
