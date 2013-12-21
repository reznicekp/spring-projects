package org.preznicek.publiclibrary.database.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session session;
	
	/**
	 * Ziska <code>session</code> pro komunikaci s databazi. Metoda pouziva <code>getCurrentSession()</code>, 
	 * protoze je v projektu nastaveny tzv. <strong>declarative transaction management</strong>. Pokud 
	 * by nastaveny nebyl (a pouzival by se tedy <strong>programmatic transaction management</strong>), 
	 * musela by se session na zacatku kazde DAO metody otevrit pomoci <code>sessionFactory.openSession()</code> 
	 * a na konci kazde DAO metody zase uzavrit pomoci <code>sessionFactory.closeSession()</code>.<br><br>
	 * Declarative transaction management se definuje v app-servlet.xml (<code><tx:annotation-driven/></code> 
	 * a definovanim bean s <code>id=transactionManager</code>). U kazde <code>@Serivce</code> metody 
	 * se potom definuje anotace <code>@Transactional</code> s prislusnymi parametry.
	 * 
	 * @see <a href="http://docs.spring.io/spring/docs/2.5.x/reference/transaction.html">http://docs.spring.io/spring/docs/2.5.x/reference/transaction.html</a>
	 */
	protected void getSession() {
		session = sessionFactory.getCurrentSession();
	}
}
