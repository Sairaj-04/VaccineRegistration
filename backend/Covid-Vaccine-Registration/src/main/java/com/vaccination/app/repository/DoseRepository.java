package com.vaccination.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccination.app.Entity.Dose;
import com.vaccination.app.Entity.VaccineCenter;

public interface DoseRepository extends JpaRepository<Dose, Integer> {
	//get Dose list by center 
	public List<Dose> findByCenter(VaccineCenter center);
}
