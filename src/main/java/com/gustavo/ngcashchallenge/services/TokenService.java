package com.gustavo.ngcashchallenge.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secretKey;
  private HttpServletRequest request;
  private HttpServletResponse response;

  public TokenService(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }

  public void generateToken(User user){
    Map<String, String> tokenPayload = new HashMap<>();
    tokenPayload.put("id", user.getId().toString());
    tokenPayload.put("name", user.getName());
    tokenPayload.put("username", user.getUsername());
    tokenPayload.put("role", user.getRole().toString());
    tokenPayload.put("accountId", Long.toString(user.getAccount().getId()));

    String token = JWT.create().withIssuer("ngcash-api")
            .withSubject(user.getUsername())
            .withPayload(tokenPayload)
            .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
            .sign(Algorithm.HMAC256(secretKey));

    Cookie cookie = new Cookie("token", token);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    response.addCookie(cookie);
  }



  public String recoverToken(HttpServletRequest request, String cookieName) {
    if(request != null)
      return Arrays.stream(request.getCookies())
              .filter(cookie -> cookieName.equals(cookie.getName()))
              .map(Cookie::getValue)
              .findAny().map(Object::toString).orElse("");
    return "";
  }

  public String validateToken(String token){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      return JWT.require(algorithm).withIssuer("ngcash-api").build().verify(token).getSubject();
    }catch (JWTVerificationException exception){
      return "";
    }
  }

  public  Map<String, Claim> retrieveDataFromToken(String cookieName){
    String token = this.recoverToken(this.request, cookieName);

    Map<String, Claim> jwtData = JWT.decode(token).getClaims();
    return jwtData;
  }
}
