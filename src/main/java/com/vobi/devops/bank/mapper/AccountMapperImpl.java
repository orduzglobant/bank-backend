package com.vobi.devops.bank.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Component;

import com.vobi.devops.bank.domain.Account;
import com.vobi.devops.bank.domain.Customer;
import com.vobi.devops.bank.dto.AccountDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.3.1200.v20200916-0645, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO accountToAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setCustId_Customer( accountCustomerCustId( account ) );
        accountDTO.setAccoId( account.getAccoId() );
        accountDTO.setBalance( account.getBalance() );
        accountDTO.setEnable( account.getEnable() );
        accountDTO.setPassword( account.getPassword() );
        accountDTO.setVersion( account.getVersion() );

        return accountDTO;
    }

    @Override
    public Account accountDTOToAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setCustomer( accountDTOToCustomer( accountDTO ) );
        account.setAccoId( accountDTO.getAccoId() );
        account.setBalance( accountDTO.getBalance() );
        account.setEnable( accountDTO.getEnable() );
        account.setPassword( accountDTO.getPassword() );
        account.setVersion( accountDTO.getVersion() );

        return account;
    }

    @Override
    public List<AccountDTO> listAccountToListAccountDTO(List<Account> accounts) {
        if ( accounts == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( accounts.size() );
        for ( Account account : accounts ) {
            list.add( accountToAccountDTO( account ) );
        }

        return list;
    }

    @Override
    public List<Account> listAccountDTOToListAccount(List<AccountDTO> accountDTOs) {
        if ( accountDTOs == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( accountDTOs.size() );
        for ( AccountDTO accountDTO : accountDTOs ) {
            list.add( accountDTOToAccount( accountDTO ) );
        }

        return list;
    }

    private Integer accountCustomerCustId(Account account) {
        if ( account == null ) {
            return null;
        }
        Customer customer = account.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Integer custId = customer.getCustId();
        if ( custId == null ) {
            return null;
        }
        return custId;
    }

    protected Customer accountDTOToCustomer(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setCustId( accountDTO.getCustId_Customer() );

        return customer;
    }
}
