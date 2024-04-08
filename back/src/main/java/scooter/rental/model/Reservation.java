package scooter.rental.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private LocalDateTime rentingDate;
	
	@Column
	private LocalDateTime returnDate;
	
	@Column (nullable = false)
	private String email;
	
	@ManyToOne
	private Scooter scooter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getRentingDate() {
		return rentingDate;
	}

	public void setRentingDate(LocalDateTime rentingDate) {
		this.rentingDate = rentingDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Scooter getScooter() {
		return scooter;
	}

	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
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
		Reservation other = (Reservation) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", rentingDate=" + rentingDate + ", returnDate=" + returnDate + ", email="
				+ email + ", scooter=" + scooter + "]";
	}
	
	
	
	

}
