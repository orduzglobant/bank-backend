package co.com.coomeva.devops.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.coomeva.devops.bank.dto.DepositDTO;
import co.com.coomeva.devops.bank.dto.TransactionResultDTO;
import co.com.coomeva.devops.bank.dto.TransferDTO;
import co.com.coomeva.devops.bank.dto.WithdrawDTO;
import co.com.coomeva.devops.bank.service.BankTransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*")
public class BankTransactionController {

	@Autowired
	BankTransactionService bankTransaction;

	@PostMapping("/transfer")
	public ResponseEntity<TransactionResultDTO> transfer(@RequestBody TransferDTO transferDTO) throws Exception {

		TransactionResultDTO transactionResultDTO = bankTransaction.transfer(transferDTO);
		return ResponseEntity.ok().body(transactionResultDTO);

	}

	@PostMapping("/withdraw")
	public ResponseEntity<TransactionResultDTO> withdraw(@RequestBody WithdrawDTO withdrawDTO) throws Exception {

		TransactionResultDTO transactionResultDTO = bankTransaction.withdraw(withdrawDTO);
		return ResponseEntity.ok().body(transactionResultDTO);

	}

	@PostMapping("/deposit")
	public ResponseEntity<TransactionResultDTO> deposit(@RequestBody DepositDTO depositDTO) throws Exception {

		TransactionResultDTO transactionResultDTO = bankTransaction.deposit(depositDTO);
		return ResponseEntity.ok().body(transactionResultDTO);

	}

}
