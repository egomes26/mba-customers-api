package br.com.mba.customer.services;

import br.com.mba.customer.documents.User;
import br.com.mba.customer.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    public List<UserDTO> listAll();
    public UserDTO save(UserDTO user);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUUID(UUID uuidUser);
    public UserDTO findById(UUID uuid);
}
