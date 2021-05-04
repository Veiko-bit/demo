package ee.bcs.valiit.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class BankSqlLoginController {
    @PostMapping("/public/login")
    public String sampleLogin(@RequestBody LoginRequest loginRequest){
        return
    }
}
