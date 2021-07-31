package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the etape_validation database table.
 * 
 */
@Entity
@NamedQuery(name="EtapeValidation.findAll", query="SELECT e FROM EtapeValidation e")
public class EtapeValidation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long etapeValidationID;

	private String etape;

	public EtapeValidation() {
	}

	public Long getEtapeValidationID() {
		return this.etapeValidationID;
	}

	public void setEtapeValidationID(Long etapeValidationID) {
		this.etapeValidationID = etapeValidationID;
	}

	public String getEtape() {
		return this.etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}

}