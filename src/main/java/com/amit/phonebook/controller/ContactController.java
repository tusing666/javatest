package com.amit.phonebook.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amit.phonebook.model.Users;
import com.amit.phonebook.model.UsersContact;
import com.amit.phonebook.model.UsersLog;
import com.amit.phonebook.repository.UsersRepository;
import com.amit.phonebook.repository.UsersContactRepository;
import com.amit.phonebook.repository.UsersLogRepository;

@Controller
public class ContactController {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UsersContactRepository usersContactRepository;
	@Autowired
	private UsersLogRepository usersLogRepository;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/get")
	@ResponseBody
	public List<Users> getContact(@RequestParam(value = "name") Optional<String> optName, Model model) {

//		System.out.println("name: " + name);
//		List<Name> names = nameRepository.findByName(name);
		List<Users> names = new ArrayList<Users>();

		if(optName.isPresent())
			names = usersRepository.findByNameContaining(optName.get());
//		Name names = nameRepository.findById(1).get();
		
//		System.out.println("Size: "+ names.size());
//		for (Name name1 : names) {
//			
//		}
//		names.forEach( x -> System.out.println("Name: " + x.getName()));
		names.forEach(System.out::println);
		
//		System.out.println("Name: " + names.get(0).getName());
		
//		model.addAttribute("name", name);
		model.addAttribute("name", names.get(0).getName());
//		return "hello";
		return names;
	}
	
	@GetMapping("/all")
	@ResponseBody
	public List<Users> getAllContact(@RequestParam(value = "fields", required = false) Optional<String> optionalFieldName) {

		List<Users> listNames = new ArrayList<Users>();
		List<String> listStr = new ArrayList<String>();
		
//		if(optionalFieldName.isPresent())
//			listNames = nameRepository.findByNameContaining(optionalFieldName.get());
//		listNames.forEach( x -> listStr.add(x.getName()) );
		
//		nameRepository.findAll().forEach( x -> listStr.add(x.getName()) );
		usersRepository.findAll().forEach( x -> listNames.add(x) );
		
		
		
//		return "hello";
//		return listStr;
		return listNames;
	}

	@GetMapping("/addcontact")
	@ResponseBody
	public List<UsersContact> addCont() {
//	public List<UsersLog> addCont() {

		Users users = new Users();
		Users users2 = new Users();
		UsersContact contact = new UsersContact();
		UsersContact contact2 = new UsersContact();
//		 UsersLog usersLog = new UsersLog();
//		 UsersLog usersLog2 = new UsersLog();
//		 
//		 usersLog.setLog("Hi youtube").setUsers(users);
//		 usersLog2.setLog("Hi Viewers").setUsers(users);
		 
		 users.setTeamName("Development")
		 	.setSalary(10000)
		 	.setName("YouTube");
//		 	.setUsersLog(Arrays.asList(usersLog, usersLog2));
		 users.setUsersContact(Arrays.asList(contact, contact2));
		 
		 users2.setTeamName("Development")
		 	.setSalary(10000)
		 	.setName("YouTube");
		 users2.setUsersContact(Arrays.asList(contact, contact2));
		 
		 contact.setPhoneNo(111111);
//		 	.setUser(users);
		 contact.setUsersList(Arrays.asList(users, users2));
		 
		 contact2.setPhoneNo(22222);
		 contact2.setUsersList(Arrays.asList(users, users2));
		 
		 usersRepository.save(users);
		 usersRepository.save(users2);
		 
		 usersContactRepository.save(contact);
		 usersContactRepository.save(contact2);
//		 usersLogRepository.save(usersLog);
//		 usersLogRepository.save(usersLog2);
		 
//		 return usersLogRepository.findAll();
		 
		 return usersContactRepository.findAll();

//		return "Added to database";
	}
	
	@PostMapping("/add")
	public String addContact() {

		return "";
	}
	
	@RequestMapping("/edit")
	public String editContact() {

		return "edit-contact";
	}

	@PostMapping("/update")
	public String updateContact() {

		return "";
	}

	@PostMapping("/delete")
	public String deleteContact() {

		return "";
	}

}
