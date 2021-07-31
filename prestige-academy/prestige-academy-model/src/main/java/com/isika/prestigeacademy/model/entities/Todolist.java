package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the todolist database table.
 * 
 */


@Entity
@NamedQueries({@NamedQuery(name="Todolist.findAll", query="SELECT t FROM Todolist t"),
		@NamedQuery(name = "Todolist.findEnCours", query = "SELECT t FROM Todolist t WHERE t.niveauProgress.progressionID= :progression")
})
public class Todolist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@SequenceGenerator(allocationSize = 500, initialValue = 20, name = "todolistID")
	private Long todolistID;

	private String contenuTache;

	private String dateLimiteTache;

	private String nomTache;

	private String personnesConcernees;

	//bi-directional many-to-one association to NiveauxPriorite
	@ManyToOne
	@JoinColumn(name = "niveauxPrioriteID")
	private NiveauxPriorite niveauxPriorite;
	
	@ManyToOne
	@JoinColumn(name="progressionID")
	private ProgressionToDo niveauProgress ;

	public Todolist() {
	}

	public Long getTodolistID() {
		return this.todolistID;
	}

	public void setTodolistID(Long todolistID) {
		this.todolistID = todolistID;
	}

	public String getContenuTache() {
		return this.contenuTache;
	}

	public void setContenuTache(String contenuTache) {
		this.contenuTache = contenuTache;
	}

	public String getDateLimiteTache() {
		return this.dateLimiteTache;
	}

	public void setDateLimiteTache(String dateLimiteTache) {
		this.dateLimiteTache = dateLimiteTache;
	}

	public String getNomTache() {
		return this.nomTache;
	}

	public void setNomTache(String nomTache) {
		this.nomTache = nomTache;
	}

	public String getPersonnesConcernees() {
		return this.personnesConcernees;
	}

	public void setPersonnesConcernees(String personnesConcernees) {
		this.personnesConcernees = personnesConcernees;
	}

	public NiveauxPriorite getNiveauxPriorite() {
		return this.niveauxPriorite;
	}

	public void setNiveauxPriorite(NiveauxPriorite niveauxPriorite) {
		this.niveauxPriorite = niveauxPriorite;
	}

	public ProgressionToDo getNiveauProgress() {
		return niveauProgress;
	}

	public void setNiveauProgress(ProgressionToDo niveauProgress) {
		this.niveauProgress = niveauProgress;
	}

}