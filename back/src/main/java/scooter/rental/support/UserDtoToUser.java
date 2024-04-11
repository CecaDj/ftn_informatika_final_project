package scooter.rental.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import scooter.rental.model.User;
import scooter.rental.service.UserService;
import scooter.rental.web.dto.UserDto;

@Component
public class UserDtoToUser implements Converter<UserDto, User>{
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserDto dto) {
		User user = null;
		if(dto.getId() != null) {
			user = userService.getOne(dto.getId()).get();
		}

		if(user == null) { 
			user = new User();
		}
				
		user.setUsername(dto.getUsername());
		
		return user;
	}

}
