package com.kyepot.bank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyepot.bank.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByAccountNumber(Long accountNumber);

}
