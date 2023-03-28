package com.vaccination.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.Entity.Appointment;
import com.vaccination.app.Entity.Dose;
import com.vaccination.app.Entity.Status;
import com.vaccination.app.exception.DoseException;
import com.vaccination.app.repository.DoseRepository;

@Service
public class DoseServiceImpl implements DoseService{

	@Autowired
	private DoseRepository doseRepository;
	
	@Override
	public Dose updateDoseStatus(Integer doseId) throws DoseException {
		Optional<Dose> doseOpt = doseRepository.findById(doseId);
		if(doseOpt.isEmpty())
			throw new DoseException("No dose found");
		
		Dose dose = doseOpt.get();
		Appointment app = dose.getAppointment();
		if(app.getBookingStatus().equals(Status.CANCELED.toString()))
			throw new DoseException("Can not update dose status, because appointment was canceled.");
		
		// If appointment status is completed
		dose.setDoseStatus(Status.COMPLETED.toString());
		
		return doseRepository.save(dose);
	}

	

}
