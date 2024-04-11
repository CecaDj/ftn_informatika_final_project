package scooter.rental.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.Address;
import scooter.rental.web.dto.AddressDTO;

@Component
public class AddressToAddressDto implements Converter<Address, AddressDTO>{

	@Override
	public AddressDTO convert(Address address) {
		AddressDTO dto = new AddressDTO(address.getId(), address.getStreet(), address.getNumber());
		return dto;
	}
	
	public List<AddressDTO> convert (List<Address> addresses) {
		List<AddressDTO> dtos = new ArrayList<AddressDTO>();
		
		for(Address address : addresses) {
			dtos.add(convert(address));
		}
		
		return dtos;
	}
	

}
