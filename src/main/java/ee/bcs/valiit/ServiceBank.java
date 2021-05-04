package ee.bcs.valiit;

import ee.bcs.valiit.solution.exception.SampleApplicationException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceBank {
    @Autowired
    private RepositoryBank repositoryBank;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createAccount1(String accountNr, Double balance, String username, String password) {
        if (balance < 0) {
            throw new SampleApplicationException("sum has to be positive");
        }
        String encodedPassword = passwordEncoder.encode(password);
        repositoryBank.createAccount1(accountNr, balance, username, encodedPassword);


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
            throw new SampleApplicationException("Not enough money");
        } else {
            balance = repositoryBank.getBalance(toAccountNr2);
            balance = balance + amount;
            repositoryBank.updateBalance(toAccountNr2, balance);
        }
    }

    public String login(String username, String password) {
        String encodedPassword = repositoryBank.getPassword(username, password);
        if (passwordEncoder.matches(password, encodedPassword)) {
            Date today = new Date();//siia vahele if lause, et alumine osa läheb tööle vaid siis kui user ja parool matchivad
            Date tokenExpirationDate = new Date(today.getTime() + 1000 * 60 * 60 * 24);
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setExpiration(tokenExpirationDate)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "c2VjcmV6")
                    .claim("username", username);
            return jwtBuilder.compact();
        }
        throw new SampleApplicationException("invalid password");
    }

}



