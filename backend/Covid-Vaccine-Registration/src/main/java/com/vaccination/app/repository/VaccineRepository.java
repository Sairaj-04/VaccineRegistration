package com.vaccination.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccination.app.Entity.Vaccine;
import com.vaccination.app.exception.VaccineException;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
	//get Vaccine list by vaccine name
	public List<Vaccine> getVaccineByName(String name) throws VaccineException;
	//get vaccine id by vaccine name
	@Query("select v.vaccineId from Vaccine v where v.name=?1")
	public Integer getIdByName(String name);
	
	public Vaccine findByName(String name);
}
