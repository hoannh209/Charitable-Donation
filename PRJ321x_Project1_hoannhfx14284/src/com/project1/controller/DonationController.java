package com.project1.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project1.entity.Donation;
import com.project1.entity.UserDonation;
import com.project1.service.DonationService;
import com.project1.service.UserDonationService;

@Controller
@RequestMapping("/donation")
public class DonationController {
	
	// need to inject donation service
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
		pagedListHolder.setPageSize(4);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "list-donations";
	}
	
	
	@GetMapping("/showFormForAddDonation")
	public String showFormForAddDonation(Model theModel) {
		
		// create model attribute to bind form data
		Donation theDonation = new Donation();
		theModel.addAttribute("donation", theDonation);
		
		return "donation-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("donationId") int theId, Model theModel) {
		Donation theDonation = donationService.getDonation(theId);
		
		theModel.addAttribute("donation", theDonation);
	
		return "donation-form";
	}
	
	@PostMapping("/saveDonation")
	public String saveDonation(@ModelAttribute("donation") Donation theDonation) {
		
		// save the donation using service
		donationService.saveDonation(theDonation);
		
		return "redirect:/donation/list";
	}
	
	@GetMapping("/searchDonations")
	public String searchDonations(@RequestParam("keyword") String keyword, 
								  HttpServletRequest request, ModelMap modelMap) {
		
		List<Donation> theDonations = donationService.searchDonations(keyword);
		PagedListHolder<Donation> pagedListHolder = new PagedListHolder<Donation>(theDonations);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(4);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "list-donations";
		
	}
	
	@GetMapping("/delete")
	public String deleteDonation(@RequestParam("donationId") int theId){
		
		donationService.deleteDonation(theId);
		
		return "redirect:/donation/list";
	}
	
	// 5.3.2
	
	@GetMapping("/unlockDonation")
	public String unlockDonation(@RequestParam("donationId")int theId) {
		donationService.unlockDonation(theId);
		
		return "redirect:/donation/list";
	}	
	
	@GetMapping("/lockDonation")
	public String lockDonation(@RequestParam("donationId")int theId) {
		donationService.lockDonation(theId);
		
		return "redirect:/donation/list";
	}	
	
	@GetMapping("/closeDonation")
	public String closeDonation(@RequestParam("donationId")int theId) {
		
		donationService.closeDonation(theId);
		return "redirect:/donation/list";
	}
	
	@GetMapping("/showDetail")
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
		theModel.addAttribute("donation", theDonation);
		theModel.addAttribute("usersOfDonation", theUsersOfDonation);
			
		return "donation-detail";
	}
	
	/*
	@GetMapping("/searchUsersOfDonation")
	public String searchUsersOfDonation(@RequestParam("theSearchName") String theSearchName, HttpServletRequest request, ModelMap modelMap) {
		
		List<UserDonation> theUsersOfDonation = userDonationService.searchUsersOfDonation(theSearchName);
		
		PagedListHolder<UserDonation> pagedListHolder = new PagedListHolder<UserDonation>(theUsersOfDonation);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(2);
		modelMap.put("pagedListHolder", pagedListHolder);
		return "donation-detail";
	}*/
	
	@GetMapping("/confirmUserDonation")
	public String confirmUserDonation(ModelMap theModel, @RequestParam("userDonationId") int theId,@RequestParam("donationId") int theDonationId) {
		Donation theDonation = donationService.getDonation(theDonationId);
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
		userDonationService.confirmUserDonation(theId);
		
		List<UserDonation> theUsersOfDonation = userDonationService.getUsersOfDonation(theDonationId);
		theModel.addAttribute("donation", theDonation);
		theModel.addAttribute("usersOfDonation", theUsersOfDonation);
	
		return "donation-detail";
	}
	
	@GetMapping("/cancelUserDonation")
	public String cancelUserDonation(ModelMap theModel, @RequestParam("userDonationId") int theId,@RequestParam("donationId") int theDonationId) {
		Donation theDonation = donationService.getDonation(theDonationId);
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
		userDonationService.cancelUserDonation(theId);
		List<UserDonation> theUsersOfDonation = userDonationService.getUsersOfDonation(theDonationId);
		theModel.addAttribute("donation", theDonation);
		theModel.addAttribute("usersOfDonation", theUsersOfDonation);
		
		return "donation-detail";
	}
}
