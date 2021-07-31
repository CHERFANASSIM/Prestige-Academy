package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the statut_processus_recrutement database table.
 * 
 */
@Entity
@NamedQuery(name = "StatutProcessusRecrutement.findAll", query = "SELECT s FROM StatutProcessusRecrutement s")
public class StatutProcessusRecrutement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statutProcessusRecrutementID;

	private String nomStatutProcessus;


	//bi-directional many-to-one association to Entreprise
	@OneToMany(mappedBy = "processusRecrutementID")
	private List<ProcessusRecrutement> processusRecrutements;

	public StatutProcessusRecrutement() {
	}

	public Long getStatutProcessusRecrutementID() {
		return this.statutProcessusRecrutementID;
	}

	public void setStatutProcessusRecrutementID(Long statutProcessusRecrutementID) {
		this.statutProcessusRecrutementID = statutProcessusRecrutementID;
	}


	public String getNomStatutProcessus() {
		return this.nomStatutProcessus;
	}

	public void setNomStatutProcessus(String nomStatutProcessus) {
		this.nomStatutProcessus = nomStatutProcessus;
	}

	
	public List<ProcessusRecrutement> getProcessusRecrutements() {
		return processusRecrutements;
	}

	public void setProcessusRecrutements(List<ProcessusRecrutement> processusRecrutements) {
		this.processusRecrutements = processusRecrutements;
	}

	public ProcessusRecrutement addProcessusRecrutement(ProcessusRecrutement processusRecrutement) {
		getProcessusRecrutements().add(processusRecrutement);
		processusRecrutement.setStatutProcessusRecrutement(this);

		return processusRecrutement;
	}

	public ProcessusRecrutement removeProcessusRecrutement(ProcessusRecrutement processusRecrutement) {
		getProcessusRecrutements().remove(processusRecrutement);
		processusRecrutement.setStatutProcessusRecrutement(null);

		return processusRecrutement;
	}


}