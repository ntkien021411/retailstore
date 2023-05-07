package com.rs.retailstore.controller;


import com.rs.retailstore.model.Customer;
import com.rs.retailstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1") // Chạy từ localhost xong đến v1 thì mới có thể truy cập vào các api bên trong
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        ResponseEntity<String> response = null;
        try {
           Customer cus =  customerService.createCustomer(customer);
            if(cus.getId() > 0){
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Customer is created succesfully for customer= " + cus.getUsername());
            }
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred from server with exception= " + e);
        }
        return response;
    }

}
