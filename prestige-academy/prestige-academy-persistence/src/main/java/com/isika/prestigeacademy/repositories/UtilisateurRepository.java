package com.isika.prestigeacademy.repositories;

import com.isika.prestigeacademy.model.entities.Utilisateur;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
@Remote
public class UtilisateurRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	public Utilisateur seConnecter(String identifiant, String mdp) {
		Utilisateur utilisateur= null;
		 
		 Query query = entityManager.createQuery("select u FROM Utilisateur u WHERE u.identifiantUtilisateur = :mailParam AND u.motdepasseUtilisateur = :passewordParam");
			query.setParameter("mailParam", identifiant);
			query.setParameter("passewordParam",mdp );
			
			List<Utilisateur> results = query.getResultList();
			if(results.size() == 1)
			{
				Utilisateur utilisateurU = results.get(0);
				utilisateur = utilisateurU;
			}
			return utilisateur;
	}



	public Utilisateur findById(long utilisateurID) {
		return this.entityManager.find(Utilisateur.class, utilisateurID);
	}
}
