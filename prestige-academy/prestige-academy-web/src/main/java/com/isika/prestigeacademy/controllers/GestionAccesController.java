package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.services.UtilisateurService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class GestionAccesController implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilisateurService utilisateurService;
	
	private Utilisateur u;
	
	public boolean droitModification() {
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	long userID = (long) session.getAttribute("userID");
	
	Utilisateur u = utilisateurService.findById(userID);
	if(u.getUtilisateursID()==1) {
		return true;
	}
	else if(u.getUtilisateursID()==2) {
		return true;
	}
	else if(u.getUtilisateursID()==3) {
		return true;
	}
	else if(u.getUtilisateursID()==4) {
		return false;
	}
	return false;
	
}

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}

	public UtilisateurService getUtilisateurService() {
		return utilisateurService;
	}

	public void setUtilisateurService(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
}
