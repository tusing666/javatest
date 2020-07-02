package com.amit.phonebook.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "users_log", catalog = "test2")
@Entity
public class UsersLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "log_id")
	private int id;
	
	private String log;
	
//	@Column(name = "user_id")
//	private int userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private Users users;

	public UsersLog() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLog() {
		return log;
	}

	public UsersLog setLog(String log) {
		this.log = log;
		return this;
	}

	public Users getUsers() {
		return users;
	}

	public UsersLog setUsers(Users users) {
		this.users = users;
		return this;
	}

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUser_id(int userId) {
//		this.userId = userId;
//	}
}
