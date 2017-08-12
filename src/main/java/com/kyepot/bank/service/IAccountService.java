package com.kyepot.bank.service;

import java.math.BigDecimal;

import com.kyepot.bank.model.Account;
import com.kyepot.bank.model.User;

public interface IAccountService {

    Long createAccount(User user);

    BigDecimal depositMoney(Long accountNumber, BigDecimal amount);

    BigDecimal withdrawMoney(Long accountNumber, BigDecimal amount);

    Account getAccountDetails(Long accountNumber);
}
