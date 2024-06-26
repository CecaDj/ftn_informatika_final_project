package scooter.rental.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scooter.rental.model.Scooter;
import scooter.rental.service.ScooterService;
import scooter.rental.support.ScooterDtoToScooter;
import scooter.rental.support.ScooterToScooterDto;
import scooter.rental.web.dto.ReturnScooterDto;
import scooter.rental.web.dto.ScooterDto;


@RestController
@RequestMapping (value = "/api/scooters", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ScooterController {
	
	@Autowired 
	private ScooterService scooterService;
	
	@Autowired
	private ScooterToScooterDto toScooterDto;
	
	@Autowired
	private ScooterDtoToScooter toScooter;
	
	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<ScooterDto>> getAll(@RequestParam (required = false, defaultValue = "0") Integer pageNo,
			@RequestParam (required = false) Long addressId, @RequestParam (required = false) Integer batteryLevelMin,
			@RequestParam (required = false) Integer batteryLevelMax) {
		
		Page<Scooter> page = scooterService.search(addressId, batteryLevelMin, batteryLevelMax, pageNo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toScooterDto.convert(page.getContent()), headers, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ScooterDto> getOne (@PathVariable Long id){
		
		Optional<Scooter> scooter = scooterService.getOne(id);
		
		if(scooter.isPresent()) {
			return new ResponseEntity<>(toScooterDto.convert(scooter.get()), HttpStatus.OK);
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}

}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScooterDto> create(@Valid @RequestBody ScooterDto dto){
		
		Scooter scooter = toScooter.convert(dto);
		
		if(scooter.getAddress() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Scooter saved = scooterService.save(scooter);
		
		return new ResponseEntity<>(toScooterDto.convert(saved), HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping (value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScooterDto> edit (@PathVariable Long id, @Valid @RequestBody ScooterDto dto){
		
		if(!dto.getId().equals(id)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
		
		Scooter scooter = toScooter.convert(dto);
		
		if(scooter.getAddress() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Scooter edited = scooterService.update(scooter);
		
		return new ResponseEntity<>(toScooterDto.convert(edited), HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping (value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		Scooter deleted = scooterService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping (value = "/{id}/return", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> returnScooter (@PathVariable Long id, @RequestBody ReturnScooterDto returnScooter){
		
		if(!returnScooter.getId().equals(id)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
		
		Scooter returned = scooterService.returnScooter(returnScooter);
		
		if(returned != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
			
	}

}
