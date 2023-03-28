package com.vaccination.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccination.app.Entity.VaccineCenter;
import com.vaccination.app.exception.VaccineCenterException;

@Repository
public interface VaccineCenterRepository extends JpaRepository<VaccineCenter, Integer> {
	//get Vaccine center list list by center name
	public List<VaccineCenter> getVaccineCenterByCenterName(String centerName) throws VaccineCenterException;
	//get vaccine center id by center name
	@Query("select vc.centerCode from VaccineCenter vc where vc.centerName=?1")
	public Integer getIdByName(String name);
}
