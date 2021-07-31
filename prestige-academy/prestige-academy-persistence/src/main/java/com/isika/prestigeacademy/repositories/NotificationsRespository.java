package com.isika.prestigeacademy.repositories;


import com.isika.prestigeacademy.model.entities.Notifications;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.repositories.dao.GenericDao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Remote
public class NotificationsRespository extends GenericDao<Notifications> {


    @PersistenceContext
    private EntityManager entityManager;

    public List<Notifications> findByUser(Utilisateur user) {
    	List<Notifications> notifications = null;

        Query query = entityManager.createQuery("SELECT n FROM Notifications n WHERE n.idReceive =:paramUser AND n.status =1");
        query.setParameter("paramUser", user.getUtilisateursID());
        notifications = query.getResultList();

        return notifications;
    }

}
