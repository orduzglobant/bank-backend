package co.com.coomeva.devops.bank.service;

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

import co.com.coomeva.devops.bank.domain.Transaction;
import co.com.coomeva.devops.bank.domain.TransactionType;
import co.com.coomeva.devops.bank.exception.ZMessManager;
import co.com.coomeva.devops.bank.repository.TransactionTypeRepository;
import co.com.coomeva.devops.bank.utility.Utilities;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

	@Autowired
	private TransactionTypeRepository transactionTypeRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(TransactionType transactionType) throws ConstraintViolationException {

		Set<ConstraintViolation<TransactionType>> constraintViolations = validator.validate(transactionType);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return transactionTypeRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TransactionType> findAll() {
		return transactionTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TransactionType save(TransactionType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TransactionType");
		}

		validate(entity);

		if (transactionTypeRepository.existsById(entity.getTrtyId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return transactionTypeRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TransactionType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TransactionType");
		}

		if (entity.getTrtyId() == null) {
			throw new ZMessManager().new EmptyFieldException("trtyId");
		}

		if (transactionTypeRepository.existsById(entity.getTrtyId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getTrtyId()).ifPresent(entidad -> {
			List<Transaction> transactions = entidad.getTransactions();
			if (Utilities.validationsList(transactions) == true) {
				throw new ZMessManager().new DeletingException("transactions");
			}
		});

		transactionTypeRepository.deleteById(entity.getTrtyId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("trtyId");
		}
		if (transactionTypeRepository.existsById(id)) {
			delete(transactionTypeRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public TransactionType update(TransactionType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("TransactionType");
		}

		validate(entity);

		if (transactionTypeRepository.existsById(entity.getTrtyId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return transactionTypeRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TransactionType> findById(Integer trtyId) {

		return transactionTypeRepository.findById(trtyId);
	}

}
