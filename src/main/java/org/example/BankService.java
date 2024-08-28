package org.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    @Override
    public String toString() {
        return "BankService{" +
                "accounts=" + accounts +
                '}';
    }

    Map<String, Account> accounts;

    public String openAccount(Client customer) {
        String accountNumber = "A" + customer.customerNumber();
        Account account = new Account(accountNumber, BigDecimal.ZERO, new ArrayList<>(List.of(customer)));
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    public void transferMoney(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        accounts.get(sourceAccountNumber).withdraw(amount);
        accounts.get(destinationAccountNumber).deposit(amount);
    }

    public List<String> split(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        List<String> newAccounts = new ArrayList<>();
        accounts.remove(accountNumber);
        BigDecimal toDeposit = acc.getBalance().divide(new BigDecimal(acc.getOwners().size()), RoundingMode.DOWN);
        int roundingError = acc.getBalance().subtract(toDeposit.multiply(new BigDecimal(acc.getOwners().size()))).multiply(new BigDecimal(100)).intValue();
        for (Client owner : acc.getOwners()) {
            String newNumber = openAccount(owner);
            newAccounts.add(newNumber);
            accounts.get(newNumber).deposit(toDeposit);
            if (roundingError > 0) {
                accounts.get(newNumber).deposit(new BigDecimal("0.01"));
                roundingError--;
            }
        }
        return newAccounts;
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        accounts.get(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        accounts.get(accountNumber).withdraw(amount);
    }

    public void addOwnerToAccount(String accountNumber, Client customer) {
        accounts.get(accountNumber).getOwners().add(customer);
    }

    public BankService() {
        accounts = new HashMap<>();
    }
}
