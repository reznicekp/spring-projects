package org.preznicek.publiclibrary.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.preznicek.publiclibrary.database.domain.Book;
import org.preznicek.publiclibrary.database.domain.BookCondition;
import org.preznicek.publiclibrary.database.domain.Feedback;
import org.preznicek.publiclibrary.database.domain.User;
import org.preznicek.publiclibrary.database.domain.UserBookState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(method=RequestMethod.GET)
public class DatabaseModelController {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Pro ucel rychleho vytvoreni testovacich dat v databazi.
	 * @return
	 */
	@RequestMapping(value="/generateDatabase")
	public String generateDatabase() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		User user1 = new User();
		user1.setFirstname("Jan");
		user1.setLastname("Novak");
		user1.setBirthYear(Integer.valueOf(1980));
		user1.setEmail("jnovak@seznam.cz");
		user1.setPassword("a");
		user1.setGpsLatitude(new BigDecimal(50.05));
		user1.setGpsLongitude(new BigDecimal(14.41));
		session.saveOrUpdate(user1);
		User user2 = new User();
		user2.setFirstname("Josef");
		user2.setLastname("Dvorak");
		user2.setBirthYear(Integer.valueOf(1980));
		user2.setEmail("jdvorak@seznam.cz");
		user2.setPassword("a");
		user2.setGpsLatitude(new BigDecimal(50.05));
		user2.setGpsLongitude(new BigDecimal(14.41));
		session.saveOrUpdate(user2);
		User user3 = new User();
		user3.setFirstname("Zdenek");
		user3.setLastname("Troska");
		user3.setBirthYear(Integer.valueOf(1956));
		user3.setEmail("ztroska@seznam.cz");
		user3.setPassword("a");
		user3.setGpsLatitude(new BigDecimal(50.05));
		user3.setGpsLongitude(new BigDecimal(14.41));
		session.saveOrUpdate(user3);
		User user4 = new User();
		user4.setFirstname("Martin");
		user4.setLastname("Dejdar");
		user4.setBirthYear(Integer.valueOf(1965));
		user4.setEmail("mdejdar@seznam.cz");
		user4.setPassword("a");
		user4.setGpsLatitude(new BigDecimal(50.05));
		user4.setGpsLongitude(new BigDecimal(14.41));
		session.saveOrUpdate(user4);
		User user5 = new User();
		user5.setFirstname("Ivan");
		user5.setLastname("Trojan");
		user5.setBirthYear(Integer.valueOf(1965));
		user5.setEmail("itrojan@seznam.cz");
		user5.setPassword("a");
		user5.setGpsLatitude(new BigDecimal(50.05));
		user5.setGpsLongitude(new BigDecimal(14.41));
		session.saveOrUpdate(user5);
		
		Feedback feedback1 = new Feedback();
		feedback1.setRentTiming(new Boolean(true));
		feedback1.setBookDamage(new Boolean(false));
		feedback1.setBookLoss(new Boolean(false));
		feedback1.setUser(user1);
		session.saveOrUpdate(feedback1);
		
		Book book1 = new Book();
		book1.setName("Cesta kolem sveta za 80 dni");
		book1.setAuthor("Jules Verne");
		book1.setYear(Integer.valueOf(1800));
		book1.setCanBeHired(Boolean.TRUE);
		book1.setOwner(user1);
		book1.setCondition(new BookCondition());
		book1.getCondition().setBentEdges(Integer.valueOf(1));
		book1.getCondition().setMissingLeaves(Integer.valueOf(2));
		book1.getCondition().setSpilled(Integer.valueOf(3));
		book1.getCondition().setTornLeaves(Integer.valueOf(4));
		session.saveOrUpdate(book1);
		Book book2 = new Book();
		book2.setName("Cvicebnice anglicke gramatiky");
		book2.setAuthor("John Smith");
		book2.setYear(Integer.valueOf(2000));
		book2.setCanBeHired(Boolean.TRUE);
		book2.setCondition(new BookCondition());
		book2.getCondition().setBentEdges(Integer.valueOf(1));
		book2.getCondition().setMissingLeaves(Integer.valueOf(2));
		book2.getCondition().setSpilled(Integer.valueOf(3));
		book2.getCondition().setTornLeaves(Integer.valueOf(4));
		book2.setOwner(user1);
		session.saveOrUpdate(book2);
		Book book3 = new Book();
		book3.setName("Killing Floor");
		book3.setAuthor("Lee Child");
		book3.setYear(Integer.valueOf(1995));
		book3.setCanBeHired(Boolean.TRUE);
		book3.setCondition(new BookCondition());
		book3.getCondition().setBentEdges(Integer.valueOf(1));
		book3.getCondition().setMissingLeaves(Integer.valueOf(2));
		book3.getCondition().setSpilled(Integer.valueOf(3));
		book3.getCondition().setTornLeaves(Integer.valueOf(4));
		book3.setOwner(user2);
		session.saveOrUpdate(book3);
		Book book4 = new Book();
		book4.setName("Jak dostat tatinka do polepsovny");
		book4.setAuthor("Marie Polednakova");
		book4.setYear(Integer.valueOf(1990));
		book4.setCanBeHired(Boolean.TRUE);
		book4.setCondition(new BookCondition());
		book4.getCondition().setBentEdges(Integer.valueOf(1));
		book4.getCondition().setMissingLeaves(Integer.valueOf(2));
		book4.getCondition().setSpilled(Integer.valueOf(3));
		book4.getCondition().setTornLeaves(Integer.valueOf(4));
		book4.setOwner(user2);
		session.saveOrUpdate(book4);
		Book book5 = new Book();
		book5.setName("Cesta kolem sveta za 80 dni");
		book5.setAuthor("Jules Verne");
		book5.setYear(Integer.valueOf(1800));
		book5.setCanBeHired(Boolean.TRUE);
		book5.setOwner(user4);
		book5.setCondition(new BookCondition());
		book5.getCondition().setBentEdges(Integer.valueOf(2));
		book5.getCondition().setMissingLeaves(Integer.valueOf(2));
		book5.getCondition().setSpilled(Integer.valueOf(3));
		book5.getCondition().setTornLeaves(Integer.valueOf(3));
		session.saveOrUpdate(book5);
		Book book6 = new Book();
		book6.setName("Cesta kolem sveta za 80 dni");
		book6.setAuthor("Jules Verne");
		book6.setYear(Integer.valueOf(1810));
		book6.setCanBeHired(Boolean.TRUE);
		book6.setOwner(user5);
		book6.setCondition(new BookCondition());
		book6.getCondition().setBentEdges(Integer.valueOf(3));
		book6.getCondition().setMissingLeaves(Integer.valueOf(2));
		book6.getCondition().setSpilled(Integer.valueOf(2));
		book6.getCondition().setTornLeaves(Integer.valueOf(3));
		session.saveOrUpdate(book6);
		
		UserBookState state1 = new UserBookState();
		state1.setUser(user2);
		state1.setBook(book1);
		state1.setHired(new Date());
		session.saveOrUpdate(state1);
		UserBookState state2 = new UserBookState();
		state2.setUser(user3);
		state2.setBook(book1);
		state2.setRequested(new Date());
		session.saveOrUpdate(state2);
		
		transaction.commit();
		session.close();
		
		return "redirect:/home";
	}
}
