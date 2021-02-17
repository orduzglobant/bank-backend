package com.vobi.devops.bank.builder;

import com.vobi.devops.bank.domain.Customer;
import com.vobi.devops.bank.domain.DocumentType;

public class CustomerBuilder {
	
	private CustomerBuilder() {}
	
	public static Customer getCustomer() {
		Customer customer=new Customer();
		customer.setCustId(1);
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEmail("dgomez@vortexbird.com");
		customer.setEnable("Y");
		customer.setName("Diego Armando Gomez");
		customer.setPhone("+57 316 482 4629");
		customer.setToken("2713nsandks");
		
		DocumentType documentType=new DocumentType(1,"S","CEDULA",null);
		customer.setDocumentType(documentType);
		
		return customer;
	}

}
