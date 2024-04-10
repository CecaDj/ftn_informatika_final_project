package scooter.rental.service;

import java.util.List;

import scooter.rental.model.Address;

public interface AddressService {
	
	List<Address> getAll();
	Address getOne (Long id);

}
