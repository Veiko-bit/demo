package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
public class ControllerBankSql {
    @Autowired
    private ServiceBank serviceBank;
    @CrossOrigin

    //http://localhost:8080/veiko/bank2/createAccount1?accountNumber1=EE123&balance=100&username=veiko1&password=password1
    @PostMapping("veiko/bank2/createAccount1")
    public void createAccount1(@RequestParam("accountNumber1") String accountNr,
                               @RequestParam("balance") Double balance,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
        serviceBank.createAccount1 (accountNr, balance, username, password);
    }

    //http://localhost:8080/veiko/bank2/account/EE123
    @CrossOrigin
    @GetMapping("veiko/bank2/account/{accountNumber1}")
    public String getBalance(@PathVariable("accountNumber1") String accountNr) {
        return serviceBank.getBalance(accountNr);
    }

    //http://localhost:8080/veiko/bank2/deposit/EE123/100
    @CrossOrigin
    @PutMapping("veiko/bank2/deposit/{accountNumber1}/{addDeposit}")
    public Double addDeposit(@PathVariable("accountNumber1") String accountNr,
                             @PathVariable("addDeposit") Double amount) {
        return serviceBank.addDeposit(accountNr, amount);
    }

    //http://localhost:8080/veiko/bank2/withdraw/EE123/30
    @CrossOrigin
    @PutMapping("veiko/bank2/withdraw/{accountNumber}/{withdrawMoney}")
    public Double withdrawMoney(@PathVariable("accountNumber") String accountNr,
                                @PathVariable("withdrawMoney") Double amount) {
        return serviceBank.withdrawMoney(accountNr, amount);
    }

    //http://localhost:8080/veiko/bank3/account/EE123/EE124/20
    @CrossOrigin
    @PutMapping("veiko/bank3/account/{accountNumber1}/{accountNumber2}/{transferMoney}")
    public void transferMoney(@PathVariable("accountNumber1") String fromAccountNr1,
                                @PathVariable("accountNumber2") String toAccountNr2,
                                @PathVariable("transferMoney") Double amount) {
        serviceBank.transferMoney(fromAccountNr1, toAccountNr2, amount);
    }
}











