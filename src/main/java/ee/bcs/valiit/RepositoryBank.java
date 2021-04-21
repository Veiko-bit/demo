package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RepositoryBank {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount1(String accountNr, Double balance) {
        String sql = "INSERT INTO bank_accounts(account_number, account_balance) VALUES(:dbAccNo, :dbAmount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo",accountNr);
        paramMap.put("dbAmount",balance);
        jdbcTemplate.update(sql,paramMap);
    }
    public String getBalance(String accountNr) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :dbAccNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return "Konto Balanss on: " + balance;
    }
    public String addDeposit(String accountNr, Double amount) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :dbAccNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return "Account:" + accountNr + " balance is: " + balance;
    }
    public String withdrawMoney(String accountNr, Double amount) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :b";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("b", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return "Account:" + accountNr + " balance is: " + balance;
    }
    public String transferMoney(String fromAccountNr1, String toAccountNr2, Double amount) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :b";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("b", fromAccountNr1);
        Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class);
        return "Amount has to be positive";
    }


}
