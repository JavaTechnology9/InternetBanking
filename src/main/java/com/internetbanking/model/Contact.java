package com.internetbanking.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="contact")
public class Contact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contactId;
	@Column
	private String mobilenumber;
	@Column
	private String email;
	@Column
	private boolean isPrimaryPhone;
	
	@OneToOne(mappedBy = "contact",cascade = CascadeType.ALL)
	private Person person;

	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPrimaryPhone() {
		return isPrimaryPhone;
	}
	public void setPrimaryPhone(boolean isPrimaryPhone) {
		this.isPrimaryPhone = isPrimaryPhone;
	}
	
	
	 

}
