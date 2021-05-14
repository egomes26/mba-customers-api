package br.com.mba.customer.config.security;

import br.com.mba.customer.documents.User;
import br.com.mba.customer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(s);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("User Inválid!");
    }
}
