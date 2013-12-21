package org.preznicek.publiclibrary.model.formbean;

public class CredentialsFormBean extends RegistrationFormBean {

	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "CredentialsFormBean [id=" + id + ", toString()="
				+ super.toString() + "]";
	}
}
