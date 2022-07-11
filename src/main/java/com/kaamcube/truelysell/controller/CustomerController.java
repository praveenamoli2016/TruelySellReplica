package com.kaamcube.truelysell.controller;

import com.kaamcube.truelysell.model.request.*;
import com.kaamcube.truelysell.model.responce.Response;
import com.kaamcube.truelysell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/registerCustomer")
    private Response registerCustomer(@Valid @RequestBody RegistrationRequest customerRequest) {
        return customerService.registerCustomer(customerRequest);
    }
    @PostMapping("/otpValidation")
    public Response otpValidation(@Valid@RequestBody OtpRequest otpRequest ){
        return customerService.otpValidation(otpRequest);
    }
    @PostMapping("/customerLogin")
    public Response customerLogin(@Valid@RequestBody LoginRequest loginRequest ){
        return customerService.customerLogin(loginRequest);
    }
    @GetMapping("/getCustomer")
    public Response updateCustomer(@RequestParam Long customerId){
        return customerService.getCustomer(customerId);
    }

    @PostMapping("/updateCustomer")
    public Response updateCustomer(@RequestBody VendorRequest customerRequest){
        return customerService.updateCustomer(customerRequest);
    }

    @PostMapping("/bookService")
    public Response bookService(@RequestBody BookServiceRequest bookServiceRequest){
        return customerService.bookService(bookServiceRequest);
    }

    @GetMapping("/getCustomerBooking")
    public Response getCustomerBooking(@RequestParam Long customerId){
        return customerService.getCustomerBooking(customerId);
    }

    @GetMapping("/getWalletAmount")
    public Response getWalletAmount(@RequestParam Long customerId){
        return customerService.getWalletAmount(customerId);
    }

    @PostMapping("/addWalletAmount")
    public Response addWalletAmount(@RequestBody AddWalletAmountRequest addWalletAmountRequest){
        return customerService.addWalletAmount(addWalletAmountRequest);
    }

    @GetMapping("/getWalletTransaction")
    public Response getWalletTransaction(@RequestParam Long customerId){
        return customerService.getWalletTransaction(customerId);
    }
}
