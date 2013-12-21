package org.preznicek.publiclibrary.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.service.UserService;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.model.formbean.LoginFormBean;
import org.preznicek.publiclibrary.model.formbean.validator.LoginValidator;
import org.preznicek.publiclibrary.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoggingController {

	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new LoginValidator());
	}
	
	/**
	 * Provede validaci udaju zapsanych do prihlasovacoho formulare a nasledne validaci uzivatele.
	 * Pokud uzivatel s danymi prihlasovacimi udaji v databazi existuje, ulozi objekt uzivatele do 
	 * <code>session</code> a provede presmerovani na vyhledavani knih. Pokud uzivatel neni v databazi
	 * nalezen, odstrani se ze <code>session</code> objekt prihlaseneho uzivatele (pro pripad, ze by
	 * tam nahodou byl) a znovu zobrazi uvodni stranku s prihlasovacim formularem.
	 * @param loginFormBean		Obsahuje prihlasovaci udaje.
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute(value="loginFormBean") LoginFormBean loginFormBean, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "home";
		}
		
		User user = userService.userAuthentication(loginFormBean.getEmail(), loginFormBean.getPassword());
		
		if (user != null) {
			LoggedUser loggedUser = new LoggedUser();
			loggedUser.setId(user.getId());
			loggedUser.setName(user.getFirstname() + " " + user.getLastname());
			loggedUser.setEmail(user.getEmail());
			loggedUser.setGpsLatitude(user.getGpsLatitude());
			loggedUser.setGpsLongitude(user.getGpsLongitude());
			
			session.setAttribute(Constants.SessionKey.LOGGED_USER, loggedUser);
			
			return "redirect:/search";
		}
		
		session.removeAttribute(Constants.SessionKey.LOGGED_USER);
		
		result.reject("error.invalid.loggingCredentials");
		
		return "home";
	}
	
	/**
	 * Odstrani ze <code>session</code> objekt prihlaseneho uzivatele a provede presmerovani na uvodni 
	 * stranku aplikace.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.SessionKey.LOGGED_USER);
		
		return "redirect:/home";
	}
}
