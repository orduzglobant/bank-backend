package com.vobi.devops.bank.entityservice;

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

import com.vobi.devops.bank.domain.UserType;
import com.vobi.devops.bank.domain.Users;
import com.vobi.devops.bank.exception.ZMessManager;
import com.vobi.devops.bank.repository.UserTypeRepository;
import com.vobi.devops.bank.utility.Utilities;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(UserType userType) throws ConstraintViolationException {

		Set<ConstraintViolation<UserType>> constraintViolations = validator.validate(userType);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return userTypeRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserType> findAll() {

		return userTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserType save(UserType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("UserType");
		}

		validate(entity);

		if (userTypeRepository.existsById(entity.getUstyId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return userTypeRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(UserType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("UserType");
		}

		if (entity.getUstyId() == null) {
			throw new ZMessManager().new EmptyFieldException("ustyId");
		}

		if (userTypeRepository.existsById(entity.getUstyId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getUstyId()).ifPresent(entidad -> {
			List<Users> userses = entidad.getUserses();
			if (Utilities.validationsList(userses) == true) {
				throw new ZMessManager().new DeletingException("userses");
			}
		});

		userTypeRepository.deleteById(entity.getUstyId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("ustyId");
		}
		if (userTypeRepository.existsById(id)) {
			delete(userTypeRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserType update(UserType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("UserType");
		}

		validate(entity);

		if (userTypeRepository.existsById(entity.getUstyId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return userTypeRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<UserType> findById(Integer ustyId) {

		return userTypeRepository.findById(ustyId);
	}

}
