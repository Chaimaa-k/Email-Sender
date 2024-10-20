package entities;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class EmailSender {

    public static void sendEmails(String[] recipients, String subject, String body, String filePath) {
        for (String recipient : recipients) {
            sendEmail(recipient, subject, body, filePath);
        }
    }

    private static void sendEmail(String recipient, String subject, String body, String filePath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("elkarouitichaimaa@gmail.com", "password");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("elkarouitichaimaa@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // Utiliser TO au lieu de BCC

            // Sujet
            message.setSubject(subject);

            // Corps
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Pièce jointe
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(new File(filePath).getName());

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("E-mail envoyé à : " + recipient + " avec succès !");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
