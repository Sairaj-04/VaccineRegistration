package com.vaccination.app.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vaccination.app.Entity.AadharCard;
import com.vaccination.app.Entity.Appointment;
import com.vaccination.app.Entity.Availability;
import com.vaccination.app.Entity.Dose;
import com.vaccination.app.Entity.Status;
import com.vaccination.app.Entity.User;
import com.vaccination.app.Entity.UserLogin;
import com.vaccination.app.Entity.Vaccine;
import com.vaccination.app.Entity.VaccineCenter;
import com.vaccination.app.exception.AadharException;
import com.vaccination.app.exception.DoseException;
import com.vaccination.app.exception.UserException;
import com.vaccination.app.exception.VaccineCenterException;
import com.vaccination.app.exception.VaccineException;
import com.vaccination.app.repository.AadharCardRepository;
import com.vaccination.app.repository.AppointmentRepository;
import com.vaccination.app.repository.DoseRepository;
import com.vaccination.app.repository.UserRepository;
import com.vaccination.app.repository.VaccineCenterRepository;
import com.vaccination.app.repository.VaccineRepository;
import com.vaccination.app.util.JWTUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AadharCardRepository aadharCardRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private VaccineRepository vaccineRepository;

	@Autowired
	private VaccineCenterRepository vaccineCenterRepository;

	@Autowired
	private DoseRepository doseRepository;

	// Register user Details
	@Override
	public User registerAnUser(User user, Long aadharNo) throws AadharException, UserException {
		Optional<AadharCard> op = aadharCardRepository.findById(aadharNo);
		User op1 = userRepository.findByMobile(user.getMobile());
		if (op.isPresent() || op1 != null) {
			throw new AadharException("AadharCard or Moblie is Already Registered!!");
		}
		AadharCard aadharCard = new AadharCard();
		aadharCard.setAadharNo(aadharNo);
		aadharCard.setMobile(user.getMobile());
		int age = Period.between(user.getDob(), LocalDate.now()).getYears();
		if (age >= 18)
			user.setAge(age);
		else
			throw new UserException("Applicant age must be at least 18 years");

		user.setAadharcard(aadharCard);
		aadharCardRepository.save(aadharCard);
		User registeredUser = userRepository.save(user);
		if (registeredUser != null) {
			return registeredUser;
		} else {
			throw new UserException("Registration failed! Please try again with valid credentials. :)");
		}
	}

	@Override
	public ResponseEntity<?> loginUser(UserLogin userLogin, HttpServletResponse response) throws UserException {
		User user = this.userRepository.findByMobile(userLogin.getMobile());

		if (user == null)
			throw new UserException("Your mobile number is not registered");

		if (!user.getPassword().equals(userLogin.getPassword()))
			throw new UserException("Your password did not match, please retry");

		String token = JWTUtils.generateToken(user.getId().toString());
		response.setHeader("Authorization", token);
		response.addHeader("token", token);
		response.addHeader("Access-Control-Expose-Headers", "token");
		return ResponseEntity.ok().body(user);

	}

	// get all user Details
	@Override
	public List<User> getAllUsers() throws UserException {
		List<User> list = userRepository.findAll();
		if (list.isEmpty()) {
			throw new UserException("No Applicant Details");
		}
		return list;
	}

	// delete user Details
	@Override
	public Boolean deleteUser(Integer id) throws UserException {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isEmpty()) {
			throw new UserException("Applicant Id is not Correct");
		}
		User card = opt.get();
		userRepository.delete(card);
		return true;
	}

	// Get applicant by id
	@Override
	public User getUserById(Integer id) throws UserException {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new UserException("No applicant found with this Id");
		}
	}

	// Update applicant details
	@Override
	public User updateUserDetails(User user, Integer userId) throws UserException {
		User users = getUserById(userId);
		User updatedUserDetails = user;
		updatedUserDetails.setAadharcard(users.getAadharcard());
		updatedUserDetails.setDoses(users.getDoses());

		updatedUserDetails = userRepository.save(user);
		if (updatedUserDetails != null) {
			return updatedUserDetails;
		} else {
			throw new UserException("No such applicant found." + user);
		}
	}

	// Apply for vaccination
	@Override
	public User applyForVaccination(Integer id, Integer vid, Integer vcid, Integer dose, Appointment appointment)
			throws DoseException, UserException, VaccineException, VaccineCenterException {
		if (dose == 0 || dose > 2) {
			throw new DoseException("Dose can be 1 or 2 !!!");
		}
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new UserException("Applicant Id is Not Correct");
		}
		User user = userOptional.get();
		if (user.getAge() < 18) {
			throw new UserException("Applicant Age is Less Than 18");
		}
		List<Dose> doses = user.getDoses();
		if (doses.size() >= 2) {
			throw new DoseException("Both the Doses Already Taken");
		} else if (doses.size() == 1) {

			if (dose == 1) {
				throw new DoseException("First Dose Already Taken");
			}
			Dose tempDose = doses.get(0);
			if (tempDose.getDoseStatus().equals(Status.PENDING.toString())) {
				throw new DoseException("First Dose is PENDING!! You cant apply for Second");
			}

			// Change
			Optional<Vaccine> vaccOptional = vaccineRepository.findById(vid);
			if (!tempDose.getVaccine().getName().equals(vaccOptional.get().getName())) {
				throw new VaccineException("You can not change vaccine for second dose, please select " + tempDose.getVaccine().getName());
			}
			//
		} else if (dose == 2 && doses.isEmpty()) {
			throw new DoseException("Dose 1 not taken!! you cant apply for dose 2...");
		}

		Optional<Vaccine> vaccOptional = vaccineRepository.findById(vid);
		if (vaccOptional.isEmpty()) {
			throw new VaccineException("Vaccine is Not Available");
		}
		Vaccine vaccine = vaccOptional.get();
		// Change
		if (vaccine.getAvailability().equals(Availability.NOT_AVAILABLE.toString()))
			throw new VaccineException(vaccine.getName() + " is not availble currently.");

		Optional<VaccineCenter> vsOptional = vaccineCenterRepository.findById(vcid);
		if (vsOptional.isEmpty()) {
			throw new VaccineCenterException("VaccineCenter is Not Available");
		}
		VaccineCenter vaccineCenter = vsOptional.get();
		// Change
		if (vaccineCenter.getAvailability().equals(Availability.NOT_AVAILABLE.toString()))
			throw new VaccineCenterException(vaccineCenter.getCenterName() + " is not available currently, please choose another center.");

		Dose doseObj = new Dose();
		doseObj.setAppointment(appointment);
		doseObj.setCenter(vaccineCenter);
		doseObj.setDoseCount(dose);
		doseObj.setVaccine(vaccine);
		doseObj.setDoseStatus(Status.PENDING.toString());

		doses.add(doseObj);
		appointment.setBookingStatus(Status.COMPLETED.toString());

		// Validation of slot availability
		List<Dose> dosesOfCenter = doseRepository.findByCenter(vaccineCenter);
		for (Dose d : dosesOfCenter) {
			Appointment app = d.getAppointment();
			String status = d.getDoseStatus();
			LocalDate date = app.getDate();
			String slot = app.getSlot();
			if (date.toString().equals(appointment.getDate().toString())) {
				if (status.equals(Status.PENDING.toString())) {
					if (slot.equals(appointment.getSlot())) {
						throw new DoseException("Slot Already Booked!!");
					}
				}
			}
		}
		userRepository.save(user);

		return user;
	}

	// Checking list of doses of user
	public List<Dose> listOfDosesOfUser(Integer id) throws UserException {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isEmpty())
			throw new UserException("User not found.");

		User user = userOpt.get();
		return user.getDoses();
	}

	// Checking vaccination status
	@Override
	public List<String> getVaccinationStatus(String mobile) throws UserException {

		User existingUser = userRepository.findByMobile(mobile);
		if (existingUser != null) {
			String name = existingUser.getName();
			List<Dose> doses = existingUser.getDoses();

			Dose[] dosesArr = doses.toArray(new Dose[doses.size()]);

			List<String> list = new ArrayList<>();

			for (int i = 0; i < doses.size(); i++) {
				int doseCount = dosesArr[i].getDoseCount();
				String doseStatus = dosesArr[i].getDoseStatus();

				String vaccineName = dosesArr[i].getVaccine().getName();

				String str = "UserName: " + name + ", DoseCount: " + doseCount + ", DoseStatus: " + doseStatus
						+ ", VaccineName: " + vaccineName;

				list.add(str);
			}

			return list;
		} else {
			throw new UserException("No user found with this moble number: " + mobile);
		}
	}

	// changed(softdelete)
	@Override
	public Appointment cancelAppointment(Integer id) throws DoseException {
		Optional<Dose> opt = doseRepository.findById(id);
		if (opt.isEmpty()) {
			throw new DoseException("Dose id is invalid");
		}
		Dose dose = opt.get();
		Appointment ap = dose.getAppointment();
		ap.setBookingStatus(Status.CANCELED.toString());

		appointmentRepository.save(ap);
		return ap;
	}

	// Change slot for appointment
	@Override
	public Appointment changeSlot(Appointment appointment) throws UserException {
		Optional<Appointment> opt = appointmentRepository.findById(appointment.getBookingid());
		if (opt.isEmpty()) {
			throw new UserException("Booking ID of Appointment is Not Correct");
		}
		// changed
		appointment.setBookingStatus(Status.COMPLETED.toString());
		return appointmentRepository.save(appointment);
	}

}
