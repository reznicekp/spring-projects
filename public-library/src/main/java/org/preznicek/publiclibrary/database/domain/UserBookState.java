package org.preznicek.publiclibrary.database.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserBookState {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional=false)
	private User user;
	
	@ManyToOne(optional=false)
	private Book book;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requested;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date refused;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date hired;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date returned;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getRequested() {
		return requested;
	}
	public void setRequested(Date requested) {
		this.requested = requested;
	}
	public Date getRefused() {
		return refused;
	}
	public void setRefused(Date refused) {
		this.refused = refused;
	}
	public Date getHired() {
		return hired;
	}
	public void setHired(Date hired) {
		this.hired = hired;
	}
	public Date getReturned() {
		return returned;
	}
	public void setReturned(Date returned) {
		this.returned = returned;
	}
}
