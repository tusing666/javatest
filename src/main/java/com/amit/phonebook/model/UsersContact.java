package com.amit.phonebook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users_contact", catalog = "test2")
public class UsersContact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private int id;
	private int phoneNo;
//	@Column(name = "user_id")
//	private int userId;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn( name="user_id", referencedColumnName = "user_id")
//	private Users users;
	
	@ManyToMany(cascade = CascadeType.ALL,  mappedBy = "usersContact")
//	@JoinColumn()
	private List<Users> usersList;
	
	public UsersContact() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public UsersContact setId(int id) {
		this.id = id;
		return this;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public UsersContact setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
		return this;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

//	public Users getUser() {
//		return users;
//	}
//
//	public UsersContact setUser(Users users) {
//		this.users = users;
//		return this;
//	}

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

//	public Users getUsers() {
//		return users;
//	}
//
//	public void setUsers(Users users) {
//		this.users = users;
//	}
	
}
