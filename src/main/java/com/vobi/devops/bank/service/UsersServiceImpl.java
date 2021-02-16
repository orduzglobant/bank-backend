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
import com.vobi.devops.bank.domain.Users;
import com.vobi.devops.bank.exception.ZMessManager;
import com.vobi.devops.bank.repository.UsersRepository;
import com.vobi.devops.bank.utility.Utilities;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Users users) throws ConstraintViolationException {

		Set<ConstraintViolation<Users>> constraintViolations = validator.validate(users);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return usersRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {

		return usersRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users save(Users entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Users");
		}

		validate(entity);

		if (usersRepository.existsById(entity.getUserEmail())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return usersRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Users entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Users");
		}

		if (entity.getUserEmail() == null) {
			throw new ZMessManager().new EmptyFieldException("userEmail");
		}

		if (usersRepository.existsById(entity.getUserEmail()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getUserEmail()).ifPresent(entidad -> {
			List<Transaction> transactions = entidad.getTransactions();
			if (Utilities.validationsList(transactions) == true) {
				throw new ZMessManager().new DeletingException("transactions");
			}
		});

		usersRepository.deleteById(entity.getUserEmail());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("userEmail");
		}
		if (usersRepository.existsById(id)) {
			delete(usersRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Users update(Users entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Users");
		}

		validate(entity);

		if (usersRepository.existsById(entity.getUserEmail()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return usersRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Users> findById(String userEmail) {

		return usersRepository.findById(userEmail);
	}

}
