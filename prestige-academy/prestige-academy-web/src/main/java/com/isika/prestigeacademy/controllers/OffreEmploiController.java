package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.OffreEmploi;
import com.isika.prestigeacademy.model.entities.PreferenceTypeContrat;
import com.isika.prestigeacademy.services.OffreEmploiService;

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
public class OffreEmploiController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4652901633928263854L;

	private static final Logger LOGGER = Logger.getLogger(TodolistController.class.getSimpleName());

	@Inject
	private OffreEmploiService offreEmploiService;

	private OffreEmploi offreEmploi = new OffreEmploi();

	private OffreEmploi offreModifie;

	private List<Entreprise> listeEntreprises;
	private List<OffreEmploi> listeOffreEmploi;

	private String selectedEntreprise;

	@PostConstruct
	private void chargerOffreEmploi() {
		listeOffreEmploi = offreEmploiService.rechercheAllOffreEmploi();
		listeEntreprises = offreEmploiService.rechercheEntreprise();
	}

	public void eventAjoutEvenementDetecter(@Observes(notifyObserver = Reception.IF_EXISTS) final OffreEmploi newJob) {

		chargerOffreEmploi();
	}

	public void addOffreEmploi() {

		Entreprise entreprise = listeEntreprises.stream()
				.filter(value -> selectedEntreprise.contentEquals(value.getNomEntreprise())).findFirst().get();

		Long offreEmploiID = offreEmploiService.addOffreEmploi(offreEmploi, entreprise);
		showMessageAndReloadData("Offre d'Emploi ajoutée avec succès");
		offreEmploi = new OffreEmploi();

	}

	public void supprimerJob(OffreEmploi selectedOffreEmploi) {
		boolean suppression = offreEmploiService.supprimerOffreEmploi(selectedOffreEmploi.getOffreEmploiID());
		if (suppression) {

			showMessageAndReloadData("Offre d'Emploi supprimée avec succès");
		}
	}

	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerOffreEmploi();

	}

	public void modifieOffreEmploi() {

		Entreprise entreprise = listeEntreprises.stream()
				.filter(value -> selectedEntreprise.contentEquals(value.getNomEntreprise())).findFirst().get();

		offreModifie.setEntreprise(entreprise);

		OffreEmploi modifier = offreEmploiService.modifier(offreModifie);

		if (modifier != null) {
			showMessageAndReloadData("Offre d'emploi modifiée avec succès");
		}

	}

	public void chargerOffreEmploi(OffreEmploi selected) {
		offreModifie = selected;
		selectedEntreprise = offreModifie.getEntreprise().getNomEntreprise();
		LOGGER.info("Selected Entreprise: " + selectedEntreprise);
	}

	public OffreEmploiService getOffreEmploiService() {
		return offreEmploiService;
	}

	public void setOffreEmploiService(OffreEmploiService offreEmploiService) {
		this.offreEmploiService = offreEmploiService;
	}

	public OffreEmploi getOffreEmploi() {
		return offreEmploi;
	}

	public void setOffreEmploi(OffreEmploi offreEmploi) {
		this.offreEmploi = offreEmploi;
	}

	public OffreEmploi getOffreModifie() {
		return offreModifie;
	}

	public void setOffreModifie(OffreEmploi offreModifie) {
		this.offreModifie = offreModifie;
	}

	public List<Entreprise> getListeEntreprises() {
		return listeEntreprises;
	}

	public void setListeEntreprises(List<Entreprise> listeEntreprises) {
		this.listeEntreprises = listeEntreprises;
	}

	public List<OffreEmploi> getListeOffreEmploi() {
		return listeOffreEmploi;
	}

	public void setListeOffreEmploi(List<OffreEmploi> listeOffreEmploi) {
		this.listeOffreEmploi = listeOffreEmploi;
	}

	public String getSelectedEntreprise() {
		return selectedEntreprise;
	}

	public void setSelectedEntreprise(String selectedEntreprise) {
		this.selectedEntreprise = selectedEntreprise;
	}

}
