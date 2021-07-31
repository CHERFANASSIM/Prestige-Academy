package com.isika.prestigeacademy.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Named
@RequestScoped
public class MailReceiveController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4608517800763229853L;
	private static final String host = "imap.gmail.com";
	private static final int port = 993;
	private static final String userName = "hr.presigeacademy@gmail.com";
	private static final String password = "crmprojet3";
//	private static final String saveDirectory = "D:/Attachment/";

	private List<MailMessageViewModel> listeMessages = new ArrayList<>();
	private Session session;

	@PostConstruct
	public void init() {
		initSession();
		chargerMail();
	}
	
	public void downloadEmailAttachments(String host, int port, String userName, String password) {
		try {
			// connects to the message store
			URLName url = new URLName("imaps", host, port, "INBOX", "hr.presigeacademy@gmail.com", "crmprojet3");
			if(session == null) {
				initSession();
			}
			
			Store store = session.getStore(url);
			store.connect();

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
				String from = (fromAddress != null && fromAddress.length != 0) ? fromAddress[0].toString() : "";
				String subject = message.getSubject();
				String sentDate = message.getSentDate().toString();
				String contentType = message.getContentType();
				String messageContent = "";

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							// TODO : à faire autrement la sauvegarde d'un fichier
							// car c'est une appli web !!!
							// part.saveFile(saveDirectory + File.separator + fileName);
						} else {
							// this part may be the message content
							messageContent = part.getContent().toString();
						}
					}
					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}
				
				// TODO : vous pouvez enrichir l'objet MailMessage pour utiliser d'autres champs
				// c'est mieux que d'exposer le IMAPMessage de JavaMail
				listeMessages.add(new MailMessageViewModel(from, subject, sentDate, messageContent, attachFiles));
			}
			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void initSession() {
		if (session == null) {
			Properties properties = null;
			try {
				properties = System.getProperties();
			} catch (SecurityException e) {
				properties = new Properties();
			}
			session = Session.getInstance(properties, null);
		}
	}

	public void chargerMail() {
		downloadEmailAttachments(host, port, userName, password);
	}

	public List<MailMessageViewModel> getListeMessages() {
		return listeMessages;
	}

	public void setListeMessages(List<MailMessageViewModel> listeMessages) {
		this.listeMessages = listeMessages;
	}

}
