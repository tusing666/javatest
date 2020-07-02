package com.amit.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.phonebook.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
