package com.vobi.devops.bank.mapper;

import java.util.List;

import com.vobi.devops.bank.domain.Account;
import com.vobi.devops.bank.dto.AccountDTO;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
* Mapper Build with MapStruct https://mapstruct.org/
* MapStruct is a code generator that greatly simplifies the
* implementation of mappings between Java bean type
* based on a convention over configuration approach.
*/

public interface AccountMapper {
    
    public AccountDTO accountToAccountDTO(Account account);

    
    public Account accountDTOToAccount(AccountDTO accountDTO);

    public List<AccountDTO> listAccountToListAccountDTO(List<Account> accounts);

    public List<Account> listAccountDTOToListAccount(
        List<AccountDTO> accountDTOs);
}
