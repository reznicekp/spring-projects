package org.preznicek.publiclibrary.database.dao;

import org.hibernate.Query;
import org.preznicek.publiclibrary.database.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao {

	/**
	 * Ulozi uzivatele do databaze.
	 * @param user	Instance tridy <code>User</code> se vsemi informacemi.
	 */
	public void upsert(User user) {
		getSession();
		session.saveOrUpdate(user);
	}
	
	/**
	 * Zjisti, jestli se v databazi nachazi uzivatel s danymi prihlasovacimi udaji.
	 * @param email
	 * @param password
	 * @return	Instance tridy <code>User</code> se vsemi informacemi, pokud byl uzivatel nalezen, jinak <code>null</code>.
	 */
	public User userAuthentication(String email, String password) {
		getSession();
		Query query = session.getNamedQuery("User.authentication");
		query.setString("email", email);
		query.setString("password", password);
		User user = (User) query.uniqueResult();
		
		return user;
	}
	
	/**
	 * Vrati informace o danem uzivateli.
	 * @param id	ID uzivatele.
	 * @return		Instance tridy <code>User</code> se vsemi informacemi.
	 */
	public User getUserDetail(Long id) {
		getSession();
		User user = (User) session.get(User.class, id);
		
		return user;
	}
}
