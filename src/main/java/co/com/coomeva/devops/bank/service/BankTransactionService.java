package co.com.coomeva.devops.bank.service;

import co.com.coomeva.devops.bank.dto.DepositDTO;
import co.com.coomeva.devops.bank.dto.TransactionResultDTO;
import co.com.coomeva.devops.bank.dto.TransferDTO;
import co.com.coomeva.devops.bank.dto.WithdrawDTO;

public interface BankTransactionService {

	TransactionResultDTO withdraw(WithdrawDTO withdrawDTO) throws Exception;

	TransactionResultDTO deposit(DepositDTO depositDTO) throws Exception;

	TransactionResultDTO transfer(TransferDTO transferDTO) throws Exception;

}
