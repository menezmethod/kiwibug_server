package com.kb.kiwibugback.auth.payload.request;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String employeeName;

    private Date createdOn;

    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.employeeName;
    }
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
    }
    public void setUsername(final String username) {
        this.username = username;
    }

    public void setName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return this.email;
    }
 
    public void setEmail(final String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return this.password;
    }
 
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return role;
    }
    
    public void setRole(final Set<String> role) {
      this.role = role;
    }
}
