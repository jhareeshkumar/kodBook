package com.kodbook.dto;

public class UserDto {
    private String userName;
    private String email;
    private String dob;
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
    public UserDto(String userName, String email, String dob, String gender, String city, String bio, String college,
	    String linkedIn, String gitHub) {
	super();
	this.userName = userName;
	this.email = email;
	this.dob = dob;
	this.gender = gender;
	this.city = city;
	this.bio = bio;
	this.college = college;
	this.linkedIn = linkedIn;
	this.gitHub = gitHub;
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
	return "UserDto [userName=" + userName + ", email=" + email + ", dob=" + dob + ", gender=" + gender + ", city="
		+ city + ", bio=" + bio + ", college=" + college + ", linkedIn=" + linkedIn + ", gitHub=" + gitHub
		+ "]";
    }
}
