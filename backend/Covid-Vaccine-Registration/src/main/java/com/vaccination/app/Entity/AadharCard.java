package com.vaccination.app.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class AadharCard {
	@Id
//	@NotNull
	private Long aadharNo;

	@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]")
	private String mobile;

	public AadharCard() {
		super();

	}

	public AadharCard(@NotNull Long aadharNo,
			@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits [0-9]") String mobile) {
		super();
		this.aadharNo = aadharNo;
		this.mobile = mobile;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "AadharCard [aadharNo=" + aadharNo + ", mobile=" + mobile + "]";
	}

}
