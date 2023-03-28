package com.vaccination.app.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingid;

	@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits")
	private String mobile;

	@NotNull
	@FutureOrPresent(message = "Appointment should be present date or future")
	private LocalDate date;

	@NotNull(message = "slot count cannot be null.")
	@NotBlank(message = "slot status cannot be blank.")
	@NotEmpty(message = "slot status cannot be empty.")
	private String slot;

	// BookingStatus will update accordingly
	private String bookingStatus;

	public Appointment() {
		super();
	}

	public Appointment(Integer bookingid,
			@Pattern(regexp = "^[0-9]{10}", message = "Mobile number length must be 10 digits") String mobile,
			@NotNull @FutureOrPresent(message = "Appointment should be present date or future") LocalDate date,
			@NotNull(message = "slot count cannot be null.") @NotBlank(message = "slot status cannot be blank.") @NotEmpty(message = "slot status cannot be empty.") String slot,
			String bookingStatus) {
		super();
		this.bookingid = bookingid;
		this.mobile = mobile;
		this.date = date;
		this.slot = slot;
		this.bookingStatus = bookingStatus;
	}

	public Integer getBookingid() {
		return bookingid;
	}

	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Override
	public String toString() {
		return "Appointment [bookingid=" + bookingid + ", mobile=" + mobile + ", date=" + date + ", slot=" + slot
				+ ", bookingStatus=" + bookingStatus + "]";
	}

}
