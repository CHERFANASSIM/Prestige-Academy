package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.DossierPaiement;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.StatutFinancement;
import com.isika.prestigeacademy.services.FinancementService;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FinancementControlleur implements Serializable {

	private static final long serialVersionUID = 40200552022598871L;
	
	@Inject
	private FinancementService financementService;
	
	private Stagiaire stagiaireFinancementModif;
	
	private List<Stagiaire> listeStagiaires;
	private List<DossierPaiement> listeDossiersPaiements;
	private List<StatutFinancement> listeStatutsFinancements;
	
	private String selectedStagiaire;
	private String selectedDossierPaiement;
	private String selectedStatutFinancement;


	@PostConstruct
	private void chargerListeFinancementStagiaires() {	
		listeStagiaires = financementService.rechercheToutStagiaire();
		listeDossiersPaiements= financementService.rechercheDossiersFinancements();
		listeStatutsFinancements=financementService.rechercheStatutsFinancements();
		
	}

	public void updateFinancement() {
		
		System.out.println("methode filtre");
		DossierPaiement dossierP = listeDossiersPaiements.stream()
			.filter(value -> selectedDossierPaiement.contentEquals(value.getCommentaire())).findFirst().get();
		System.out.println("dossier");
			
		StatutFinancement nomStatut = listeStatutsFinancements.stream()
				.filter(value -> selectedStatutFinancement.contentEquals(value.getNomFinancement())).findFirst().get();
		System.out.println("statut");

	
		stagiaireFinancementModif.setDossierPaiement(dossierP);
		stagiaireFinancementModif.setStatutFinancement(nomStatut);
		
		Stagiaire update = financementService.modifier(stagiaireFinancementModif);
		if (update != null) {
			showMessageAndReloadData("Financement modifié");
		}
	}
		
	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerListeFinancementStagiaires();
	}
  
	public List<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}


	public void setListeStagiaires(List<Stagiaire> listeStagiaires) {
		this.listeStagiaires = listeStagiaires;
	}


	public List<DossierPaiement> getListeDossiersPaiements() {
		return listeDossiersPaiements;
	}


	public void setListeDossiersPaiements(List<DossierPaiement> listeDossiersPaiements) {
		this.listeDossiersPaiements = listeDossiersPaiements;
	}


	public List<StatutFinancement> getListeStatutsFinancements() {
		return listeStatutsFinancements;
	}


	public void setListeStatutsFinancements(List<StatutFinancement> listeStatutsFinancements) {
		this.listeStatutsFinancements = listeStatutsFinancements;
	}



	public String getSelectedStagiaire() {
		return selectedStagiaire;
	}


	public void setSelectedStagiaire(String selectedStagiaire) {
		this.selectedStagiaire = selectedStagiaire;
	}


	public String getSelectedDossierPaiement() {
		return selectedDossierPaiement;
	}


	public void setSelectedDossierPaiement(String selectedDossierPaiement) {
		this.selectedDossierPaiement = selectedDossierPaiement;
	}


	public String getSelectedStatutFinancement() {
		return selectedStatutFinancement;
	}


	public void setSelectedStatutFinancement(String selectedStatutFinancement) {
		this.selectedStatutFinancement = selectedStatutFinancement;
	}


	public Stagiaire getStagiaireFinancementModif() {
		return stagiaireFinancementModif;
	}

	public void setStagiaireFinancementModif(Stagiaire stagiaireFinancementModif) {
		this.stagiaireFinancementModif = stagiaireFinancementModif;
	}

	// Primefaces 
	public void onRowEdit(RowEditEvent event) {

		stagiaireFinancementModif = (Stagiaire) event.getObject();
		updateFinancement();
		FacesMessage msg = new FacesMessage("La modification a été enregistrée", "" + ((Stagiaire) event.getObject()).getStagiaireID());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

		
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ""+ ((Stagiaire)event.getObject()).getStagiaireID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
   
    }

