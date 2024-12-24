package com.kodbook.dto;

public class UserDto {
    private String username;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String city;
    private String bio;
    private String college;
    private String linkedIn;
    private String gitHub;
    public UserDto() {
	super();
	// TODO Auto-generated constructor stub
    }
    public UserDto(String username, String email, String dateOfBirth, String gender, String city, String bio, String college,
	    String linkedIn, String gitHub) {
	super();
	this.username = username;
	this.email = email;
	this.dateOfBirth = dateOfBirth;
	this.gender = gender;
	this.city = city;
	this.bio = bio;
	this.college = college;
	this.linkedIn = linkedIn;
	this.gitHub = gitHub;
    }
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getdateOfBirth() {
        return dateOfBirth;
    }
    public void setdateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }
    public String getLinkedIn() {
        return linkedIn;
    }
    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }
    public String getGitHub() {
        return gitHub;
    }
    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }
    @Override
    public String toString() {
	return "UserDto [username=" + username + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", city="
		+ city + ", bio=" + bio + ", college=" + college + ", linkedIn=" + linkedIn + ", gitHub=" + gitHub
		+ "]";
    }
}
