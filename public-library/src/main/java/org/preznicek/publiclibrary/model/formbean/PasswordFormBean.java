package org.preznicek.publiclibrary.model.formbean;

public class PasswordFormBean extends BaseFormBean {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private boolean validationMessage;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean getValidationMessage() {
		return validationMessage;
	}
	public void setValidationMessage(boolean validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	@Override
	public String toString() {
		return "PasswordFormBean [oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", confirmPassword="
				+ confirmPassword + ", validationMessage=" + validationMessage
				+ "]";
	}
}
