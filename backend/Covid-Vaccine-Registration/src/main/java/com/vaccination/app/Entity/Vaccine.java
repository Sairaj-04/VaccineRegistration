package com.vaccination.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Vaccine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineId;

	@NotNull(message = "Vaccine name cannot be null.")
	@NotBlank(message = "Vaccine name cannot be blank.")
	@NotEmpty(message = "Vaccine name cannot be empty.")
	private String name;

	@NotNull(message = "Vaccine description cannot be null.")
	private String description;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String availability;

	public Vaccine() {
		super();
	}

	public Integer getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Vaccine [vaccineId=" + vaccineId + ", name=" + name + ", description=" + description + ", availability="
				+ availability + "]";
	}

	
}
