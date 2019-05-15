package com.foolishworks.knotting.utils;

import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.foolishworks.knotting.constants.KnottingConstants;

@Component
@PropertySource("classpath:config.properties")
public class MailerUtil extends KnottingConstants {

	private final Logger slf4jLogger = LoggerFactory.getLogger(MailerUtil.class);

	@Autowired
	GeneralUtils generalUtils;

	@Autowired
	private Environment environment;

	public boolean sendWelcomeMail(String email) {

		slf4jLogger.debug("Entering function {}", "sendWelcomeMail");

		try{
			VelocityContext velocityContext = new VelocityContext();
			sendMailUsingVelocityTemplate(environment.getProperty("welcome.email.subject"), email, environment.getProperty("welcome.email.template"), velocityContext);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "sendWelcomeMail");

		return true;
	}

	public boolean sendNewEntryMail(String phoneNumber, String email, String finalCost, String couponDiscount, String serviceList) {

		slf4jLogger.debug("Entering function {}", "sendNewEntryMail");

		try{
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("finalCost", finalCost);
			velocityContext.put("couponDiscount", couponDiscount);
			velocityContext.put("serviceList", serviceList);
			
			sendMailUsingVelocityTemplate(environment.getProperty("new.entry.email.subject"), email, environment.getProperty("new.entry.email.template"), velocityContext);
			
			//send a mail to support desk's - new service mailbox
			sendMailUsingVelocityTemplate(environment.getProperty("support.new.entry.email.subject")+phoneNumber+"/"+email, newServiceMailbox, environment.getProperty("support.entry.email.template"), velocityContext);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "sendNewEntryMail");

		return true;
	}

	public boolean sendApprovedMail(String email, String entryName) {

		slf4jLogger.debug("Entering function {}", "sendApprovedMail");

		try{
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("entryName", entryName);
			
			sendMailUsingVelocityTemplate(environment.getProperty("entry.approved.email.subject"), email, environment.getProperty("entry.approved.email.template"), velocityContext);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "sendApprovedMail");

		return true;
	}

	public boolean sendRejectionMail(String email, String entryName, String rejectionReason) {

		slf4jLogger.debug("Entering function {}", "sendRejectionMail");

		try{
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("entryName", entryName);
			velocityContext.put("rejectionReason", rejectionReason);
			
			sendMailUsingVelocityTemplate(environment.getProperty("entry.rejected.email.subject"), email, environment.getProperty("entry.rejected.email.template"), velocityContext);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "sendRejectionMail");

		return true;
	}

	public boolean mailExceptionDetails(String screen, String exceptionMessage, String exceptionDetails, Object userId, Object userType) {

		slf4jLogger.debug("Entering function {}", "mailExceptionDetails");

		String user = (userId == null)?"Guest":((Long)userId).toString();
		String type = (userType == null)?"Guest":(String)userType;

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("userId", user);
		velocityContext.put("userType", type);
		velocityContext.put("exceptionDetails", exceptionDetails);

		sendMailUsingVelocityTemplate(environment.getProperty("mail.exception.email.subject")+" - "+screen+":"+exceptionMessage, exceptionMailbox, environment.getProperty("mail.exception.email.template"), velocityContext);

		slf4jLogger.debug("Exiting function {}", "mailExceptionDetails");

		return true;
	}

	public boolean verifyEmail(String functionName, String email, Long id) {

		slf4jLogger.debug("Entering function {}", "verifyEmail");

		try{
			StringBuilder verificationEmailLink = new StringBuilder();
			String encryptedId = generalUtils.encryptStringUsingPassword(id.toString());
			String encryptedEmail = generalUtils.encryptStringUsingPassword(email);

			verificationEmailLink.append(domainPath).append(functionName).append("?id=").append(URLEncoder.encode(encryptedId, "UTF-8")).append("&email=").append(URLEncoder.encode(encryptedEmail, "UTF-8"));

			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("verifylink", verificationEmailLink.toString());

			sendMailUsingVelocityTemplate(environment.getProperty("mail.verify.email.subject"), email, environment.getProperty("mail.verify.email.template"), velocityContext);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "verifyEmail");

		return true;
	}

	private boolean sendMailUsingVelocityTemplate(String subject, String to, String templateName, VelocityContext velocityContext) {

		slf4jLogger.debug("Entering function {}", "sendMailUsingVelocityTemplate");

		Properties properties = new Properties();
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		VelocityEngine velocityEngine = new VelocityEngine(properties);

		Template template = velocityEngine.getTemplate(templateName);

		StringWriter stringWriter = new StringWriter();

		template.merge(velocityContext, stringWriter);

		sendMail(subject, stringWriter.toString(), to);

		slf4jLogger.debug("Exiting function {}", "sendMailUsingVelocityTemplate");

		return true;
	}

	private void sendMail(String subject, String message, String toList) {

		slf4jLogger.debug("Entering function {}", "sendMail");

		Session session = setupChannel(environment.getProperty("connection.host"), environment.getProperty("connection.port"),
				environment.getProperty("connection.mailaddress"), environment.getProperty("connection.mailpassword"));

		triggerMail(session, subject, message, toList);

		slf4jLogger.debug("Exiting function {}", "sendMail");

	}

	private Session setupChannel(String host, String port, final String mailAddress, final String mailPassword){

		slf4jLogger.debug("Entering function {}", "setupChannel");

		Session session = null;

		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.startssl.enable", "true");

		session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailAddress,mailPassword);
			}
		});

		slf4jLogger.debug("Exiting function {}", "setupChannel");

		return session;

	}

	private void triggerMail(Session session, String mailSubject, String mailMessage, String toList) {

		slf4jLogger.debug("Entering function {}", "triggerMail");

		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(environment.getProperty("connection.mailaddress"), environment.getProperty("connection.from.name")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toList));
			message.setSubject(mailSubject);
			message.setContent(mailMessage, "text/html");

			Transport.send(message);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}

		slf4jLogger.debug("Exiting function {}", "triggerMail");

	}

}
