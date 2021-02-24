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
import com.vobi.devops.bank.dto.TransferDTO;
import com.vobi.devops.bank.dto.WithdrawDTO;
import com.vobi.devops.bank.entityservice.AccountServiceImpl;
import com.vobi.devops.bank.entityservice.TransactionServiceImpl;
import com.vobi.devops.bank.entityservice.TransactionTypeServiceImpl;
import com.vobi.devops.bank.entityservice.UsersServiceImpl;
import com.vobi.devops.bank.service.BankTransactionService;
import com.vobi.devops.bank.service.BankTransactionServiceImpl;

@WebMvcTest(value = BankTransactionController.class)
class BankTransactionControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	BankTransactionService bankTransactionService;
	

	@Test
	void debeRetornar200EnDeposit() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=15000.0;
		
		DepositDTO depositDTO=new DepositDTO(accountId,amount,userEmail);
		String jsonDepositDTO=objectMapper.writeValueAsString(depositDTO);
		
		TransactionResultDTO transactionResultDTO=new TransactionResultDTO(32, 85000.0);
		
		when(bankTransactionService.deposit(depositDTO)).thenReturn(transactionResultDTO);

		mockMvc.perform(post("/api/v1/transactions/deposit")
				.contentType("application/json")
				.content(jsonDepositDTO))
				.andExpect(status().isOk())
				.andReturn();
	
	}
	
	@Test
	void debeRetornar200EnWithdraw() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=15000.0;
		
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String jsonWithdrawDTO=objectMapper.writeValueAsString(withdrawDTO);

		mockMvc.perform(post("/api/v1/transactions/withdraw")
				.contentType("application/json")
				.content(jsonWithdrawDTO))
				.andExpect(status().isOk())
				.andReturn();
	
	}
	
	@Test
	void debeRetornar200EnTransfer() throws Exception{
		String accoIdOrigin="4640-0341-9387-5781";
		String accoIdDestination="6592-7866-3024-5314";		
		Double amount=15000.0;
		String userEmail="vondrusek1@wisc.edu";
		
		TransferDTO transferDTO=new TransferDTO(accoIdOrigin, accoIdDestination, amount, userEmail);
		
		String jsonTransferDTO=objectMapper.writeValueAsString(transferDTO);

		mockMvc.perform(post("/api/v1/transactions/transfer")
				.contentType("application/json")
				.content(jsonTransferDTO))
				.andExpect(status().isOk())
				.andReturn();
	
	}
	
	@Test
	void debeRetornar400EnDeposit() throws Exception{
		//Arrange		
		DepositDTO depositDTO=new DepositDTO(null,null,null);
		String jsonDepositDTO=objectMapper.writeValueAsString(depositDTO);
		
		mockMvc.perform(post("/api/v1/transactions/deposit")
				.contentType("application/json")
				.content(jsonDepositDTO))
				.andExpect(status().isBadRequest())
				.andReturn();				
	}
	
	
	
	

}
