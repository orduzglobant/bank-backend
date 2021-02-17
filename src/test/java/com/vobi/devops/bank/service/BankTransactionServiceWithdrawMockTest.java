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
import com.vobi.devops.bank.dto.WithdrawDTO;
import com.vobi.devops.bank.exception.ZMessManager.AccountNotEnableException;
import com.vobi.devops.bank.exception.ZMessManager.AccountNotFoundException;
import com.vobi.devops.bank.exception.ZMessManager.UserDisableException;
import com.vobi.devops.bank.exception.ZMessManager.UserNotFoundException;


@ExtendWith(MockitoExtension.class)
class BankTransactionServiceWithdrawMockTest {
	
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
	void debeLanzarExceptionWithdrawDTONull() throws Exception{
		//Arrange
		WithdrawDTO withdrawDTO=null;
		String messageExpected="El WithdrawDTO es nulo";		

        //Act
		Exception exception =assertThrows(Exception.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
		
        //Assert
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeLanzarExceptionAccountIdNull() throws Exception{
		//Arrange
		
		String userEmail="vondrusek1@wisc.edu";
		Double amount=15000.0;
				
		WithdrawDTO withdrawDTO=new WithdrawDTO(null,amount,userEmail);
		String messageExpected="El AccoId es obligatorio";		

        //Act
		Exception exception =assertThrows(Exception.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
		
        //Assert
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeLanzarExceptionAmountMenorACero() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=-1.0;
				
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String messageExpected="El Amount es obligatorio y debe ser mayor que cero";		

        //Act
		Exception exception =assertThrows(Exception.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
		
        //Assert
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeLanzarExceptionAmountNull() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=null;
				
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String messageExpected="El Amount es obligatorio y debe ser mayor que cero";		

        //Act
		Exception exception =assertThrows(Exception.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
		
        //Assert
		assertEquals(messageExpected,exception.getMessage());	
	}
	
	@Test
	void debeLanzarExceptionUserEmailNull() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail=null;
		Double amount=1500000.0;
				
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String messageExpected="El UserEmail es obligatorio";		

        //Act
		Exception exception =assertThrows(Exception.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
		
        //Assert
		assertEquals(messageExpected,exception.getMessage());	
	}
	
	@Test
	void debeLanzarExceptionAccountNotFound() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=1500000.0;
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String messageExpected="The account with id " + accountId +" was not found";
		when(accountService.findById(accountId)).thenReturn(Optional.ofNullable(null));

        //Act
		Exception exception =assertThrows(AccountNotFoundException.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
        //Assert
		
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeLanzarExceptionAccountNoActiva() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=1500000.0;
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String messageExpected="La cuenta con id " + accountId +" no esta activa";
		Account account=AccountBuilder.getAccountDisable();
		when(accountService.findById(accountId)).thenReturn(Optional.ofNullable(account));

        //Act
		Exception exception =assertThrows(AccountNotEnableException.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
        //Assert
		
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeLanzarExceptionUserNotFound() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=1500000.0;
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		String messageExpected="La user con Email " + userEmail +" no esta existe";
		Account account=AccountBuilder.getAccount();
		
		
		
		when(accountService.findById(accountId)).thenReturn(Optional.ofNullable(account));
		when(userService.findById(userEmail)).thenReturn(Optional.ofNullable(null));
		
        //Act
		Exception exception =assertThrows(UserNotFoundException.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
        //Assert
		
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeLanzarExceptionUserDisable() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=1500000.0;
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		
		String messageExpected="El user con Email " + userEmail +" no esta activo";
		Account account=AccountBuilder.getAccount();
		Users user=UsersBuilder.getUsersDisable();
		
		
		when(accountService.findById(accountId)).thenReturn(Optional.ofNullable(account));
		when(userService.findById(userEmail)).thenReturn(Optional.ofNullable(user));
		
        //Act
		Exception exception =assertThrows(UserDisableException.class,()->{
			bankTransactionService.withdraw(withdrawDTO);
		});
        //Assert
		
		assertEquals(messageExpected,exception.getMessage());		
	}
	
	@Test
	void debeWithdraw() throws Exception{
		//Arrange
		String accountId="4640-0341-9387-5781";
		String userEmail="vondrusek1@wisc.edu";
		Double amount=1500000.0;
		WithdrawDTO withdrawDTO=new WithdrawDTO(accountId,amount,userEmail);
		
		TransactionResultDTO transactionResultDTO=null;
		
		
		Account account=AccountBuilder.getAccount();
		Users user=UsersBuilder.getUsers();
		TransactionType transactionType=TransactionTypeBuilder.getTransactionTypeConsignacion();
		
		Double amountExpected=account.getBalance()-amount;
		
		when(accountService.findById(accountId)).thenReturn(Optional.ofNullable(account));
		when(userService.findById(userEmail)).thenReturn(Optional.ofNullable(user));
		when(transactionTypeService.findById(1)).thenReturn(Optional.ofNullable(transactionType));
		
		when(transactionService.save(any(Transaction.class))).then(new Answer<Transaction>() {
	        int sequence = 1;

			@Override
			public Transaction answer(InvocationOnMock invocation) throws Throwable {
				Transaction transaction=invocation.getArgument(0);
				transaction.setTranId(sequence);
				return transaction;
			}
	            
	       
	    });

		transactionResultDTO=bankTransactionService.withdraw(withdrawDTO);
		
        //Assert
		assertEquals(amountExpected, transactionResultDTO.getBalance());				
	}
	
	
	

}
