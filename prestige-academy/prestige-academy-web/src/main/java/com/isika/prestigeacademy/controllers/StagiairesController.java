package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.EmailRecu;
import com.isika.prestigeacademy.model.entities.ProcessusRecrutement;
import com.isika.prestigeacademy.model.entities.Stagiaire;
import com.isika.prestigeacademy.model.entities.StatutFinancement;
import com.isika.prestigeacademy.model.entities.StatutRecrutement;
import com.isika.prestigeacademy.services.EmailRecuService;
import com.isika.prestigeacademy.services.StagiairesService;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class StagiairesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(StagiairesController.class.getSimpleName());

	@Inject
	private StagiairesService stagiairesService;

	@Inject
	private EmailRecuService emailRecuService;


	private Stagiaire stagiaireModifie;
	private List<Stagiaire> listeSommeliers;
	private List<Stagiaire> listeChefsRang;
	private List<Stagiaire> listeMaitresHotel;
	private List<Stagiaire> listeGerantshotel;
	private List<StatutRecrutement> listeStatutRecru;
	private List<StatutFinancement> listeStatutsFinancement;
	private List<ProcessusRecrutement> listeProcessRecru;
	private List<EmailRecu> listeMails;

	private Stagiaire stagiaireSelected;
	private Stagiaire stagiaireNew;
	private String statutRecrutementSelected;
	private String statutFinancementSelected;

	@PostConstruct
	public void chargerListesPromo() {
		listeSommeliers = stagiairesService.rechercheTousLesSommeliers();
		listeChefsRang = stagiairesService.rechercheTousLesChefsRang();
		listeMaitresHotel = stagiairesService.rechercheTousLesMaitresHotel();
		listeGerantshotel = stagiairesService.rechercheTousLesGerantsHotel();
		listeStatutRecru = stagiairesService.rechercheStatutsRecrutement();
		listeStatutsFinancement = stagiairesService.rechercheStatutsFinancement();
		stagiaireNew= new Stagiaire();
		stagiaireSelected= new Stagiaire();

	}

	 public void chargerLeStagiaire(SelectEvent event){
	        stagiaireSelected=(Stagiaire) event.getObject();
	        stagiaireNew= stagiaireSelected;
	        listeMails = emailRecuService.rechercheLesMailsParDestinataire(stagiaireNew.getStagiaireID());
	        listeProcessRecru = stagiairesService.rechercheLesRecrutements(stagiaireNew.getStagiaireID());
	    }
	    



	public void modifierStagiaire() {

		// On récupère la nouvelle valeur sélectionnée (par id)
		StatutFinancement statFi = listeStatutsFinancement.stream()
				.filter(value -> statutFinancementSelected.equals(value.getNomFinancement())).findFirst().get();

		// On récupère la nouvelle valeur sélectionnée (par id)
		StatutRecrutement statRecru = listeStatutRecru.stream()
				.filter(value -> statutRecrutementSelected.equals(value.getNomStatutRecrutement())).findFirst().get();

		// On met à jour la sélection
		stagiaireSelected.setStatutFinancement(statFi);
		stagiaireSelected.setStatutRecrutement(statRecru);

		// Modification
		Stagiaire modifier = stagiairesService.modifier(stagiaireSelected);
		if (modifier != null) {
			showMessageAndReloadData("Stagiaire modifié avec succès");
		}
	}

	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerListesPromo();
	}

	

	public StagiairesService getStagiairesService() {
		return stagiairesService;
	}

	public void setStagiairesService(StagiairesService stagiairesService) {
		this.stagiairesService = stagiairesService;
	}

	public List<Stagiaire> getListeSommeliers() {
		return listeSommeliers;
	}

	public void setListeSommeliers(List<Stagiaire> listeSommeliers) {
		this.listeSommeliers = listeSommeliers;
	}

	public List<Stagiaire> getListeChefsRang() {
		return listeChefsRang;
	}

	public void setListeChefsRang(List<Stagiaire> listeChefsRang) {
		this.listeChefsRang = listeChefsRang;
	}

	public List<Stagiaire> getListeMaitresHotel() {
		return listeMaitresHotel;
	}

	public void setListeMaitresHotel(List<Stagiaire> listeMaitresHotel) {
		this.listeMaitresHotel = listeMaitresHotel;
	}

	public List<Stagiaire> getListeGerantshotel() {
		return listeGerantshotel;
	}

	public void setListeGerantshotel(List<Stagiaire> listeGerantshotel) {
		this.listeGerantshotel = listeGerantshotel;
	}

	public String getStatutRecrutementSelected() {
		return statutRecrutementSelected;
	}

	public void setStatutRecrutementSelected(String statutRecrutementSelected) {
		this.statutRecrutementSelected = statutRecrutementSelected;
	}

	public String getStatutFinancementSelected() {
		return statutFinancementSelected;
	}

	public void setStatutFinancementSelected(String statutFinancementSelected) {
		this.statutFinancementSelected = statutFinancementSelected;
	}

	public List<StatutRecrutement> getListeStatutRecru() {
		return listeStatutRecru;
	}

	public void setListeStatutRecru(List<StatutRecrutement> listeStatutRecru) {
		this.listeStatutRecru = listeStatutRecru;
	}

	public List<StatutFinancement> getListeStatutsFinancement() {
		return listeStatutsFinancement;
	}

	public void setListeStatutsFinancement(List<StatutFinancement> listeStatutsFinancement) {
		this.listeStatutsFinancement = listeStatutsFinancement;
	}

	public Stagiaire getStagiaireModifie() {
		return stagiaireModifie;
	}

	public void setStagiaireModifie(Stagiaire stagiaireModifie) {
		this.stagiaireModifie = stagiaireModifie;
	}

	public List<EmailRecu> getListeMails() {
		return listeMails;
	}

	public void setListeMails(List<EmailRecu> listeMails) {
		this.listeMails = listeMails;
	}

	public Stagiaire getStagiaireSelected() {
		return stagiaireSelected;
	}

	public void setStagiaireSelected(Stagiaire stagiaireSelected) {
		this.stagiaireSelected = stagiaireSelected;
	}

	public Stagiaire getStagiaireNew() {
		return stagiaireNew;
	}

	public void setStagiaireNew(Stagiaire stagiaireNew) {
		this.stagiaireNew = stagiaireNew;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public List<ProcessusRecrutement> getListeProcessRecru() {
		return listeProcessRecru;
	}

	public void setListeProcessRecru(List<ProcessusRecrutement> listeProcessRecru) {
		this.listeProcessRecru = listeProcessRecru;
	}
	

}
