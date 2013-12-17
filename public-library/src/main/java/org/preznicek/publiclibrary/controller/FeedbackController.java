package org.preznicek.publiclibrary.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.preznicek.publiclibrary.database.domain.Feedback;
import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.service.FeedbackService;
import org.preznicek.publiclibrary.model.formbean.FeedbackFormBean;
import org.preznicek.publiclibrary.model.formbean.validator.FeedbackValidator;
import org.preznicek.publiclibrary.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedbackController {

	@Autowired
	private ApplicationContext context;
	@Autowired
	private FeedbackService feedbackService;
	
	@InitBinder(value="feedbackFormBean")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new FeedbackValidator());
	}
	
	/**
	 * Provede validaci a ulozi referenci na daneho uzivatele. Pokud validace neprojde, musi se znovu 
	 * sestavit polozky pro radiobuttony.
	 * @param feedbackFormBean	Obsahuje udaje o referenci.
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/private/save-feedback", method=RequestMethod.POST)
	public ModelAndView saveFeedback(@Valid @ModelAttribute(value="feedbackFormBean") FeedbackFormBean feedbackFormBean, BindingResult result) {
		if (result.hasErrors()) {
			HashMap<Integer, String> items = Utils.getYesNoRadioItems(context);
			
			ModelAndView mav = new ModelAndView("feedback");
			mav.addObject("items", items);
			return mav;
		}
		
		Feedback feedback = new Feedback();
		feedback.setRentTiming(Boolean.valueOf(!feedbackFormBean.getRentTiming().booleanValue()));
		feedback.setBookDamage(Boolean.valueOf(!feedbackFormBean.getBookDamage().booleanValue()));
		feedback.setBookLoss(Boolean.valueOf(!feedbackFormBean.getBookLoss().booleanValue()));
		
		User user = new User();
		user.setId(Long.valueOf(feedbackFormBean.getIdUser()));
		feedback.setUser(user);
		
		feedbackService.save(feedback);
		
		return new ModelAndView("redirect:/private/my-books");
	}
}
