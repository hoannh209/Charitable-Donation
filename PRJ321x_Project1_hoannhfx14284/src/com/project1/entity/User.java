package com.project1.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	// define the fields, annotate the fields with database column names
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="email")
	private String email;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="note")
	private String note;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="status")
	private int status;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="created")
	private String created;
	
	// set up mapping to Role entity
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id")
	private Role role;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,
			                                       CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="user_donation", joinColumns = @JoinColumn(name="user_id"), 
									 inverseJoinColumns = @JoinColumn(name="donation_id"))
	private List<Donation> donations;
	
	// create constructors
	public User() {
		
	}
	
	public User(String address, String email, String fullName, String note, String password, String phoneNumber,
			int status, String userName, String created) {
		this.address = address;
		this.email = email;
		this.fullName = fullName;
		this.note = note;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.userName = userName;
		this.created = created;
	}

	// generate getter/setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	// add a convenience method
	/*public void addDonation(Donation theDonation) {
		if (donations == null) {
			donations = new ArrayList<>();
		}
		donations.add(theDonation);
	}*/

	// generate toString method
	@Override
	public String toString() {
		return "User [id=" + id + ", address=" + address + ", email=" + email + ", fullName=" + fullName + ", note="
				+ note + ", password=" + password + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", userName=" + userName + ", created=" + created + ", role=" + role + "]";
	}
}
