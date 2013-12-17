package org.preznicek.publiclibrary.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.LongValidator;
import org.preznicek.publiclibrary.controller.propertyeditor.NullEditor;
import org.preznicek.publiclibrary.database.PageData;
import org.preznicek.publiclibrary.database.domain.Book;
import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.domain.UserBookState;
import org.preznicek.publiclibrary.database.service.BookService;
import org.preznicek.publiclibrary.database.service.FeedbackService;
import org.preznicek.publiclibrary.database.service.UserBookStateService;
import org.preznicek.publiclibrary.exception.InvalidUrlException;
import org.preznicek.publiclibrary.exception.InvalidUrlPublicException;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.model.formbean.BookFormBean;
import org.preznicek.publiclibrary.model.formbean.FeedbackFormBean;
import org.preznicek.publiclibrary.model.formbean.PageableFormBean;
import org.preznicek.publiclibrary.model.formbean.SearchFormBean;
import org.preznicek.publiclibrary.model.formbean.validator.BookValidator;
import org.preznicek.publiclibrary.utils.Constants;
import org.preznicek.publiclibrary.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BookController extends PageableController {

	@Autowired
	private BookService bookService;
	@Autowired
	private UserBookStateService userBookStateService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private ApplicationContext context;
	
	@InitBinder(value="bookFormBean")
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, "year", new NullEditor());
		binder.setValidator(new BookValidator());
	}
	
	/**
	 * Zajistuje zobrazeni detailu knihy po kliknuti na dany zaznam ve vyhledavani. Zobrazi 
	 * se zakladni informace o knize (nazev, autor apod.) a seznam uzivatelu, kteri danou knihu vlastni.
	 * Vzdy je zabrazeno jmeno uzivatele, stav konkretni knihy, priznak, jestli je konkretni kniha 
	 * nyni pujcena nebo je k dispozici, pripadne pocet zadosti na konkretni knihu.<br>
	 * Pokud jeste prave prihlaseny uzivatel neodeslal zadost na pujceni, muze tak ucinit kliknutim 
	 * na odkaz u prislusne konkretni knihy.<br>
	 * Parametry se predavaji jako <code>@RequestParam</code> (ne <code>@PathVariable</code>), protoze 
	 * <code>year</code> neni povinny. Pokud by byly parametry predany jako <code>@PathVariable</code>, 
	 * <code>year</code> by musel byt vyplneny nebo by se to muselo resit pomoci druhe metody, ktera 
	 * by nemela <code>year</code> namapovany a delala by jinak to same.
	 * @param name		Nazev knihy.
	 * @param author	Autor knihy.
	 * @param year		Rok vydani knihy.
	 * @param session
	 * @param referer	Odkaz na stranku, ze ktere uzivatel do teto funkce prisel (kvuli tlacitku Zpet).
	 * @param pageable	Strankovani uzivatelu vlastnicich knihu.
	 * @return			<code>ModelAndView</code> podle toho, jestli se jedna o verejne vyhledavani nebo soukrome vyhledavani (po prihlaseni).
	 * @throws InvalidUrlException	Pokud je zadany rok nevalidni, zobrazi se chybova stranka s verejnym nebo soukromym menu.
	 */
	@RequestMapping(value="/private/book", method=RequestMethod.GET)
	public ModelAndView book(@RequestParam(value="name") String name, @RequestParam(value="author") String author, @RequestParam(value="year") String year, HttpSession session, @RequestHeader(value="Referer", required=false) String referer, Pageable pageable) throws InvalidUrlException {
		System.out.println("name=" + name + ", author=" + author + ", year=" + year);
		
		if (!StringUtils.isEmpty(year) && !IntegerValidator.getInstance().isValid(year)) {
			if ("".equals(sectionPostfix(session))) {
				throw new InvalidUrlException();
			}
			throw new InvalidUrlPublicException();
		}
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/search";
		}
		
		Integer yearInt = !StringUtils.isEmpty(year) ? Integer.valueOf(year) : null;
		
		Book bookInfo = new Book();
		bookInfo.setName(name);
		bookInfo.setAuthor(author);
		bookInfo.setYear(yearInt);
		
		PageData booksPageData = null;
		
		LoggedUser loggedUser = getLoggedUser(session);
		booksPageData = bookService.searchBooks(name, author, yearInt, loggedUser, pageable.getPageNumber());
		
		for (Object item : booksPageData.getItems()) {
			Book book = (Book) item;
			
			if (loggedUser != null) {
				book.setIsNowRequestedByLoggedUser(userBookStateService.isBookNowRequested(book.getId(), loggedUser.getId()));
			}
			
			book.setIsNowHired(userBookStateService.isBookNowHired(book.getId()));
			book.setRequestCount(userBookStateService.bookRequestCount(book.getId()));
		}
		
		PageableFormBean pageableFormBean = new PageableFormBean();
		pageableFormBean.setPage(String.valueOf(pageable.getPageNumber() + 1));
		HashMap<String, Boolean> pagingButtons = managePagingButtons(pageableFormBean, booksPageData.getTotalCount());
		
		if (pageable.getPageNumber() == 0) {
			session.setAttribute(Constants.SessionKey.HISTORY_STACK, referer);
		}
		
		ModelAndView mav = new ModelAndView("book" + sectionPostfix(session));
		mav.addObject("bookInfo", bookInfo);
		mav.addObject("bookList", booksPageData.getItems());
		mav.addAllObjects(pagingButtons);
		return mav;
	}
	
	/**
	 * Zobrazi formular umoznujici editaci knihy. Vola se po kliknuti na nazev knihy v sekci 
	 * <strong>My books</strong>. Zobrazi se zakladni udaje, priznak, jestli je knihu mozne zobrazovat
	 * ve vyhledavani, a stav knihy. Pokud je na editovane knize vytvorena zadost (jakymkoli uzivatelem),
	 * nelze nastavit priznak skryti ve vyhledavani (checkbox Make public je disablovany).
	 * @param id		ID editovane knihy.
	 * @param referer	Odkaz na stranku, ze ktere uzivatel do teto funkce prisel (kvuli tlacitku Zpet).
	 * @param session
	 * @return
	 * @throws InvalidUrlException	Pokud neni zadane validni ID knihy.
	 */
	@RequestMapping(value="/private/book-edit/{id}", method=RequestMethod.GET)
	public ModelAndView bookEdit(@PathVariable(value="id") String id, @RequestHeader(value="Referer", required=false) String referer, HttpSession session) throws InvalidUrlException {
		if (!LongValidator.getInstance().isValid(id)) {
			throw new InvalidUrlException();
		}
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/private/my-books";
		}
		
		Long idLong = Long.valueOf(id);
		
		Book book = bookService.getBookDetail(idLong);
		
		BookFormBean bookFormBean = new BookFormBean();
		bookFormBean.setId(book.getId());
		bookFormBean.setName(book.getName());
		bookFormBean.setAuthor(book.getAuthor());
		bookFormBean.setYear(String.valueOf(book.getYear()));
		bookFormBean.setCanBeHired(book.getCanBeHired());
		bookFormBean.setCondition(book.getCondition());
		bookFormBean.setPhotoExists(Boolean.valueOf(!StringUtils.isEmpty(book.getPhotoMimeType())));
		
		Boolean bookNowRequested = userBookStateService.isBookNowRequested(book.getId());
		Boolean readonlyForm = Boolean.valueOf(book.getOwner().getId().longValue() != getLoggedUser(session).getId().longValue());
		
		ModelAndView mav = new ModelAndView("book-edit");
		mav.addObject("bookFormBean", bookFormBean);
		mav.addObject("bookNowRequested", bookNowRequested);
		mav.addObject("readonlyForm", readonlyForm);
		mav.addObject("referer", referer);
		return mav;
	}
	
	/**
	 * Vrati obrazek knihy v poodobe dat v <code>response</code>. Data jsou ulozena v databazi jako 
	 * <code>BLOB</code>, tedy jako byte large object.
	 * @param idBook		ID knihy, ktere obrazek se zjistuje.
	 * @param response		Response, ktery nese informace o obrazku.
	 * @throws InvalidUrlException	Pokud neni zadane validni ID knihy.
	 * @throws IOException			Pokud selze vytvareni <code>OutputStream</code>.
	 */
	@RequestMapping(value="/private/get-photo/{id}")
	public void getPhoto(@PathVariable(value="id") String idBook, HttpServletResponse response) throws InvalidUrlException, IOException {
		if (!LongValidator.getInstance().isValid(idBook)) {
			throw new InvalidUrlException();
		}
		
		Long idBookLong = Long.valueOf(idBook);
		Book book = bookService.getBookDetail(idBookLong);
		
		byte[] photo = new byte[book.getPhoto().length];
		for (int i = 0; i < photo.length; i++) {
			photo[i] = book.getPhoto()[i].byteValue();
		}
		
		response.setContentType(book.getPhotoMimeType());
		InputStream is = new ByteArrayInputStream(photo);
		IOUtils.copy(is, response.getOutputStream());
	}
	
	/**
	 * Provede validaci udaju a ulozeni knihy (pouziva se pro vytvoreni nove knihy i pro upravu existujici 
	 * knihy). Nasledne dojde k presmerovani na stranku podle toho, jestli se jedna o vytvoreni nove nebo 
	 * upravu existujici knihy (viz komentar uvnitr funkce).
	 * @param bookFormBean	Obsahuje udaje o knize.
	 * @param result		Kvuli validaci - pokud <code>result</code> obsahuje chyby, prislusna pole se zvyrazni.
	 * @param referer		Kvuli presmerovani po editaci existujici knihy.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/private/book-do-edit", method=RequestMethod.POST)
	public ModelAndView bookDoEdit(@Valid @ModelAttribute(value="bookFormBean") BookFormBean bookFormBean, BindingResult result, @RequestParam(value="referer") String referer, HttpSession session) {
		if (result.hasErrors()) {
			return new ModelAndView("book-edit", "referer", referer);
		}
		
		Book book = new Book();
		book.setId(bookFormBean.getId());
		book.setName(bookFormBean.getName());
		book.setAuthor(bookFormBean.getAuthor());
		book.setYear(!StringUtils.isEmpty(bookFormBean.getYear()) ? Integer.valueOf(bookFormBean.getYear()) : null);
		book.setCanBeHired(bookFormBean.getCanBeHired());
		book.setCondition(bookFormBean.getCondition());
		
		Book oldBook = new Book();
		// puvodni kniha se zjistuje pouze pokud se jedna o editaci (ne o vytvareni nove knihy)
		if (bookFormBean.getId() != null && (book.getCanBeHired() == null || bookFormBean.getPhoto().isEmpty())) {
			oldBook = bookService.getBookDetail(bookFormBean.getId());
		}
		
		// pokud je checkbox disablovany (na knihu existuje zadost), vrati se ve form beane jako null, coz ale nelze ulozit, proto se zde zjisti puvodni hodnota
		if (book.getCanBeHired() == null) {
			book.setCanBeHired(oldBook.getCanBeHired());
		}
		
		// pokud nebyl vybran zadny soubor s obrazkem, pri ulozeni by se v DB obrazek prepsal prazdnym polem bytu, proto se nacita puvodni obrazek z DB
		if (bookFormBean.getPhoto().isEmpty()) {
			book.setPhoto(oldBook.getPhoto());
			book.setPhotoMimeType(oldBook.getPhotoMimeType());
		} else {
			Byte[] photo = new Byte[bookFormBean.getPhoto().getBytes().length];
			for (int i = 0; i < photo.length; i++) {
				photo[i] = Byte.valueOf(bookFormBean.getPhoto().getBytes()[i]);
			}
			book.setPhoto(photo);
			book.setPhotoMimeType(bookFormBean.getPhoto().getContentType());
		}
		
		LoggedUser loggedUser = getLoggedUser(session);
		User user = new User();
		user.setId(loggedUser.getId());
		book.setOwner(user);
		
		bookService.upsert(book);
		
		if (StringUtils.isEmpty(referer)) {
			// pokud je referer prazdny, je tato funkce pouzita pro vytvoreni nove knihy (ne pro editaci stavajici)
			// a v takovem pripade je uzivatel presmerovan na prvni stranku (z hlediska strankovani) prehledu svych
			// knih
			return new ModelAndView("redirect:/private/my-books");
		}
		// pokud referer neni prazdny, je tato funkce pouzita pro editaci stavajici knihy a uzivatel je presmerovan
		// presne na stranku, ze ktere na editaci prisel
		return new ModelAndView(new RedirectView(referer));
	}
	
	/**
	 * Zobrazi prehled knih prihlaseneho uzivatele (knihy, ktere prihlaseny uzivatel vlastni). Na strance
	 * je mozne prehled zuzit pomoci vyhledavaciho filtru, ale bezprostredne po nacteni stranky se zobrazi 
	 * vsechny knihy (daneho uzivatele). Protoze v odkazu neni definovany parametr <code>page</code>,
	 * je potreba ho definovat explicitne uvnitr funkce (kvuli strankovani).<br>
	 * Pro zjisteni nekterych informaci o knize je volana funkce <code>manageBooksAttributes()</code>.
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/private/my-books", method=RequestMethod.GET)
	public ModelAndView myBooks(HttpSession session) {
		PageData booksPageData = bookService.searchMyBooks(null, getLoggedUser(session).getId(), 0);
		
		SearchFormBean searchFormBean = new SearchFormBean();
		searchFormBean.setPage("1");
		HashMap<String, Boolean> pagingButtons = managePagingButtons(searchFormBean, booksPageData.getTotalCount());
		
		manageBooksAttributes(booksPageData.getItems());
		
		ModelAndView mav = new ModelAndView("myBooks");
		mav.addObject("searchFormBean", searchFormBean);
		mav.addObject("bookList", booksPageData.getItems());
		mav.addAllObjects(pagingButtons);
		return mav;
	}
	
	/**
	 * Pouziti filtru v sekci <strong>My books</strong>. Vyhledaji se knihy prihlaseneho uzivatele 
	 * podle filtracnich kriterii. Obsah funkce je stejny jako <code>myBooks</code>.
	 * @param searchFormBean	Obsahuje vyhledavaci udaje.
	 * @param session			Pro zjisteni prave prihlaseneho uzivatele.
	 * @param pageable			Kvuli strankovani.
	 * @return
	 */
	@RequestMapping(value="/private/my-books/search", method=RequestMethod.GET)
	public ModelAndView myBooksSearch(@ModelAttribute(value="searchFormBean") SearchFormBean searchFormBean, HttpSession session, Pageable pageable) {
		PageData booksPageData = bookService.searchMyBooks(searchFormBean.getSearchedSubject(), getLoggedUser(session).getId(), pageable.getPageNumber());
		
		HashMap<String, Boolean> pagingButtons = managePagingButtons(searchFormBean, booksPageData.getTotalCount());
		
		manageBooksAttributes(booksPageData.getItems());
		
		ModelAndView mav = new ModelAndView("myBooks");
		mav.addObject("bookList", booksPageData.getItems());
		mav.addAllObjects(pagingButtons);
		return mav;
	}
	
	/**
	 * Zobrazi obrazovku s vypujcenymi knihami. Kazda kniha muze byt ve stavu prave vypujcena (hired), 
	 * je na ni pozadavek (requested) nebo byl pozadavek na vypujceni zamitnut (refused). Tato funkce 
	 * zobrazi knihy v uvedenych stavech bez pouziti vyhledavaciho filtru, tzn. vsechny (s ohledem na 
	 * strankovani).
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/private/borrowed-books", method=RequestMethod.GET)
	public ModelAndView borrowedBooks(HttpSession session) {
		PageData booksPageData = bookService.searchBorrowedBooks(null, getLoggedUser(session).getId(), 0);
		
		SearchFormBean searchFormBean = new SearchFormBean();
		searchFormBean.setPage("1");
		HashMap<String, Boolean> pagingButtons = managePagingButtons(searchFormBean, booksPageData.getTotalCount());
		
		ModelAndView mav = new ModelAndView("borrowedBooks");
		mav.addObject("searchFormBean", searchFormBean);
		mav.addObject("bookList", booksPageData.getItems());
		mav.addAllObjects(pagingButtons);
		return mav;
	}
	
	/**
	 * Funkce je podobna funkci <code>borrowedBooks</code> s tim rozdilem, ze zde uzivatel pouzil 
	 * vyhledavaci filtr.
	 * @param searchFormBean	Obsahuje vyhledavaci udaje.
	 * @param session			Pro zjisteni prave prihlaseneho uzivatele.
	 * @param pageable			Kvuli strankovani.
	 * @return
	 */
	@RequestMapping(value="/private/borrowed-books/search", method=RequestMethod.GET)
	public ModelAndView borrowedBooksSearch(@ModelAttribute(value="searchFormBean") SearchFormBean searchFormBean, HttpSession session, Pageable pageable) {
		PageData booksPageData = bookService.searchBorrowedBooks(searchFormBean.getSearchedSubject(), getLoggedUser(session).getId(), pageable.getPageNumber());
		
		HashMap<String, Boolean> pagingButtons = managePagingButtons(searchFormBean, booksPageData.getTotalCount());
		
		ModelAndView mav = new ModelAndView("borrowedBooks");
		mav.addObject("bookList", booksPageData.getItems());
		mav.addAllObjects(pagingButtons);
		return mav;
	}
	
	/**
	 * Funkce se pouziva pri zobrazovani seznamu knih prihlaseneho uzivatele. Zjisti, zda je dana kniha 
	 * prave pujcena (a pripadne komu a od kdy) a je mozne vypujcku ukoncit.<br>
	 * U kazde knihy je vypsany seznam zadosti jinych uzivatelu, kteri si danou knihu chteji vypujcit.
	 * Tento seznam obsahuje jmeno uzivatele, kdy o vypujceni pozadal a odkaz pro vypujceni nebo zamitnuti.
	 * Odkaz pro vypujceni neni aktivni, pokud je dana kniha prave vypujcena. U kazde zadosti o vypujceni
	 * je uvedena souhrnna reference na daneho uzivatele, ktery o vypujceni zada - kolikrat nedodrzel 
	 * domluvenou dobu vypujceni, kolikrat vratil knihu poskozenou a kolikrat knihu nevratil vubec.
	 * @param bookItems
	 */
	private void manageBooksAttributes(List<?> bookItems) {
		for (Object item : bookItems) {
			Book book = (Book) item;
			for (UserBookState state : book.getUserBookStateList()) {
				if (state.getHired() != null && state.getReturned() == null) {
					book.setHiredTo(state.getUser());
					book.setHiredWhen(state.getHired());
				}
				if (state.getRequested() != null) {
					// osetreni NuuPointerException
					if (book.getRequestedStateList() == null) {
						book.setRequestedStateList(new ArrayList<UserBookState>());
					}
					HashMap<String, Integer> feedbackCount = feedbackService.getFeedbackCountForUser(state.getUser().getId());
					state.getUser().setFeedbackRentTimingCount(feedbackCount.get(Constants.FeedbackKind.RENT_TIMING));
					state.getUser().setFeedbackBookDamageCount(feedbackCount.get(Constants.FeedbackKind.BOOK_DAMAGE));
					state.getUser().setFeedbackBookLossCount(feedbackCount.get(Constants.FeedbackKind.BOOK_LOSS));
					book.getRequestedStateList().add(state);
				}
			}
		}
	}
	
	/**
	 * Ulozi zaznam o vypujceni knihy nebo zamitnuti zadosti o vypujceni knihy.
	 * @param hireOrRefuse	Urcuje, jestli se jedna o vypujceni nebo zamitnuti.
	 * @param idBook		ID knihy.
	 * @param idUser		ID uzivatele, ktery o vypujceni knihy pozadal.
	 * @return				Presmerovani na sekci <strong>My books</strong>.
	 * @throws InvalidUrlException	Pokud zadane ID knihy nebo uzivatele neni validni.
	 */
	@RequestMapping(value="/private/my-books/{hireOrRefuse}/{idBook}/{idUser}", method=RequestMethod.GET)
	public String hireOrRefuseBook(@PathVariable(value="hireOrRefuse") String hireOrRefuse, @PathVariable(value="idBook") String idBook, @PathVariable(value="idUser") String idUser, @RequestHeader(value="Referer", required=false) String referer) throws InvalidUrlException {
		if (!LongValidator.getInstance().isValid(idBook) || !LongValidator.getInstance().isValid(idUser)) {
			throw new InvalidUrlException();
		}
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/private/my-books";
		}
		
		Long idBookLong = Long.valueOf(idBook);
		Long idUserLong = Long.valueOf(idUser);
		
		if ("hire".equals(hireOrRefuse)) {
			bookService.hireBook(idBookLong, idUserLong);
		} else if ("refuse".equals(hireOrRefuse)) {
			bookService.refuseBook(idBookLong, idUserLong);
		}
		
		return "redirect:" + referer;
	}
	
	/**
	 * Zaznamena, ze dana kniha byla vracena - vypujcka byla ukoncena. Nasledne nabidne prihlasenemu 
	 * uzivateli (vlastnikovi) knihy zapsat referenci na uzivatele, ktery mel knihu pujcenou a nyni 
	 * ji vratil.
	 * @param id	ID vypujcene a nyni vracene knihy.
	 * @return
	 * @throws InvalidUrlException Pokud ID knihy neni validni.
	 */
	@RequestMapping(value="/private/my-books/return/{id}", method=RequestMethod.GET)
	public ModelAndView returnBook(@PathVariable(value="id") String id) throws InvalidUrlException {
		if (!LongValidator.getInstance().isValid(id)) {
			throw new InvalidUrlException();
		}
		
		Long idBook = Long.valueOf(id);
		Long idUser = bookService.returnBook(idBook);
		
		FeedbackFormBean feedbackFormBean = new FeedbackFormBean();
		feedbackFormBean.setIdUser(String.valueOf(idUser));
		
		HashMap<Integer, String> items = Utils.getYesNoRadioItems(context);
		
		ModelAndView mav = new ModelAndView("feedback");
		mav.addObject("feedbackFormBean", feedbackFormBean);
		mav.addObject("items", items);
		return mav;
	}
	
	/**
	 * Ulozi zaznam o zadosti o vypujceni knihy prihlasenym uzivatelem.
	 * @param id		ID knihy.
	 * @param session	Pro zjisteni prave prihlaseneho uzivatele.
	 * @param referer
	 * @return
	 * @throws InvalidUrlException Pokud neni ID knihy validni.
	 */
	@RequestMapping(value="/private/book/request/{id}", method=RequestMethod.GET)
	public String requestBook(@PathVariable(value="id") String id, HttpSession session, @RequestHeader(value="Referer", required=false) String referer) throws InvalidUrlException {
		if (!LongValidator.getInstance().isValid(id)) {
			throw new InvalidUrlException();
		}
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/search";
		}
		
		Long idLong = Long.valueOf(id);
		LoggedUser loggedUser = getLoggedUser(session);
		bookService.requestBook(idLong, loggedUser.getId());
		
		return "redirect:" + referer;
	}
	
	/**
	 * Zavola funkci pro odstraneni zaznamu stavu z tabulky userbookstate. Pouziva se, pokud uzivatel 
	 * vytvoril zadost a chce ji hned zrusit (<code>cancel-request</code>) nebo pokud zadost o vypujceni 
	 * knihy byla zamitnuta a uzivatel, ktery zadal, informaci o zamitnuti potvrdi kliknutim na OK 
	 * (<code>confirm-refuse</code>).
	 * @param id		ID ruseneho stavu.
	 * @param referer
	 * @return
	 * @throws InvalidUrlException	Pokud neni ID ruseneho stavu validni.
	 */
	@RequestMapping(value={"/private/borrowed-books/cancel-request/{id}", "/private/borrowed-books/confirm-refuse/{id}"}, method=RequestMethod.GET)
	public String cancelState(@PathVariable(value="id") String id, @RequestHeader(value="Referer", required=false) String referer) throws InvalidUrlException {
		if (!LongValidator.getInstance().isValid(id)) {
			throw new InvalidUrlException();
		}
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/private/borrowed-books";
		}
		
		Long idLong = Long.valueOf(id);
		userBookStateService.cancelState(idLong);
		
		return "redirect:" + referer;
	}
	
	/**
	 * Odstrani z databaze trvale knihu se vsemi zavislostmi (userbookstate).
	 * @param id		ID odstranovane knihy.
	 * @param referer
	 * @return
	 * @throws InvalidUrlException	Pokud neni ID odstranovane knihy validni.
	 */
	@RequestMapping(value="/private/delete-book/{id}")
	public String deleteBook(@PathVariable(value="id") String id, @RequestHeader(value="Referer", required=false) String referer) throws InvalidUrlException {
		if (!LongValidator.getInstance().isValid(id)) {
			throw new InvalidUrlException();
		}
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/private/my-books";
		}
		
		Long idLong = Long.valueOf(id);
		bookService.delete(idLong);
		
		return "redirect:" + referer;
	}
	
	/**
	 * Zobrazi prazdny formular pro vytvoreni nove knihy.
	 * @return
	 */
	@RequestMapping(value="/private/new-book", method=RequestMethod.GET)
	public ModelAndView newBook(@RequestHeader(value="Referer", required=false) String referer) {
		if (referer == null) {
			referer = Constants.BASE_APPLICATION_URL + "/private/my-books";
		}
		
		ModelAndView mav = new ModelAndView("book-new", "bookFormBean", new BookFormBean());
		mav.addObject("referer", referer);
		return mav;
	}
}
