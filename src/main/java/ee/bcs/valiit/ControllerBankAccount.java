package ee.bcs.valiit;


import ee.bcs.valiit.solution.controller.SampleCreateAccountRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ControllerBankAccount {
    private static Map<String, Double> accountBalanceMap = new HashMap<>();


    //http//localhost:8080/veiko/bank/createAccount?accountNr=EE123&balance=100
    @GetMapping("veiko/bank/createAccount")
    public void createAccount(@RequestParam("accountNr") String accountNr, @RequestParam("balance") Double balance
    ) {
        accountBalanceMap.put(accountNr, balance);
    }


    // http://localhost:8080/veiko/bank/account/EE123
    @GetMapping("veiko/bank/account/{accountNumber}")
    public String getBalance(@PathVariable("accountNumber") String accountNr) {
        return "Konto balanss on: " + accountBalanceMap.get(accountNr);
    }

    //http://localhost:8080/veiko/bank/account/EE123
    @PutMapping("veiko/bank/account/{accountNumber}/{addDeposit}")
    public String addDeposit(@PathVariable("accountNumber") String accountNr, @PathVariable("addDeposit") Double amount) {

        if (amount > 0) {
            Double currentBalance = accountBalanceMap.get(accountNr) + amount;
            accountBalanceMap.put(accountNr, amount + currentBalance);
            return "Account:" + accountNr + " balance is: " + accountBalanceMap.get(amount);
        }
        else {
            return "";
        }
    }
}
