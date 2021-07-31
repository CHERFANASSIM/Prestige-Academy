package com.isika.prestigeacademy.services;


import com.isika.prestigeacademy.model.entities.Notifications;
import com.isika.prestigeacademy.model.entities.Utilisateur;
import com.isika.prestigeacademy.repositories.NotificationsRespository;
import org.joda.time.DateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class NotificationsServices implements Serializable {

    @Inject
    private NotificationsRespository notificationsRespository;

    public List<Notifications> rechercheNotificationsByUser(Utilisateur user) {
        return notificationsRespository.findByUser(user);
    }


    public Long createNotification(Notifications notificationCree, String message, Long sender, Long receiver) {
        notificationCree = new Notifications();
        notificationCree.setDate(DateTime.now().toDate());
        notificationCree.setMessage(message);
        notificationCree.setIdSender(sender);
        notificationCree.setIdReceive(receiver);
        notificationCree.setStatus(true);
        Notifications notification = notificationsRespository.add(notificationCree);
        return notification.getNotificationID();
    }


	public Notifications modifier(Notifications selected) {
		return notificationsRespository.update(selected);
	}
}
