package com.isika.prestigeacademy.controllers;

public class WebUtils {

	public static String CONNEXION = "Connexion.xhtml";
	public static String HOME = "Dashbord.xhtml";
	public static String INDEXENTREPRISE = "IndexEntreprise.xhtml";
	public static String INDEXSTAGIAIRE = "IndexStagiaire.xhtml";
	private static final String REDIRECT = "?faces-redirect=true";

	private WebUtils() {

	}

	public static String redirectTo(String page) {
		return page + REDIRECT;
	}
}
