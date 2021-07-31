package com.isika.prestigeacademy.controllers;

public class MailMessageViewModel {

	private String from;
	private String subject;
	private String content;
	private String sentDate;
	private String attachement;


	public MailMessageViewModel() {
	}

	public MailMessageViewModel(String from, String subject, String sentDate, String content, String attachement) {
		this.from = from;
		this.subject = subject;
		this.sentDate = sentDate;
		this.content = content;
		this.attachement = attachement;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSentDate() {
		return sentDate;
	}

	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

}
