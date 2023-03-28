package com.vaccination.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class VaccineCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer centerCode;

	@NotNull(message = "center name cannot be null.")
	@NotBlank(message = "center name cannot be blank.")
	@NotEmpty(message = "center name cannot be empty.")
	@Size(min = 3, max = 30, message = "center name should of 3-30 characters")
	private String centerName;

	@NotNull(message = "address details cannot be null.")
	@NotBlank(message = "address details cannot be blank.")
	@NotEmpty(message = "address details cannot be empty.")
	@Size(min = 5, max = 50, message = "Enter Center's full address of 5-50 characters")
	private String address;

	@NotNull(message = "city name cannot be null.")
	@NotBlank(message = "city name cannot be blank.")
	@NotEmpty(message = "city name cannot be empty.")
	@Size(min = 3, message = "Enter city name must consist of 3 characters.")
	private String city;

	@NotNull(message = "state name cannot be null.")
	@NotBlank(message = "state name cannot be blank.")
	@NotEmpty(message = "state name cannot be empty.")
	@Size(min = 3, message = "Enter state name must consist of 3 characters.")
	private String state;

	@Pattern(regexp = "^[0-9]{6}", message = "PinCode Length must be 6 digits [0-9]")
	private String pincode;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String availability;

	public VaccineCenter() {
		super();
	}

	public VaccineCenter(Integer centerCode,
			@NotNull(message = "center name cannot be null.") @NotBlank(message = "center name cannot be blank.") @NotEmpty(message = "center name cannot be empty.") @Size(min = 3, max = 30, message = "center name should of 3-30 characters") String centerName,
			@NotNull(message = "address details cannot be null.") @NotBlank(message = "address details cannot be blank.") @NotEmpty(message = "address details cannot be empty.") @Size(min = 5, max = 50, message = "Enter Center's full address of 5-50 characters") String address,
			@NotNull(message = "city name cannot be null.") @NotBlank(message = "city name cannot be blank.") @NotEmpty(message = "city name cannot be empty.") @Size(min = 3, message = "Enter city name must consist of 3 characters.") String city,
			@NotNull(message = "state name cannot be null.") @NotBlank(message = "state name cannot be blank.") @NotEmpty(message = "state name cannot be empty.") @Size(min = 3, message = "Enter state name must consist of 3 characters.") String state,
			@Pattern(regexp = "^[0-9]{6}", message = "PinCode Length must be 6 digits [0-9]") String pincode,
			String availability) {
		super();
		this.centerCode = centerCode;
		this.centerName = centerName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.availability = availability;
	}

	public Integer getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(Integer centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "VaccineCenter [centerCode=" + centerCode + ", centerName=" + centerName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", availability=" + availability
				+ "]";
	}

	
}
