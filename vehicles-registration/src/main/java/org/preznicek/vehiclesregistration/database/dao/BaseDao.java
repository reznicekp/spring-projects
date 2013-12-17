package org.preznicek.vehiclesregistration.database.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session session;
	
	protected void getSession() {
		session = sessionFactory.getCurrentSession();
	}
}
