package br.com.mba.customer.controller;

import br.com.mba.customer.config.security.TokenService;
import br.com.mba.customer.documents.User;
import br.com.mba.customer.dto.LoginDTO;
import br.com.mba.customer.dto.UserDTO;
import br.com.mba.customer.services.UserService;
import br.com.mba.customer.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO login) {
//		log.info("salvando armas...");
        UsernamePasswordAuthenticationToken loginData = login.convert();
        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.buildToken(authentication);

            Optional<User> user = userService.findByEmail(login.getEmail());

            return ResponseEntity.ok( new TokenDTO(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }


}
