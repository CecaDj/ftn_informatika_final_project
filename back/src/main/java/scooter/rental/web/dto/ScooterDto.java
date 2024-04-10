package scooter.rental.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ScooterDto {
	
	private Long id;
	
	@Size (max = 20)
	private String productCode;
	
	@Min(0)
	@Max(100)
	private Integer batteryLevel;
	
	private Integer maxSpeed;
	
	private Boolean rented;
	
	private Long addressId;
	
	private String addressStreet;
	
	private Integer addressNumber;

	public ScooterDto() {
	}

	public ScooterDto(Long id, @Size(max = 20) String productCode, @Min(0) @Max(100) Integer batteryLevel,
			Integer maxSpeed, Boolean rented, Long addressId, String addressStreet, Integer addressNumber) {
		this.id = id;
		this.productCode = productCode;
		this.batteryLevel = batteryLevel;
		this.maxSpeed = maxSpeed;
		this.rented = rented;
		this.addressId = addressId;
		this.addressStreet = addressStreet;
		this.addressNumber = addressNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public Integer getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Boolean getRented() {
		return rented;
	}

	public void setRented(Boolean rented) {
		this.rented = rented;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public Integer getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(Integer addressNumber) {
		this.addressNumber = addressNumber;
	}
	
	

}
