package com.project1.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1.entity.Donation;
import com.project1.entity.UserDonation;
import com.project1.service.DonationService;
import com.project1.service.UserDonationService;


@Controller
@RequestMapping("/userdonation")
public class UserDonationController {
	
	@Autowired
	private DonationService donationService;
	
	@Autowired
	private UserDonationService userDonationService;
	
	@GetMapping("/list")
	public String listDonations(HttpServletRequest request, ModelMap modelMap) {
		
		List<Donation> theDonations = donationService.getDonations();
		PagedListHolder<Donation> pagedListHolder = new PagedListHolder<Donation>(theDonations);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(5);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "list-users-donations";
	}
	
	@GetMapping("/detail")
	public String showDetail(@RequestParam("donationId") int theId, ModelMap theModel) {
		
		Donation theDonation = donationService.getDonation(theId);
		int status = theDonation.getStatus();
		switch (status) {
		case 0:
			theDonation.setStatusText("Mới tạo");
			break;
		case 1:
			theDonation.setStatusText("Đang quyên góp");
			break;
		case 2:
			theDonation.setStatusText("Kết thúc quyên góp");
			break;
		case 3:
			theDonation.setStatusText("Đóng quyên góp");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + status);
		}
		List<UserDonation> theUsersOfDonation = userDonationService.getUsersOfDonation(theId);
		int totalMoney = userDonationService.getTotalMoney(theId);
		theDonation.setMoney(totalMoney);
		theModel.addAttribute("donation", theDonation);
		theModel.addAttribute("usersOfDonation",theUsersOfDonation);
		
		
		return "user-donation-detail";
	}
	
	
	@PostMapping("/saveUserForDonation")
	public String saveUserForDonation(HttpServletRequest request, @RequestParam("donationId") int theId) {
		
		Donation theDonation = donationService.getDonation(theId);
		UserDonation theUserDonation = new UserDonation();
		
		
		String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		theUserDonation.setName(request.getParameter("name").trim());
		theUserDonation.setMoney(Integer.parseInt(request.getParameter("money").trim()));
		theUserDonation.setText(request.getParameter("text").trim());
		theUserDonation.setCreated(localDate);
		theUserDonation.setDonation(theDonation);
		
		  
		userDonationService.saveUserForDonation(theUserDonation);
		
		return "redirect:/userdonation/list";
		
	}
	
	
	
	
	
	
	
	

}
