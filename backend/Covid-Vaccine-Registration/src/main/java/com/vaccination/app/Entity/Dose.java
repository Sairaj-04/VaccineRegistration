package com.vaccination.app.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Dose {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer doseId;

	@NotNull(message = "Dose count cannot be null.")
	@Min(value = 1, message = "dose count cannot be less than 0")
	@Max(value = 2, message = "dose count cannot be more than 2")
	private Integer doseCount;

	@NotNull(message = "Dose count cannot be null.")
	@NotBlank(message = "Dose status cannot be blank.")
	@NotEmpty(message = "Dose status cannot be empty.")
	private String doseStatus;

	@ManyToOne(cascade = CascadeType.ALL) // unidirection
	@JoinColumn(name = "vaccineId")
	private Vaccine vaccine;

	@ManyToOne(cascade = CascadeType.ALL) // unidirection
	@JoinColumn(name = "bookingid")
	private Appointment appointment;

	@ManyToOne(cascade = CascadeType.ALL) // unidirection
	@JoinColumn(name = "centerCode")
	private VaccineCenter center;

	public Dose() {
		super();
	}

	public Dose(Integer doseId,
			@NotNull(message = "Dose count cannot be null.") @Min(value = 1, message = "dose count cannot be less than 0") @Max(value = 2, message = "dose count cannot be more than 2") Integer doseCount,
			@NotNull(message = "Dose count cannot be null.") @NotBlank(message = "Dose status cannot be blank.") @NotEmpty(message = "Dose status cannot be empty.") String doseStatus,
			Vaccine vaccine, Appointment appointment, VaccineCenter center) {
		super();
		this.doseId = doseId;
		this.doseCount = doseCount;
		this.doseStatus = doseStatus;
		this.vaccine = vaccine;
		this.appointment = appointment;
		this.center = center;
	}

	public Integer getDoseId() {
		return doseId;
	}

	public void setDoseId(Integer doseId) {
		this.doseId = doseId;
	}

	public Integer getDoseCount() {
		return doseCount;
	}

	public void setDoseCount(Integer doseCount) {
		this.doseCount = doseCount;
	}

	public String getDoseStatus() {
		return doseStatus;
	}

	public void setDoseStatus(String doseStatus) {
		this.doseStatus = doseStatus;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public VaccineCenter getCenter() {
		return center;
	}

	public void setCenter(VaccineCenter center) {
		this.center = center;
	}

	@Override
	public String toString() {
		return "Dose [doseId=" + doseId + ", doseCount=" + doseCount + ", doseStatus=" + doseStatus + ", vaccine="
				+ vaccine + ", appointment=" + appointment + ", center=" + center + "]";
	}

}
