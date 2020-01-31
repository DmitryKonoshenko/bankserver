package com.divanxan.service;

import com.divanxan.data.Account;

import java.math.BigDecimal;
import java.util.Set;

public class AccountService {

    private static Set<Account> users;

    public static void initUsers(Set<Account> users) {
        AccountService.users = users;
    }

    public static String moneyTransfers(Long sendAccountId, Long receiveAccountId, BigDecimal amount) {
        Account from = null;
        Account to = null;

        for (Account a : users){
            if(a.getId().equals(sendAccountId)) from = a;
            if(a.getId().equals(receiveAccountId)) to = a;
        }
        if(from == null || to == null) return "Id of account is wrong";
        BigDecimal fromM = from.getAccount();
        BigDecimal toM = to.getAccount();

        if(fromM.compareTo(amount)<0) return "Insufficient funds";

        StringBuilder sb = new StringBuilder();
        sb.append("write-off account number: ").append(from.getId()).append("\n");
        sb.append("write-off amount before: ").append(fromM).append("\n");
        sb.append("credit account number: ").append(to.getId()).append("\n");
        sb.append("credit amount before: ").append(toM).append("\n");

        from.setAccount(fromM.subtract(amount));
        to.setAccount(toM.add(amount));

        sb.append("write-off amount after: ").append(from.getAccount()).append("\n");
        sb.append("credit amount after: ").append(to.getAccount()).append("\n");

        return sb.toString();
    }
}
