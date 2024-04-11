package scooter.rental.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.Scooter;
import scooter.rental.service.AddressService;
import scooter.rental.service.ScooterService;
import scooter.rental.web.dto.ScooterDto;

@Component
public class ScooterDtoToScooter implements Converter<ScooterDto, Scooter>{
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ScooterService scooterService;

	@Override
	public Scooter convert(ScooterDto dto) {
		
		Scooter scooter = null;
		
		if(dto.getId() == null) {
			scooter = new Scooter();
		}
		
		if(dto.getId() != null) {
			scooter = scooterService.getOne(dto.getId()).get();
		}
		
		if(scooter != null) {
			scooter.setAddress(addressService.getOne(dto.getAddressId()));
			scooter.setRented(dto.getRented());
			scooter.setMaxSpeed(dto.getMaxSpeed());
			scooter.setBatteryLevel(dto.getBatteryLevel());
			scooter.setProductCode(dto.getProductCode());
		}
		return scooter;
	}
	

}
