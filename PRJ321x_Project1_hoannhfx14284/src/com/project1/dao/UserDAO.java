package com.project1.dao;

import java.util.List;

import com.project1.entity.User;

public interface UserDAO {

	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);

	public List<User> searchUsers(String theSearchPhoneNumber);

	public void lockUser(int theId);

	public void unlockUser(int theId);

}
