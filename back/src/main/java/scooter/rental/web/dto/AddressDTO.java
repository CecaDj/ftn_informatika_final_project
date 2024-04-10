package scooter.rental.web.dto;

public class AddressDTO {
	
	private Long id;
	
	private String street;
	
	private Integer number;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String street, Integer number) {
		this.id = id;
		this.street = street;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
	

}
