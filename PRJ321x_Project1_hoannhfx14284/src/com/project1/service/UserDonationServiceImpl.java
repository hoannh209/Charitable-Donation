package com.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project1.dao.UserDonationDAO;
import com.project1.entity.UserDonation;

@Service
public class UserDonationServiceImpl implements UserDonationService {

	@Autowired
	private UserDonationDAO userDonationDAO;
	
	
	@Override
	@Transactional
	public List<UserDonation> getUsersOfDonation(int theId) {
		
		return userDonationDAO.getUsersOfDonation(theId);
	}


	@Override
	@Transactional
	public void saveUserForDonation(UserDonation theUserDonation) {
		
		userDonationDAO.saveUserForDonation(theUserDonation);
		
	}


	@Override
	@Transactional
	public List<UserDonation> searchUsersOfDonation(String theSearchName) {
		
		return userDonationDAO.searchUsersOfDonation(theSearchName);
	}


	@Override
	@Transactional
	public void confirmUserDonation(int theId) {
		userDonationDAO.confirmUserDonation(theId);
	}


	@Override
	@Transactional
	public void cancelUserDonation(int theId) {
		userDonationDAO.cancelUserDonation(theId);
		
	}


	@Override
	@Transactional
	public int getTotalMoney(int theId) {
		
		return userDonationDAO.getTotalMoney(theId);
	}

}
