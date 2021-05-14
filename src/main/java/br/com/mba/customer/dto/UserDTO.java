package br.com.mba.customer.dto;

import br.com.mba.customer.documents.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.UUID;

public class UserDTO {
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    public UserDTO() {
    }

    public UserDTO(UserDTO user) {
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.password;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserDTO userDtoCopy(UserDTO user){
        return new UserDTO(user);
    }
    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email,password);
    }
}
