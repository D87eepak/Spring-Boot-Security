package com.spring.boot.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.security.models.AppUsers;

public interface UserRepository extends JpaRepository<AppUsers, Long>{

	Optional<AppUsers> findByUserName(String  userName);
}
