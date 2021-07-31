package com.isika.prestigeacademy.controllers;

import com.isika.prestigeacademy.model.entities.EnquetesSatisfaction;
import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.services.EnqueteStatService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.NestedSelectItem;

import java.io.Serializable;

@Named
@RequestScoped
public class EnqueteStatController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3282731590092437942L;
	private Entreprise entreprise;
	private EnquetesSatisfaction newEnquete = new EnquetesSatisfaction();
	private int question1;
	private int question2;
	private int question3;
	private int question4;
	private int question5;
	private int question6;

	@Inject
	private EnqueteStatService enqueteStatService;

	@Inject
	private ConnexionController connexionController;

	@PostConstruct
	public void charger() {
		entreprise = connexionController.getEntrepriseConnecte();
		question1 = 0;
		question2 = 0;
		question3 = 0;
		question4 = 0;
		question5 = 0;
		question6 = 0;
	}

	public String enregistrerEnquete() {
		// On cherche l'entreprise
		if (entreprise != null) {
			long enqueteID = enqueteStatService.creation1(newEnquete, entreprise, question1, question2, question3,
					question4, question5, question6);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Enquete enregistrée",
					"Id [" + enqueteID + "]");
			FacesContext.getCurrentInstance().addMessage(null, m);
			resetFields();
		}
		return WebUtils.redirectTo("/EnqueteSatisfaction.xhtml");
	}

	private void resetFields() {
		this.newEnquete = new EnquetesSatisfaction();
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public EnquetesSatisfaction getNewEnquete() {
		return newEnquete;
	}

	public void setNewEnquete(EnquetesSatisfaction newEnquete) {
		this.newEnquete = newEnquete;
	}

	public int getQuestion1() {
		return question1;
	}

	public void setQuestion1(int question1) {
		this.question1 = question1;
	}

	public int getQuestion2() {
		return question2;
	}

	public void setQuestion2(int question2) {
		this.question2 = question2;
	}

	public int getQuestion3() {
		return question3;
	}

	public void setQuestion3(int question3) {
		this.question3 = question3;
	}

	public int getQuestion5() {
		return question5;
	}

	public void setQuestion5(int question5) {
		this.question5 = question5;
	}

	public int getQuestion4() {
		return question4;
	}

	public void setQuestion4(int question4) {
		this.question4 = question4;
	}

	public int getQuestion6() {
		return question6;
	}

	public void setQuestion6(int question6) {
		this.question6 = question6;
	}

}
