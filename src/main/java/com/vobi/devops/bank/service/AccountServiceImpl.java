package com.vobi.devops.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.devops.bank.domain.Account;
import com.vobi.devops.bank.domain.RegisteredAccount;
import com.vobi.devops.bank.domain.Transaction;
import com.vobi.devops.bank.exception.ZMessManager;
import com.vobi.devops.bank.repository.AccountRepository;
import com.vobi.devops.bank.utility.Utilities;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service

public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Account account) throws ConstraintViolationException {

		Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return accountRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll() {

		return accountRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Account save(Account entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Account");
		}

		validate(entity);

		if (accountRepository.existsById(entity.getAccoId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return accountRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Account entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Account");
		}

		if (entity.getAccoId() == null) {
			throw new ZMessManager().new EmptyFieldException("accoId");
		}

		if (accountRepository.existsById(entity.getAccoId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getAccoId()).ifPresent(entidad -> {
			List<RegisteredAccount> registeredAccounts = entidad.getRegisteredAccounts();
			if (Utilities.validationsList(registeredAccounts) == true) {
				throw new ZMessManager().new DeletingException("registeredAccounts");
			}
			List<Transaction> transactions = entidad.getTransactions();
			if (Utilities.validationsList(transactions) == true) {
				throw new ZMessManager().new DeletingException("transactions");
			}
		});

		accountRepository.deleteById(entity.getAccoId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("accoId");
		}
		if (accountRepository.existsById(id)) {
			delete(accountRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Account update(Account entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Account");
		}

		validate(entity);

		if (accountRepository.existsById(entity.getAccoId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return accountRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findById(String accoId) {

		return accountRepository.findById(accoId);
	}

}
