package org.preznicek.publiclibrary.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.preznicek.publiclibrary.database.domain.UserBookState;
import org.springframework.stereotype.Repository;

@Repository
public class UserBookStateDao extends BaseDao {

	/**
	 * Zjisti, jestli je dana kniha prave vypujcena.
	 * @param idBook	ID knihy.
	 * @return
	 */
	public Boolean isNowHired(Long idBook) {
		getSession();
		
		Criteria criteria = session.createCriteria(UserBookState.class);
		criteria.add(Restrictions.eq("book.id", idBook));
		criteria.add(Restrictions.isNotNull("hired"));
		criteria.add(Restrictions.isNull("returned"));
		criteria.setProjection(Projections.rowCount());
		Integer count = (Integer) criteria.uniqueResult();
		
		return Boolean.valueOf(count.intValue() > 0);
	}
	
	/**
	 * Zjisti, jestli je prave dana kniha pozadovana (libovolnym uzivatelem - jestli na ni je vytvorena
	 * zadost).
	 * @param idBook	ID knihy.
	 * @return
	 */
	public Boolean isNowRequested(Long idBook) {
		getSession();
		
		Criteria criteria = session.createCriteria(UserBookState.class);
		criteria.add(Restrictions.eq("book.id", idBook));
		criteria.add(Restrictions.isNotNull("requested"));
		criteria.setProjection(Projections.rowCount());
		Integer count = (Integer) criteria.uniqueResult();
		
		return Boolean.valueOf(count.intValue() > 0);
	}
	
	/**
	 * Zjisti, jestli je prave dana kniha pozadovana danym uzivatelem.
	 * @param idBook	ID knihy.
	 * @param idUser	ID uzivatele.
	 * @return
	 */
	public Boolean isNowRequested(Long idBook, Long idUser) {
		getSession();
		
		Criteria criteria = session.createCriteria(UserBookState.class);
		criteria.add(Restrictions.eq("book.id", idBook));
		criteria.add(Restrictions.eq("user.id", idUser));
		criteria.add(Restrictions.isNotNull("requested"));
		criteria.setProjection(Projections.rowCount());
		Integer count = (Integer) criteria.uniqueResult();
		
		return Boolean.valueOf(count.intValue() > 0);
	}
	
	/**
	 * Zjisti pocet zadosti o vypujceni dane knihy.
	 * @param idBook	ID knihy.
	 * @return
	 */
	public Integer requestCount(Long idBook) {
		getSession();
		
		Criteria criteria = session.createCriteria(UserBookState.class);
		criteria.add(Restrictions.eq("book.id", idBook));
		criteria.add(Restrictions.isNotNull("requested"));
		criteria.setProjection(Projections.rowCount());
		Integer count = (Integer) criteria.uniqueResult();
		
		return Integer.valueOf(count.intValue());
	}
	
	/**
	 * Odstrani zaznam stavu. Pouziva se pri zruseni pozadavku nebo potvrzeni zamitnuti.
	 * @param idState	ID stavu.
	 */
	public void cancelState(Long idState) {
		getSession();
		
		UserBookState state = (UserBookState) session.get(UserBookState.class, idState);
		session.delete(state);
	}
}
