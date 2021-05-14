package br.com.mba.customer.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String birthDate;


    public Customer() {
    }

    public Customer(UUID id, String nome, String birthDate) {
        this.id = id;
        this.name = nome;
        this.birthDate = birthDate;
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
}
