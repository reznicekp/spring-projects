package org.preznicek.publiclibrary.database.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="User.authentication", query="from User where email = :email and password = :password")
})
@org.hibernate.annotations.Entity(dynamicUpdate=true, selectBeforeUpdate=true)
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	private String firstname;
	
	@Column(nullable=false)
	private String lastname;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(name="birth_year")
	private Integer birthYear;
	
	@Column(name="gps_latitude", nullable=false, precision=19, scale=7)
	private BigDecimal gpsLatitude;
	
	@Column(name="gps_longitude", nullable=false, precision=19, scale=7)
	private BigDecimal gpsLongitude;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Feedback> feedbackList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<Book> ownBookList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<UserBookState> userBookStateList;
	
	@Transient
	private Integer feedbackRentTimingCount;
	
	@Transient
	private Integer feedbackBookDamageCount;
	
	@Transient
	private Integer feedbackBookLossCount;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}
	public BigDecimal getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(BigDecimal gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public BigDecimal getGpsLongitude() {
		return gpsLongitude;
	}
	public void setGpsLongitude(BigDecimal gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	public List<Book> getOwnBookList() {
		return ownBookList;
	}
	public void setOwnBookList(List<Book> ownBookList) {
		this.ownBookList = ownBookList;
	}
	public List<UserBookState> getUserBookStateList() {
		return userBookStateList;
	}
	public void setUserBookStateList(List<UserBookState> userBookStateList) {
		this.userBookStateList = userBookStateList;
	}
	public Integer getFeedbackRentTimingCount() {
		return feedbackRentTimingCount;
	}
	public void setFeedbackRentTimingCount(Integer feedbackRentTimingCount) {
		this.feedbackRentTimingCount = feedbackRentTimingCount;
	}
	public Integer getFeedbackBookDamageCount() {
		return feedbackBookDamageCount;
	}
	public void setFeedbackBookDamageCount(Integer feedbackBookDamageCount) {
		this.feedbackBookDamageCount = feedbackBookDamageCount;
	}
	public Integer getFeedbackBookLossCount() {
		return feedbackBookLossCount;
	}
	public void setFeedbackBookLossCount(Integer feedbackBookLossCount) {
		this.feedbackBookLossCount = feedbackBookLossCount;
	}
}
