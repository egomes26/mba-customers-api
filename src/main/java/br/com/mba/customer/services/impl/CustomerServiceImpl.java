package br.com.mba.customer.services.impl;

import br.com.mba.customer.documents.Customer;
import br.com.mba.customer.dto.CustomerDTO;
import br.com.mba.customer.repositories.CustomerRepository;
import br.com.mba.customer.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CustomerDTO> listAll(String name, Pageable pagination) {
        Page<Customer> professionals = null;

        if(name == null){
            professionals = repository.findAll(pagination);
        }else{
            professionals = repository.findByName(name,pagination);
        }

        return CustomerDTO.converter(professionals);
    }

    @Override
    public CustomerDTO save(CustomerDTO professional) {

        Customer entity = null;
        if(professional.getId() == null){
            professional.setId(UUID.randomUUID());
        }
        try {
            entity = convertToEntity(professional);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertToDto(repository.save(entity));
    }

    @Override
    public CustomerDTO findByUuid(UUID id) {
        Optional<Customer> professionalOptional = repository.findById(id);

        CustomerDTO customerDTO = convertToDto(professionalOptional.get());

        return customerDTO;
    }


    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private CustomerDTO convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    private Customer convertToEntity(CustomerDTO dto) throws ParseException {
        return modelMapper.map(dto, Customer.class);
    }
}
