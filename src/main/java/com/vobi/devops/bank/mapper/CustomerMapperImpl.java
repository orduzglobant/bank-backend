package com.vobi.devops.bank.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

import com.vobi.devops.bank.domain.Customer;
import com.vobi.devops.bank.domain.DocumentType;
import com.vobi.devops.bank.dto.CustomerDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setDotyId_DocumentType( customerDocumentTypeDotyId( customer ) );
        customerDTO.setAddress( customer.getAddress() );
        customerDTO.setCustId( customer.getCustId() );
        customerDTO.setEmail( customer.getEmail() );
        customerDTO.setEnable( customer.getEnable() );
        customerDTO.setName( customer.getName() );
        customerDTO.setPhone( customer.getPhone() );
        customerDTO.setToken( customer.getToken() );

        return customerDTO;
    }

    @Override
    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setDocumentType( customerDTOToDocumentType( customerDTO ) );
        customer.setAddress( customerDTO.getAddress() );
        customer.setCustId( customerDTO.getCustId() );
        customer.setEmail( customerDTO.getEmail() );
        customer.setEnable( customerDTO.getEnable() );
        customer.setName( customerDTO.getName() );
        customer.setPhone( customerDTO.getPhone() );
        customer.setToken( customerDTO.getToken() );

        return customer;
    }

    @Override
    public List<CustomerDTO> listCustomerToListCustomerDTO(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( customerToCustomerDTO( customer ) );
        }

        return list;
    }

    @Override
    public List<Customer> listCustomerDTOToListCustomer(List<CustomerDTO> customerDTOs) {
        if ( customerDTOs == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( customerDTOs.size() );
        for ( CustomerDTO customerDTO : customerDTOs ) {
            list.add( customerDTOToCustomer( customerDTO ) );
        }

        return list;
    }

    private Integer customerDocumentTypeDotyId(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        DocumentType documentType = customer.getDocumentType();
        if ( documentType == null ) {
            return null;
        }
        Integer dotyId = documentType.getDotyId();
        if ( dotyId == null ) {
            return null;
        }
        return dotyId;
    }

    protected DocumentType customerDTOToDocumentType(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        DocumentType documentType = new DocumentType();

        documentType.setDotyId( customerDTO.getDotyId_DocumentType() );

        return documentType;
    }
}
