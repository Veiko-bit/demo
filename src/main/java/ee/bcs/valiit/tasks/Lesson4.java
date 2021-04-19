package ee.bcs.valiit.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    // HashMap<String, Account> accountBalanceMap = new HashMap<>();
    private static Map<String, Double> accountBalanceMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Insert command");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            } else if (line.equalsIgnoreCase("createAccount")) {
                // TODO 1
                // Add command: "createAccount ${accountNr}"
                // this has to store accountNr with 0 balance
                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine();
                System.out.println("Please enter inital balance");
                Double balance = scanner.nextDouble();
                scanner.nextLine();
                accountBalanceMap.put(accountNr, balance);
            } else if (line.equalsIgnoreCase("getBalance")) {
                // TODO 2
                // Add command: "getBalance ${accountNr}"
                // this has to display account balance of specific account
                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine();
                System.out.println("Account balance is: " + accountBalanceMap.get(accountNr));
            } else if (line.equalsIgnoreCase("depositMoney")) {
                // TODO 3
                // Add command: "depositMoney ${accountNr} ${amount}
                // this has to add specified amount of money to account
                // You have to check that amount is positive number
                if (line.equalsIgnoreCase("depositMoney")) {
                    System.out.println("Please enter account nr");
                    String accountNr = scanner.nextLine();
                    System.out.println("Please enter amount");
                    Double amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount > 0) {
                        Double currentBalance = accountBalanceMap.get(accountNr);
                        Double newBalance = currentBalance + amount;
                        accountBalanceMap.put(accountNr, newBalance);
                    } else {
                        System.out.println("Sisestatud summa peab olema positiivne number");

                        if (line.equalsIgnoreCase("depositMoney")) {
                            System.out.println("Please enter account nr");
                            accountNr = scanner.nextLine();
                            System.out.println("Please enter amount");
                            amount = scanner.nextDouble();
                            scanner.nextLine();
                        }
                        if (amount > 0) {
                            Double currentBalance = accountBalanceMap.get(accountNr);
                            Double newBalance = currentBalance + amount;
                            accountBalanceMap.put(accountNr, newBalance);
                        } else if (line.equalsIgnoreCase("withdrawMoney")) {
                            System.out.println("Please enter account nr");
                            accountNr = scanner.nextLine();
                            System.out.println("Please enter amount");
                            amount = scanner.nextDouble();
                            scanner.nextLine();
                            if (amount > 0) {
                                Double currentBalance = accountBalanceMap.get(accountNr);
                                Double newBalance = currentBalance - amount;
                                if (newBalance >= 0) {
                                    accountBalanceMap.put(accountNr, newBalance);
                                } else {
                                    System.out.println("Kontol pole piisavalt raha");
                                }


                                // TODO 4
                                // Add command: "withdrawMoney ${accountNr} ${amount}
                                // This has to remove specified amount of money from account
                                // You have to check that amount is positive number
                                // You may not allow this transaction if account balance would become negative

                                // TODO 5
                                // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
                                // This has to remove specified amount from fromAccount and add it to toAccount
                                // Your application needs to check that toAccount is positive
                                // And from account has enough money to do that transaction
                                //  else {
                                System.out.println("Unknown command");

                            }
                        }
                    }
                }
            }
        }
    }
}
