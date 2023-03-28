package com.vaccination.app.Entity;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserLogin {

	@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]")
	private String mobile;
	
	@JsonProperty(access = Access.WRITE_ONLY)
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$", message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@Pattern(regexp = "[a-zA-Z0-9@#!%^&*]{8,16}", message = "Password must contain min 8 and max 16 alphanumeric ,only @#!%^&* allowed")
	private String password;

	public UserLogin() {
		super();
	}

	public UserLogin(
			@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]") String mobile,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$", message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ") String password) {
		super();
		this.mobile = mobile;
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLogin [mobile=" + mobile + ", password=" + password + "]";
	}
	

}
