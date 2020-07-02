package com.amit.phonebook.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.amit.phonebook.model.Student;
import com.amit.phonebook.repository.StudentRepository;

//@Controller
public class StudentController {
	
	@Autowired
    private StudentRepository studentRepository;

	@GetMapping(value="/student")
    public String viewTheForm(Model model){
        Student newStudent = new Student();
        model.addAttribute("student",newStudent);
        return "student";
    }
	
	@PostMapping("/student")
	public String addStudent(@Valid Student student, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Binding result error");
			return "student";
		}else {
			model.addAttribute("student", student);
			
			if (student.getName() != null) {
                try {
                    // check for comments and if not present set to 'none'
                    String comments = checkNullString(student.getComments());
                    if (comments != "None") {
                        System.out.println("nothing changes");
                    } else {
                        student.setComments(comments);
                    }
                } catch (Exception e) {
 
                    System.out.println(e);
 
                }
//                studentRepository.save(student);
                System.out.println("new student added: " + student);
            }
			
			return "thanks";
		}
		
	}
	
	public String checkNullString(String str){
        String endString = null;
        if(str == null || str.isEmpty()){
            System.out.println("yes it is empty");
            str = null;
            Optional<String> opt = Optional.ofNullable(str);
            endString = opt.orElse("None");
            System.out.println("endString : " + endString);
        }
        else{
            ; //do nothing
        }
         
         
        return endString;
         
    }
}
