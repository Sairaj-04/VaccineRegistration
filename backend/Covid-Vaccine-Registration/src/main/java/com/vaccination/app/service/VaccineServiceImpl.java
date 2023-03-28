package com.vaccination.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.Entity.Availability;
import com.vaccination.app.Entity.Vaccine;
import com.vaccination.app.exception.VaccineException;
import com.vaccination.app.repository.VaccineRepository;

@Service
public class VaccineServiceImpl implements VaccineService {
	@Autowired
	private VaccineRepository vaccineRepository;
	
	private String vNA = "Vaccine not found";

	// Add Vaccine Details
	@Override
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineException {
		vaccine.setAvailability(Availability.AVAILABLE.toString());
		
		Vaccine v = vaccineRepository.findByName(vaccine.getName());
		if(v != null)
			throw new VaccineException("Vaccine already present with this name");
		return vaccineRepository.save(vaccine);
	}

	// Make vaccine available
	@Override
	public Vaccine makeVaccineAvailable(Integer vid) throws VaccineException {
		Optional<Vaccine> opt = vaccineRepository.findById(vid);
		if (opt.isEmpty())
			throw new VaccineException(vNA);
		Vaccine vaccine = opt.get();
		vaccine.setAvailability(Availability.AVAILABLE.toString());
		vaccineRepository.save(vaccine);
		return vaccine;
	}

	// Make vaccine unavailable
	@Override
	public Vaccine makeVaccineUnavailable(Integer vid) throws VaccineException {
		Optional<Vaccine> opt = vaccineRepository.findById(vid);
		if (opt.isEmpty())
			throw new VaccineException(vNA);
		Vaccine vaccine = opt.get();
		vaccine.setAvailability(Availability.NOT_AVAILABLE.toString());
		vaccineRepository.save(vaccine);
		return vaccine;
	}

	// upadte vaccine to available
	@Override
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineException {
		Optional<Vaccine> opt = vaccineRepository.findById(vaccine.getVaccineId());

		if (opt.isPresent()) {
			return vaccineRepository.save(vaccine);
		} else
			throw new VaccineException(vNA);

	}

	// Delete Vaccine Details
	@Override
	public Vaccine deleteVaccine(Integer vaccineId) throws VaccineException {
		Optional<Vaccine> opt = vaccineRepository.findById(vaccineId);

		if (opt.isPresent()) {

			Vaccine existingVaccine = opt.get();

			vaccineRepository.delete(existingVaccine);

			return existingVaccine;

		} else
			throw new VaccineException("Vaccine not found with vaccine id :" + vaccineId);
	}

	// Get Vaccine Details by vaccine id
	@Override
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineException {
		Optional<Vaccine> opt = vaccineRepository.findById(vaccineId);

		if (opt.isPresent()) {

			return opt.get();

		} else
			throw new VaccineException("Vaccine not found with vaccine id :" + vaccineId);
	}

	// Get all Vaccine Details
	@Override
	public List<Vaccine> getAllVaccine() throws VaccineException {
		List<Vaccine> vaccList = vaccineRepository.findAll();
		if (vaccList.isEmpty())
			throw new VaccineException("Vaccines not found Please Add Vaccine Details");
		else
			return vaccList;
	}

	// get Vaccine code(id) by Name
	@Override
	public Integer getIdByName(String name) throws VaccineException {
		int id = vaccineRepository.getIdByName(name);
		if (id != 0) {
			return id;
		} else {
			throw new VaccineException("No vaccine exist with name:" + name);
		}
	}

}
