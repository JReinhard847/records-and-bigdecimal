package org.example;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*Species dog = new Species("Dog", 200);
        Animal animal1 = new Animal("1", "Bello", dog, 5, new Owner("Frank", 30, "Zuhause"));
        Animal animal2 = new Animal("1", "Garfield", new Species("Cat", 150), 4, new Owner("Maria", 31, "Zuhause"));
        Animal animal3 = new Animal("1", "Bello2", dog, 5, new Owner("Frank", 30, "Zuhause"));
        Zoo zoo = new Zoo(List.of(animal1, animal2, animal3));
        System.out.println(zoo.totalFoodRequirements());*/
        Client c1 = new Client("John", "Doe", 1);
        Client c2 = new Client("Jane", "Doe", 2);
        Client c3 = new Client("Jim", "Doe", 3);
        BankService bank = new BankService();
        String accNum = bank.openAccount(c1);
        bank.addOwnerToAccount(accNum, c2);
        bank.addOwnerToAccount(accNum, c3);
        bank.deposit(accNum, new BigDecimal("100"));
        System.out.println(bank);
        bank.split(accNum);
        System.out.println(bank);

    }
}