package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class ControllerBankSqlCopy {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //http//localhost:8080/veiko/bank/createAccount1?accountNumber1=EE123§balance=100
    @PostMapping("veiko/bank/createAccount1")
    public void createAccount1(@RequestParam("accountNumber1") String accountNr,
                               @RequestParam("balance") Double balance) {
        String sql = "INSERT INTO bank_accounts(account_number, account_balance) VALUES(:dbAccNo, :dbAmount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        paramMap.put("dbAmount", balance);
        jdbcTemplate.update(sql, paramMap);
    }

    //http://localhost:8080/veiko/bank/account/EE123
    @GetMapping("veiko/bank/account/{accountNumber1}")
    public String getBalance(@PathVariable("accountNumber1") String accountNr) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :dbAccNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return "Konto Balanss on: " + balance;
    }

    //http://localhost:8080/veiko/bank/account/EE123/100
    @PutMapping("veiko/bank/account/{accountNumber1}/{addDeposit}")
    public String addDeposit(@PathVariable("accountNumber1") String accountNr,
                             @PathVariable("addDeposit") Double amount) {
        if (amount > 0) {
            String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :dbAccNo";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("dbAccNo", accountNr);
            Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
            Double newBalance = balance + amount;
            String sql2 = "UPDATE bank_accounts SET account_balance = :a WHERE account_nr = :b";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap.put("a", newBalance);
            paramMap.put("b", accountNr);
            jdbcTemplate.update(sql2, paramMap2);
            return "Account:" + accountNr + " balance is: " + balance;
        } else {
            return "Entered sum has to be positive";
        }
    }

    //http://localhost:8080/veiko/bank/account/EE123/30
    @PutMapping("veiko/bank/account/{accountNumber}/{withdrawMoney}")
    public String withdrawMoney(@PathVariable("accountNumber") String accountNr,
                                @PathVariable("withdrawMoney") Double amount) {
        if (amount > 0) {
            String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :b";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("b", accountNr);
            Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
            Double newBalance = balance - amount;
            if (newBalance >= 0) {
                String sql2 = "UPDATE bank_accounts SET account_balance = :c WHERE account_nr = :b";
                Map<String, Object> paramMap2 = new HashMap<>();
                paramMap2.put("c", newBalance);
                paramMap2.put("b", accountNr);
                jdbcTemplate.update(sql2, paramMap2);
                return "Account:" + accountNr + " balance is: " + balance;
            } else {
                return "not enough money";
            }
        } else {
            return "Entered sum has to be positive";
        }
    }

    //http://localhost:8080/veiko/bank/account/EE123/EE124/20
    @PutMapping("veiko/bank/account/{accountNumber1}/{accountNumber2}/{transferMoney}")
    public String transferMoney(@PathVariable("accountNumber1") String fromAccountNr1,
                                @PathVariable("accountNumber2") String toAccountNr2,
                                @PathVariable("transferMoney") Double amount) {
        if (amount > 0) {
            String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :b";
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("b", fromAccountNr1);
            Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
            Double newBalance = balance - amount;
            if (newBalance < 0) {

                return "Not enough money";
            } else {
                String sql1 = "UPDATE bank_accounts SET account_balance = :c WHERE account_nr = :b";
                Map<String, Object> paramMap1 = new HashMap<>();
                paramMap1.put("c", newBalance);
                paramMap1.put("b", fromAccountNr1);
                jdbcTemplate.update(sql1, paramMap1);

                String sql2 = "SELECT account_balance FROM bank_accounts WHERE account_nr = :c";
                Map<String, Object> paramMap2 = new HashMap<>();
                paramMap2.put("c", toAccountNr2);
                balance = jdbcTemplate.queryForObject(sql2, paramMap2, Double.class);
                newBalance = balance + amount;
                String sql3 = "UPDATE bank_accounts SET account_balance = :d WHERE account_nr = :c";
                Map<String, Object> paramMap3 = new HashMap<>();
                paramMap3.put("a", newBalance);
                paramMap3.put("b", toAccountNr2);
                jdbcTemplate.update(sql3, paramMap3);
                return "success";
            }
        }return "Amount has to be positive";
    }
}


