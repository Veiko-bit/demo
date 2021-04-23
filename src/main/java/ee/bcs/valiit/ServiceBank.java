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

    public Double addDeposit(String accountNr, Double amount) {
        if (amount > 0) {
            Double balance = repositoryBank.getBalance(accountNr);
            balance = balance + amount;
            repositoryBank.updateBalance(accountNr, balance);
            return balance;
        } else {
            return null;
        }
    }

    public String withdrawMoney (String accountNr, Double amount) {
        if (amount > 0) {
            Double balance = repositoryBank.getBalance(accountNr);
            balance = balance - amount;
            repositoryBank.updateBalance(accountNr, balance);
            if (balance >= 0) {
                return "Account:" + accountNr + " balance is: " + balance;
            } else {
                return "not enough money";
            }
        } else {
            return "Entered sum has to be positive";
        }
    }
    public String transferMoney (String fromAccountNr1, String toAccountNr2, Double amount) {
        if (amount > 0) {
            Double balance = repositoryBank.getBalance (fromAccountNr1);
            balance = balance - amount;
            repositoryBank.updateBalance(fromAccountNr1, balance);
            if (balance < 0) {
                return "Not enough money";
            } else {
                balance = repositoryBank.getBalance(toAccountNr2);
                balance = balance + amount;
                repositoryBank.updateBalance(toAccountNr2, balance);



                return "success";
            }
        }return "Amount has to be positive";
    }
}



