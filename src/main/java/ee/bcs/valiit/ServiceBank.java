package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceBank {
    @Autowired
    private RepositoryBank repositoryBank;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount1(String accountNr, Double balance) {
        repositoryBank.createAccount1(accountNr, balance);
    }

    public String getBalance(String accountNr) {
        Double balance = repositoryBank.getBalance(accountNr);
        return "Konto Balanss on: " + balance;
    }

    public String addDeposit(String accountNr, Double amount) {
        if (amount > 0) {
            Double balance = repositoryBank.addDeposit(accountNr, amount);
            Double newBalance = balance + amount;
            repositoryBank.addDeposit(accountNr, amount);
            return "Account:" + accountNr + " balance is: " + balance;
        } else {
            return "Entered sum has to be positive";

    }

    public String withdrawMoney(String accountNr, Double amount) {
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

    public String transferMoney(String fromAccountNr1, String toAccountNr2, Double amount) {
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
                }return "Amount has to be positive";;

    }
}




