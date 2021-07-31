package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.services.EntreprisesService;
import com.isika.prestigeacademy.services.StagiairesService;
import com.isika.prestigeacademy.services.UtilisateurService;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;


@Named()
@SessionScoped
public class UtilitairesController implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private UtilisateurService utilisateurService;
	@Inject
	private StagiairesService stagiaireService;
	@Inject
	private EntreprisesService entrepriseService;

	private Utilisateur utilisateurConnecte;
	private Stagiaire stagiaireConnecte;
	private Entreprise entrepriseConnecte;



	@NotBlank
	@NotEmpty
	private String identifiant;
	
	@Email
	@NotBlank
	@NotEmpty
	private String mailStagiaire;
	
	@Email
	@NotBlank
	@NotEmpty
	private String mailEntreprise;

	@NotBlank
	@NotEmpty
	private String mdp;
	
	@NotBlank
	@NotEmpty
	private String mdpStagiaire;
	
	@NotBlank
	@NotEmpty
	private String mdpEntreprise;

	private String message;
	

	public void DroitUtilisateur() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("userID", utilisateurConnecte.getUtilisateursID());
	}


	public String seConnecter() {
		Utilisateur utilisateurSeConnecte = utilisateurService.seConnecter(identifiant, mdp);

		String redirection = "";

		if (utilisateurSeConnecte != null) {
			utilisateurConnecte = utilisateurSeConnecte;	
			if (utilisateurSeConnecte.getNiveauAcces().getNiveauAccesID()==1) {
				redirection = WebUtils.redirectTo(WebUtils.HOME);
			}
			else  {
				message= "Vous n'êtes pas autoriser à acceder à cette plateforme";
			}
		}	
		else {
			message= "Attention : Mail et/ou Mot de passe Invalide(s). La connexion a échouée";    
		}
		DroitUtilisateur();
		return redirection;

	}
	
	
	public String seConnecterStagiaire() {
		Stagiaire stagiaireSeConnecte = stagiaireService.seConnecter(mailEntreprise, mdpEntreprise);
		String redirection = "";

		if (stagiaireSeConnecte != null) {
			stagiaireConnecte = stagiaireSeConnecte;
			redirection = WebUtils.redirectTo(WebUtils.INDEXSTAGIAIRE );
		}
		else {
			message= "Attention : Mail et/ou Mot de passe Invalide(s). La connexion a échouée";    
		}
		return redirection;
	}
	
	
	public String seConnecterEntreprise() {
		Entreprise entrepriseSeConnecte = entrepriseService.seConnecter(mailStagiaire, mdpStagiaire);
		String redirection = "";

		if (entrepriseSeConnecte != null) {
			entrepriseConnecte = entrepriseSeConnecte;	
			redirection = WebUtils.redirectTo(WebUtils.INDEXENTREPRISE );
		}	
		else {
			message= "Attention : Mail et/ou Mot de passe Invalide(s). La connexion a échouée";    
		}
		return redirection;

	}
	


	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}
	public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
		this.utilisateurConnecte = utilisateurConnecte;
	}

	public Stagiaire getStagiaireConnecte() {
		return stagiaireConnecte;
	}

	public void setStagiaireConnecte(Stagiaire stagiaireConnecte) {
		this.stagiaireConnecte = stagiaireConnecte;
	}

	public Entreprise getEntrepriseConnecte() {
		return entrepriseConnecte;
	}

	public void setEntrepriseConnecte(Entreprise entrepriseConnecte) {
		this.entrepriseConnecte = entrepriseConnecte;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public StagiairesService getStagiaireService() {
		return stagiaireService;
	}

	public void setStagiaireService(StagiairesService stagiaireService) {
		this.stagiaireService = stagiaireService;
	}

	public String getMdpStagiaire() {
		return mdpStagiaire;
	}

	public void setMdpStagiaire(String mdpStagiaire) {
		this.mdpStagiaire = mdpStagiaire;
	}

	public String getMdpEntreprise() {
		return mdpEntreprise;
	}

	public void setMdpEntreprise(String mdpEntreprise) {
		this.mdpEntreprise = mdpEntreprise;
	}

	public String getMailStagiaire() {
		return mailStagiaire;
	}

	public void setMailStagiaire(String mailStagiaire) {
		this.mailStagiaire = mailStagiaire;
	}

	public String getMailEntreprise() {
		return mailEntreprise;
	}

	public void setMailEntreprise(String mailEntreprise) {
		this.mailEntreprise = mailEntreprise;
	}


	public UtilisateurService getUtilisateurService() {
		return utilisateurService;
	}


	public void setUtilisateurService(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

}
