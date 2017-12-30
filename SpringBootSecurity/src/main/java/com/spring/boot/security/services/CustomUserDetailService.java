package com.spring.boot.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.security.models.AppUsers;
import com.spring.boot.security.models.CustomUserDetails;
import com.spring.boot.security.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		AppUsers user=null;
		Optional<AppUsers> optionaluser= userRepository.findByUserName(userName);
		if(optionaluser.isPresent()){
			 user=optionaluser.get();
		}
		return new CustomUserDetails(user);
	}

}
