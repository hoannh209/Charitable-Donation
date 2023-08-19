package com.project1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_donation")
public class UserDonation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "money")
	private int money;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "text")
	private String text;
	
	@OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
						 CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "donation_id")
	private Donation donation;
	
	@OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
			 CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private User user;
	
	public UserDonation() {
		
	}

	public UserDonation(String created, int money, String name, int status, String text) {
		this.created = created;
		this.money = money;
		this.name = name;
		this.status = status;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDonation [id=" + id + ", created=" + created + ", money=" + money + ", name=" + name + ", status="
				+ status + ", text=" + text + ", donation=" + donation + ", user=" + user + "]";
	}
	
	
	
	
	
	
}
