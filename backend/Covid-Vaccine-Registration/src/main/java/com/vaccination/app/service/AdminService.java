package com.vaccination.app.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.vaccination.app.Entity.Admin;
import com.vaccination.app.Entity.AdminLoginDTO;
import com.vaccination.app.exception.AdminException;

public interface AdminService {
	// Register Admin
	public Admin registerAdmin(Admin admin) throws AdminException;

	// Login Admin
	public ResponseEntity<?> loginAdmin(AdminLoginDTO adminLoginDTO, HttpServletResponse response) throws AdminException;

}
