package com.vobi.devops.bank.entitycontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.devops.bank.domain.Account;
import com.vobi.devops.bank.dto.AccountDTO;
import com.vobi.devops.bank.entityservice.AccountService;
import com.vobi.devops.bank.mapper.AccountMapper;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
*
*/
@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin(origins = "*")
public class AccountRestController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;

    @GetMapping(value = "/{accoId}")
    public ResponseEntity<?> findById(@PathVariable("accoId")
    String accoId) throws Exception {
        

        Account account = (accountService.findById(accoId).isPresent() == true)
            ? accountService.findById(accoId).get() : null;

        return ResponseEntity.ok()
                             .body(accountMapper.accountToAccountDTO(account));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
       

        return ResponseEntity.ok()
                             .body(accountMapper.listAccountToListAccountDTO(
                accountService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    AccountDTO accountDTO) throws Exception {
       

        Account account = accountMapper.accountDTOToAccount(accountDTO);
        account = accountService.save(account);

        return ResponseEntity.ok()
                             .body(accountMapper.accountToAccountDTO(account));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    AccountDTO accountDTO) throws Exception {
        

        Account account = accountMapper.accountDTOToAccount(accountDTO);
        account = accountService.update(account);

        return ResponseEntity.ok()
                             .body(accountMapper.accountToAccountDTO(account));
    }

    @DeleteMapping(value = "/{accoId}")
    public ResponseEntity<?> delete(@PathVariable("accoId")
    String accoId) throws Exception {
        

        accountService.deleteById(accoId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(accountService.count());
    }
}
