package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CustomerRepository;
import com.example.model.Customer;



@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
     CustomerRepository repository;
    
    @GetMapping("/user")
    public List<Customer> getUsers() {
        return repository.findAll();
    }
    
    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody Customer customer) {
    	Optional<Customer> byid = repository.findById(customer.getCustomerID());
    	if(byid.isPresent())
    	{
    		return ResponseEntity.status(500).body("User already exists");
    	}
    	else
    	{
    		return ResponseEntity.status(201).body(repository.save(customer));
    	}
    	
    }
    @PutMapping("/update")
    public Customer updateUser(@RequestBody Customer customer) {
        return repository.save(customer);
        
  
    	
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id)
    {   Optional<Customer> byid = repository.findById(id);
       if(byid.isPresent())
       {
    	   return "No such user exist";
       }
       else
       {   
        repository.deleteById(id);
    	return "user has been deleted";
       }
    }
}
