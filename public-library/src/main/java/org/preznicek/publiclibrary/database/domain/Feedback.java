package org.preznicek.publiclibrary.database.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Feedback {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="rent_timing", nullable=false)
	@Type(type="true_false")
	private Boolean rentTiming;
	
	@Column(name="book_damage", nullable=false)
	@Type(type="true_false")
	private Boolean bookDamage;
	
	@Column(name="book_loss", nullable=false)
	@Type(type="true_false")
	private Boolean bookLoss;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private User user;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getRentTiming() {
		return rentTiming;
	}
	public void setRentTiming(Boolean rentTiming) {
		this.rentTiming = rentTiming;
	}
	public Boolean getBookDamage() {
		return bookDamage;
	}
	public void setBookDamage(Boolean bookDamage) {
		this.bookDamage = bookDamage;
	}
	public Boolean getBookLoss() {
		return bookLoss;
	}
	public void setBookLoss(Boolean bookLoss) {
		this.bookLoss = bookLoss;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
