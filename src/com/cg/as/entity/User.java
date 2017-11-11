package com.cg.as.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@SequenceGenerator(name="seq2",sequenceName="userid_sequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq2")
	@Column(name = "userid")
	private int userId;

	@Column(name = "Username")
	@Valid
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 20, message = "About Me must be between 4 and 20 characters")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._]{4,20}$", message = "Error: Username can have only characters,digits, '.(dot)' and '_'")
	private String username;

	@Column(name = "Password")
	@Valid
	@NotNull(message = "Please provide a valid password")
	@Size(min = 8, max = 20, message = "About Me must be between 8 and 20 characters")
	private String password;

	@Column(name = "User_email")
	// @Email(message = "Email should be valid")
	// @NotEmpty(message = "Email is required")
	private String email;

	@Column(name = "Mobile_no")
	// @Pattern(regexp="[1-9][0-9]{9}",message="Error: please provide a valid mobile number")
	private long mobileNo;

	@Column(name = "Role")
	private String role;

	public User() {
		
	}

	public User(String username, String password, String email, long mobileNo,
			String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobileNo = mobileNo;
		this.role = role;
	}

	public String getUsername() {
		return username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
