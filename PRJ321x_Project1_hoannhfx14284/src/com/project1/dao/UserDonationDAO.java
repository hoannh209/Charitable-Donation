package com.project1.dao;

import java.util.List;

import com.project1.entity.UserDonation;

public interface UserDonationDAO {

	List<UserDonation> getUsersOfDonation(int theId);

	void saveUserForDonation(UserDonation theUserDonation);

	List<UserDonation> searchUsersOfDonation(String theSearchName);

	void confirmUserDonation(int theId);

	void cancelUserDonation(int theId);

	int getTotalMoney(int theId);

}
