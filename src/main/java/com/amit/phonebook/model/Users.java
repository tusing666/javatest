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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity()
@Table(name = "users", catalog = "test2") // catalog = database name
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	private String name;
	private Integer salary;
	private String teamName;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
//	private List<UsersLog> usersLogs;
	
	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "contact_id")
	private List<UsersContact> usersContact;
	
	public Users() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public Users setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public Users setName(String name) {
		this.name = name;
		return this;
	}
	public Integer getSalary() {
		return salary;
	}
	public Users setSalary(Integer salary) {
		this.salary = salary;
		return this;
	}
	public String getTeamName() {
		return teamName;
	}
	public Users setTeamName(String teamName) {
		this.teamName = teamName;
		return this;
	}

	public List<UsersContact> getUsersContact() {
		return usersContact;
	}

	public void setUsersContact(List<UsersContact> usersContact) {
		this.usersContact = usersContact;
	}

//	public List<UsersLog> getUsersLogs() {
//		return usersLogs;
//	}
//
//	public Users setUsersLog(List<UsersLog> usersLogs) {
//		this.usersLogs = usersLogs;
//		return this;
//	}

		
}
