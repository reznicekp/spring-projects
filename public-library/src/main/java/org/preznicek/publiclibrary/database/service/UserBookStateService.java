package org.preznicek.publiclibrary.database.service;

import org.preznicek.publiclibrary.database.dao.UserBookStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBookStateService {

	@Autowired
	private UserBookStateDao userBookStateDao;
	
	@Transactional(readOnly=true)
	public Boolean isBookNowHired(Long idBook) {
		return userBookStateDao.isNowHired(idBook);
	}
	
	@Transactional(readOnly=true)
	public Boolean isBookNowRequested(Long idBook) {
		return userBookStateDao.isNowRequested(idBook);
	}
	
	@Transactional(readOnly=true)
	public Boolean isBookNowRequested(Long idBook, Long idUser) {
		return userBookStateDao.isNowRequested(idBook, idUser);
	}
	
	@Transactional(readOnly=true)
	public Integer bookRequestCount(Long idBook) {
		return userBookStateDao.requestCount(idBook);
	}
	
	@Transactional
	public void cancelState(Long idState) {
		userBookStateDao.cancelState(idState);
	}
}
