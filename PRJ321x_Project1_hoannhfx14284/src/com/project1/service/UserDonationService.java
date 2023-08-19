package com.project1.service;

import java.util.List;


import com.project1.entity.UserDonation;

public interface UserDonationService {
	public List<UserDonation> getUsersOfDonation(int theId);

	public void saveUserForDonation(UserDonation theUserDonation);

	public List<UserDonation> searchUsersOfDonation(String theSearchName);

	public void confirmUserDonation(int theId);

	public void cancelUserDonation(int theId);

	public int getTotalMoney(int theId);
}
