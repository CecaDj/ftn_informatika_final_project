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
import javax.persistence.OneToMany;

@Entity
public class Address {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String street;
	
	@Column (nullable = false)
	private Integer number;
	
	@OneToMany (mappedBy = "address", cascade = CascadeType.ALL)
	private List<Scooter> scooters = new ArrayList<Scooter>();

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

	public List<Scooter> getScooters() {
		return scooters;
	}

	public void setScooters(List<Scooter> scooters) {
		this.scooters = scooters;
	}

	
	
	

}
