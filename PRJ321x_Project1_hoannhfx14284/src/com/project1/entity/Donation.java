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
import javax.persistence.Table;



@Entity
@Table(name="donation")
public class Donation {
	
	// define the fields, annotate the fields with database column names
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="created")
	private String created;
	
	@Column(name="description")
	private String description;
	
	@Column(name="name")
	private String name;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="organization_name")
	private String organizationName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="money")
	private int money;
	
	@Column(name="status")
	private int status;
	
	@Column(name="status_text")
	private String statusText;
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,
                                                   CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="user_donation", joinColumns = @JoinColumn(name="donation_id"), 
									 inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> users;
	
	// create constructors
	public Donation() {
		
	}

	public Donation(String code, String created, String description, String name, String startDate, String endDate,
			String organizationName, String phoneNumber, int money, int status, String statusText) {
		super();
		this.code = code;
		this.created = created;
		this.description = description;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organizationName = organizationName;
		this.phoneNumber = phoneNumber;
		this.money = money;
		this.status = status;
		this.statusText = statusText;
	}

	// generate setter/getter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	
	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	// add a convenience method
	public void addUser(User theUser) {
		if(users == null) {
			users = new ArrayList<>();
		}
		users.add(theUser);
	}

	// generate toStrig method
	@Override
	public String toString() {
		return "Donation [id=" + id + ", code=" + code + ", created=" + created + ", description=" + description
				+ ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", organizationName="
				+ organizationName + ", phoneNumber=" + phoneNumber + ", money=" + money + ", status=" + status + "]";
	}
}
