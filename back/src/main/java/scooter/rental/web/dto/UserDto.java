package scooter.rental.web.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {
	private Long id;
	@NotBlank
	private String username;
	
	
	public UserDto(Long id, @NotBlank String username) {
		this.id = id;
		this.username = username;
	}
	
	public UserDto() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
