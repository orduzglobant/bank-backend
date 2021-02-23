package com.vobi.devops.bank.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vobi.devops.bank.builder.AccountBuilder;
import com.vobi.devops.bank.builder.TransactionTypeBuilder;
import com.vobi.devops.bank.builder.UsersBuilder;
import com.vobi.devops.bank.domain.Account;
import com.vobi.devops.bank.domain.Transaction;
import com.vobi.devops.bank.domain.TransactionType;
import com.vobi.devops.bank.domain.Users;
import com.vobi.devops.bank.dto.DepositDTO;
import com.vobi.devops.bank.dto.TransactionResultDTO;
import com.vobi.devops.bank.entityservice.AccountServiceImpl;
import com.vobi.devops.bank.entityservice.TransactionServiceImpl;
import com.vobi.devops.bank.entityservice.TransactionTypeServiceImpl;
import com.vobi.devops.bank.entityservice.UsersServiceImpl;
import com.vobi.devops.bank.service.BankTransactionServiceImpl;

@WebMvcTest(value = BankTransactionController.class)
//@ExtendWith(SpringExtension.class)
class BankTransactionControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	BankTransactionServiceImpl bankTransactionService;
	

	@Test
	void debeRetornar200EnDeposit() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=15000.0;
		Double amountExpected=85000.0;
		
		TransactionResultDTO transactionResultDTO=new TransactionResultDTO(34, amountExpected);
		
		DepositDTO depositDTO=new DepositDTO(accountId,amount,userEmail);
		String jsonDepositDTO=objectMapper.writeValueAsString(depositDTO);
		
		when(bankTransactionService.deposit(depositDTO)).thenReturn(transactionResultDTO);

		mockMvc.perform(post("/api/v1/transactions/deposit")
				.contentType("application/json")
				.content(jsonDepositDTO))
				.andExpect(status().isOk())
				.andReturn();
	
	}
	
	@Test
	void debeRetornar400PorqueAccoIdEsNullEnDeposit() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=15000.0;
		Double amountExpected=85000.0;
		
		
		DepositDTO depositDTO=new DepositDTO(null,amount,userEmail);
		TransactionResultDTO transactionResultDTO=new TransactionResultDTO(34, amountExpected);
		
		String jsonDepositDTO=objectMapper.writeValueAsString(depositDTO);
		
		when(bankTransactionService.deposit(depositDTO)).thenReturn(transactionResultDTO);

		mockMvc.perform(post("/api/v1/transactions/deposit")
				.contentType("application/json")
				.content(jsonDepositDTO))
				.andExpect(status().isBadRequest())
				.andReturn();				
	}
	
	
	
	

}
