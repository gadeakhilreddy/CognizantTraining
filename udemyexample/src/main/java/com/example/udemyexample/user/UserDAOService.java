package com.example.udemyexample.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 4;
	static {
		users.add(new User(1,"adam",new Date()));
		users.add(new User(2,"akhil",new Date()));
		users.add(new User(3,"hero",new Date()));
		users.add(new User(4,"shiva",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == 0) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	public User findOne(int id) {
		for(User user : users){
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
		
	}
	
	
	
	

}
