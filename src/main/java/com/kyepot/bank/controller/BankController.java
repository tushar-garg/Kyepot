package com.kyepot.bank.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyepot.bank.model.Account;
import com.kyepot.bank.model.User;
import com.kyepot.bank.service.AccountService;

@Controller
@RequestMapping(path = "/account/operation")
public class BankController {
    @Autowired
    private AccountService accountService;

    @PutMapping(path = "/createAccount")
    public @ResponseBody Map<String, String> createAccount(@RequestBody User user) {
        Long account = accountService.createAccount(user);
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", account.toString());
        return map;
    }

    @PostMapping(path = "/depositMoney")
    public @ResponseBody Map<String, String> depositMoney(@RequestBody Account account) {
        BigDecimal newBalance = accountService.depositMoney(account.getAccountNumber(), account.getAmount());
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", account.getAccountNumber().toString());
        map.put("newBalance", newBalance.toPlainString());
        return map;
    }

    @PostMapping(path = "/withdrawMoney")
    public @ResponseBody Map<String, String> withdrawMoney(@RequestBody Account account) {
        BigDecimal newBalance = accountService.withdrawMoney(account.getAccountNumber(), account.getAmount());
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", account.getAccountNumber().toString());
        map.put("newBalance", newBalance.toPlainString());
        return map;
    }

    @GetMapping(path = "/accountDetails")
    public @ResponseBody Map<String, String> createAccount(@RequestParam Long accountNumber) {
        Account account = accountService.getAccountDetails(accountNumber);
        Map<String, String> map = new HashMap<>();
        map.put("accountNumber", account.getAccountNumber().toString());
        map.put("balance", account.getAmount().toPlainString());
        map.put("name", account.getUser().getName());
        map.put("email", account.getUser().getEmail());
        return map;
    }

}
