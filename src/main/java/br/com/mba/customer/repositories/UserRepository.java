package br.com.mba.customer.repositories;

import br.com.mba.customer.documents.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User,UUID> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByUuid(UUID uuid);
    Optional<User> findByEmail(String email);
}
