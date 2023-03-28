package com.vaccination.app.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vaccination.app.Entity.Admin;
import com.vaccination.app.Entity.AdminLoginDTO;
import com.vaccination.app.exception.AdminException;
import com.vaccination.app.repository.AdminRepository;
import com.vaccination.app.util.JWTUtils;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	
	
	

	// Admin Registration
	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		Admin admin3 = adminRepository.findByMobile(admin.getMobile());
		if (admin3 != null)
			throw new AdminException("Mobile Already Registered");
		Admin admin2 = adminRepository.save(admin);
		if (admin2 == null) {
			throw new AdminException("Enter Valid Moblie Number or Password.");
		}
		return admin2;
	}

	@Override
	public ResponseEntity<?> loginAdmin(AdminLoginDTO adminLoginDTO, HttpServletResponse response) throws AdminException {
		Admin admin = this.adminRepository.findByMobile(adminLoginDTO.getMobile());

		if (admin == null)
			throw new AdminException("Your log in mobile number is not exists");

		if (!admin.getPassword().equals(adminLoginDTO.getPassword()))
			throw new AdminException("Your log in Password did not match, please retry");
		
		String token= JWTUtils.generateToken(admin.getAdminId().toString());
		response.setHeader("Authorization", token);
		response.addHeader("token", token);
		response.addHeader("Access-Control-Expose-Headers", "token");
		return ResponseEntity.ok().body(admin);

	}
	
}
