package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.TypeProspect;
import com.isika.prestigeacademy.services.ProspectService;

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
public class ProspectControlleur implements Serializable {
	private static final long serialVersionUID = 40200552022598871L;

	private static final Logger LOGGER = Logger.getLogger(EvenementControlleur.class.getSimpleName());	
	
	@Inject
	private ProspectService prospectService;

	private Entreprise entreprise = new Entreprise();
	private Entreprise entrepriseProspectModifiee;
	private String selectedStatut;
	



	private List<Entreprise> listeProspects;
	private List<Statut> listeStatuts;
	private List<TypeProspect> listetypeProspect;

	private boolean afficherDialogue;

	@PostConstruct
	private void chargerListesDesProspects() {
		listeProspects = prospectService.rechercherLesProspects();
		setListeStatuts(prospectService.rechercherStatuts());
		setListetypeProspect(prospectService.rechercheToutTypeDeProspect());
	}

	public void eventAjoutProspectDetecter(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Entreprise entreprise) {
		chargerListesDesProspects();
	}

	public void chargerEntreprise(Entreprise entrepriseProspect) {
		entreprise = entrepriseProspect;

	}

	public void createProspect() {

		Long ProspectId = prospectService.creerProspect(entreprise);
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Prospect a été enregistré",
				"Id [" + ProspectId + "]");
		FacesContext.getCurrentInstance().addMessage(null, m);

	}
	
//	public void AutocompletionNomProspect() {	
//		Statut statutNom = listeStatuts.stream()
//				.filter(value -> selectedStatut.equals(value.getNomStatut()))
//				.findAny()
//				.get();
//			prospectAuto.setStatut(statutNom);	
//			
//			selectedStatut = prospectAuto.getStatut().getNomStatut();
//			LOGGER.info("Autocompletion de prospect : " + selectedStatut);

	//}
	


//	  public List<Entreprise> prospectRecherche(String prospectS) {
//        List<Entreprise> pro = new ArrayList<>();
//		      //using data factory for getting suggestions
//		      for (String s : new Entreprise().getStatut().getNomStatut()) {
//		          if (s.toLowerCase().startsWith(pro.toLowerCase())) {
//        	  prospectS.add(s);
//		          }
//		      }
//	      return prospectS;
//		  }

	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerEntreprise(entreprise);
	}



	public void show() {
		afficherDialogue = true;
	}

	public void hide() {
		afficherDialogue = false;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<Entreprise> getListeProspects() {
		return listeProspects;
	}

	public void setListeProspects(List<Entreprise> listeProspects) {
		this.listeProspects = listeProspects;
	}
	
	public boolean isAfficherDialogue() {
		return afficherDialogue;
	}

	public void setAfficherDialogue(boolean afficherDialogue) {
		this.afficherDialogue = afficherDialogue;
	}

	public List<Statut> getListeStatuts() {
		return listeStatuts;
	}

	public void setListeStatuts(List<Statut> listeStatuts) {
		this.listeStatuts = listeStatuts;
	}

	public List<TypeProspect> getListetypeProspect() {
		return listetypeProspect;
	}

	public void setListetypeProspect(List<TypeProspect> listetypeProspect) {
		this.listetypeProspect = listetypeProspect;
	}

	public Entreprise getEntrepriseProspectModifiee() {
		return entrepriseProspectModifiee;
	}

	public void setEntrepriseProspectModifiee(Entreprise entrepriseProspectModifiee) {
		this.entrepriseProspectModifiee = entrepriseProspectModifiee;
	}

	public String getSelectedStatut() {
		return selectedStatut;
	}

	public void setSelectedStatut(String selectedStatut) {
		this.selectedStatut = selectedStatut;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
