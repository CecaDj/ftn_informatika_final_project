package scooter.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import scooter.rental.model.User;

public interface UserService {
	
    Optional<User> getOne(Long id);

    List<User> getAll();

    Page<User> getAll(int pageNo);

    User save(User user);

    void delete(Long id);

    Optional<User> findbyUsername(String username);


}
