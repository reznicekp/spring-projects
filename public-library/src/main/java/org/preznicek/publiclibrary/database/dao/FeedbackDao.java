package org.preznicek.publiclibrary.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.preznicek.publiclibrary.database.domain.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDao extends BaseDao {

	/**
	 * Ulozi zaznam dane reference.
	 * @param feedback
	 */
	public void save(Feedback feedback) {
		getSession();
		session.save(feedback);
	}
	
	/**
	 * Zjisti, kolikrat dany uzivatel nedodrzel dohodnutou dobu vypujceni.
	 * @param idUser	ID uzivatele.
	 * @return
	 */
	public Integer getRentTimingCountForUser(Long idUser) {
		getSession();
		
		Criteria criteria = session.createCriteria(Feedback.class);
		criteria.add(Restrictions.eq("user.id", idUser));
		criteria.add(Restrictions.eq("rentTiming", Boolean.TRUE));
		criteria.setProjection(Projections.rowCount());
		Integer result = (Integer) criteria.uniqueResult();
		
		return result;
	}
	
	/**
	 * Zjisti, kolikrat dany uzivatel vratil knihu v horsim stavu.
	 * @param idUser	ID uzivatele.
	 * @return
	 */
	public Integer getBookDamageCountForUser(Long idUser) {
		getSession();
		
		Criteria criteria = session.createCriteria(Feedback.class);
		criteria.add(Restrictions.eq("user.id", idUser));
		criteria.add(Restrictions.eq("bookDamage", Boolean.TRUE));
		criteria.setProjection(Projections.rowCount());
		Integer result = (Integer) criteria.uniqueResult();
		
		return result;
	}
	
	/**
	 * Zjisti, kolikrat dany uzivatel nevratil knihu.
	 * @param idUser	ID uzivatele.
	 * @return
	 */
	public Integer getBookLossCountForUser(Long idUser) {
		getSession();
		
		Criteria criteria = session.createCriteria(Feedback.class);
		criteria.add(Restrictions.eq("user.id", idUser));
		criteria.add(Restrictions.eq("bookLoss", Boolean.TRUE));
		criteria.setProjection(Projections.rowCount());
		Integer result = (Integer) criteria.uniqueResult();
		
		return result;
	}
}
