package com.isika.prestigeacademy.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name="ProgressionToDo.findAll", query="SELECT p FROM ProgressionToDo p")
public class ProgressionToDo implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long progressionID;

	private String niveauProgression;

	@OneToMany(mappedBy="niveauProgress")
	private List<Todolist> todolistP ;


	public Long getProgressionID() {
		return progressionID;
	}

	public void setProgressionID(Long progressionID) {
		this.progressionID = progressionID;
	}

	public String getNiveauProgression() {
		return niveauProgression;
	}

	public void setNiveauProgression(String niveauProgression) {
		this.niveauProgression = niveauProgression;
	}


	public List<Todolist> getTodolistP() {
		return todolistP;
	}

	public void setTodolistP(List<Todolist> todolistP) {
		this.todolistP = todolistP;
	}

	public Todolist addTodolist(Todolist todolist) {
		getTodolistP().add(todolist);
		todolist.setNiveauProgress(this);

		return todolist;
	}

	public Todolist removeTodolist(Todolist todolist) {
		getTodolistP().remove(todolist);
		todolist.setNiveauProgress(null);

		return todolist;
	}
}
