package com.example.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    @Column(name= "name", nullable =false, length= 50 )
    private String name;
    @Column(name = "email", nullable= false, length = 100)
    private String email;
    @Column(name = "address", nullable = true, length =200)
    private String address;
    @Column(name = "phone", nullable = false, length =10)
    private long phone;
    
    @OneToMany(targetEntity = Policy.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_fk", referencedColumnName = "customerID")
    private List<Policy> policies;
}
