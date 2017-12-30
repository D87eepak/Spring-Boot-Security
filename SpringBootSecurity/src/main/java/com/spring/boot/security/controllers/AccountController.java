package com.spring.boot.security.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.security.models.AppUsers;
import com.spring.boot.security.models.Roles;
import com.spring.boot.security.repository.UserRepository;

@RestController
public class AccountController {

	@Autowired
	UserRepository userReposiory;
	@RequestMapping(value="/private/{accNo}")
	public String getPrivateAccountNo(@PathVariable final int accNo){
		
		return "private account No="+String.valueOf(accNo);
	}
	
	@RequestMapping(value="/login")
	public String getLogin(){
		
		return "login";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/public/{accNo}")
	public String getPublicAccountNo(@PathVariable final int accNo){
		return "public account No="+String.valueOf(accNo);
	}
	
	@PostConstruct
	public void save(){
		AppUsers user=new AppUsers();
		List<Roles> roleList=new ArrayList<Roles>();
		user.setUserName("ADMIN");
		user.setPassword("ADMIN");
		Roles role=new Roles();
		role.setRoleName("ADMIN");
		role.setUser(user);
		roleList.add(role);
		user.setRolesList(roleList);
		userReposiory.save(user);
	}
}
