package org.preznicek.publiclibrary.database.service;

import java.util.HashMap;

import org.preznicek.publiclibrary.database.dao.FeedbackDao;
import org.preznicek.publiclibrary.database.domain.Feedback;
import org.preznicek.publiclibrary.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Transactional
	public void save(Feedback feedback) {
		feedbackDao.save(feedback);
	}
	
	@Transactional(readOnly=true)
	public HashMap<String, Integer> getFeedbackCountForUser(Long idUser) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put(Constants.FeedbackKind.RENT_TIMING, feedbackDao.getRentTimingCountForUser(idUser));
		map.put(Constants.FeedbackKind.BOOK_DAMAGE, feedbackDao.getBookDamageCountForUser(idUser));
		map.put(Constants.FeedbackKind.BOOK_LOSS, feedbackDao.getBookLossCountForUser(idUser));
		
		return map;
	}
}
