package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.TypeFinancement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Stateless
public class TypeFinancementRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<TypeFinancement> findAll() {
		return this.entityManager
				.createNamedQuery("TypeFinancement.findAll", TypeFinancement.class)
				.getResultList();
	}

	public TypeFinancement findT(short s) {
		return this.entityManager.find(TypeFinancement.class, s);
	}

}
