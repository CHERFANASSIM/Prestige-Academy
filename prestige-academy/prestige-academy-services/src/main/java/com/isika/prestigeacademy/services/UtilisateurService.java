package com.isika.prestigeacademy.services;

import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.repositories.UtilisateurRepository;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;


@Stateless
@Remote
public class UtilisateurService implements Serializable {

    @EJB
    private UtilisateurRepository utilisateurRepository;


    public UtilisateurRepository getConnexionRepository() {
        return utilisateurRepository;
    }

    public void setConnexionRepository(UtilisateurRepository connexionRepository) {
			this.utilisateurRepository = connexionRepository;
		}

	public Utilisateur seConnecter(String identifiant, String mdp) {
		return utilisateurRepository.seConnecter(identifiant, mdp);
	}

	public Utilisateur findById(long utilisateurID) {
		return utilisateurRepository.findById(utilisateurID);
		
	}
}
