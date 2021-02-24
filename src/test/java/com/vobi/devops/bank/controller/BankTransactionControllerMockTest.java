package com.vobi.devops.bank.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.vobi.devops.bank.dto.DepositDTO;
import com.vobi.devops.bank.dto.TransactionResultDTO;
import com.vobi.devops.bank.service.BankTransactionService;

@ExtendWith(MockitoExtension.class)
class BankTransactionControllerMockTest {
	
	@Mock
	BankTransactionService bankTransactionService;
	
	@InjectMocks
	BankTransactionController bankTransactionController;
	
	
	@Test
	void debeRetornar200EnDeposit() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=15000.0;
		ResponseEntity<TransactionResultDTO> transactionResultDTOExpected=null;
		DepositDTO depositDTO=new DepositDTO(accountId,amount,userEmail);
		
		
		TransactionResultDTO transactionResultDTO=new TransactionResultDTO(32, 85000.0);
		
		
		when(bankTransactionService.deposit(depositDTO)).thenReturn(transactionResultDTO);

		//Act
		transactionResultDTOExpected=bankTransactionController.deposit(depositDTO);
		
		
		String statusCode=transactionResultDTOExpected.getStatusCode().toString();
		System.out.println(statusCode);
	}
	
	
}
