package com.vobi.devops.bank.builder;

import com.vobi.devops.bank.domain.Account;

public class AccountBuilder {
	
	private AccountBuilder() {}
	
	public static Account getAccount() {
		
		Account account=new Account();
		account.setAccoId("4640-0341-9387-5781");
		account.setBalance(1500000.0);
		account.setEnable("S");
		account.setPassword("hfkusdhjfkjsdf");
		account.setVersion(1L);
		account.setCustomer(CustomerBuilder.getCustomer());
		
		return account;
	}
	
	
	public static Account getAccountDisable() {
		
		Account account=getAccount();
		account.setEnable("N");
		
		return account;
	}

}
