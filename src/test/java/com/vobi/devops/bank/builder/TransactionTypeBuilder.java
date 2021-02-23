package com.vobi.devops.bank.builder;

import com.vobi.devops.bank.domain.TransactionType;

public class TransactionTypeBuilder {
	
	private TransactionTypeBuilder() {}
	
	
	
	public static TransactionType getTransactionTypeWithdraw() {
		TransactionType transactionType=new TransactionType();
		transactionType.setEnable("Y");
		transactionType.setName("RETIRO");
		transactionType.setTrtyId(1);
		
		return transactionType;
	}
	
	public static TransactionType getTransactionTypeDeposit() {
		TransactionType transactionType=new TransactionType();
		transactionType.setEnable("Y");
		transactionType.setName("CONSIGNACION");
		transactionType.setTrtyId(2);
		
		return transactionType;
	}
	
	public static TransactionType getTransactionTypeTransfer() {
		TransactionType transactionType=new TransactionType();
		transactionType.setEnable("Y");
		transactionType.setName("TRANSFERENCIA");
		transactionType.setTrtyId(3);
		
		return transactionType;
	}

}
