package org.preznicek.publiclibrary.model.formbean;

import org.preznicek.publiclibrary.database.domain.BookCondition;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class BookFormBean extends BaseFormBean {

	private Long id;
	private String name;
	private String author;
	private String year;
	private Boolean canBeHired;
	private BookCondition condition;
	private CommonsMultipartFile photo;
	private Boolean photoExists;
	
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Boolean getCanBeHired() {
		return canBeHired;
	}
	public void setCanBeHired(Boolean canBeHired) {
		this.canBeHired = canBeHired;
	}
	public BookCondition getCondition() {
		return condition;
	}
	public void setCondition(BookCondition condition) {
		this.condition = condition;
	}
	public CommonsMultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}
	public Boolean getPhotoExists() {
		return photoExists;
	}
	public void setPhotoExists(Boolean photoExists) {
		this.photoExists = photoExists;
	}
	
	@Override
	public String toString() {
		return "BookFormBean [id=" + id + ", name=" + name + ", author="
				+ author + ", year=" + year + ", canBeHired=" + canBeHired
				+ ", condition=" + condition + ", photo=" + photo
				+ ", photoExists=" + photoExists + "]";
	}
}
