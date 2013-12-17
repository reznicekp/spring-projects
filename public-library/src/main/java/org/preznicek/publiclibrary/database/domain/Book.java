package org.preznicek.publiclibrary.database.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String author;
	
	private Integer year;
	
	@Lob
	private Byte[] photo;
	
	@Column(name="photo_mime_type")
	private String photoMimeType;
	
	@Column(name="can_be_hired", nullable=false)
	@Type(type="yes_no")
	private Boolean canBeHired;
	
	@Transient
	private User hiredTo;		// pro ucel vypisu v sekci My Books
	
	@Transient
	private Date hiredWhen;		// pro ucel vypisu v sekci My Books
	
	@Transient
	private Boolean isNowHired;	// pro ucel vypisu v sekci Book Detail se seznamem majitelu knih a moznosti pozadat o vypujceni
	
	@Transient
	private Boolean isNowRequestedByLoggedUser;	// pro ucel vypisu v sekci Book Detail se seznamem majitelu knih a moznosti pozadat o vypujceni
	
	@Transient
	private Integer requestCount;	// pro ucel vypisu v sekci Book Detail se seznamem majitelu knih a moznosti pozadat o vypujceni
	
	@Transient
	private List<UserBookState> requestedStateList;	// pro ucel vypisu v sekci My Books
	
	@Embedded
	private BookCondition condition;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private User owner;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="book")
	private List<UserBookState> userBookStateList;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoMimeType() {
		return photoMimeType;
	}
	public void setPhotoMimeType(String photoMimeType) {
		this.photoMimeType = photoMimeType;
	}
	public Boolean getCanBeHired() {
		return canBeHired;
	}
	public void setCanBeHired(Boolean canBeHired) {
		this.canBeHired = canBeHired;
	}
	public User getHiredTo() {
		return hiredTo;
	}
	public void setHiredTo(User hiredTo) {
		this.hiredTo = hiredTo;
	}
	public Date getHiredWhen() {
		return hiredWhen;
	}
	public void setHiredWhen(Date hiredWhen) {
		this.hiredWhen = hiredWhen;
	}
	public Boolean getIsNowHired() {
		return isNowHired;
	}
	public void setIsNowHired(Boolean isNowHired) {
		this.isNowHired = isNowHired;
	}
	public Boolean getIsNowRequestedByLoggedUser() {
		return isNowRequestedByLoggedUser;
	}
	public void setIsNowRequestedByLoggedUser(Boolean isNowRequestedByLoggedUser) {
		this.isNowRequestedByLoggedUser = isNowRequestedByLoggedUser;
	}
	public Integer getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(Integer requestCount) {
		this.requestCount = requestCount;
	}
	public List<UserBookState> getRequestedStateList() {
		return requestedStateList;
	}
	public void setRequestedStateList(List<UserBookState> requestedStateList) {
		this.requestedStateList = requestedStateList;
	}
	public BookCondition getCondition() {
		return condition;
	}
	public void setCondition(BookCondition condition) {
		this.condition = condition;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<UserBookState> getUserBookStateList() {
		return userBookStateList;
	}
	public void setUserBookStateList(List<UserBookState> userBookStateList) {
		this.userBookStateList = userBookStateList;
	}
}
