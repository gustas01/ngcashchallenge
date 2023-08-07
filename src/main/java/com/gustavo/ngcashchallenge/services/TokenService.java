package com.gustavo.ngcashchallenge.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TokenService {
  public String recoverToken(HttpServletRequest request, String cookieName) {
    if(request != null)
      return Arrays.stream(request.getCookies())
              .filter(cookie -> cookieName.equals(cookie.getName()))
              .map(Cookie::getValue)
              .findAny().map(Object::toString).orElse("");
    return "";
  }
}
