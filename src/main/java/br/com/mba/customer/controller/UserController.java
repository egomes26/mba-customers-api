//package br.com.mba.customer.controller;
//
//import br.com.mba.customer.dto.ResponseDTO;
//import br.com.mba.customer.dto.UserDTO;
//import br.com.mba.customer.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/v1/user")
//public class UserController {
//    @Autowired
//    private UserService service;
//
//    @PostMapping
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public ResponseDTO<UserDTO> save(@RequestBody UserDTO user) {
////		log.info("salvando armas...");
//        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<UserDTO>();
//        responseDTO.setData(service.save(user));
//        return responseDTO;
//    }
//
//}
//
//
