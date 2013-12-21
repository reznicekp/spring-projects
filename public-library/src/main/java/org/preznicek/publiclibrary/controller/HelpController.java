package org.preznicek.publiclibrary.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelpController extends BaseController {

	/**
	 * Zobrazi stranku s napovedou (pro verejnou cast i pro cast po prihlaseni).
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/help", method=RequestMethod.GET)
	public String help(HttpSession session) {
		return "help" + sectionPostfix(session);
	}
}
