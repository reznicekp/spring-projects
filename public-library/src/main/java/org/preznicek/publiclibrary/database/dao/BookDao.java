package org.preznicek.publiclibrary.database.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.preznicek.publiclibrary.database.PageData;
import org.preznicek.publiclibrary.database.domain.Book;
import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.domain.UserBookState;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.preznicek.publiclibrary.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class BookDao extends BaseDao {

	/**
	 * Najde v databazi danou knihu.
	 * @param id	ID knihy.
	 * @return		Instance tridy <code>Book</code> se vsemi informacemi.
	 */
	public Book getBookDetail(Long id) {
		getSession();
		
		Book book = (Book) session.get(Book.class, id);
		book.getOwner().getId();
		
		return book;
	}
	
	/**
	 * Vyhleda knihy podle zadanych kriterii. Pokud je prihlaseny nejaky uzivatel, jeho knihy vynecha. 
	 * Protoze nevraci cely objekt tridy <code>Book</code>, je potreba provest prevod objektu vracenych 
	 * z databaze.
	 * @param searchedSubject	Filtracni kriterium, podle ktereho se prohledava nazev knihy a autor.
	 * @param idOwner			ID prihlaseneho uzivatele. Muze byt <code>null</code>, pokud jde o vyhledavani ve verejne casti.
	 * @param pageNumber		Kvuli strankovani. <strong>Oznacuje poradi polozky, ne stranky.</strong>
	 * @return					Vrati jen atributy <code>name</code>, <code>author</code> a <code>year</code>.
	 */
	@SuppressWarnings("unchecked")
	public PageData searchBooks(String searchedSubject, Long idOwner, int pageNumber) {
		getSession();
		
		List<Book> bookList = new ArrayList<Book>();
		List<Object> bookListTmp = new ArrayList<Object>();
		
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("canBeHired", Boolean.TRUE));
		if (!StringUtils.isEmpty(searchedSubject)) {
			criteria.add(Restrictions.or(
					Restrictions.like("name", searchedSubject, MatchMode.ANYWHERE), 
					Restrictions.like("author", searchedSubject, MatchMode.ANYWHERE)));
		}
		if (idOwner != null) {
			criteria.add(Restrictions.ne("owner.id", idOwner));
		}
		criteria.addOrder(Order.asc("name")).addOrder(Order.asc("author")).addOrder(Order.desc("year"));
		criteria.setProjection(
				Projections.distinct(Projections.projectionList()
				.add(Projections.property("name"))
				.add(Projections.property("author"))
				.add(Projections.property("year"))));
		int count = criteria.list().size();
		
		criteria.setFirstResult(pageNumber);
		criteria.setMaxResults(Constants.PAGING);
		bookListTmp = criteria.list();
		
		for (Object o : bookListTmp) {
			Object[] row = (Object[]) o;
			Book book = new Book();
			book.setName((String) row[0]);
			book.setAuthor((String) row[1]);
			book.setYear((Integer) row[2]);
			bookList.add(book);
		}
		
		return new PageData(bookList, count);
	}
	
	/**
	 * Vyhleda konkretni knihy podle nazvu, autora a roku (rok je nepovinny). Knihy prihlaseneho uzivatele 
	 * se zde nezobrazuji. Zaznamy jsou serazeny podle vzdalenosti od prihlaseneho uzivatele.
	 * @param name			Nazev knihy.
	 * @param author		Autor knihy.
	 * @param year			Rok vydani knihy.
	 * @param idOwner		ID prihlaseneho uzivatele. Funkce se vola vzdy jen v soukrome casti, tzn. nejaky uzivatel je vzdy prihlasen.
	 * @param pageNumber	Kvuli strankovani. <strong>Oznacuje poradi polozky, ne stranky.</strong>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageData searchBooks(String name, String author, Integer year, LoggedUser owner, int pageNumber) {
		getSession();
		
		List<Book> bookList = new ArrayList<Book>();
		
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("canBeHired", Boolean.TRUE));
		criteria.add(Restrictions.eq("name", name))
				.add(Restrictions.eq("author", author));
		if (year != null) {
			criteria.add(Restrictions.eq("year", year));
		}
		criteria.add(Restrictions.ne("owner.id", owner.getId()));
		
		List<Book> allBooks = criteria.list();
		int count = allBooks.size();
		
		Collections.sort(allBooks, new BookComparator(owner.getGpsLatitude(), owner.getGpsLongitude()));
		for (int i = pageNumber; i < allBooks.size() && i < pageNumber + Constants.PAGING; i++) {
			bookList.add(allBooks.get(i));
		}
		
		return new PageData(bookList, count);
	}
	
	private class BookComparator implements Comparator<Book> {

		private double latitude;
		private double longitude;
		
		public BookComparator(BigDecimal latitude, BigDecimal longitude) {
			this.latitude = latitude.doubleValue();
			this.longitude = longitude.doubleValue();
		}
		
		public int compare(Book book1, Book book2) {
			// vzdalenost majitele knihy 1 od prihlaseneho uzivatele
			double gpsLongitude1 = book1.getOwner().getGpsLongitude().doubleValue();
			double a1 = gpsLongitude1 - longitude;
			
			double gpsLatitude1 = book1.getOwner().getGpsLatitude().doubleValue();
			double b1 = gpsLatitude1 - latitude;
			
			double c1 = Math.sqrt(Math.pow(a1, 2) + Math.pow(b1, 2));
			
			// vzdalenost majitele knihy 2 od prihlaseneho uzivatele
			double gpsLongitude2 = book2.getOwner().getGpsLongitude().doubleValue();
			double a2 = gpsLongitude2 - longitude;
			
			double gpsLatitude2 = book2.getOwner().getGpsLatitude().doubleValue();
			double b2 = gpsLatitude2 - latitude;
			
			double c2 = Math.sqrt(Math.pow(a2, 2) + Math.pow(b2, 2));
			
			if (c1 > c2) {
				return 1;
			} else if (c1 < c2) {
				return -1;
			} else {
				return 0;
			}
		}
		
	}
	
	/**
	 * Podle vyhledavaneho retezce prohleda vsechny knihy (nazev, autor), ktere vlastni dany uzivatel.
	 * @param searchedSubject	Filtracni kriterium, podle ktereho se prohledava nazev knihy a autor.
	 * @param idOwner			ID uzivatele vlastniciho knihy.
	 * @param pageNumber		Kvuli strankovani. <strong>Oznacuje poradi polozky, ne stranky.</strong>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageData searchMyBooks(String searchedSubject, Long idOwner, int pageNumber) {
		getSession();
		
		List<Book> bookList = new ArrayList<Book>();
		
		Criteria criteria = session.createCriteria(Book.class);
		if (!StringUtils.isEmpty(searchedSubject)) {
			criteria.add(Restrictions.or(
					Restrictions.like("name", searchedSubject, MatchMode.ANYWHERE), 
					Restrictions.like("author", searchedSubject, MatchMode.ANYWHERE)));
		}
		if (idOwner != null) {
			criteria.add(Restrictions.eq("owner.id", idOwner));
		}
		criteria.addOrder(Order.asc("name")).addOrder(Order.asc("author")).addOrder(Order.desc("year"));
		int count = criteria.list().size();
		
		criteria.setFirstResult(pageNumber);
		criteria.setMaxResults(Constants.PAGING);
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // uplatni se, pokud neexistuje strankovani
		bookList = criteria.list();
		
		// zajisteni lazy nacitani stavu knihy (na atribut userBookStateList tridy Book nelze nastavit fetch=FetchType.EAGER, protoze se potom duplikuji zaznamy v sekci My Books)
		for (Book book : bookList) {
			if (book.getUserBookStateList() != null) {
				book.getUserBookStateList().size();
			}
		}
		
		return new PageData(bookList, count);
	}
	
	/**
	 * Najde v databazi vsechny stavy prihlaseneho uzivatele. Pokud uzivatel pouzil vyhledavaci filtr,
	 * do vyhledavaciho selectu entity UserBookState se pripoji cast s entitou Book 
	 * (<code>criteria.createCriteria("book", "book");</code>) a definuji se kriteria na vyhledani 
	 * podle nazvu nebo autora knihy.
	 * @param searchedSubject	Filtracni kriterium, podle ktereho se prohledava nazev knihy a autor.
	 * @param idLoggedUser		ID prihlaseneho uzivatele.
	 * @param pageNumber		Kvuli strankovani. <strong>Oznacuje poradi polozky, ne stranky.</strong>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageData searchBorrowedBooks(String searchedSubject, Long idLoggedUser, int pageNumber) {
		getSession();
		
		List<UserBookState> stateList = new ArrayList<UserBookState>();
		
		Criteria criteria = session.createCriteria(UserBookState.class);
		criteria.add(Restrictions.eq("user.id", idLoggedUser));
		criteria.add(Restrictions.or(Restrictions.isNull("hired"), Restrictions.isNull("returned")));	// nezobrazuji se knihy, ktere byly v minulosti vypujceny a uz jsou vraceny
		if (!StringUtils.isEmpty(searchedSubject)) {
			criteria.createCriteria("book", "book");
			criteria.add(Restrictions.or(
					Restrictions.like("book.name", searchedSubject, MatchMode.ANYWHERE), 
					Restrictions.like("book.author", searchedSubject, MatchMode.ANYWHERE)));
		}
		int count = criteria.list().size();
		
		criteria.setFirstResult(pageNumber);
		criteria.setMaxResults(Constants.PAGING);
		stateList = criteria.list();
		
		return new PageData(stateList, count);
	}
	
	/**
	 * U dane knihy zrusi datum zadani zadosti o vypujceni a nastavi datum vypujceni.
	 * @param idBook	ID knihy.
	 * @param idUser	ID uzivatele, ktery o knihu zadal.
	 */
	public void hireBook(Long idBook, Long idUser) {
		getSession();
		
		Book book = (Book) session.get(Book.class, idBook);
		for (UserBookState state : book.getUserBookStateList()) {
			if (state.getUser().getId().equals(idUser) && state.getRequested() != null) {
				state.setHired(new Date());
				state.setRequested(null);
				break;
			}
		}
	}
	
	/**
	 * U dane knihy zrusi datum zadani zadosti o vypujceni a nastavi datum zamitnuti.
	 * @param idBook	ID knihy.
	 * @param idUser	ID uzivatele, ktery o knihu zadal.
	 */
	public void refuseBook(Long idBook, Long idUser) {
		getSession();
		
		Book book = (Book) session.get(Book.class, idBook);
		for (UserBookState state : book.getUserBookStateList()) {
			if (state.getUser().getId().equals(idUser) && state.getRequested() != null) {
				state.setRefused(new Date());
				state.setRequested(null);
				break;
			}
		}
	}
	
	/**
	 * Pro danou knihu najde vsechny zaznamy, kdy je vypujcena a jeste neni vracena. Tomuto stavu nastavi 
	 * atribut <code>returned</code> na aktualni datum.
	 * @param id	ID knihy.
	 * @return		Musi vracet ID uzivatele, aby mu bylo mozne vyplnit referenci.
	 */
	public Long returnBook(Long id) {
		getSession();
		
		Long idUser = null;
		
		Book book = (Book) session.get(Book.class, id);
		List<UserBookState> userBookStateList = book.getUserBookStateList();
		for (UserBookState userBookState : userBookStateList) {
			if (userBookState.getHired() != null && userBookState.getReturned() == null) {
				userBookState.setReturned(new Date());
				idUser = userBookState.getUser().getId();
				break;
			}
		}
		
		session.saveOrUpdate(book);
		
		return idUser;
	}
	
	/**
	 * Pro danou knihu vytvori zaznam zadosti o vypujceni danym uzivatelem.
	 * @param idBook	ID knihy.
	 * @param idUser	ID uzivatele, ktery o knihu zada.
	 */
	public void requestBook(Long idBook, Long idUser) {
		getSession();
		
		Book book = (Book) session.get(Book.class, idBook);
		
		UserBookState userBookState = new UserBookState();
		userBookState.setRequested(new Date());
		
		User user = new User();
		user.setId(idUser);
		userBookState.setUser(user);
		userBookState.setBook(book);
		
		book.getUserBookStateList().add(userBookState);
		
		session.saveOrUpdate(book);
	}
	
	/**
	 * Ulozi danou knihu.
	 * @param book	Instance tridy <code>Book</code> se vsemi informacemi.
	 */
	public void upsert(Book book) {
		getSession();
		session.saveOrUpdate(book);
	}
	
	/**
	 * Trvale odstrani knihu z databaze se vsemi vazbami v tabulce userbookstate.
	 * @param idBook	ID odstranovane knihy.
	 */
	public void delete(Long idBook) {
		getSession();
		
		Book book = (Book) session.get(Book.class, idBook);
		session.delete(book);
	}
}
