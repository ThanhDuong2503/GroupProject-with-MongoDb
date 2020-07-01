package de.neuefische.projectplanning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JWTUtils jwtUtils;
  private final MongoDbUserDetailsService detailsService;

  @Autowired
  public JwtAuthFilter(JWTUtils jwtUtils, MongoDbUserDetailsService detailsService) {
    this.jwtUtils = jwtUtils;
    this.detailsService = detailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    Optional<String> jwtToken = getJwtToken(httpServletRequest);

    if (jwtToken.isPresent()) {
      try {
        String userName = jwtUtils.extractUserName(jwtToken.get());
        UserDetails userDetails = detailsService.loadUserByUsername(userName);

        if (jwtUtils.validateToken(jwtToken.get(), userDetails.getUsername())) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }


  private Optional<String> getJwtToken(HttpServletRequest httpServletRequest) {
    String authorizationHeader = httpServletRequest.getHeader("Authorization");

    if (authorizationHeader != null) {
      String token = authorizationHeader.replace("Bearer", "").trim();
      return Optional.of(token);
    }
    return Optional.empty();
  }
}
