package com.vaccination.app.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vaccination.app.Entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	// getting appointment object by date and slot
	@Query("select a from Appointment a where a.date=?1 and a.slot=?2")
	public Appointment getAppointmentByDateAndSlot(LocalDate date, String slot);
}
