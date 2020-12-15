package com.internetbanking.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long roleId;
	
	@Column
	private int name;
	
	@ManyToMany(mappedBy = "roles" , cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER /* , targetEntity = User.class */)
	/* @Access(AccessType.PROPERTY) */
	@JsonIgnore
	  private Set<User> user = new HashSet<>();

	
	public Long getId() {
		return roleId;
	}

	public void setId(Long roleId) {
		this.roleId = roleId;
	}

	public int getName() {
		return name;
	}

	public void setName(int user) {
		this.name = user;
	}
	public Set<User> getUsers() {
		return user;
	}

	public void setUsers(Set<User> user) {
		this.user = user;
	}

}
