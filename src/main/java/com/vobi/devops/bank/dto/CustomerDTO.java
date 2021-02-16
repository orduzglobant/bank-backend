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

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String address;
    @NotNull
    private Integer custId;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String email;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String enable;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String phone;
    private String token;
    private Integer dotyId_DocumentType;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
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
	public Integer getDotyId_DocumentType() {
		return dotyId_DocumentType;
	}
	public void setDotyId_DocumentType(Integer dotyId_DocumentType) {
		this.dotyId_DocumentType = dotyId_DocumentType;
	}
    
    
    
}
