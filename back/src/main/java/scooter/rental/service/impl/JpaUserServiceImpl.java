package scooter.rental.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import scooter.rental.enumeration.Role;
import scooter.rental.model.User;
import scooter.rental.repository.UserRepository;
import scooter.rental.service.UserService;

@Service
public class JpaUserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(int pageNo) {
        return userRepository.findAll(PageRequest.of(pageNo, 4));
    }

    @Override
    public User save(User user) {
    	user.setRole(Role.USER);
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    	userRepository.deleteById(id);
    }

	@Override
	public Optional<User> findbyUsername(String username) {
		return userRepository.findFirstByUsername(username);
	}
	
  

}
