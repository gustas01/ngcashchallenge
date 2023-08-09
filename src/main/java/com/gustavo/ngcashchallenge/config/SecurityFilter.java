package com.gustavo.ngcashchallenge.config;

import com.gustavo.ngcashchallenge.repositories.UserRepository;
import com.gustavo.ngcashchallenge.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
  private TokenService tokenService;
  private UserRepository userRepository;

  public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if(request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/auth/register")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = request.getCookies() != null ? tokenService.recoverToken(request, "token") : "";
    if(token != ""){
      var subject = tokenService.validateToken(token);
      UserDetails user = userRepository.findByusername(subject);

      var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

    }
    filterChain.doFilter(request, response);

//    if(token == "") throw new RuntimeException("Token inválido");
  }
}
