package org.preznicek.publiclibrary.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.preznicek.publiclibrary.database.PageData;
import org.preznicek.publiclibrary.database.service.BookService;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.model.formbean.SearchFormBean;
import org.preznicek.publiclibrary.model.formbean.validator.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController extends PageableController {

	@Autowired
	private BookService bookService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SearchValidator());
	}
	
	/**
	 * Zobrazi stranku s filtracnim formularem pro vyhledavani knih. Filtracni formular obsahuje jedno 
	 * pole. Podle zadaneho udaje se vyhledavaji knihy podle jejich nazvu a autora.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView search(HttpSession session) {
		SearchFormBean searchFormBean = new SearchFormBean();
		searchFormBean.setSearchedSubject("i");
		return new ModelAndView("search" + sectionPostfix(session), "searchFormBean", searchFormBean);
//		return new ModelAndView("search" + sectionPostfix(session), "searchFormBean", new SearchFormBean());
	}
	
	/**
	 * Provede validaci zadaneho vyhledavaciho udaje (nesmi byt prazdny), vyhleda knihy a vyhledane 
	 * knihy zobrazi v seznamu.
	 * @param searchFormBean	Obsahuje vyhledavaci kriterium.
	 * @param result			Kvuli validaci formulare.
	 * @param session
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/do-search", method=RequestMethod.GET)
	public ModelAndView search(@Valid @ModelAttribute(value="searchFormBean") SearchFormBean searchFormBean, BindingResult result, HttpSession session, Pageable pageable) {
		if (result.hasErrors()) {
			return new ModelAndView("search" + sectionPostfix(session));
		}
		
		PageData booksPageData = null;
		
		LoggedUser loggedUser = getLoggedUser(session);
		if (loggedUser != null && loggedUser.getId() != null) {
			booksPageData = bookService.searchBooks(searchFormBean.getSearchedSubject(), loggedUser.getId(), pageable.getPageNumber());
		} else {
			booksPageData = bookService.searchBooks(searchFormBean.getSearchedSubject(), null, pageable.getPageNumber());
		}
		
		HashMap<String, Boolean> pagingButtons = managePagingButtons(searchFormBean, booksPageData.getTotalCount());
		
		ModelAndView mav = new ModelAndView("search" + sectionPostfix(session));
		mav.addObject("bookList", booksPageData.getItems());
		mav.addAllObjects(pagingButtons);
		return mav;
	}
}
