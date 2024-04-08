package scooter.rental.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Scooter {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false, unique = true)
	private String productCode;
	
	@Column
	private Integer batteryLevel;
	
	@Column (nullable = false)
	private Long maxSpeed;
	
	@Column (nullable = false)
	private Boolean rented;
	
	@ManyToOne
	private Address address;
	
	@OneToMany (mappedBy = "scooter", cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<Reservation>();

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

	public Long getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Long maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Boolean getRented() {
		return rented;
	}

	public void setRented(Boolean rented) {
		this.rented = rented;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scooter other = (Scooter) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Scooter [id=" + id + ", productCode=" + productCode + ", batteryLevel=" + batteryLevel + ", maxSpeed="
				+ maxSpeed + ", rented=" + rented + ", address=" + address + ", reservations=" + reservations + "]";
	}
	
	

}
