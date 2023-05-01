package com.banking.controller;

import com.banking.app.BAUtils;
import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.service.AccountService;

public class AccountController {

    private AccountService accountService;

    public AccountController(){
        this.accountService = new AccountService();
    }

    public void createAccount(User user) {
        Account account = new Account(user);
        accountService.save(account);
    }

    public void transferMoney(User user) {
        user.getAccounts().forEach(x-> System.out.println("Hesap no: "+ x.getHesapNo()));
        String fromAccountNo = BAUtils.readString("Hangi hesaptan gönderilecek ");
        String toAccountNo = BAUtils.readString("Hangi hesaba gönderilecek ");
        double amount = BAUtils.readDouble("Ne kadar göndermek istiyorsunuz ");

        accountService.transferMoney(fromAccountNo, toAccountNo, amount, user);
    }
}
