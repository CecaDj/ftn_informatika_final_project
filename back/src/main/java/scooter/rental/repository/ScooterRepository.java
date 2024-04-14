package scooter.rental.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import scooter.rental.model.Scooter;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long>{
	
	@Query("SELECT s FROM Scooter s WHERE " +
			"(:addressId IS NULL OR s.address.id = :addressId) AND" +
	        "(:batteryLevelMin IS NULL OR s.batteryLevel >= :batteryLevelMin) AND " +
	        "(:batteryLevelMax IS NULL OR s.batteryLevel <= :batteryLevelMax)")
	Page<Scooter> search(@Param("addressId") Long addressId, @Param("batteryLevelMin") Integer batteryLevelMin, 
			@Param ("batteryLevelMax") Integer batteryLevelMax, Pageable pageable);
	

}
