package br.com.mba.customer.services;

import br.com.mba.customer.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {
    public Page<CustomerDTO> listAll(String name, Pageable pagination);
    public CustomerDTO save(CustomerDTO professional);
    public CustomerDTO findByUuid(UUID id);
    public void delete(UUID id);
}
