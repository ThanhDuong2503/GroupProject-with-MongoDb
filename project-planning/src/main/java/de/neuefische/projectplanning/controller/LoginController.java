package de.neuefische.projectplanning.controller;

import de.neuefische.projectplanning.model.LoginData;
import de.neuefische.projectplanning.security.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("auth/login")
@RestController
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final JWTUtils jwtUtils;

  public LoginController(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping
  public String login(@RequestBody LoginData data){
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
      return jwtUtils.createToken(new HashMap<>(Map.of("dumbo","yeah")), data.getUsername());
    }catch (Exception e){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials");
    }
  }

}

