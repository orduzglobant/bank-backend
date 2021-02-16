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
@Table(name = "customer", schema = "public")

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cust_id", unique = true, nullable = false)
	@NotNull
	private Integer custId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doty_id")
	@NotNull
	private DocumentType documentType;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "address", nullable = false)
	private String address;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "email", nullable = false)
	private String email;
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
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "phone", nullable = false)
	private String phone;
	@Column(name = "token")
	private String token;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Account> accounts = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<RegisteredAccount> registeredAccounts = new ArrayList<>();

	public Customer(@NotNull Integer custId, @NotNull DocumentType documentType,
			@NotNull @NotEmpty @Size(max = 255) String address, @NotNull @NotEmpty @Size(max = 255) String email,
			@NotNull @NotEmpty @Size(max = 255) String enable, @NotNull @NotEmpty @Size(max = 255) String name,
			@NotNull @NotEmpty @Size(max = 255) String phone, String token, List<Account> accounts,
			List<RegisteredAccount> registeredAccounts) {
		super();
		this.custId = custId;
		this.documentType = documentType;
		this.address = address;
		this.email = email;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
		this.accounts = accounts;
		this.registeredAccounts = registeredAccounts;
	}

	public Customer() {
		super();
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<RegisteredAccount> getRegisteredAccounts() {
		return registeredAccounts;
	}

	public void setRegisteredAccounts(List<RegisteredAccount> registeredAccounts) {
		this.registeredAccounts = registeredAccounts;
	}

}