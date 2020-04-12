package com.rest.helloworld;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.helloworld.exceptions.UserBadInputException;
import com.rest.helloworld.exceptions.UserNotFoundException;
import com.rest.user.User;
import com.rest.user.UserDaoService;

@RestController
public class HelloWorldController {
	private Logger logger = LogManager.getLogger(HelloWorldController.class);
	
	@Autowired
	UserDaoService userDaoService;

	@GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	
	@GetMapping(path = "/corona")
	public HelloCoronaBean greetCorona() {
		return new HelloCoronaBean(String.format("Hello , %s", "Corona"));
	}
}
