package com.test.demo.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.test.demo.security.services.UserDetailsServiceImpl;
import com.test.demo.utils.JsonUtil;

import io.jsonwebtoken.ExpiredJwtException;

public class AuthTokenFilter extends OncePerRequestFilter {
  
  @Autowired
  private JwtUtils jwtUtils;
  
  String[] ignoreUri = {"/api/auth/signup","/api/auth/signin"};
  

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
    	
    	
      String jwt = parseJwt(request);
      
      try {
          if (!jwtUtils.validateJwtToken(jwt)) {
              createResposenData(response, HttpStatus.FORBIDDEN, "Invalid JWT token");
          }
      } catch (ExpiredJwtException exj) {
          createResposenData(response, HttpStatus.FORBIDDEN, "Token Expired");
      }
      
      
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }else {
    	  createResposenData(response, HttpStatus.UNAUTHORIZED, "Invalid Token!");
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
      
    }

    filterChain.doFilter(request, response);
  }

  
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
      var uri = request.getRequestURI();


      boolean hasWebjar = uri.indexOf("webjars") != -1;
      boolean hasSwagger = uri.indexOf("swagger-resources") != -1;
      boolean hasActuator = uri.indexOf("actuator") != -1;

      return Arrays.asList(ignoreUri).contains(uri) || hasWebjar || hasSwagger || hasActuator;

  }
  
  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }

    return null;
  }
  
  private void createResposenData(HttpServletResponse response, HttpStatus responseStatus, String errorMsg) throws IOException {

      var map = new HashMap<>();
      map.put("message", errorMsg);
      map.put("code", responseStatus.value()); 

      PrintWriter out = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.setStatus(responseStatus.value());
      out.print(JsonUtil.prettyJSON(map));
      out.flush();
      out.close();
      response.sendError(responseStatus.value(), errorMsg);
  }
}
