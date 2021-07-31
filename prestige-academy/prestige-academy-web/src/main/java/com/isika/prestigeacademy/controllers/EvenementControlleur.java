package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.CompteRendu;
import com.isika.prestigeacademy.model.entities.Evenement;
import com.isika.prestigeacademy.model.entities.TypeEvenement;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.services.EvenementService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class EvenementControlleur implements Serializable {

	private static final long serialVersionUID = 40200552022598871L;

	private static final Logger LOGGER = Logger.getLogger(EvenementControlleur.class.getSimpleName());

	@Inject
	private EvenementService evenementService;

	// Création
	private Evenement evenementCree = new Evenement();
	private CompteRendu compteRendu = new CompteRendu();

	// Modification
	private Evenement evenementModifie;

	// Listes
	private List<TypeEvenement> listeTypeEvenement;
	private List<Utilisateur> listeUtilisateurs;
	private List<Evenement> listeEvenement;

	private String selectedUtilisateur;
	private String selectedTypeEvenement;
	private Long idTypeEvent;
	private Long idUtilisateur;

	@PostConstruct
	private void chargerListeEvenements() {
		listeEvenement = evenementService.rechercheToutEvenement();
		listeTypeEvenement = evenementService.rechercheToutTypesEvenements();
		listeUtilisateurs = evenementService.rechercheToutUtilisateurs();

	}

	public void eventAjoutEvenementDetecter(@Observes(notifyObserver = Reception.IF_EXISTS) final Evenement evenement) {
		chargerListeEvenements();
	}
	
	public String createEvenenement() {
		// On cherche le type d'evt par son nom
		TypeEvenement typeEvt = listeTypeEvenement.stream()
				.filter(value -> selectedTypeEvenement.contentEquals(value.getNomTypeEvenement()))
				.findFirst()
				.get();

		// On cherche l'utilisateur par son nom
		Utilisateur user = listeUtilisateurs.stream()		
				.filter(value-> selectedUtilisateur.contentEquals(value.getNomPrenomUtilisateurs()))
				.findFirst()
				.get();
		Long Evenementid = evenementService.creerEvenement(evenementCree, typeEvt, user);
		
		showMessageAndReloadData("Évènement créé avec succès");
		evenementCree= new Evenement();
		return WebUtils.redirectTo("ListEvents.xhtml");
		
		
		
		}


		
	
	public void modifierEvenement() {

		// On récupère la nouvelle valeur sélectionnée (par id)
		TypeEvenement typeEvt = listeTypeEvenement.stream()
				.filter(value -> selectedTypeEvenement.contentEquals(value.getNomTypeEvenement()))
				.findFirst()
				.get();
		Utilisateur utilNom = listeUtilisateurs.stream()
				.filter(value -> selectedUtilisateur.contentEquals(value.getNomPrenomUtilisateurs()))
				.findFirst()
				.get();

		// On met à jour la sélection
		evenementModifie.setUtilisateur(utilNom);
		evenementModifie.setTypeEvenement(typeEvt);

		// Modification
		Evenement modifier = evenementService.modifier(evenementModifie);
		if (modifier != null) {
			showMessageAndReloadData("Évènement modifié");
			evenementModifie=new Evenement();
		}
	}

	// modification du compte rendu seulement
	public void modifierCompteRenduEvenement() {
		if(evenementModifie.getCompteRendu() == null) {
			evenementModifie.setCompteRendu(compteRendu);
		}
		Evenement evtModifier = evenementService.modifier(evenementModifie);
		if (evtModifier  != null) {
			showMessageAndReloadData("Compte-Rendu mis à jour");
		}
	}

	public void chargerEvenement(Evenement selected) {
		evenementModifie = selected;
		selectedTypeEvenement = evenementModifie.getTypeEvenement().getNomTypeEvenement();
		selectedUtilisateur = evenementModifie.getUtilisateur().getNomPrenomUtilisateurs();
	}

	public void chargerEvenementCompteRendu(Evenement selected) {
		evenementModifie = selected;
		compteRendu = selected.getCompteRendu();
		if(compteRendu == null) {
			compteRendu = new CompteRendu();
		}
	}

	public void supprimerEvenement(Evenement selectedEvenement) {
		boolean suppression = evenementService.supprimerEvenement(selectedEvenement.getEvenementID());
		if (suppression) {
			showMessageAndReloadData("Évènement supprimé avec succès");
		}
	}

	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerListeEvenements();
	}

	public List<TypeEvenement> getListeTypeEvenement() {
		return listeTypeEvenement;
	}

	public void setListeTypeEvenement(List<TypeEvenement> listeTypeEvenement) {
		this.listeTypeEvenement = listeTypeEvenement;
	}

	public List<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}

	public void setListeUtilisateurs(List<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public List<Evenement> getListeEvenement() {
		return listeEvenement;
	}

	public void setListeEvenement(List<Evenement> listeEvenement) {
		this.listeEvenement = listeEvenement;
	}

	public Evenement getEvenementCree() {
		return evenementCree;
	}

	public void setEvenementCree(Evenement evenementCree) {
		this.evenementCree = evenementCree;
	}

	public Evenement getEvenementModifie() {
		return evenementModifie;
	}

	public void setEvenementModifie(Evenement evenementModifie) {
		this.evenementModifie = evenementModifie;
	}

	public Long getIdTypeEvent() {
		return idTypeEvent;
	}

	public void setIdTypeEvent(Long idTypeEvent) {
		this.idTypeEvent = idTypeEvent;
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public EvenementService getEvenementService() {
		return evenementService;
	}

	public void setEvenementService(EvenementService evenementService) {
		this.evenementService = evenementService;
	}

	public String getSelectedUtilisateur() {
		return selectedUtilisateur;
	}

	public void setSelectedUtilisateur(String selectedUtilisateur) {
		this.selectedUtilisateur = selectedUtilisateur;
	}

	public String getSelectedTypeEvenement() {
		return selectedTypeEvenement;
	}

	public void setSelectedTypeEvenement(String selectedTypeEvenement) {
		this.selectedTypeEvenement = selectedTypeEvenement;
	}

	public CompteRendu getCompteRendu() {
		return compteRendu;
	}
	public void setCompteRendu(CompteRendu compteRendu) {
		this.compteRendu = compteRendu;
	}
}
