package com.vaccination.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.Entity.Availability;
import com.vaccination.app.Entity.VaccineCenter;
import com.vaccination.app.exception.VaccineCenterException;
import com.vaccination.app.repository.VaccineCenterRepository;

@Service
public class VaccineCenterServiceImpl implements VaccineCenterService {
	@Autowired
	private VaccineCenterRepository vaccineCenterRepository;

	@Override
	public VaccineCenter addVaccineCenter(VaccineCenter vCenter) throws VaccineCenterException {
		vCenter.setAvailability(Availability.AVAILABLE.toString());
		VaccineCenter vc = vaccineCenterRepository.save(vCenter);
		if (vc == null)
			throw new VaccineCenterException("VaccineCenter not found");
		return vc;
	}

	// Make vaccine center available
	@Override
	public VaccineCenter makeVaccineCenterAvailable(Integer vcid) throws VaccineCenterException {
		Optional<VaccineCenter> opt = vaccineCenterRepository.findById(vcid);
		if (opt.isEmpty())
			throw new VaccineCenterException("Vaccine enter not found");
		VaccineCenter vaccineCenter = opt.get();
		vaccineCenter.setAvailability(Availability.AVAILABLE.toString());
		vaccineCenterRepository.save(vaccineCenter);
		return vaccineCenter;
	}

	// Make vaccine center unavailable
	@Override
	public VaccineCenter makeVaccineCenterUnavailable(Integer vcid) throws VaccineCenterException {
		Optional<VaccineCenter> opt = vaccineCenterRepository.findById(vcid);
		if (opt.isEmpty())
			throw new VaccineCenterException("Vaccine center not found");
		VaccineCenter vaccineCenter = opt.get();
		vaccineCenter.setAvailability(Availability.NOT_AVAILABLE.toString());
		vaccineCenterRepository.save(vaccineCenter);
		return vaccineCenter;
	}

	@Override
	public VaccineCenter updateVaccineCenter(VaccineCenter vCenter) throws VaccineCenterException {
		Optional<VaccineCenter> opt = vaccineCenterRepository.findById(vCenter.getCenterCode());

		if (opt.isPresent()) {
			return vaccineCenterRepository.save(vCenter);
		} else
			throw new VaccineCenterException("VaccineCenter not found");

	}

	@Override
	public VaccineCenter deleteVaccineCenter(Integer centerCode) throws VaccineCenterException {
		Optional<VaccineCenter> opt = vaccineCenterRepository.findById(centerCode);

		if (opt.isPresent()) {

			VaccineCenter existingVaccineCenter = opt.get();

			vaccineCenterRepository.delete(existingVaccineCenter);

			return existingVaccineCenter;

		} else
			throw new VaccineCenterException("Vaccine Center not found with vaccineCenter code:" + centerCode);
	}

	@Override
	public VaccineCenter getVaccineById(Integer centerCode) throws VaccineCenterException {
		Optional<VaccineCenter> opt = vaccineCenterRepository.findById(centerCode);

		if (opt.isPresent()) {

			return opt.get();

		} else
			throw new VaccineCenterException("Vaccine Center not found with vaccine Center code :" + centerCode);
	}

	@Override
	public List<VaccineCenter> getAllVaccineCenter() throws VaccineCenterException {
		List<VaccineCenter> vCenterList = vaccineCenterRepository.findAll();
		if (vCenterList.isEmpty())
			throw new VaccineCenterException("Vaccine Center's not found Please Add Vaccine Center Details");
		else
			return vCenterList;
	}

	@Override
	public Integer getVCIdByName(String name) throws VaccineCenterException {
		int id = vaccineCenterRepository.getIdByName(name);
		if (id != 0) {
			return id;
		} else {
			throw new VaccineCenterException("No vaccine center exist with name:" + name);
		}
	}

}
