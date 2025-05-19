package com.kodbook.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class SignupRequest {
    private String username;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String gender;
    private String password;

    public SignupRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SignupRequest(String username, String email, LocalDate dateOfBirth, String gender, String password) {
        super();
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignupRequest [username=" + username + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", gender="
                + gender + ", password=" + password + "]";
    }

}
