package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class ControllerBankSql {
    @Autowired
    private ServiceBank serviceBank;


    //http://localhost:8080/veiko/bank/createAccount1?accountNumber1=EE123§balance=100
    @PostMapping("veiko/bank/createAccount1")
    public void createAccount1(@RequestParam("accountNumber1") String accountNr,
                               @RequestParam("balance") Double balance) {
        serviceBank.createAccount1 (accountNr, balance);
    }

    //http://localhost:8080/veiko/bank/account/EE123
    @GetMapping("veiko/bank/account/{accountNumber1}")
    public String getBalance(@PathVariable("accountNumber1") String accountNr) {
        return serviceBank.getBalance(accountNr);
    }

    //http://localhost:8080/veiko/bank/account/EE123/100
    @PutMapping("veiko/bank/account/{accountNumber1}/{addDeposit}")
    public Double addDeposit(@PathVariable("accountNumber1") String accountNr,
                             @PathVariable("addDeposit") Double amount) {
        return serviceBank.addDeposit(accountNr, amount);
    }

    //http://localhost:8080/veiko/bank/account/EE123/30
    @PutMapping("veiko/bank/account/{accountNumber}/{withdrawMoney}")
    public String withdrawMoney(@PathVariable("accountNumber") String accountNr,
                                @PathVariable("withdrawMoney") Double amount) {
        return serviceBank.withdrawMoney(accountNr, amount);
    }

    //http://localhost:8080/veiko/bank/account/EE123/EE124/20
    @PutMapping("veiko/bank/account/{accountNumber1}/{accountNumber2}/{transferMoney}")
    public String transferMoney(@PathVariable("accountNumber1") String fromAccountNr1,
                                @PathVariable("accountNumber2") String toAccountNr2,
                                @PathVariable("transferMoney") Double amount) {
        return serviceBank.transferMoney(fromAccountNr1, toAccountNr2, amount);
    }
}











