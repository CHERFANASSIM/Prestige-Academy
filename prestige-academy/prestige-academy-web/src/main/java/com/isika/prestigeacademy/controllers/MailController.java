package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.EmailRecu;
import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.services.EmailRecuService;
import com.isika.prestigeacademy.services.EntreprisesService;
import com.isika.prestigeacademy.services.StagiairesService;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Named
@SessionScoped
public class MailController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3990508737949483292L;
	private String port;
	private String host;
	private String Destinataire;
	private List<Stagiaire> listeStagiaire;
	private List<Entreprise> listeEntreprise;
	private EmailRecu newEmail;
	private UploadedFile file;
	private String emplacement;
	final static String userName = "hr.presigeacademy@gmail.com";
	final static String password = "crmprojet3";

	private static final String TIRET= "-";

	private static final Logger LOGGER = Logger.getLogger(MailController.class.getSimpleName());


	@Inject
	private EmailRecuService emailRecuService;
	@Inject
	private StagiairesService stagiairesService;
	@Inject
	private EntreprisesService entrepriseService;

	
	@PostConstruct
	private void chargerListes() {
		newEmail = new EmailRecu();
		listeStagiaire = stagiairesService.rechercheTousLesStagiaires();
		listeEntreprise = entrepriseService.rechercheTousEntreprises();
	}



	public void uploadedFile() {

		byte[] data = file.getContents();

		String filename = "Pièce jointe" + TIRET + file.getFileName().replace(" ", "");

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		emplacement = externalContext.getRealPath("") + File.separator + filename;

		FileImageOutputStream pjOutput;
		try {
			//On essaie d'enregistrer la pj ici : " + emplacement)
			pjOutput = new FileImageOutputStream(new File(emplacement));
			pjOutput.write(data, 0, data.length);
			pjOutput.close();
		} catch (IOException e) {
			LOGGER.info("Erreur upload file");
		}
		showMessageAndReloadData("Pièce Jointe Récupérée");

	}


	public void sendEmailWithAttachments(String toAddress, String subject, String message)
			throws MessagingException {
		byte[] contents = file.getContents();

		uploadedFile();
		
		
		try {
			// SMTP info
			host = "smtp.gmail.com";
			port = "587";
			
			// Propriétés SMTP server
			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.user", userName);
			properties.put("mail.password", password);

			// attachments
			String attachFiles;
			attachFiles = emplacement;


			// Créaton d'une nouvelle session Authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};
			Session session = Session.getInstance(properties, auth);

			// Création d'un nouvel Email
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(userName));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());

			// Création message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(message, "text/html");

			// Création multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Ajout des Pieces jointes
			if (attachFiles != null && !attachFiles.isEmpty()) {
				
					MimeBodyPart attachPart = new MimeBodyPart();

					try {
						attachPart.attachFile(attachFiles);
					} catch (IOException ex) {
						LOGGER.severe(message);
					}

					multipart.addBodyPart(attachPart);
				}
			
			// Email multipart
			msg.setContent(multipart);

			// Envoyer email
			Transport.send(msg);
			showMessageAndReloadData("Email envoyé avec succès !");
		} catch (Exception ex) {
			showMessageAndReloadData("L'envoie de l'Email a échoué");
		}
	}


	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
	}

	
	public void enregistrerEmail(String selectedMailTo, String selectedObject, String selectedContent, String attachement) {
		
		if (file != null) {
		attachement= file.getFileName();
		} 
		else { 
			attachement=" ";
		}

		// On cherche le stagiare par son mail
		Stagiaire stagiaire = listeStagiaire.stream().filter(value -> selectedMailTo.equals(value.getMailStagiaire()))
				.findFirst().get();

		if (stagiaire != null) {
			long newEmailID = emailRecuService.creation1(newEmail, stagiaire, selectedObject, selectedContent, attachement);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enregistré", "Id [" + newEmail + "]");
			FacesContext.getCurrentInstance().addMessage(null, m);
		} else {
			Entreprise entreprise = listeEntreprise.stream()
					.filter(value -> selectedMailTo.equals(value.getMailEntreprise())).findFirst().get();

			long newEmailID = emailRecuService.creation2(newEmail, entreprise, selectedObject, selectedContent, attachement);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enregistré", "Id [" + newEmail + "]");
			FacesContext.getCurrentInstance().addMessage(null, m);
		}

	}

	

	public EmailRecu getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(EmailRecu newEmail) {
		this.newEmail = newEmail;
	}

	public List<Stagiaire> getListeStagiaire() {
		return listeStagiaire;
	}

	public void setListeStagiaire(List<Stagiaire> listeStagiaire) {
		this.listeStagiaire = listeStagiaire;
	}

	public List<Entreprise> getListeEntreprise() {
		return listeEntreprise;
	}

	public void setListeEntreprise(List<Entreprise> listeEntreprise) {
		this.listeEntreprise = listeEntreprise;
	}

	public String getDestinataire() {
		return Destinataire;
	}

	public void setDestinataire(String destinataire) {
		Destinataire = destinataire;
	}

	public UploadedFile getFiles() {
		return file;
	}

	public void setFiles(UploadedFile files) {
		this.file = files;
	}


	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public static String getUsername() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}
	

}
