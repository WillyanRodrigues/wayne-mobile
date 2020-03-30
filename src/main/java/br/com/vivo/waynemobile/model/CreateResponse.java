package br.com.vivo.waynemobile.model;

public class CreateResponse {

	public CreateResponse(Long id) {
		setId(id);
	}
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
