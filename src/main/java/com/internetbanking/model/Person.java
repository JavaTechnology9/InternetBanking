package com.internetbanking.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name="person")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="person_id")
	private Long personId;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id"/* , referencedColumnName = "person_id" */)
	//@PrimaryKeyJoinColumn
	private Address address; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId"/* , referencedColumnName = "person_id" */)
	
	private Account account; 
	@Column
	private String gender;
	@Column
	private String sortname;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id"/* , referencedColumnName = "person_id" */)
	//@PrimaryKeyJoinColumn
	private Contact contact;
	@OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	

}
