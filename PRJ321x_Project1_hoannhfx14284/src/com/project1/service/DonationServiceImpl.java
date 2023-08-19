package com.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project1.dao.DonationDAO;
import com.project1.entity.Donation;

@Service
public class DonationServiceImpl implements DonationService {
	
	// inject donation DAO
	@Autowired
	private DonationDAO donationDAO;

	@Override
	@Transactional
	public List<Donation> getDonations() {
		
		return donationDAO.getDonations();
	}

	@Override
	@Transactional
	public void saveDonation(Donation theDonation) {
		
		donationDAO.saveDonation(theDonation);
		
	}

	@Override
	@Transactional
	public List<Donation> searchDonations(String keyword) {
		
		return donationDAO.searchDonations(keyword);
	}

	@Override
	@Transactional
	public void deleteDonation(int theId) {
		
		donationDAO.deleteDonation(theId);
		
	}

	@Override
	@Transactional
	public Donation getDonation(int theId) {
		
		return donationDAO.getDonation(theId);
	}

	@Override
	@Transactional
	public void unlockDonation(int theId) {
		donationDAO.unlockDonation(theId);
		
	}

	@Override
	@Transactional
	public void lockDonation(int theId) {
		donationDAO.lockDonation(theId);
	}

	@Override
	@Transactional
	public void closeDonation(int theId) {
		
		donationDAO.closeDonation(theId);
		
	}
	
}
