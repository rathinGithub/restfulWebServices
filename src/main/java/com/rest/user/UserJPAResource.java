package com.rest.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.helloworld.exceptions.UserBadInputException;
import com.rest.helloworld.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/jpa")
public class UserJPAResource {
	private Logger logger = LogManager.getLogger(UserJPAResource.class);

	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id--> " + id);
		}
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());
		resource.add(linkTo.withRel("All Users"));
		return ResponseEntity.status(HttpStatus.OK).body(resource);
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		if (user.getName() == null || user.getBirthDate() == null) {
			logger.info("User object is empty");
			throw new UserBadInputException("Please enter all the details");
		}
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
