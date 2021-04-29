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
        Date today = new Date();
        Date tokenExpirationDate = new Date(today.getTime()+1000*60*60*24);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setExpiration(tokenExpirationDate)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"c2VjcmV6")
                .claim("username", loginRequest.getUsername());
        return jwtBuilder.compact();
    }
}
