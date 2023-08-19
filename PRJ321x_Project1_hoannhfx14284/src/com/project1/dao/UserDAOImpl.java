package com.project1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project1.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sorted by last name
		Query<User> theQuery = currentSession.createQuery("from User order by fullName", User.class);
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();
		
		// return the result
		return users;
	}

	@Override
	public void saveUser(User theUser) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		theUser.setStatus(1);
		
		// save/update the user
		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public User getUser(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve user from database using the primary key
		User theUser = currentSession.get(User.class, theId);
		
		return theUser;
	}

	@Override
	public void deleteUser(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete user with primary key
		User theUser = currentSession.get(User.class, theId);
		currentSession.delete(theUser);
	}

	@Override
	public List<User> searchUsers(String theSearchPhoneNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = null;
		
		if (theSearchPhoneNumber != null && theSearchPhoneNumber.trim().length() > 0) {
            
            theQuery =currentSession.createQuery("from User where phoneNumber like :thePhoneNumber", User.class);
            theQuery.setParameter("thePhoneNumber", "%" + theSearchPhoneNumber + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from User", User.class);            
        }
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public void lockUser(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, theId);
		theUser.setStatus(0);
	}

	@Override
	public void unlockUser(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		User theUser = currentSession.get(User.class, theId);
		theUser.setStatus(1);
		
	}

}
