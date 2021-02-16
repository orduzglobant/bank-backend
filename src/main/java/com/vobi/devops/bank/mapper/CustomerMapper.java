package com.vobi.devops.bank.mapper;

import java.util.List;

import com.vobi.devops.bank.domain.Customer;
import com.vobi.devops.bank.dto.CustomerDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org/
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/

public interface CustomerMapper {
    
    public CustomerDTO customerToCustomerDTO(Customer customer);

    
    public Customer customerDTOToCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> listCustomerToListCustomerDTO(
        List<Customer> customers);

    public List<Customer> listCustomerDTOToListCustomer(
        List<CustomerDTO> customerDTOs);
}
