package com.isika.prestigeacademy.controllers;


import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Notifications;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.services.EntreprisesService;
import com.isika.prestigeacademy.services.NotificationsServices;
import com.isika.prestigeacademy.services.UtilisateurService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class NotificationsController implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(Notifications.class.getSimpleName());
	//Création
	private final Notifications notificationCree = new Notifications();

	@Inject
	private NotificationsServices notificationsServices;
	@Inject
	private UtilisateurService utilisateurService;
	@Inject
	private EntreprisesService entreprisesService; 

	//Utilisateur Session
	private Utilisateur user;
	 private Utilisateur receiver;
	 private String message;
	 private Notifications notif;

	//Compteur des notifications
	private int compteurNotifcation = 0;

    

	//Listes
	private List<Utilisateur> utilisateurs;
	private List<Notifications> notificationsList;

	@PostConstruct
	private void chargerListeNotifications() {
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Long idReceive = (Long) httpSession.getAttribute("userID");
		user  = utilisateurService.findById(idReceive);

		notificationsList = notificationsServices.rechercheNotificationsByUser(user);
		compteurNotifcation = notificationsList.size();

	}
	public String supprimer(Notifications selected) {
		selected.setStatus(false);
		selected = notificationsServices.modifier(selected);
		notificationsList = notificationsServices.rechercheNotificationsByUser(user);
		compteurNotifcation = notificationsList.size();
		return WebUtils.redirectTo("Dashbord.xhtml");
	}

	
	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
	}
	
	
	
	
	public void ajouterUneNotification(Notifications notif, String message, Long sender, Long receiver) {
		Long NotifID = notificationsServices.createNotification(notif,message,sender,receiver);
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public List<Notifications> getNotificationsList() {
		return notificationsList;
	}

	public void setNotificationsList(List<Notifications> notificationsList) {
		this.notificationsList = notificationsList;
	}

	public int getCompteurNotifcation() {
		return compteurNotifcation;
	}

	public void setCompteurNotifcation(int compteurNotifcation) {
		this.compteurNotifcation = compteurNotifcation;
	}
}
