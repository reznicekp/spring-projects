package org.preznicek.publiclibrary.utils;

public interface Constants {

	public static final String BASE_APPLICATION_URL = "http://localhost:8080/public-library";
	public static final int PAGING = 2;
	public static final String DATE_FORMAT_STRING = "d.M.yyyy HH:mm";
	
	public interface SessionKey {
		public static final String LOGGED_USER = "loggedUser";
		public static final String HISTORY_STACK = "historyStack";
	}
	
	public interface FeedbackKind {
		public static final String RENT_TIMING = "rentTiming";
		public static final String BOOK_DAMAGE = "bookDamage";
		public static final String BOOK_LOSS = "bookLoss";
	}
}
