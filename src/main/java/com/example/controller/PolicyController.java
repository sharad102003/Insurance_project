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

import com.example.model.Policy;
import com.example.dao.PolicyRepository;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired 
    PolicyRepository repository;

    @GetMapping("/policies")
    public List<Policy> getPolicies() {
        return repository.findAll();
    }

   
    @PostMapping("/addpolicy")
    public ResponseEntity<?> addPolicy(@RequestBody Policy policy) {
    	System.out.print("the policy data is:"+ policy);
        Optional<Policy> byId = repository.findById(policy.getPolicyID());
        if(byId.isPresent()) {
            return ResponseEntity.status(500).body("Policy already exists");
        } else {
            return ResponseEntity.status(201).body(repository.save(policy));
        }
    }


    @PutMapping("/update")
    public Policy updatePolicy(@RequestBody Policy policy) {
        return repository.save(policy);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable String id) {
        Optional<Policy> byId = repository.findById(id);
        if(byId.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(200).body("Policy has been deleted");
        } else {
            return ResponseEntity.status(404).body("No such policy exists");
        }
    }
}
