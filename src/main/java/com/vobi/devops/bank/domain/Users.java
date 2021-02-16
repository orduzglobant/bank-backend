package com.vobi.devops.bank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "users", schema = "public")
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_email", unique = true, nullable = false)
	@NotNull
	private String userEmail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usty_id")
	@NotNull
	private UserType userType;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "enable", nullable = false)
	private String enable;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "token")
	private String token;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<Transaction> transactions = new ArrayList<>();

	public Users() {
		super();
	}

	public Users(@NotNull String userEmail, @NotNull UserType userType,
			@NotNull @NotEmpty @Size(max = 255) String enable, @NotNull @NotEmpty @Size(max = 255) String name,
			String token, List<Transaction> transactions) {
		super();
		this.userEmail = userEmail;
		this.userType = userType;
		this.enable = enable;
		this.name = name;
		this.token = token;
		this.transactions = transactions;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}