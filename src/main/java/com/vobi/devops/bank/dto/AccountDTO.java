package com.vobi.devops.bank.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
*/

public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String accoId;
    @NotNull
    private Double balance;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String enable;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String password;
    @NotNull
    private Long version;
    private Integer custId_Customer;
    
	public AccountDTO(@NotNull @NotEmpty @Size(max = 255) String accoId, @NotNull Double balance,
			@NotNull @NotEmpty @Size(max = 1) String enable, @NotNull @NotEmpty @Size(max = 255) String password,
			@NotNull Long version, Integer custId_Customer) {
		super();
		this.accoId = accoId;
		this.balance = balance;
		this.enable = enable;
		this.password = password;
		this.version = version;
		this.custId_Customer = custId_Customer;
	}

	public AccountDTO() {
		super();
	}

	public String getAccoId() {
		return accoId;
	}

	public void setAccoId(String accoId) {
		this.accoId = accoId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getCustId_Customer() {
		return custId_Customer;
	}

	public void setCustId_Customer(Integer custId_Customer) {
		this.custId_Customer = custId_Customer;
	}
    
    
    
}
