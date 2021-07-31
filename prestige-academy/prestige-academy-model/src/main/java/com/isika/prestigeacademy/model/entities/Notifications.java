package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Notifications implements Serializable {

    private static final long serialVersionUID = 5308743959644166406L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationID;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String message;

    private boolean status;

    private Long idSender;

    private Long idReceive;


    public Notifications() {
    }

    public Long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Long notificationID) {
        this.notificationID = notificationID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

	public Long getIdReceive() {
		return idReceive;
	}

	public void setIdReceive(Long idReceive) {
		this.idReceive = idReceive;
	}

    

}
