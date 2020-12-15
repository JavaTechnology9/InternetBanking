package com.internetbanking.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.internetbanking.token.UserToken;

/*import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor*/

@Entity
@Table(name="users")
public class User implements Serializable/*UserDetails*/ {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column
	private String username;
	@Column
	private String password;

	@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER )
	@JoinTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	@JsonIgnore
	 private Set<Role> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	//@PrimaryKeyJoinColumn
	@JoinColumn(name = "person_id"/* , referencedColumnName = "user_id" */)
	private Person person;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private UserToken userToken;
	@Column
	private Boolean enabled = false;
	@Column
	private Boolean locked = false;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(boolean enabled) {
		this.enabled=enabled;
	}
	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * final SimpleGrantedAuthority simpleGrantedAuthority = new
	 * SimpleGrantedAuthority(username); return
	 * Collections.singletonList(simpleGrantedAuthority); }
	 * 
	 * @Override public boolean isAccountNonExpired() { // TODO Auto-generated
	 * method stub return false; }
	 * 
	 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
	 * stub return locked; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
	 * method stub return false; }
	 * 
	 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
	 * return enabled; }
	 */
	public boolean isEnabled() { 
		return enabled; }
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public UserToken getUserToken() {
		return userToken;
	}
	public void setUserToken(UserToken userToken) {
		this.userToken = userToken;
	}
	

}
