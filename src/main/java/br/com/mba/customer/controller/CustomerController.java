package br.com.mba.customer.controller;

import br.com.mba.customer.dto.CustomerDTO;
import br.com.mba.customer.dto.ResponseDTO;
import br.com.mba.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseDTO<CustomerDTO> save(@RequestBody CustomerDTO customer) {

        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<CustomerDTO>();
        responseDTO.setData(service.save(customer));
        return responseDTO;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseDTO<Page<CustomerDTO>> list(
            @RequestParam(required = false) String name,
            @PageableDefault(sort = "name",direction = Sort.Direction.ASC) Pageable pagination) {

        ResponseDTO<Page<CustomerDTO>> response;
        response = new ResponseDTO<Page<CustomerDTO>>();
        response.setData(service.listAll(name,pagination));

        return response;
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseDTO<CustomerDTO> findById(@PathVariable UUID id) {

        ResponseDTO<CustomerDTO> response;
        response = new ResponseDTO<CustomerDTO>();
        response.setData(service.findByUuid(id));

        return response;
    }

    @PutMapping
    @ResponseBody
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseDTO<CustomerDTO> update(@RequestBody CustomerDTO customer) {
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<CustomerDTO>();
        responseDTO.setData(service.save(customer));
        return responseDTO;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        ResponseDTO<CustomerDTO> responseDTO = new ResponseDTO<CustomerDTO>();
        service.delete(id);
    }
    
}
