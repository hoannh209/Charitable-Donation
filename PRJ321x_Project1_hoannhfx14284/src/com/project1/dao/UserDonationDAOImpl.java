package com.project1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project1.entity.Donation;
import com.project1.entity.UserDonation;

@Repository
public class UserDonationDAOImpl implements UserDonationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<UserDonation> getUsersOfDonation(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query ... sorted by last name
		Query<UserDonation> theQuery = currentSession.createQuery("from UserDonation where donation_id like :theId order by status", UserDonation.class);
		theQuery.setParameter("theId", theId);
						
		// execute query and get result list
		List<UserDonation> usersOfDonation = theQuery.getResultList();
		
		return usersOfDonation;
	}

	@Override
	public void saveUserForDonation(UserDonation theUserDonation) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
			
						
		// save the user for donation
		currentSession.save(theUserDonation);
		
	}

	@Override
	public List<UserDonation> searchUsersOfDonation(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<UserDonation> theQuery = null;
		
		if (theSearchName != null && theSearchName.trim().length() > 0) {
            
            theQuery =currentSession.createQuery("from UserDonation where name like :theName", UserDonation.class);
            theQuery.setParameter("theName", "%" + theSearchName + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from UserDonation", UserDonation.class);            
        }
		
		List<UserDonation> usersOfDonation = theQuery.getResultList();
		
		return usersOfDonation;
		
	}

	@Override
	public void confirmUserDonation(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		UserDonation theUserDonation = currentSession.get(UserDonation.class, theId);
		theUserDonation.setStatus(1);
		
	}

	@Override
	public void cancelUserDonation(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		UserDonation theUserDonation = currentSession.get(UserDonation.class, theId);
		theUserDonation.setStatus(2);
		
	}

	@Override
	public int getTotalMoney(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
						
		// create a query ... 
		Query<Long> theQuery = currentSession.createQuery("select sum(money) from UserDonation where donation_id =:theId and status =:theStatus", Long.class);
		theQuery.setParameter("theId", theId);
		theQuery.setParameter("theStatus", 1);
		
		int totalMoney = theQuery.getSingleResult().intValue();
		
		return totalMoney;
	}

}
