package org.preznicek.publiclibrary.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.preznicek.publiclibrary.controller.propertyeditor.GpsEditor;
import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.service.UserService;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.model.formbean.RegistrationFormBean;
import org.preznicek.publiclibrary.model.formbean.validator.RegistrationValidator;
import org.preznicek.publiclibrary.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "gpsLatitude", new GpsEditor());
		binder.registerCustomEditor(String.class, "gpsLongitude", new GpsEditor());
		binder.setValidator(new RegistrationValidator());
	}
	
	/**
	 * Zobrazi formular pro registraci noveho uzivatele.
	 * @return
	 */
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public ModelAndView registration() {
		RegistrationFormBean registrationFormBean = new RegistrationFormBean();
		registrationFormBean.setEmail("@");
		
		return new ModelAndView("registration", "registrationFormBean", registrationFormBean);
	}
	
	/**
	 * Provede validaci udaju a vytvori v databazi noveho uzivatele. Pokud se to podari, uzivatele 
	 * je ihned prihlasen do aplikace.
	 * @param registrationFormBean	Obsahuje udaje o registrujicim se uzivateli.
	 * @param result				Kvuli validaci udaju z formulare.
	 * @return
	 */
	@RequestMapping(value="/sign-in", method=RequestMethod.POST)
	public String signIn(@Valid @ModelAttribute(value="registrationFormBean") RegistrationFormBean registrationFormBean, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "registration";
		}
		
		User user = new User();
		user.setFirstname(registrationFormBean.getFirstname());
		user.setLastname(registrationFormBean.getLastname());
		user.setEmail(registrationFormBean.getEmail());
		user.setPassword("noveheslo");
		user.setBirthYear(Integer.valueOf(registrationFormBean.getBirthYear()));
		user.setGpsLatitude(BigDecimal.valueOf(Double.parseDouble(registrationFormBean.getGpsLatitude().replaceAll(",", "."))));
		user.setGpsLongitude(BigDecimal.valueOf(Double.parseDouble(registrationFormBean.getGpsLongitude().replaceAll(",", "."))));
		userService.upsert(user);
		
		LoggedUser loggedUser = new LoggedUser();
		loggedUser.setId(user.getId());
		loggedUser.setName(user.getFirstname() + " " + user.getLastname());
		loggedUser.setEmail(user.getEmail());
		loggedUser.setGpsLatitude(user.getGpsLatitude());
		loggedUser.setGpsLongitude(user.getGpsLongitude());
		
		session.setAttribute(Constants.SessionKey.LOGGED_USER, loggedUser);
		
		return "redirect:/search";
	}
}
