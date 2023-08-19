package com.project1.service;

import java.util.List;

import com.project1.entity.Donation;

public interface DonationService {

	public List<Donation> getDonations();

	public void saveDonation(Donation theDonation);

	public List<Donation> searchDonations(String keyword);

	public void deleteDonation(int theId);

	public Donation getDonation(int theId);

	public void unlockDonation(int theId);

	public void lockDonation(int theId);

	public void closeDonation(int theId);

}
