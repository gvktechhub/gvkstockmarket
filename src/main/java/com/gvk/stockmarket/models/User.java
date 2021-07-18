package com.gvk.stockmarket.models;

import java.util.List;

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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	@NonNull
	private String username;
	
	@Column(nullable = false)
	@NonNull
	private String password;
	
	@Column(length = 10, nullable = false, unique = true)
	@NonNull
	private String mobile;
	
	@Column(nullable = false, unique = true)
	@NonNull
	private String email;
	
	@Column
	private Boolean enabled = true;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name="user_roles", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name="role_id"))
	@Column(name="roles")
	@NonNull
	private List<Role> roles; 

}
