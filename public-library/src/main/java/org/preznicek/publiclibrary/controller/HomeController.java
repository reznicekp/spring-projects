package org.preznicek.publiclibrary.controller;

import org.preznicek.publiclibrary.aspect.annotation.HomeMethod;
import org.preznicek.publiclibrary.model.formbean.LoginFormBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	/**
	 * Zobrazi uvodni stranku aplikace. Na strance se nachazi formular pro prihlaseni, u ktereho je
	 * predvyplnen zavinac v poli <strong>Email</strong>.
	 * @return
	 */
	@HomeMethod
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home() {
		LoginFormBean loginFormBean = new LoginFormBean();
//		loginFormBean.setEmail("@");
		loginFormBean.setEmail("jnovak@seznam.cz");
		
		return new ModelAndView("home", "loginFormBean", loginFormBean);
	}
}
