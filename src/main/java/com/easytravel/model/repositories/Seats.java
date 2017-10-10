package com.easytravel.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easytravel.model.entities.Seat;

public interface Seats extends JpaRepository<Seat, Long> {
	
	List<Seat> findByUserIsNull();
	Seat findByNumber(String number);
}
