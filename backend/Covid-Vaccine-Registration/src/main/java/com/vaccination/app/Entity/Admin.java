package com.vaccination.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;

	@NotNull(message = "Admin name cannot be null.")
	@Size(min = 3, message = "Admin name must be of atleast 3 character length.")
	private String name;

	@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]")
	private String mobile;

//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$", message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@Pattern(regexp = "[a-zA-Z0-9@#!%^&*]{8,16}", message = "Password must contain min 8 and max 16 alphanumeric ,only @#!%^&* allowed")
	private String password;

	public Admin() {
		super();

	}

	public Admin(Integer adminId,
			@NotNull(message = "Admin name cannot be null.") @Size(min = 3, message = "Admin name must be of atleast 3 character length.") String name,
			@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]") String mobile,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$", message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ") String password) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.mobile = mobile;
		this.password = password;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Admin [adminId=" + adminId + ", name=" + name + ", mobile=" + mobile + ", password=" + password + "]";
	}

}
