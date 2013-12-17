package org.preznicek.publiclibrary.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.preznicek.publiclibrary.controller.propertyeditor.GpsEditor;
import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.service.UserService;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.model.formbean.CredentialsFormBean;
import org.preznicek.publiclibrary.model.formbean.PasswordFormBean;
import org.preznicek.publiclibrary.model.formbean.validator.CredentialsValidator;
import org.preznicek.publiclibrary.model.formbean.validator.PasswordValidator;
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
public class CredentialsController extends BaseController {

	@Autowired
	private UserService userService;
	
	@InitBinder(value="credentialsFormBean")
	public void credentialsBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "gpsLatitude", new GpsEditor());
		binder.registerCustomEditor(String.class, "gpsLongitude", new GpsEditor());
		binder.setValidator(new CredentialsValidator());
	}
	
	@InitBinder(value="passwordFormBean")
	public void passwordBinder(WebDataBinder binder) {
		binder.setValidator(new PasswordValidator());
	}
	
	/**
	 * Zobrazi stranku s osobnimi udaji prihlaseneho uzivatele (needitovatelne).
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/private/credentials", method=RequestMethod.GET)
	public ModelAndView credentials(HttpSession session) {
		LoggedUser loggedUser = getLoggedUser(session);
		
		User user = userService.getUserDetail(loggedUser.getId());
		
		return new ModelAndView("credentials", "user", user);
	}
	
	/**
	 * Zobrazi stranku s formularem pro editaci osobnich udaju prihlaseneho uzivatele.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/private/credentials-edit", method=RequestMethod.GET)
	public ModelAndView credentialsEdit(HttpSession session) {
		LoggedUser loggedUser = getLoggedUser(session);
		
		User user = userService.getUserDetail(loggedUser.getId());
		
		CredentialsFormBean credentialsFormBean = new CredentialsFormBean();
		credentialsFormBean.setId(String.valueOf(user.getId()));
		credentialsFormBean.setFirstname(user.getFirstname());
		credentialsFormBean.setLastname(user.getLastname());
		credentialsFormBean.setEmail(user.getEmail());
		credentialsFormBean.setBirthYear(String.valueOf(user.getBirthYear()));
		credentialsFormBean.setGpsLatitude(String.valueOf(user.getGpsLatitude()));
		credentialsFormBean.setGpsLongitude(String.valueOf(user.getGpsLongitude()));
		
		return new ModelAndView("credentialsEdit", "credentialsFormBean", credentialsFormBean);
	}
	
	/**
	 * Provede validaci zadanych udaju ulozeni osobnich udaju prihlaseneho uzivatele. Protoze se na 
	 * teto strance nezadava heslo, musi se dotahnout explicitne z databaze. Nasledne provede presmerovani
	 * na stranku s needitovatelnymi osobnimi udaji.
	 * @param credentialsFB		Obsahuje udaje z formulare.
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/private/credentials-edit", method=RequestMethod.POST)
	public String credentialsDoEdit(@Valid @ModelAttribute(value="credentialsFormBean") CredentialsFormBean credentialsFB, BindingResult result) {
		if (result.hasErrors()) {
			return "credentialsEdit";
		}
		
		User userForPassword = userService.getUserDetail(Long.valueOf(credentialsFB.getId()));
		
		User user = new User();
		user.setId(Long.valueOf(credentialsFB.getId()));
		user.setFirstname(credentialsFB.getFirstname());
		user.setLastname(credentialsFB.getLastname());
		user.setEmail(credentialsFB.getEmail());
		user.setPassword(userForPassword.getPassword());
		user.setBirthYear(Integer.valueOf(credentialsFB.getBirthYear()));
		user.setGpsLatitude(BigDecimal.valueOf(Double.parseDouble(credentialsFB.getGpsLatitude().replaceAll(",", "."))));
		user.setGpsLongitude(BigDecimal.valueOf(Double.parseDouble(credentialsFB.getGpsLongitude().replaceAll(",", "."))));
		
		userService.upsert(user);
		
		return "redirect:/private/credentials";
	}
	
	/**
	 * Zobrazi prazdny formular pro zmenu hesla prihlaseneho uzivatele.
	 * @return
	 */
	@RequestMapping(value="/private/password", method=RequestMethod.GET)
	public ModelAndView passwordEdit() {
		return new ModelAndView("password", "passwordFormBean", new PasswordFormBean());
	}
	
	/**
	 * Provede validaci puvodniho a noveho hesla a nasledne ulozeni noveho hesla. Pokud se zmena hesla
	 * uspesne dokonci, uzivatel neni presmerovan na jinou stranku, ale zobrazi se mu opet stejna stranka
	 * jako pro zmenu hesla, ale je zde napsano potvrzeni o uspesne zmene hesla.
	 * @param passwordFB	Obsahuje udaje o heslu (puvodni, nove, potvrzeni noveho).
	 * @param result
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/private/password", method=RequestMethod.POST)
	public ModelAndView passwordDoEdit(@Valid @ModelAttribute(value="passwordFormBean") PasswordFormBean passwordFB, BindingResult result, HttpSession session) {
		LoggedUser loggedUser = getLoggedUser(session);
		User checkedUser = userService.userAuthentication(loggedUser.getEmail(), passwordFB.getOldPassword());
		if (checkedUser == null) {
			result.rejectValue("oldPassword", "error.invalid.oldPaswordNotCorrect");
			return new ModelAndView("password");
		}
		
		if (result.hasErrors()) {
			return new ModelAndView("password");
		}
		
		userService.changePassword(loggedUser.getId(), passwordFB.getNewPassword());
		
		PasswordFormBean passwordFormBean = new PasswordFormBean();
		passwordFormBean.setValidationMessage(true);
		
		return new ModelAndView("password", "passwordFormBean", passwordFormBean);
	}
}
