package com.vaccination.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaccination.app.Entity.Appointment;
import com.vaccination.app.Entity.Dose;
import com.vaccination.app.Entity.User;
import com.vaccination.app.Entity.UserLogin;
import com.vaccination.app.exception.AadharException;
import com.vaccination.app.exception.DoseException;
import com.vaccination.app.exception.JwtTokenMalformedException;
import com.vaccination.app.exception.JwtTokenMissingException;
import com.vaccination.app.exception.UserException;
import com.vaccination.app.exception.VaccineCenterException;
import com.vaccination.app.exception.VaccineException;
import com.vaccination.app.service.UserService;
import com.vaccination.app.util.JWTUtils;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	// Register applicant
	@PostMapping("/register/{adno}")
	public User registerUser(@Valid @RequestBody User user, @PathVariable("adno") Long adno)
			throws AadharException, UserException {
		return userService.registerAnUser(user, adno);
	}

	// Applicant login
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody UserLogin userLogin, HttpServletResponse response)
			throws UserException {
		return userService.loginUser(userLogin, response);
	}

	// Get User
	@GetMapping("/user/{id}")
	public User getUser(@Valid @PathVariable("id") Integer id, HttpServletRequest request) throws UserException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.getUserById(id);
	}

	// Update User
	@PutMapping("/user/{userId}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable("userId") Integer userId, HttpServletRequest request) throws UserException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.updateUserDetails(user, userId);
	}

	// book appointment
	@PostMapping("/vaccination/{id}/{vid}/{vcid}/{dose}")
	public User applyForVaccination(@Valid @RequestBody Appointment appointment, @PathVariable("id") Integer id,
			@PathVariable("vid") Integer vid, @PathVariable("vcid") Integer vcid, @PathVariable("dose") Integer dose, HttpServletRequest request)
			throws UserException, DoseException, VaccineException, VaccineCenterException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.applyForVaccination(id, vid, vcid, dose, appointment);
	}

	// Cancel appointment
	@PutMapping("/vaccination/{doseID}")
	public Appointment cancelAppointment(@Valid @PathVariable("doseID") Integer id, HttpServletRequest request)
			throws UserException, DoseException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.cancelAppointment(id);
	}

	// update slot for appointment
	@PutMapping("/vaccination")
	public Appointment updateSlot(@Valid @RequestBody Appointment appointment, HttpServletRequest request) throws UserException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.changeSlot(appointment);
	}

	// get doses list
	@GetMapping("/doses{userId}")
	public List<Dose> getAllDoses(@Valid @PathVariable("userId") Integer id, HttpServletRequest request) throws UserException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.listOfDosesOfUser(id);
	}

}
