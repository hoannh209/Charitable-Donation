package com.project1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project1.entity.Donation;



@Repository
public class DonationDAOImpl implements DonationDAO {
	
	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Donation> getDonations() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query ... sorted by last name
		Query<Donation> theQuery = currentSession.createQuery("from Donation order by code", Donation.class);
				
		// execute query and get result list
		List<Donation> donations = theQuery.getResultList();
				
		// return the result
		return donations;
		
	}

	@Override
	public void saveDonation(Donation theDonation) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		theDonation.setStatus(0);
				
		// save/update the user
		currentSession.saveOrUpdate(theDonation);
		
	}
	

	@Override
	public List<Donation> searchDonations(String keyword) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		Query<Donation> theQuery = null;
		
		String s1 = "mới tạo";
		String s2 = "đang quyên góp";
		String s3 = "kết thúc quyên góp";
		String s4 = "đóng quyên góp";
		int status;
		
		if (keyword != null && keyword.trim().length() > 0) {     
			if(s1.contains(keyword.toLowerCase()) || s2.contains(keyword.toLowerCase()) || 
			   s3.contains(keyword.toLowerCase()) || s4.contains(keyword.toLowerCase())) {
				if(s1.contains(keyword.toLowerCase())) {
					keyword = "0";
				}else if(s2.contains(keyword.toLowerCase())) {
					keyword = "1";
				}else if (s3.contains(keyword.toLowerCase())) {
					keyword = "2";
				}else if (s4.contains(keyword.toLowerCase())) {
					keyword = "3";
				}
				
				status = Integer.parseInt(keyword);
				theQuery =currentSession.createQuery("from Donation where status like :theStatus", Donation.class);
				theQuery.setParameter("theStatus", status);
			} else {
				theQuery =currentSession.createQuery("from Donation where code like :theKeyword or phoneNumber like :theKeyword or organizationName like :theKeyword", Donation.class);
				theQuery.setParameter("theKeyword", "%" + keyword + "%");
			}
			
		} else {
			
			theQuery =currentSession.createQuery("from Donation", Donation.class);            
		}
		
		List<Donation> donations = theQuery.getResultList();
		
		return donations;
	
	}

	@Override
	public void deleteDonation(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// delete donation with primary key
		Donation theDonation = currentSession.get(Donation.class, theId);
		currentSession.delete(theDonation);
		
	}

	@Override
	public Donation getDonation(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve donation from database using the primary key
		Donation theDonation = currentSession.get(Donation.class, theId);
		
		return theDonation;
	}

	@Override
	public void unlockDonation(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// retrieve donation from database using the primary key
		Donation theDonation = currentSession.get(Donation.class, theId);
		
		theDonation.setStatus(1);
		
	}

	@Override
	public void lockDonation(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve donation from database using the primary key
		Donation theDonation = currentSession.get(Donation.class, theId);
		
		theDonation.setStatus(2);
	}

	@Override
	public void closeDonation(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve donation from database using the primary key
		Donation theDonation = currentSession.get(Donation.class, theId);
		
		theDonation.setStatus(3);
	}
}
