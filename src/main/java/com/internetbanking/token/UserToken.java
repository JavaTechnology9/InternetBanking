package com.internetbanking.token;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.internetbanking.model.User;

@Entity
public class UserToken {
	//We will create a token and a unique link for every user registration
	 private static final int EXPIRATION = 60 * 24;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tokenId;
	@Column
	private String tokenInfo;
	//createdDate field will be used to check if the token is expired or not.
	@Column
	private Date createdDate;
	
	private Date expiryDate;
	
	private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
	public UserToken() {
		
	}
	
	public UserToken(String token) {
		tokenInfo = token;
		this.createdDate = calculateExpiryDate(EXPIRATION);
	}
	 
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id"/* , referencedColumnName = "token_id" */)
	//@PrimaryKeyJoinColumn
	
	private User user;

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenInfo() {
		return tokenInfo;
	}

	public void setTokenInfo(String tokenInfo) {
		this.tokenInfo = tokenInfo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	

	
	
	
}


