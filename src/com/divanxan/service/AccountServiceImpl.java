package com.divanxan.service;

import com.divanxan.data.Account;

import java.math.BigDecimal;
import java.util.Set;

public class AccountServiceImpl implements AccountService {

    private static Set<Account> users;

    public AccountServiceImpl(Set<Account> users) {
        AccountServiceImpl.users = users;
    }

    public static String moneyTransfers(Long sendAccountId, Long receiveAccountId, BigDecimal amount) {
        Account from = null;
        Account to = null;

        for (Account a : users){
            if(a.getId() == sendAccountId) from = a;
            if(a.getId() == receiveAccountId) to = a;
        }
        if(from == null || to == null) return "Id of account is wrong";
        BigDecimal fromM = from.getAccount();
        BigDecimal toM = to.getAccount();

        if(fromM.compareTo(amount)<0) return "Insufficient funds";

        StringBuilder sb = new StringBuilder();
        sb.append("write-off account number: "+ from.getId()+"\n");
        sb.append("write-off amount before: "+ fromM+"\n");
        sb.append("credit account number: "+ to.getId()+"\n");
        sb.append("credit amount before: "+ toM+"\n");

        from.setAccount(fromM.subtract(amount));
        to.setAccount(toM.add(amount));

        sb.append("write-off amount after: "+ from.getAccount()+"\n");
        sb.append("credit amount after: "+ to.getAccount()+"\n");

        return sb.toString();
    }
}
