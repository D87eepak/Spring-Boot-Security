package com.spring.boot.security.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AppUsers")
public class AppUsers {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String password;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER,mappedBy="user")
	private List<Roles> rolesList=new ArrayList<Roles>();
	
	public AppUsers(){
		
	}
	
	public AppUsers(AppUsers user){
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.rolesList=user.getRolesList();
		this.id=user.getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Roles> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Roles> rolesList) {
		this.rolesList = rolesList;
	}
	
}
