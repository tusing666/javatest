package com.amit.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.amit.phonebook.repository.UsersContactRepository;

public class NumberService {

	@Autowired
	private UsersContactRepository numberRepository;
}
