package com.isika.prestigeacademy.services;


import com.isika.prestigeacademy.model.entities.EnquetesSatisfaction;
import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.repositories.EnqueteStatRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;


@Stateless
@Remote
public class EnqueteStatService implements Serializable {

	@EJB
	private EnqueteStatRepository enqueteStatRepository;


	public EnqueteStatRepository getEnqueteStatRepository() {
		return enqueteStatRepository;
	}


	public void setEnqueteStatRepository(EnqueteStatRepository enqueteStatRepository) {
		this.enqueteStatRepository = enqueteStatRepository;
	}


	public long creation1(EnquetesSatisfaction enquete, Entreprise entreprise, int q1, int q2, int q3, int q4, int q5, int q6) {
		System.out.println("hello1");
		enquete.setQuestion1(q1);
		enquete.setQuestion2(q2);
		enquete.setQuestion3(q3);
		enquete.setQuestion4(q4);
		enquete.setQuestion5(q5);
		enquete.setQuestion6(q6);
		entreprise.addEnquetesSatisfaction(enquete);
		System.out.println("hello2");
		return enqueteStatRepository.creation1(enquete);
	}

	
}
