package org.preznicek.publiclibrary.database.service;

import org.preznicek.publiclibrary.database.PageData;
import org.preznicek.publiclibrary.database.dao.BookDao;
import org.preznicek.publiclibrary.database.domain.Book;
import org.preznicek.publiclibrary.model.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	@Transactional(readOnly=true)
	public Book getBookDetail(Long id) {
		return bookDao.getBookDetail(id);
	}
	
	@Transactional(readOnly=true)
	public PageData searchBooks(String searchedSubject, Long idOwner, int pageNumber) {
		return bookDao.searchBooks(searchedSubject, idOwner, pageNumber);
	}
	
	@Transactional(readOnly=true)
	public PageData searchBooks(String name, String author, Integer year, LoggedUser owner, int pageNumber) {
		return bookDao.searchBooks(name, author, year, owner, pageNumber);
	}
	
	@Transactional(readOnly=true)
	public PageData searchMyBooks(String searchedSubject, Long idOwner, int pageNumber) {
		return bookDao.searchMyBooks(searchedSubject, idOwner, pageNumber);
	}
	
	@Transactional(readOnly=true)
	public PageData searchBorrowedBooks(String searchedSubject, Long idOwner, int pageNumber) {
		return bookDao.searchBorrowedBooks(searchedSubject, idOwner, pageNumber);
	}
	
	@Transactional
	public void hireBook(Long idBook, Long idUser) {
		bookDao.hireBook(idBook, idUser);
	}
	
	@Transactional
	public void refuseBook(Long idBook, Long idUser) {
		bookDao.refuseBook(idBook, idUser);
	}
	
	@Transactional
	public Long returnBook(Long id) {
		return bookDao.returnBook(id);
	}
	
	@Transactional
	public void requestBook(Long idBook, Long idUser) {
		bookDao.requestBook(idBook, idUser);
	}
	
	@Transactional
	public void upsert(Book book) {
		bookDao.upsert(book);
	}
	
	@Transactional
	public void delete(Long idBook) {
		bookDao.delete(idBook);
	}
}
