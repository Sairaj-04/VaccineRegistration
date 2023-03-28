package com.vaccination.app.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "name cannot be null.")
	@NotBlank(message = "name cannot be blank.")
	@NotEmpty(message = "name cannot be empty.")
	@Size(min = 3, max = 15, message = "Name should be of 3 to 15 characters")
	private String name;

	@NotNull(message = "gender cannot be null.")
	@NotBlank(message = "gender cannot be blank.")
	@NotEmpty(message = "gender cannot be empty.")
	@Size(max = 1, message = "Enter M/F only")
	private String gender;

	@NotNull(message = "dob cannot be null.")
//	@Past(message = "DoB should be in past.")
	private LocalDate dob;

	// Verified accordingly as per DOB
	private Integer age;

	@NotNull(message = "address details cannot be null.")
	@NotBlank(message = "address details cannot be blank.")
	@NotEmpty(message = "address details cannot be empty.")
	@Size(min = 7, max = 70, message = "Enter your full address")
	private String address;

	@NotNull(message = "city name cannot be null.")
	@NotBlank(message = "city name cannot be blank.")
	@NotEmpty(message = "city name cannot be empty.")
	private String city;

	@NotNull(message = "state name cannot be null.")
	@NotBlank(message = "state name cannot be blank.")
	@NotEmpty(message = "state name cannot be empty.")
	private String state;

	@Pattern(regexp = "^[0-9]{6}", message = "Pincode Length must be 6 digits [0-9]")
	private String pincode;

	@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]")
	private String mobile;

	//@JsonProperty(access = Access.WRITE_ONLY)
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$", message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@Pattern(regexp = "[a-zA-Z0-9@#!%^&*]{8,16}", message = "Password must contain min 8 and max 16 alphanumeric ,only @#!%^&* allowed")
	private String password;

	@OneToOne
//	@JoinColumn(name = "adNo")
	@JoinColumn(name = "aadharNo")
	private AadharCard aadharcard; // unidirectional relation with AadharCard Entity

	@OneToMany(cascade = CascadeType.ALL) // unidirectional relation with Dose Entity
	@JoinColumn(name = "id")
//	@JoinColumn(name = "doseId")
	private List<Dose> doses;

	public User() {
		super();
	}

	public User(Integer id,
			@NotNull(message = "name cannot be null.") @NotBlank(message = "name cannot be blank.") @NotEmpty(message = "name cannot be empty.") @Size(min = 3, max = 15, message = "Name should be of 3 to 15 characters") String name,
			@NotNull(message = "gender cannot be null.") @NotBlank(message = "gender cannot be blank.") @NotEmpty(message = "gender cannot be empty.") @Size(max = 1, message = "Enter M/F only") String gender,
			@NotNull(message = "dob cannot be null.") @Past(message = "DoB should be in past.") LocalDate dob,
			Integer age,
			@NotNull(message = "address details cannot be null.") @NotBlank(message = "address details cannot be blank.") @NotEmpty(message = "address details cannot be empty.") @Size(min = 7, max = 70, message = "Enter your full address") String address,
			@NotNull(message = "city name cannot be null.") @NotBlank(message = "city name cannot be blank.") @NotEmpty(message = "city name cannot be empty.") String city,
			@NotNull(message = "state name cannot be null.") @NotBlank(message = "state name cannot be blank.") @NotEmpty(message = "state name cannot be empty.") String state,
			@Pattern(regexp = "^[0-9]{6}", message = "Pincode Length must be 6 digits [0-9]") String pincode,
			@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]") String mobile,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{6,15}$", message = "password length must contain atleast 6 characters of atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ") String password,
			AadharCard aadharcard, List<Dose> doses) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.mobile = mobile;
		this.password = password;
		this.aadharcard = aadharcard;
		this.doses = doses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public AadharCard getAadharcard() {
		return aadharcard;
	}

	public void setAadharcard(AadharCard aadharcard) {
		this.aadharcard = aadharcard;
	}

	public List<Dose> getDoses() {
		return doses;
	}

	public void setDoses(List<Dose> doses) {
		this.doses = doses;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", age=" + age
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", mobile="
				+ mobile + ", password=" + password + ", aadharcard=" + aadharcard + ", doses=" + doses + "]";
	}

	
}
