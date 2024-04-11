package scooter.rental.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.User;
import scooter.rental.web.dto.UserDto;

@Component
public class UserToUserDto implements Converter< User, UserDto>{

	@Override
	public UserDto convert(User user) {
		UserDto dto = new UserDto(user.getId(), user.getUsername());
		return dto;
	}
	
	public List<UserDto> convert (List<User> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		
		for(User user : users) {
			dtos.add(convert(user));
		}
		
		return dtos;
	}

}
