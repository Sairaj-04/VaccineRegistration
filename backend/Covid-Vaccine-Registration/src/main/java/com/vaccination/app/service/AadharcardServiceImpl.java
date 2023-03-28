package com.vaccination.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.Entity.AadharCard;
import com.vaccination.app.exception.AadharException;
import com.vaccination.app.repository.AadharCardRepository;

@Service
public class AadharcardServiceImpl implements AadharcardService {

	@Autowired
	private AadharCardRepository aadharCardRepository;

	// Add AadharCard of Applicant
	@Override
	public AadharCard addAadharCard(AadharCard aadharCard) throws AadharException {
		if (checkIfRegistered(aadharCard.getAadharNo()))
			throw new AadharException("Aadhar card Already Registered");

		return aadharCardRepository.save(aadharCard);
	}

	// Get AadharCard Details by Aadhar number
	@Override
	public AadharCard getAadharCard(Long aadharNo) throws AadharException {
		Optional<AadharCard> optional = aadharCardRepository.findById(aadharNo);

		if(optional.isEmpty()) {
			throw new AadharException("Aadhar card Not Registered");
		}
		return optional.get();
	}

	// Check AadharaCard is registered or Not
	@Override
	public Boolean checkIfRegistered(Long aadharNo) {
		return aadharCardRepository.findById(aadharNo).isPresent();
	}

}
