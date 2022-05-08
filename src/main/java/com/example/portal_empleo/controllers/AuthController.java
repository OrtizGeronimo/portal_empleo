package com.example.portal_empleo.controllers;

import com.example.portal_empleo.config.User;
import com.example.portal_empleo.domain.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam("user") String username, @RequestParam("psw") String psw) {
        try {
            Usuario usuario = null; //encontrar el usuario, y según el tipo del mismo vamos a darle los permisos
            if (usuario == null) {
                return "";
            }
            String token = getJWTToken(username);
            User u = new User();
            u.setUsr(username);
            u.setToken(token);
            return "";
        } catch (Exception e) {
            return "";
        }
    }



    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("userJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
