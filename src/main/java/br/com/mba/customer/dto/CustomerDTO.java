package br.com.mba.customer.dto;

import br.com.mba.customer.documents.Customer;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class CustomerDTO {

    private UUID id;
    private String name;
    private String birthDate;


    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.birthDate = customer.getBirthDate();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public static Page<CustomerDTO> converter(Page<Customer> professionals){
        return professionals.map(CustomerDTO::new);
    }

}
