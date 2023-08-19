package com.project1.dao;

import java.util.List;

import com.project1.entity.Donation;

public interface DonationDAO {

	public List<Donation> getDonations();

	public void saveDonation(Donation theDonation);

	public List<Donation> searchDonations(String keyword);

	public void deleteDonation(int theId);

	public Donation getDonation(int theId);

	public void unlockDonation(int theId);

	public void lockDonation(int theId);

	public void closeDonation(int theId);

}
