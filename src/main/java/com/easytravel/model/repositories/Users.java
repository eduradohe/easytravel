package com.easytravel.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easytravel.model.entities.User;

public interface Users extends JpaRepository<User, Long> {
	
	User findByName( String name );

}
