package scooter.rental.web.dto;

public class ReservationDto {
	
	private Long id;
	
	private String email;
	
	private Long scooterId;

	public ReservationDto() {
	}

	public ReservationDto(Long id, String email, Long scooterId) {
		this.id = id;
		this.email = email;
		this.scooterId = scooterId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getScooterId() {
		return scooterId;
	}

	public void setScooterId(Long scooterId) {
		this.scooterId = scooterId;
	}
	
	
	
	

	

}