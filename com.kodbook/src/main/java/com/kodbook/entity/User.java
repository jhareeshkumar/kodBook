package com.kodbook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String dob;
    private String gender;

    public User() {
	super();
	// TODO Auto-generated constructor stub
    }

    public User(Long id, String userName, String email, String password, String dob, String gender) {
	super();
	this.id = id;
	this.userName = userName;
	this.email = email;
	this.password = password;
	this.dob = dob;
	this.gender = gender;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getDob() {
	return dob;
    }

    public void setDob(String dob) {
	this.dob = dob;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", dob="
		+ dob + ", gender=" + gender + "]";
    }
}
