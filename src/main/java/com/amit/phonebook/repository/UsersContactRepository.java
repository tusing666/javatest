package com.amit.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.amit.phonebook.model.UsersContact;

public interface UsersContactRepository extends JpaRepository<UsersContact, Integer> {

}
