package com.vaccination.app.service;

import java.util.List;

import com.vaccination.app.Entity.Vaccine;
import com.vaccination.app.exception.VaccineException;

public interface VaccineService {
	//Add Vaccine Details
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineException;
    
	//Add Vaccine Details
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineException;
    
	//Get Vaccine Details by vaccine id
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineException;
    
	//Delete Vaccine Details
	public Vaccine deleteVaccine(Integer vaccineId) throws VaccineException;
    
	//Get all Vaccine Details
	public List<Vaccine> getAllVaccine() throws VaccineException;
	
	//get Vaccine code(id) by Name
	public Integer getIdByName(String name) throws VaccineException;

	Vaccine makeVaccineAvailable(Integer vid) throws VaccineException;

	Vaccine makeVaccineUnavailable(Integer vid) throws VaccineException;
}
