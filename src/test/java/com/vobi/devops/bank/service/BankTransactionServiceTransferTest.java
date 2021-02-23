package com.vobi.devops.bank.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

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
import com.vobi.devops.bank.entityservice.AccountServiceImpl;
import com.vobi.devops.bank.entityservice.TransactionServiceImpl;
import com.vobi.devops.bank.entityservice.TransactionTypeServiceImpl;
import com.vobi.devops.bank.entityservice.UsersServiceImpl;
import com.vobi.devops.bank.exception.ZMessManager.AccountNotEnableException;
import com.vobi.devops.bank.exception.ZMessManager.AccountNotFoundException;
import com.vobi.devops.bank.exception.ZMessManager.UserDisableException;
import com.vobi.devops.bank.exception.ZMessManager.UserNotFoundException;


@ExtendWith(MockitoExtension.class)
class BankTransactionServiceTransferTest {
	
	@InjectMocks
	private BankTransactionServiceImpl bankTransactionService;
	
	@Mock
	AccountServiceImpl accountService;

	@Mock
	UsersServiceImpl userService;

	@Mock
	TransactionTypeServiceImpl transactionTypeService;

	@Mock
	TransactionServiceImpl transactionService;
	
	
	@Test
	void debeHacerUnTransfer() throws Exception{
		//Arrange
		String accoIdOrigin="4640-0341-9387-5781";
		String accoIdDestination="6592-7866-3024-5314";
		String accoIdBank="9999-9999-9999-9999";
		Double amount=15000.0;
		String userEmail="vondrusek1@wisc.edu";
		TransactionResultDTO transactionResultDTO=null;
		
		TransferDTO transferDTO=new TransferDTO(accoIdOrigin, accoIdDestination, amount, userEmail);
		
		Account accountOrigin=AccountBuilder.getAccount();
		accountOrigin.setAccoId(accoIdOrigin);
		
		Account accountDestination=AccountBuilder.getAccount();
		accountDestination.setAccoId(accoIdDestination);
		
		Account accountBank=AccountBuilder.getAccount();
		accountBank.setAccoId(accoIdBank);
		
		Users user=UsersBuilder.getUsers();
		
		TransactionType transactionTypeWithdraw=TransactionTypeBuilder.getTransactionTypeWithdraw();
		TransactionType transactionTypeDeposit=TransactionTypeBuilder.getTransactionTypeDeposit();
		TransactionType transactionTypeTransfer=TransactionTypeBuilder.getTransactionTypeTransfer();
		
		Double amountExpected=accountOrigin.getBalance()-amount-2000.0;
		
		when(accountService.findById(accoIdOrigin)).thenReturn(Optional.ofNullable(accountOrigin));
		when(accountService.findById(accoIdDestination)).thenReturn(Optional.ofNullable(accountDestination));
		when(accountService.findById(accoIdBank)).thenReturn(Optional.ofNullable(accountBank));
		
		when(userService.findById(userEmail)).thenReturn(Optional.ofNullable(user));
		
		when(transactionTypeService.findById(1)).thenReturn(Optional.ofNullable(transactionTypeWithdraw));
		when(transactionTypeService.findById(2)).thenReturn(Optional.ofNullable(transactionTypeDeposit));
		when(transactionTypeService.findById(3)).thenReturn(Optional.ofNullable(transactionTypeTransfer));
		
		when(transactionService.save(any(Transaction.class))).then(new Answer<Transaction>() {
	        int sequence = 1;

			@Override
			public Transaction answer(InvocationOnMock invocation) throws Throwable {
				Transaction transaction=invocation.getArgument(0);
				transaction.setTranId(sequence);
				return transaction;
			}            
	       
	    });

		transactionResultDTO=bankTransactionService.transfer(transferDTO);
		
        //Assert
		assertEquals(amountExpected, transactionResultDTO.getBalance());				
	}
	
	
	

}
