package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.NiveauxPriorite;
import com.isika.prestigeacademy.model.entities.ProgressionToDo;
import com.isika.prestigeacademy.model.entities.Todolist;
import com.isika.prestigeacademy.repositories.ToDoListRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ToDoListService implements Serializable {


	private static final long serialVersionUID = -258056990874443509L;
	
	@EJB
	private ToDoListRepository todolistRepo ;


	public Long addNewToDo (Todolist todo, NiveauxPriorite  selectedNiveauPriorite, ProgressionToDo selectedProgression) {

		selectedNiveauPriorite.addTodolist(todo);
		selectedProgression.addTodolist(todo);
		return todolistRepo.addTodo(todo);
	}

	public List<Todolist> rechercheAllTodolist(){
		return	todolistRepo.findAll();

	}

	public List<NiveauxPriorite> rechercheNiveauxPriorite(){

		return todolistRepo.findAllNiveauPriorite();
	}
	public List<ProgressionToDo> rechercheProgressionToDo(){

		return todolistRepo.findAllProgressionToDO();
	}

	public ToDoListRepository getTodolistRepo() {
		return todolistRepo;
	}

	public void setTodolistRepo(ToDoListRepository todolistRepo) {
		this.todolistRepo = todolistRepo;
	}

	public boolean supprimerTodo(Long long1) {
		Todolist todoById = todolistRepo.findById(long1);
		if (todoById != null) {
			return todolistRepo.remove(todoById);
		}
		return false;
	}

	public Todolist modifier(Todolist SelectedTodo) {
		return todolistRepo.update(SelectedTodo);
	}

	public List<Todolist> rechercheTachesEnCours() {
		long enCours;
		enCours=2;
		return todolistRepo.rechercheTaches(enCours);
	}
	
	public List<Todolist> rechercheTachesTermine() {
		long termine;
		termine=3;
		return todolistRepo.rechercheTaches(termine);
	}

	public List<Todolist> rechercheTachesAfaire() {
		long aFaire;
		aFaire=1;
		return todolistRepo.rechercheTaches(aFaire);
	}

	public Todolist passerEnCours(Todolist tacheSelected) {
		return todolistRepo.passerEnCours(tacheSelected);
	}

	




}
