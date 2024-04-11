package scooter.rental.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.Scooter;
import scooter.rental.web.dto.ScooterDto;

@Component
public class ScooterToScooterDto implements Converter<Scooter, ScooterDto>{

	@Override
	public ScooterDto convert(Scooter scooter) {
		ScooterDto dto = new ScooterDto(scooter.getId(), scooter.getProductCode(), scooter.getBatteryLevel(), 
				scooter.getMaxSpeed(), scooter.getRented(), scooter.getAddress().getId(), scooter.getAddress().getStreet(), 
				scooter.getAddress().getNumber());
		
		return dto;
	}
	
	public List<ScooterDto> convert (List<Scooter> scooters){
		List<ScooterDto> dtos = new ArrayList<ScooterDto>();
		
		for(Scooter s : scooters) {
			dtos.add(convert(s));
		}
		return dtos;
	}
	

}
