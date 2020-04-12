package com.rest.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rest.helloworld.exceptions.UserNotFoundException;

@Component
public class UserDaoService {
      
	static List<User> users  = new ArrayList<>();
	
	static int userCount = 3;
	
	static {
		users.add(new User(1, "Raadha", new Date()));
		users.add(new User(2, "Pidha", new Date()));
		users.add(new User(3, "Madha", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId() == null) {
			 user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(int id) {
		User user = users.stream().filter(u-> u.getId()== id).findAny().orElse(null);
		return user;
	}
	
	public User deleteByUserId(int id) {
		User user = findUser(id);
		if(user == null) {
			throw new UserNotFoundException("id--> "+id);
		}
		users.removeIf(u -> u.getId() == user.getId()); 
		return user;
	}
	
}
