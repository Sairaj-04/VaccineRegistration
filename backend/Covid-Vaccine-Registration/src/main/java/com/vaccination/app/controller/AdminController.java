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

import com.vaccination.app.Entity.Admin;
import com.vaccination.app.Entity.AdminLoginDTO;
import com.vaccination.app.Entity.Dose;
import com.vaccination.app.Entity.User;
import com.vaccination.app.Entity.Vaccine;
import com.vaccination.app.Entity.VaccineCenter;
import com.vaccination.app.exception.AdminException;
import com.vaccination.app.exception.DoseException;
import com.vaccination.app.exception.JwtTokenMalformedException;
import com.vaccination.app.exception.JwtTokenMissingException;
import com.vaccination.app.exception.UserException;
import com.vaccination.app.exception.VaccineCenterException;
import com.vaccination.app.exception.VaccineException;
import com.vaccination.app.service.AdminService;
import com.vaccination.app.service.DoseService;
import com.vaccination.app.service.UserService;
import com.vaccination.app.service.VaccineCenterService;
import com.vaccination.app.service.VaccineService;
import com.vaccination.app.util.JWTUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private VaccineService vaccineService;

	@Autowired
	private VaccineCenterService vaccineCenterService;

	@Autowired
	private UserService userService;

	@Autowired
	private DoseService doseService;

	// Admin register
	@PostMapping("/register")
	public Admin registerAdmin(@Valid @RequestBody Admin a) throws AdminException {
		return adminService.registerAdmin(a);
	}

	// Admin login
	@PostMapping("/login")
	public ResponseEntity<?> loginAdmin(@Valid @RequestBody AdminLoginDTO adminLoginDTO, HttpServletResponse response) throws AdminException {
		
		return adminService.loginAdmin(adminLoginDTO, response);
	}

	// get all vaccine details
	@GetMapping("/vaccines")
	public List<Vaccine> getAllVaccine(HttpServletRequest request) throws VaccineException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineService.getAllVaccine();
	}

	// get all vaccine center details
	@GetMapping("/vaccinecenters")
	public List<VaccineCenter> getAllVaccineCenter(HttpServletRequest request) throws VaccineCenterException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineCenterService.getAllVaccineCenter();
	}

	// add vaccine
	@PostMapping("/vaccine")
	public Vaccine addVaccine(@Valid @RequestBody Vaccine v, HttpServletRequest request) throws VaccineException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineService.addVaccine(v);
	}

	// add vaccine center
	@PostMapping("/vaccinecenter")
	public VaccineCenter addVaccineCenter(@Valid @RequestBody VaccineCenter vc, HttpServletRequest request) throws VaccineCenterException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineCenterService.addVaccineCenter(vc);
	}
	
	// Make vaccine available
	@PutMapping("/vaccineA/{id}")
	public Vaccine makeVaccineAvailable(@Valid @PathVariable("id") Integer id, HttpServletRequest request) throws VaccineException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineService.makeVaccineAvailable(id);
	}

	// Make vaccine unavailable
	@PutMapping("/vaccineU/{id}")
	public Vaccine makeVaccineUnavailable(@Valid @PathVariable("id") Integer id, HttpServletRequest request) throws VaccineException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineService.makeVaccineUnavailable(id);
	}

	// Make vaccine center available
	@PutMapping("/vaccineCenterA/{vcid}")
	public VaccineCenter makeVaccineCenterAvailable(@Valid @PathVariable("vcid") Integer vcid, HttpServletRequest request)
			throws VaccineCenterException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineCenterService.makeVaccineCenterAvailable(vcid);
	}

	// Make vaccine center unavailable
	@PutMapping("/vaccineCenterU/{vcid}")
	public VaccineCenter makeVaccineCenterUnavailable(@Valid @PathVariable("vcid") Integer vcid, HttpServletRequest request)
			throws VaccineCenterException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return vaccineCenterService.makeVaccineCenterUnavailable(vcid);
	}

	// Will update dose status as completed
	@PutMapping("/doseStatus/{doseId}")
	public Dose updateDoseStatus(@Valid @PathVariable("doseId") Integer doseId, HttpServletRequest request) throws DoseException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return doseService.updateDoseStatus(doseId);
	}

	// get all Applicant details
	@GetMapping("/users")
	public List<User> getAllUsers(HttpServletRequest request) throws UserException, JwtTokenMalformedException, JwtTokenMissingException {
		JWTUtils.validateToken(request);
		return userService.getAllUsers();
	}

}
