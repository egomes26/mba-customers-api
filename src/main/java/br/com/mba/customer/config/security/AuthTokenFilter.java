package br.com.mba.customer.config.security;

import br.com.mba.customer.documents.User;
import br.com.mba.customer.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class AuthTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserService userService;

    public AuthTokenFilter(TokenService tokenService,UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        boolean valid = tokenService.isTokenValid(token);
        if(valid){
            doAuth(token);
        }

        filterChain.doFilter(request,response);
    }

    private void doAuth(String token) {
        UUID uuidUser = tokenService.getUUIDUser(token);
        Optional<User> userOptional = userService.findByUUID(uuidUser);
        User user = userOptional.get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7,token.length());
    }
}
