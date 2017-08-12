package com.kyepot.bank.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyepot.bank.exception.DuplicateUserException;
import com.kyepot.bank.exception.InsufficientBalanceException;
import com.kyepot.bank.exception.InvalidAccountNumberException;
import com.kyepot.bank.model.Account;
import com.kyepot.bank.model.User;
import com.kyepot.bank.repository.AccountRepository;
import com.kyepot.bank.repository.UserRepository;

@Service
@Transactional(value = TxType.REQUIRED)
public class AccountService implements IAccountService {

    @Autowired
    private UserRepository    userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Long createAccount(User user) {
        User savedUser = null;
        List<User> users = userRepository.findByNameAndEmailAllIgnoreCase(user.getName(), user.getEmail());
        if (users.isEmpty()) {
            Account account = new Account();
            account = accountRepository.save(account);
            user.setAccount(account);
            savedUser = userRepository.save(user);
        } else {
            throw new DuplicateUserException("User with same name and email already exists");
        }
        return savedUser.getAccount().getAccountNumber();
    }

    @Override
    public BigDecimal depositMoney(Long accountNumber, BigDecimal amount) {
        BigDecimal newBalance = null;
        List<Account> accounts = accountRepository.findByAccountNumber(accountNumber);
        if (accounts.isEmpty()) {
            throw new InvalidAccountNumberException("Account doesn't exists");
        } else {
            Account account = accounts.get(0);
            BigDecimal currentBalance = account.getAmount();
            newBalance = currentBalance.add(amount);
            account.setAmount(newBalance);
            accountRepository.save(account);
        }
        return newBalance;
    }

    @Override
    public BigDecimal withdrawMoney(Long accountNumber, BigDecimal amount) {
        BigDecimal newBalance = null;
        List<Account> accounts = accountRepository.findByAccountNumber(accountNumber);
        if (accounts.isEmpty()) {
            throw new InvalidAccountNumberException("Account doesn't exists");
        } else {
            Account account = accounts.get(0);
            BigDecimal currentBalance = account.getAmount();
            if (currentBalance.compareTo(amount) == -1) {
                throw new InsufficientBalanceException();
            }
            newBalance = currentBalance.subtract(amount);
            account.setAmount(newBalance);
            accountRepository.save(account);
        }
        return newBalance;
    }

    @Override
    public Account getAccountDetails(Long accountNumber) {
        Account account = null;
        List<Account> accounts = accountRepository.findByAccountNumber(accountNumber);
        if (accounts.isEmpty()) {
            throw new InvalidAccountNumberException("Account doesn't exists");
        } else {
            account = accounts.get(0);
            account.getUser().setUsername(null);
            account.getUser().setPassword(null);
        }
        return account;
    }

}
