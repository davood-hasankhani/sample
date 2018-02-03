package com.example.demo;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class Profile implements Serializable {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long eId;
	private String name;
	private String phone;
	private String address;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference// Solved Json recursion issue
	private User user;
	
	public Profile(){}
	public Profile(Long id)
	{ 
		this.eId = id;
	}
	@JsonIgnore
	public Profile( String name, String phone,String address) 
	{
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Long getId() {
		return eId;
	}

	public void setId(Long id) {
		this.eId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		
}
