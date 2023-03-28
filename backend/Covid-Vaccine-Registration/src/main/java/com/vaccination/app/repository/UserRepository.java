package com.vaccination.app.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccination.app.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// finding an applicant by mobile number and dob
//	@Query("select i from IdCard i where i.mobile=?1 AND i.dob=?2")
	@Query("select u from User u where u.mobile=?1 AND u.dob=?2")
	public User findByMobAndDob(String mobile, LocalDate dob);
	
	// getting applicant by mobile number
	public User findByMobile(String mobile);
	
	
}
