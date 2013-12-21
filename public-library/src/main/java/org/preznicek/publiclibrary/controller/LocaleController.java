package org.preznicek.publiclibrary.controller;

import org.preznicek.publiclibrary.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocaleController {

	/**
	 * Zajistuje zmenu jazyka.
	 * @param referer
	 * @return
	 */
	@RequestMapping(value={"/changeLocale", "/private/changeLocale"}, method=RequestMethod.GET)
	public String changeLocale(@RequestHeader(value="Referer", required=false) String referer) {
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/home";
		}
		
		return "redirect:" + referer;
	}
}
