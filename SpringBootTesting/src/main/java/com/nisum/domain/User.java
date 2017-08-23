package com.nisum.domain;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class User {
	
	@Id
    public String strUserId;
    
    public String strPassword;
    public String strFirstName;
    public String strLastName;

    public String getStrUserId() {
		return strUserId;
	}

	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

	public String getStrPassword() {
		return strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public String getStrFirstName() {
		return strFirstName;
	}

	public void setStrFirstName(String strFirstName) {
		this.strFirstName = strFirstName;
	}

	public String getStrLastName() {
		return strLastName;
	}

	public void setStrLastName(String strLastName) {
		this.strLastName = strLastName;
	}

	public User() {
		//TODO: By default constructor
	}
    
}
