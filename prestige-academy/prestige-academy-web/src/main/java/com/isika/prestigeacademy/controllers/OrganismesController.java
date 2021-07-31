package com.isika.prestigeacademy.controllers;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.Part;

import com.isika.prestigeacademy.model.entities.OrganismeFinancement;
import com.isika.prestigeacademy.model.entities.Statut;
import com.isika.prestigeacademy.model.entities.TypeFinancement;
import com.isika.prestigeacademy.model.entities.TypeProspect;
import com.isika.prestigeacademy.services.OrganismesService;
import com.itextpdf.io.IOException;

@Named
@ApplicationScoped
public class OrganismesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(OrganismesController.class.getSimpleName());

	@Inject
	private OrganismesService organismeService;

	private OrganismeFinancement organismeFinancementCree = new OrganismeFinancement();

	private OrganismeFinancement organismeFinancementModifie;

	private Part uploadedFile;
	private String folder = "D://WorspacesEclipse/GitRepository/prestige-academy/prestige-academy/prestige-academy-web/src/main/webapp/images";
	private String folderPath;

	private List<OrganismeFinancement> listeOrganismes;
	private List<TypeFinancement> listeTypeFinancement;
	private List<TypeProspect> listeTypeProspect;
	private List<Statut> listeStatut;

	private String selectedTypeFinancement;
	private String selectedTypeProspect;
	private String selectedStatut;

	@PostConstruct
	private void chargerListeOrganismes() {

		listeOrganismes = organismeService.rechercherTousLesOrganismes();
		listeTypeFinancement = organismeService.rechercherTousLesTypeFinancement();
		listeTypeProspect = organismeService.rechercherTousLesTypeProspect();
		listeStatut = organismeService.rechercherTousLesStatut();

	}

	public void eventAjoutOrganismeFinancementDetecter(
			@Observes(notifyObserver = Reception.IF_EXISTS) final OrganismeFinancement organismeFinancement) {
		chargerListeOrganismes();
	}

	public void createOrganisme() {

		TypeFinancement typeFin = listeTypeFinancement.stream()
				.filter(Value -> selectedTypeFinancement.equals(Value.getNomTypeFinancement())).findFirst().get();
		TypeProspect typePros = listeTypeProspect.stream()
				.filter(value -> selectedTypeProspect.equals(value.getNomProspect())).findFirst().get();
		Statut typeStat = listeStatut.stream().filter(value -> selectedStatut.equals(value.getNomStatut())).findFirst()
				.get();
		this.organismeFinancementCree.setLogoUrlOrga(folderPath);
		LOGGER.info(organismeFinancementCree.getLogoUrlOrga());
		long organismeFinancementID = organismeService.creerOrganismeFinancement(organismeFinancementCree, typeFin,
				typePros, typeStat);

		showMessageAndReloadData("Organisme ajouté avec succès");

	}

	public void saveFile() throws java.io.IOException, MessagingException {
		LocalDateTime date = LocalDateTime.now();
		try (InputStream input = uploadedFile.getInputStream()) {
			String fileName = uploadedFile.getFileName();

			Files.copy(input, new File(folder, fileName).toPath());
			folderPath = "images/" + fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void modifierOrganisme() {
		LOGGER.info("Nous somme dans la méthode modifié");
		TypeFinancement typeFin = listeTypeFinancement.stream()
				.filter(value -> selectedTypeFinancement.contentEquals(value.getNomTypeFinancement())).findFirst()
				.get();

		TypeProspect typePros = listeTypeProspect.stream()
				.filter(value -> selectedTypeProspect.equals(value.getNomProspect())).findFirst().get();

		Statut typeStat = listeStatut.stream().filter(value -> selectedStatut.equals(value.getNomStatut())).findFirst()
				.get();

		// On met à jour la selection
		organismeFinancementModifie.setTypeFinancement(typeFin);
		organismeFinancementModifie.setTypeProspect(typePros);
		organismeFinancementModifie.setStatut(typeStat);

		// modification
		OrganismeFinancement orgModifie = organismeService.modifier(organismeFinancementModifie);
		if (orgModifie != null) {
			showMessageAndReloadData("Organisme modifé avec succès");
		}
	}

	public void chargerOrganisme(OrganismeFinancement organismeFinancementSelected) {
		organismeFinancementModifie = organismeFinancementSelected;
		selectedTypeFinancement = organismeFinancementModifie.getTypeFinancement().getNomTypeFinancement();
		selectedTypeProspect = organismeFinancementModifie.getTypeProspect().getNomProspect();
		selectedStatut = organismeFinancementModifie.getStatut().getNomStatut();

	}

	public void supprimerOrganisme(OrganismeFinancement selectedOrganismeFinancement) {
		boolean suppression = organismeService
				.supprimerOrganisme(selectedOrganismeFinancement.getOrganismeFinancementID());
		if (suppression) {
			showMessageAndReloadData("Organisme supprimé avec succès");
		}
	}

	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerListeOrganismes();
	}

	public OrganismesService getOrganismeService() {
		return organismeService;
	}

	public void setOrganismeService(OrganismesService organismeService) {
		this.organismeService = organismeService;
	}

	public OrganismeFinancement getOrganismeFinancementCree() {
		return organismeFinancementCree;
	}

	public void setOrganismeFinancementCree(OrganismeFinancement organismeFinancementCree) {
		this.organismeFinancementCree = organismeFinancementCree;
	}

	public OrganismeFinancement getOrganismeFinancementModifie() {
		return organismeFinancementModifie;
	}

	public void setOrganismeFinancementModifie(OrganismeFinancement organismeFinancementModifie) {
		this.organismeFinancementModifie = organismeFinancementModifie;
	}

	public List<OrganismeFinancement> getListeOrganismes() {
		return listeOrganismes;
	}

	public void setListeOrganismes(List<OrganismeFinancement> listeOrganismes) {
		this.listeOrganismes = listeOrganismes;
	}

	public List<TypeFinancement> getListeTypeFinancement() {
		return listeTypeFinancement;
	}

	public void setListeTypeFinancement(List<TypeFinancement> listeTypeFinancement) {
		this.listeTypeFinancement = listeTypeFinancement;
	}

	public List<TypeProspect> getListeTypeProspect() {
		return listeTypeProspect;
	}

	public void setListeTypeProspect(List<TypeProspect> listeTypeProspect) {
		this.listeTypeProspect = listeTypeProspect;
	}

	public List<Statut> getListeStatut() {
		return listeStatut;
	}

	public void setListeStatut(List<Statut> listeStatut) {
		this.listeStatut = listeStatut;
	}

	public String getSelectedTypeFinancement() {
		return selectedTypeFinancement;
	}

	public void setSelectedTypeFinancement(String selectedTypeFinancement) {
		this.selectedTypeFinancement = selectedTypeFinancement;
	}

	public String getSelectedTypeProspect() {
		return selectedTypeProspect;
	}

	public void setSelectedTypeProspect(String selectedTypeProspect) {
		this.selectedTypeProspect = selectedTypeProspect;
	}

	public String getSelectedStatut() {
		return selectedStatut;
	}

	public void setSelectedStatut(String selectedStatut) {
		this.selectedStatut = selectedStatut;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

}
