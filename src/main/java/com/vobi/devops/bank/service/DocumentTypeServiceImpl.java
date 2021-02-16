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

import com.vobi.devops.bank.domain.Customer;
import com.vobi.devops.bank.domain.DocumentType;
import com.vobi.devops.bank.exception.ZMessManager;
import com.vobi.devops.bank.repository.DocumentTypeRepository;
import com.vobi.devops.bank.utility.Utilities;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */

@Scope("singleton")
@Service

public class DocumentTypeServiceImpl implements DocumentTypeService {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(DocumentType documentType) throws ConstraintViolationException {

		Set<ConstraintViolation<DocumentType>> constraintViolations = validator.validate(documentType);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return documentTypeRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAll() {

		return documentTypeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DocumentType save(DocumentType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("DocumentType");
		}

		validate(entity);

		if (documentTypeRepository.existsById(entity.getDotyId())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return documentTypeRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(DocumentType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("DocumentType");
		}

		if (entity.getDotyId() == null) {
			throw new ZMessManager().new EmptyFieldException("dotyId");
		}

		if (documentTypeRepository.existsById(entity.getDotyId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getDotyId()).ifPresent(entidad -> {
			List<Customer> customers = entidad.getCustomers();
			if (Utilities.validationsList(customers) == true) {
				throw new ZMessManager().new DeletingException("customers");
			}
		});

		documentTypeRepository.deleteById(entity.getDotyId());

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("dotyId");
		}
		if (documentTypeRepository.existsById(id)) {
			delete(documentTypeRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DocumentType update(DocumentType entity) throws Exception {

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("DocumentType");
		}

		validate(entity);

		if (documentTypeRepository.existsById(entity.getDotyId()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return documentTypeRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DocumentType> findById(Integer dotyId) {

		return documentTypeRepository.findById(dotyId);
	}

}
