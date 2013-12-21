package org.preznicek.vehiclesregistration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BugsController {

	/**
	 * Zobrazi stranku s nevyresenymi chybami aplikace.
	 * @return
	 */
	@RequestMapping(value="/bugs", method=RequestMethod.GET)
	public String bugs() {
		return "bugs";
	}
}
