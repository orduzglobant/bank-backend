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

import com.vobi.devops.bank.domain.RegisteredAccount;
import com.vobi.devops.bank.exception.ZMessManager;
import com.vobi.devops.bank.repository.RegisteredAccountRepository;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service
public class RegisteredAccountServiceImpl implements RegisteredAccountService {
	@Autowired
	private RegisteredAccountRepository registeredAccountRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(RegisteredAccount registeredAccount) throws ConstraintViolationException {
		Set<ConstraintViolation<RegisteredAccount>> constraintViolations = validator.validate(registeredAccount);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return registeredAccountRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegisteredAccount> findAll() {

		return registeredAccountRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RegisteredAccount save(RegisteredAccount entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("RegisteredAccount");
		}

		validate(entity);

		if (registeredAccountRepository.existsById(entity.getReacId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return registeredAccountRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(RegisteredAccount entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("RegisteredAccount");
		}

		if (entity.getReacId() == null) {
			throw new ZMessManager().new EmptyFieldException("reacId");
		}

		if (registeredAccountRepository.existsById(entity.getReacId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		registeredAccountRepository.deleteById(entity.getReacId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("reacId");
		}

		if (registeredAccountRepository.existsById(id)) {
			delete(registeredAccountRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public RegisteredAccount update(RegisteredAccount entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("RegisteredAccount");
		}

		validate(entity);

		if (registeredAccountRepository.existsById(entity.getReacId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return registeredAccountRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<RegisteredAccount> findById(Integer reacId) {

		return registeredAccountRepository.findById(reacId);
	}
}
