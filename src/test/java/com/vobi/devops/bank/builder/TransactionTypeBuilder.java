package com.vobi.devops.bank.builder;

import com.vobi.devops.bank.domain.TransactionType;

public class TransactionTypeBuilder {
	
	private TransactionTypeBuilder() {}
	
	public static TransactionType getTransactionTypeConsignacion() {
		TransactionType transactionType=new TransactionType();
		transactionType.setEnable("Y");
		transactionType.setName("CONSIGNACION");
		transactionType.setTrtyId(2);
		
		return transactionType;
	}

}
