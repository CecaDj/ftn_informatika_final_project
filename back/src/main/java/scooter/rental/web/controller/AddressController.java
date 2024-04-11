package scooter.rental.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scooter.rental.model.Address;
import scooter.rental.service.AddressService;
import scooter.rental.support.AddressToAddressDto;
import scooter.rental.web.dto.AddressDTO;


@RestController
@RequestMapping (value = "/api/addresses", produces = MediaType.APPLICATION_JSON_VALUE )
public class AddressController {
	
	@Autowired 
	private AddressService addressService;
	
	@Autowired
	private AddressToAddressDto toAddressDto;
	
	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<AddressDTO>> getAll() {
		
		List<Address> addresses = addressService.getAll();
		
		return new ResponseEntity<>(toAddressDto.convert(addresses), HttpStatus.OK);
		
	}
	

}
