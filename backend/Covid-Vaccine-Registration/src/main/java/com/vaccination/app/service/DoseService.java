package com.vaccination.app.service;

import com.vaccination.app.Entity.Dose;
import com.vaccination.app.exception.DoseException;

public interface DoseService {

	public Dose updateDoseStatus(Integer doseId) throws DoseException;
}
