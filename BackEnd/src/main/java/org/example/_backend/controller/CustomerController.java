package org.example._backend.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.example._backend.dao.CustomerDAO;
import org.example._backend.dto.impl.CustomerDTO;
import org.example._backend.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/customer")

public class CustomerController {
    private final CustomerService customerService;

private final CustomerDAO customerDAO;
//    @RolesAllowed({"ADMIN"})

    @GetMapping(path = "/customer-details")
    public List<Object[]> getCustomerDetails() {
        System.out.println("getCustomerDetails in CustomerController"+customerService.findAllCustomerDetails());
        return customerService.findAllCustomerDetails();
    }
    @GetMapping("/getAll")
    public List<CustomerDTO> getAllCustomerDetails() {
        return customerDAO.findAllCustomerDetails();
    }

    @GetMapping("/customer-count")
    public ResponseEntity<Integer> getCustomerCount() {
        return ResponseEntity.ok(customerService.getCustomerCount());
    }
}
