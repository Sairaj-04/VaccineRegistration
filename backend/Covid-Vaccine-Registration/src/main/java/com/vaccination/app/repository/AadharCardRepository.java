package com.vaccination.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.app.Entity.AadharCard;
@Repository
public interface AadharCardRepository extends JpaRepository<AadharCard, Long> {

}
