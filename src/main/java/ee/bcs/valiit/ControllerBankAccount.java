package ee.bcs.valiit;


import org.apache.tomcat.util.http.fileupload.ProgressListener;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerBankAccount {
    private static Map<String, Double> accountBalanceMap = new HashMap<>();


    //http//localhost:8080/veiko/bank/createAccount?accountNumber=EE123&balance=100
    @PostMapping("veiko/bank/createAccount")
    public void createAccount(@RequestParam("accountNumber") String accountNr,
                              @RequestParam("balance") Double balance) {
        accountBalanceMap.put(accountNr, balance);
    }


    // http://localhost:8080/veiko/bank/account/EE123?balance=100
    @GetMapping("veiko/bank/account/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") String accountNr,
                             @RequestParam("balance") Double balance) {
        return "Konto balanss on: " + accountBalanceMap.get(accountNr);
    }

    //http://localhost:8080/veiko/bank/account/EE123/100
    @PutMapping("veiko/bank/account/{accountNumber}/{addDeposit}")
    public String addDeposit(@PathVariable("accountNumber") String accountNr,
                             @PathVariable("addDeposit") Double amount) {

        if (amount > 0) {
            Double currentBalance = accountBalanceMap.get(accountNr) + amount;
            accountBalanceMap.put(accountNr, amount + currentBalance);
            return "Account:" + accountNr + " balance is: " + accountBalanceMap.get(amount);
        } else {
            return "Entered sum has to be positive";
        }
    }

    //http://localhost:8080/veiko/bank/account/EE123/30
    @PutMapping("veiko/bank/account/{accountNumber}/{withdrawMoney}")
    public String withdrawMoney(@PathVariable("accountNumber") String accountNr,
                                @PathVariable("withdrawMoney") Double amount) {
        if (amount > 0) {
            Double currentBalance = accountBalanceMap.get(accountNr);
            double newBalance = currentBalance - amount;
            if (newBalance >= 0) {
                accountBalanceMap.put(accountNr, newBalance);
                return "Account:" + accountNr + " balance is: " + accountBalanceMap.get(newBalance);
            } else {
                return "Not enough Money on your account";
            }
        } else {
            return "Entered sum has to be positive";
        }
    }

    //http://localhost:8080/veiko/bank/account/EE123/EE124/20
    @PutMapping("veiko/bank/account/{accountNumber1}/{accountNumber2}/{transferMoney}")
    public String transferMoney(@PathVariable("accountNumber1") String fromAccountNr,
                                @PathVariable("accountNumber2") String toAccountNr,
                                @PathVariable("transferMoney") Double amount) {
        if (amount > 0) {
            double fromAccountBalance = accountBalanceMap.get(fromAccountNr);
            if (fromAccountBalance < amount) {
                return "Not enough money";
            } else {
                double toAccountBalance = accountBalanceMap.get(toAccountNr);
                accountBalanceMap.put(fromAccountNr, fromAccountBalance - amount);
                accountBalanceMap.put(toAccountNr, toAccountBalance + amount);
                return "success";
            }
        } else {
            return "Positive sum is needed";
        }
    }
}


