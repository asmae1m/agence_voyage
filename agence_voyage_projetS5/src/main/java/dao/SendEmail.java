package dao;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;

public class SendEmail {

	public void sendMail(String email,String password,String subject,String commentaire) {
		
        
        // Sender's email ID needs to be mentioned
        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
        
        String receiver = "notreagencebgi@gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        	
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(email, password);
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        
            // Set To: header field of the header.
            message.setFrom(email);

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(commentaire);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
