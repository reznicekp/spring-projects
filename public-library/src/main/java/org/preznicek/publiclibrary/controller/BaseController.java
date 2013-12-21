package org.preznicek.publiclibrary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.utils.Constants;

public class BaseController {

	/**
	 * Funkce se pouziva pro prirazeni spravneho retezce v navratove hodnote funkci v controllerech, ktere 
	 * obsluhuji obrazovku pristupnou jak z verejne, tak ze soukrome casti (resp. obrazovky, ktere jsou pro verejnou 
	 * i soukromou cast stejne a lisi se pouze postranni nabidkou).
	 * @param request	Request, ze ktereho se zjisti, zda je uzivatel prihlaseny (a jedna se tak o soukromou cast).
	 * @return			"_public" nebo "" (prazdny retezec)
	 */
	protected String sectionPostfix(HttpServletRequest request) {
		return sectionPostfix(request.getSession());
	}
	
	/**
	 * Funkce se pouziva pro prirazeni spravneho retezce v navratove hodnote funkci v controllerech, ktere 
	 * obsluhuji obrazovku pristupnou jak z verejne, tak ze soukrome casti (resp. obrazovky, ktere jsou pro verejnou 
	 * i soukromou cast stejne a lisi se pouze postranni nabidkou).
	 * @param session	Session, ze ktere se zjisti, zda je uzivatel prihlaseny (a jedna se tak o soukromou cast).
	 * @return			"_public" nebo "" (prazdny retezec)
	 */
	protected String sectionPostfix(HttpSession session) {
		LoggedUser loggedUser = getLoggedUser(session);
		
		if (loggedUser == null) {
			return "_public";
		}
		
		return "";
	}
	
	/**
	 * Funkce vrati prihlaseneho uzivatele nebo <code>null</code> v pripade, ze prihlaseny uzvatel neni.
	 * @param request	Request, ze ktereho se prihlaseny uzivatel zjisti.
	 * @return
	 */
	protected LoggedUser getLoggedUser(HttpServletRequest request) {
		return getLoggedUser(request.getSession());
	}
	
	/**
	 * Funkce vrati prihlaseneho uzivatele nebo <code>null</code> v pripade, ze prihlaseny uzvatel neni.
	 * @param request	Request, ze ktereho se prihlaseny uzivatel zjisti.
	 * @return
	 */
	protected LoggedUser getLoggedUser(HttpSession session) {
		return (LoggedUser) session.getAttribute(Constants.SessionKey.LOGGED_USER);
	}
}
