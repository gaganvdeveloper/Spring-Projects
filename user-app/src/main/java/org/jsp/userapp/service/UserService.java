package org.jsp.userapp.service;

import org.jsp.userapp.dao.UserDao;
import org.jsp.userapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserDao dao;
	
	
	public User saveUser(User user) {
		dao.saveUser(user);
		return user;
	}
	
	public User updateUser(User user) {
		dao.updateUser(user);
		return user;
	}
	
	public User findUserById(int id) {
		User u=dao.findUserById(id);
		return u;
	}
	
	public boolean deleteUser(int id) {
		return dao.deleteUser(id);
	}
	
	public User verifyUser(long phone, String password) {
		return dao.verifyUser(phone, password);
	}
	
}


