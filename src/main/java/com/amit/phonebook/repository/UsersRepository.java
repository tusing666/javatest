package com.amit.phonebook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.amit.phonebook.model.Users;

public interface UsersRepository extends CrudRepository<Users, Integer>{

	List<Users> findByName(String name);
	
	List<Users> findByNameLike(String name);
	
	List<Users> findByNameContaining(String name);
}
