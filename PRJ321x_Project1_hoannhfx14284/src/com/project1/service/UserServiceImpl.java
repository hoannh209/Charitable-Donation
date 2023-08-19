package com.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project1.dao.UserDAO;
import com.project1.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	// inject user DAO
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		userDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
		
	}

	@Override
	@Transactional
	public List<User> searchUsers(String theSearchPhoneNumber) {
		
		return userDAO.searchUsers(theSearchPhoneNumber);
	}

	@Override
	@Transactional
	public void lockUser(int theId) {
		
		userDAO.lockUser(theId);
		
	}

	@Override
	@Transactional
	public void unlockUser(int theId) {
		
		userDAO.unlockUser(theId);
		
	}
	
}
