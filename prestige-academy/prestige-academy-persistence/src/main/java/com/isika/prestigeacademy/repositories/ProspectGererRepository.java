package com.isika.prestigeacademy.repositories;


import com.isika.prestigeacademy.model.entities.Entreprise;
import com.isika.prestigeacademy.model.entities.GererProspect;
import com.isika.prestigeacademy.repositories.dao.GenericDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProspectGererRepository extends GenericDao<GererProspect> {


    @PersistenceContext
    private EntityManager entityManager;


    public List<GererProspect> findAll() {

        Query query = entityManager.createQuery("select g FROM GererProspect g");
        List<GererProspect> gererProspects = query.getResultList();
        return gererProspects;
    }

    public GererProspect findByStatut(Entreprise entreprise) {
        /*String statutProspect = entreprise.getStatut().getNomStatut();
        List<Statut> statutList = entityManager.createQuery("select s FROM Statut s").getResultList();



        switch (statutProspect) {
            case "Non Partenaire" :


                break;
            default:
                throw new IllegalStateException("Unexpected value: " + entreprise.getStatut().getNomStatut());
        }*/
        return null;
    }

}
