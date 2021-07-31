package com.isika.prestigeacademy.model.entities;


import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the compte_rendu database table.
 * 
 */
@Entity
@NamedQuery(name="EnquetesSatisfaction.findAll", query="SELECT e FROM EnquetesSatisfaction e")
public class EnquetesSatisfaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long enquetesSatisfactionID;

	private int question1;
	private int question2;
	private int question3;
	private int question4;
	private int question5;
	private int question6;

	//bi-directional many-to-one association to Entreprise
	@ManyToOne
	@JoinColumn(name="entrepriseID")
	private Entreprise entreprise;

	public EnquetesSatisfaction() {
	}

	public Long getEnquetesSatisfactionID() {
		return enquetesSatisfactionID;
	}

	public void setEnquetesSatisfactionID(Long enquesteSatisfactionID) {
		this.enquetesSatisfactionID = enquesteSatisfactionID;
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

	public int getQuestion4() {
		return question4;
	}

	public void setQuestion4(int question4) {
		this.question4 = question4;
	}

	public int getQuestion5() {
		return question5;
	}

	public void setQuestion5(int question5) {
		this.question5 = question5;
	}

	public int getQuestion6() {
		return question6;
	}

	public void setQuestion6(int question6) {
		this.question6 = question6;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}



}