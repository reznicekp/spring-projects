package org.preznicek.publiclibrary.database.service;

import org.preznicek.publiclibrary.database.dao.UserDao;
import org.preznicek.publiclibrary.database.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void upsert(User user) {
		userDao.upsert(user);
	}
	
	@Transactional(readOnly=true)
	public User userAuthentication(String email, String password) {
		return userDao.userAuthentication(email, password);
	}
	
	@Transactional(readOnly=true)
	public User getUserDetail(Long id) {
		return userDao.getUserDetail(id);
	}
	
	@Transactional
	public void changePassword(Long id, String password) {
		User user = userDao.getUserDetail(id);
		user.setPassword(password);
		userDao.upsert(user);
	}
}
