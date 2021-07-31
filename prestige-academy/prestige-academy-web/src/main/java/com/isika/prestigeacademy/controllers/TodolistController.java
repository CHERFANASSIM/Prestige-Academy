package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.NiveauxPriorite;
import com.isika.prestigeacademy.model.entities.ProgressionToDo;
import com.isika.prestigeacademy.model.entities.Todolist;
import com.isika.prestigeacademy.services.ToDoListService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.DragDropEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class TodolistController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4052689073598630789L;

	private static final Logger LOGGER = Logger.getLogger(TodolistController.class.getSimpleName());

	@Inject
	private ToDoListService todoService;

	// Création d'un nv todo
	private Todolist todolist = new Todolist();
	// private Todolist selectedtodolist = new Todolist();

	// Mofification
	private Todolist todoModifie;

	// Listes
	private List<NiveauxPriorite> listeNiveauPriorite;
	private List<ProgressionToDo> listeProgressionToDo;
	private List<Todolist> listeToDo;
	private List<Todolist> listeAfaire;
	private List<Todolist> listeEnCours;
	private List<Todolist> listeTermine;
	private List<Todolist> droppedTache;
	private List<Todolist> droppedTache2;
	private String colorRouge;
	private String colorOrange;
	private String colorVert;
	private String color;

	private String selectedNiveauPriorite;
	private String selectedProgression;
	private Todolist tacheSelected;

	@PostConstruct
	private void chargerTodolist() {
		listeToDo = todoService.rechercheAllTodolist();
		listeNiveauPriorite = todoService.rechercheNiveauxPriorite();
		listeProgressionToDo = todoService.rechercheProgressionToDo();
		listeAfaire = todoService.rechercheTachesAfaire();
		listeEnCours = todoService.rechercheTachesEnCours();
		listeTermine = todoService.rechercheTachesTermine();
		droppedTache = new ArrayList<>();
		droppedTache2 = new ArrayList<>();
		colorRouge="rouge";
				colorOrange="orange";
		color="vert";
		
	}

	
	public void couleurs() {
	if (tacheSelected.getNiveauxPriorite().getNiveauxPrioriteID()==1) {
		color= colorRouge;
	}

	}
	
	public void eventAjoutEvenementDetecter(@Observes(notifyObserver = Reception.IF_EXISTS) final Todolist newTodo) {
		chargerTodolist();
	}

	public void addNewTodo() {

		// on cherche un niveau de priorité par son nom

		NiveauxPriorite nivPrio = listeNiveauPriorite.stream()
				.filter(value -> selectedNiveauPriorite.contentEquals(value.getNomPriorite())).findFirst().get();

		ProgressionToDo progToDo = listeProgressionToDo.stream()
				.filter(value -> selectedProgression.contentEquals(value.getNiveauProgression())).findFirst().get();

		Long todolistID = todoService.addNewToDo(todolist, nivPrio, progToDo);
		showMessageAndReloadData("Nouvelle Tâche enregistrée");
		todolist = new Todolist();
		PrimeFaces.current().resetInputs("form:dlg1");
	}
	

	public void supprimerToDo(Todolist selectedtodolist) {
		boolean suppression = todoService.supprimerTodo(selectedtodolist.getTodolistID());
		if (suppression) {

			showMessageAndReloadData("Tâche supprimée avec succès");
		}
	}

	public void modifierTodo() {

		// recuperation de la nouvelle valeur de todo
		NiveauxPriorite nivPrio = listeNiveauPriorite.stream()
				.filter(value -> selectedNiveauPriorite.contentEquals(value.getNomPriorite())).findFirst().get();

		// pareil pour niv progression
		ProgressionToDo progToDo = listeProgressionToDo.stream()
				.filter(value -> selectedProgression.contentEquals(value.getNiveauProgression())).findFirst().get();
		// On met à jour la sélection
		todoModifie.setNiveauxPriorite(nivPrio);
		todoModifie.setNiveauProgress(progToDo);
		
		// Modification
		Todolist modifier = todoService.modifier(todoModifie);

		if (modifier != null) {
			showMessageAndReloadData("Tâche modifée");
		}
	}

	public void chargerTodo(Todolist selected) {
		todoModifie = selected;
		selectedNiveauPriorite = todoModifie.getNiveauxPriorite().getNomPriorite();
		selectedProgression = todoModifie.getNiveauProgress().getNiveauProgression();
	}

	private void showMessageAndReloadData(String message) {
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
		FacesContext.getCurrentInstance().addMessage(null, m);
		chargerTodolist();

	}

	public void tacheDrop(DragDropEvent ddEvent) {
	tacheSelected = (Todolist) ddEvent.getData();
		enCours(tacheSelected);
		droppedTache.add(tacheSelected);
		listeToDo.remove(tacheSelected);
	}

	public void tacheDrop2(DragDropEvent ddEvent) {
		Todolist tacheSelected = (Todolist) ddEvent.getData();
		termine(tacheSelected);
		droppedTache2.add(tacheSelected);
		droppedTache.remove(tacheSelected);
	}

	public void enCours(Todolist tache) {
		String enCoursNom ="En cours";
		// On récupère la nouvelle valeur sélectionnée (par id)
		ProgressionToDo enCours = listeProgressionToDo.stream()
				.filter(value -> enCoursNom.equals(value.getNiveauProgression())).findFirst().get();
		enCours.getProgressionID();
		tache.setNiveauProgress(enCours);

		// Modification
		Todolist modifier = todoService.passerEnCours(tache);
		if (modifier != null) {
			showMessageAndReloadData("Tâche modifiée avec succès");
		}
	}
	
	public void termine(Todolist tache) {
		String termineNom ="Terminé";
		// On récupère la nouvelle valeur sélectionnée (par id)
		ProgressionToDo termine = listeProgressionToDo.stream()
				.filter(value -> termineNom.equals(value.getNiveauProgression())).findFirst().get();
		termine.getProgressionID();
		tache.setNiveauProgress(termine);

		// Modification
		Todolist modifier2 = todoService.passerEnCours(tache);
		if (modifier2 != null) {
			showMessageAndReloadData("Tâche modifiée avec succès");
		}
	}

	public Todolist getTodolist() {
		return todolist;
	}

	public void setTodolist(Todolist todolist) {
		this.todolist = todolist;
	}

	public Todolist getTodoModifie() {
		return todoModifie;
	}

	public void setTodoModifie(Todolist todoModifie) {
		this.todoModifie = todoModifie;
	}

	public String getSelectedNiveauPriorite() {
		return selectedNiveauPriorite;
	}

	public void setSelectedNiveauPriorite(String selectedNiveauPriorite) {
		this.selectedNiveauPriorite = selectedNiveauPriorite;
	}

	public List<Todolist> getListeToDo() {
		return listeToDo;
	}

	public void setListeToDo(List<Todolist> listeToDo) {
		this.listeToDo = listeToDo;
	}

	public List<NiveauxPriorite> getListeNiveauPriorite() {
		return listeNiveauPriorite;
	}

	public void setListeNiveauPriorite(List<NiveauxPriorite> listeNiveauPriorite) {
		this.listeNiveauPriorite = listeNiveauPriorite;
	}

	public ToDoListService getTodoService() {
		return todoService;
	}

	public void setTodoService(ToDoListService todoService) {
		this.todoService = todoService;
	}

	public List<ProgressionToDo> getListeProgressionToDo() {
		return listeProgressionToDo;
	}

	public void setListeProgressionToDo(List<ProgressionToDo> listeProgressionToDo) {
		this.listeProgressionToDo = listeProgressionToDo;
	}

	public String getSelectedProgression() {
		return selectedProgression;
	}

	public void setSelectedProgression(String selectedProgression) {
		this.selectedProgression = selectedProgression;
	}

	public List<Todolist> getListeEnCours() {
		return listeEnCours;
	}

	public void setListeEnCours(List<Todolist> listeEnCours) {
		this.listeEnCours = listeEnCours;
	}

	public List<Todolist> getDroppedTache() {
		return droppedTache;
	}

	public void setDroppedTache(List<Todolist> droppedTache) {
		this.droppedTache = droppedTache;
	}

	public List<Todolist> getDroppedTache2() {
		return droppedTache2;
	}

	public void setDroppedTache2(List<Todolist> droppedTache2) {
		this.droppedTache2 = droppedTache2;
	}

	public Todolist getTacheSelected() {
		return tacheSelected;
	}

	public void setTacheSelected(Todolist tacheSelected) {
		this.tacheSelected = tacheSelected;
	}

	
	public List<Todolist> getListeAfaire() {
		return listeAfaire;
	}

	public void setListeAfaire(List<Todolist> listeAfaire) {
		this.listeAfaire = listeAfaire;
	}

	public List<Todolist> getListeTermine() {
		return listeTermine;
	}

	public void setListeTermine(List<Todolist> listeTermine) {
		this.listeTermine = listeTermine;
	}

	public String getColorRouge() {
		return colorRouge;
	}

	public void setColorRouge(String colorRouge) {
		this.colorRouge = colorRouge;
	}

	public String getColorOrange() {
		return colorOrange;
	}

	public void setColorOrange(String colorOrange) {
		this.colorOrange = colorOrange;
	}

	public String getColorVert() {
		return colorVert;
	}

	public void setColorVert(String colorVert) {
		this.colorVert = colorVert;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

}
