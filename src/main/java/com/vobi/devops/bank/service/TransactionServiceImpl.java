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

import com.vobi.devops.bank.domain.Transaction;
import com.vobi.devops.bank.exception.ZMessManager;
import com.vobi.devops.bank.repository.TransactionRepository;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(Transaction transaction) throws ConstraintViolationException {
		Set<ConstraintViolation<Transaction>> constraintViolations = validator.validate(transaction);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return transactionRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transaction> findAll() {

		return transactionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transaction save(Transaction entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Transaction");
		}

		validate(entity);

		return transactionRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Transaction entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Transaction");
		}

		if (entity.getTranId() == null) {
			throw new ZMessManager().new EmptyFieldException("tranId");
		}

		if (transactionRepository.existsById(entity.getTranId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		transactionRepository.deleteById(entity.getTranId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("tranId");
		}

		if (transactionRepository.existsById(id)) {
			delete(transactionRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transaction update(Transaction entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Transaction");
		}

		validate(entity);

		if (transactionRepository.existsById(entity.getTranId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return transactionRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Transaction> findById(Integer tranId) {

		return transactionRepository.findById(tranId);
	}
}
