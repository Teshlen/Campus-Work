/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Teshlen
 */
public class SendEmail {

 
	public void sendingEmailTo(String recip) {
 
                String subject = null;
                String body = null;
                String recipient = null;
                subject = "cos330 access";
                body = "Somebody has accessed your account, if this was not you please check with adimistration";
                recipient = recip;
                
		final String username = "tesh.nadesan@gmail.com";
		final String password = "sniper0000";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("tesh.nadesan@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("email sent to: " + recipient + " sucessfully");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}