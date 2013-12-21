package org.preznicek.publiclibrary.model.formbean;

public class FeedbackFormBean extends BaseFormBean {

	private Boolean rentTiming;
	private Boolean bookDamage;
	private Boolean bookLoss;
	private String idUser;
	
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
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		return "FeedbackFoemBean [rentTiming=" + rentTiming + ", bookDamage="
				+ bookDamage + ", bookLoss=" + bookLoss + ", idUser=" + idUser
				+ "]";
	}
}
