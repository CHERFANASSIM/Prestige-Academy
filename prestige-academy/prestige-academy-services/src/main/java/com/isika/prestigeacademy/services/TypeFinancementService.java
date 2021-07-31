package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.TypeFinancement;
import com.isika.prestigeacademy.repositories.TypeFinancementRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TypeFinancementService {
	
	@Inject
	private TypeFinancementRepository typeFinancementRepository;

	public List<TypeFinancement> rechercherTousLesTypeFinanacement() {
		return typeFinancementRepository.findAll();
	}

	public TypeFinancement typeF(short s) {
		s=1;
		return typeFinancementRepository.findT(s);
	}
}
