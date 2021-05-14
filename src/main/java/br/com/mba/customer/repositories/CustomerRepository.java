package br.com.mba.customer.repositories;

import br.com.mba.customer.documents.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findByName(String name, Pageable pagination);
}
