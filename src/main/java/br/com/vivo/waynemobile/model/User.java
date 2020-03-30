package br.com.vivo.waynemobile.model;

import javax.validation.constraints.NotNull;

public class User {

	@NotNull
	private Long documentNumber;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	public Long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
