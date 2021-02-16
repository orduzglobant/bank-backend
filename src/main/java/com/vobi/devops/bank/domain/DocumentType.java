package com.vobi.devops.bank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "document_type", schema = "public")

public class DocumentType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "doty_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dotyId;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType")
	private List<Customer> customers = new ArrayList<>();

	public DocumentType(@NotNull Integer dotyId, @NotNull @NotEmpty @Size(max = 255) String enable,
			@NotNull @NotEmpty @Size(max = 255) String name, List<Customer> customers) {
		super();
		this.dotyId = dotyId;
		this.enable = enable;
		this.name = name;
		this.customers = customers;
	}

	public DocumentType() {
		super();
	}

	public Integer getDotyId() {
		return dotyId;
	}

	public void setDotyId(Integer dotyId) {
		this.dotyId = dotyId;
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}