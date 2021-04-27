package ee.bcs.valiit;

import ee.bcs.valiit.solution.exception.SampleApplicationException;
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
        if (balance < 0) {
            throw new SampleApplicationException("sum has to be positive");
        }
        repositoryBank.createAccount1(accountNr, balance);
    }

    public String getBalance(String accountNr) {
        Double balance = repositoryBank.getBalance(accountNr);
        return "Konto Balanss on: " + balance;
    }

    public Double addDeposit(String accountNr, Double amount) {
        if (amount < 0) {
            throw new SampleApplicationException("Negative sum is not allowed");
        }
        Double balance = repositoryBank.getBalance(accountNr);
        balance = balance + amount;
        repositoryBank.updateBalance(accountNr, balance);
        return balance;
    }


    public Double withdrawMoney(String accountNr, Double amount) {
        if (amount < 0) {
            throw new SampleApplicationException("Entered sum has to be positive");
        }
        Double balance = repositoryBank.getBalance(accountNr);
        balance = balance - amount;
        repositoryBank.updateBalance(accountNr, balance);
        if (balance <= 0) {
            throw new SampleApplicationException("not enough money");
        }
        return balance;
    }

    public void transferMoney(String fromAccountNr1, String toAccountNr2, Double amount) {
        if (amount < 0) {
            throw new SampleApplicationException("Amount has to be positive");
        }
        Double balance = repositoryBank.getBalance(fromAccountNr1);
        balance = balance - amount;
        repositoryBank.updateBalance(fromAccountNr1, balance);
        if (balance < 0) {
            throw new SampleApplicationException ("Not enough money");
            } else {
            balance = repositoryBank.getBalance(toAccountNr2);
            balance = balance + amount;
            repositoryBank.updateBalance(toAccountNr2, balance);
        }
    }
}



