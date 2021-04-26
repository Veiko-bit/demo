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
    public Double getBalance(String accountNr) {
        String sql = "SELECT account_balance FROM bank_accounts WHERE account_nr = :dbAccNo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);
    }

    public void updateBalance (String accountNr, Double amount) {
        String sql1 = "UPDATE bank_accounts SET account_balance = :c WHERE account_nr = :b";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("c", amount);
        paramMap1.put("b", accountNr);
        jdbcTemplate.update(sql1, paramMap1);
    }
}
