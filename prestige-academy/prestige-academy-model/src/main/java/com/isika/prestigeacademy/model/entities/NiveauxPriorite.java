package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the niveaux_priorite database table.
 * 
 */
@Entity
@NamedQuery(name="NiveauxPriorite.findAll", query="SELECT n FROM NiveauxPriorite n")
public class NiveauxPriorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long niveauxPrioriteID;

	private String nomPriorite;
	private String color;
	

	//bi-directional many-to-one association to Todolist
	@OneToMany(mappedBy="niveauxPriorite")
	private List<Todolist> todolists;

	public NiveauxPriorite() {
	}

	public Long getNiveauxPrioriteID() {
		return this.niveauxPrioriteID;
	}

	public void setNiveauxPrioriteID(Long niveauxPrioriteID) {
		this.niveauxPrioriteID = niveauxPrioriteID;
	}

	public String getNomPriorite() {
		return this.nomPriorite;
	}

	public void setNomPriorite(String nomPriorite) {
		this.nomPriorite = nomPriorite;
	}

	public List<Todolist> getTodolists() {
		return this.todolists;
	}

	public void setTodolists(List<Todolist> todolists) {
		this.todolists = todolists;
	}

	public Todolist addTodolist(Todolist todolist) {
		getTodolists().add(todolist);
		todolist.setNiveauxPriorite(this);

		return todolist;
	}

	public Todolist removeTodolist(Todolist todolist) {
		getTodolists().remove(todolist);
		todolist.setNiveauxPriorite(null);

		return todolist;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}