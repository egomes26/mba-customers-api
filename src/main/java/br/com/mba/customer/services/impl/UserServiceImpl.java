package br.com.mba.customer.services.impl;

import br.com.mba.customer.dto.UserDTO;
import br.com.mba.customer.documents.User;
import br.com.mba.customer.repositories.UserRepository;
import br.com.mba.customer.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> listAll() {
        List<User> questions = repository.findAll();
//        List<Question> all = repository.findAll();

        return questions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO user) {
        User entity = null;
        if(user.getUuid() == null){
            user.setUuid(UUID.randomUUID());
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        try {
            entity = convertToEntity(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertToDto(repository.save(entity));
    }

    @Override
    public UserDTO findById(UUID uuid) {
        Optional<User> userOptional = repository.findByUuid(uuid);
        return convertToDto(userOptional.get());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUUID(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    private UserDTO convertToDto(User question) {
        return modelMapper.map(question, UserDTO.class);
    }

    private User convertToEntity(UserDTO dto) throws ParseException {
        return modelMapper.map(dto, User.class);
    }
}
