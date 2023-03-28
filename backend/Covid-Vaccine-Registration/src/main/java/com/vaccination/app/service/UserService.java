package com.vaccination.app.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.vaccination.app.Entity.Appointment;
import com.vaccination.app.Entity.Dose;
import com.vaccination.app.Entity.User;
import com.vaccination.app.Entity.UserLogin;
import com.vaccination.app.exception.AadharException;
import com.vaccination.app.exception.DoseException;
import com.vaccination.app.exception.UserException;
import com.vaccination.app.exception.VaccineCenterException;
import com.vaccination.app.exception.VaccineException;

public interface UserService {

	// Registering an Applicant
	// Parameter --> IdCard object
	// Return --> Registered Applicant
	public User registerAnUser(User user,Long adno) throws AadharException, UserException;
		
	// Applicant Login
	public ResponseEntity<?> loginUser(UserLogin userLogin, HttpServletResponse response) throws UserException;
	
	//get all Applicant Details
	public List<User> getAllUsers() throws UserException;
	
	//delete Applicant Details
	public Boolean deleteUser(Integer id) throws UserException;
	
    // Get applicant by id
	public User getUserById(Integer id) throws UserException;
	
	// Update applicant details
	public User updateUserDetails(User user, Integer userId) throws UserException;
	
	//Apply for vaccination
		public User applyForVaccination(Integer id,Integer vid,Integer vcid,Integer dose,Appointment appointment) throws DoseException, UserException, VaccineException, VaccineCenterException;
	
	// Checking vaccination status
	public List<String> getVaccinationStatus(String mobile) throws UserException;
	
	// Canceling appointment
	public Appointment cancelAppointment(Integer id) throws UserException, DoseException;
	
	// Change slot for appointment
	public Appointment changeSlot(Appointment appointment) throws UserException;
	
	
	// Checking list of doses of user
	public List<Dose> listOfDosesOfUser(Integer id) throws UserException;
	
	
}
