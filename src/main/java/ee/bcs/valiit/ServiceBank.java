package ee.bcs.valiit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceBank {
    @Autowired
    private RepositoryBank repositoryBank;

    public void createAccount1(String accountNr, Double balance) {
        repositoryBank.createAccount1(accountNr, balance);
    }
    public String getBalance(String accountNr) {
        return repositoryBank.getBalance(accountNr);
    }
    public String addDeposit(String accountNr, Double amount) {
    return repositoryBank.addDeposit(accountNr, amount);
    }
}




