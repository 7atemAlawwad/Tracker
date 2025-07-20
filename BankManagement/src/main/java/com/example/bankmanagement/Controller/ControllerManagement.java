package com.example.bankmanagement.Controller;

import com.example.bankmanagement.API.ApiResponse;
import com.example.bankmanagement.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class ControllerManagement {

    ArrayList<Customer> customerList = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customerList;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customerList.add(customer);
        return new ApiResponse("Customer added successfully", "200");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index,
                                      @RequestBody Customer customer) {

        if (index >= customerList.size())
            return new ApiResponse("Customer not found", "404");

        customerList.set(index, customer);
        return new ApiResponse("Customer updated successfully", "200");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {

        if (index >= customerList.size())
            return new ApiResponse("Customer not found", "404");

        customerList.remove(index);
        return new ApiResponse("Customer deleted successfully", "200");
    }

    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index,
                               @RequestParam double amount) {

        if (index >= customerList.size())
            return new ApiResponse("Customer not found", "404");

        if (amount <= 0)
            return new ApiResponse("Invalid amount", "400");

        Customer c = customerList.get(index);
        c.setBalance(c.getBalance() + amount);
        return new ApiResponse("Deposit successful, new balance = " + c.getBalance(), "200");
    }

    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index,
                                @RequestParam double amount) {

        if (index >= customerList.size())
            return new ApiResponse("Customer not found", "404");

        if (amount <= 0)
            return new ApiResponse("Invalid amount", "400");

        Customer c = customerList.get(index);
        if (c.getBalance() < amount)
            return new ApiResponse("Insufficient balance", "400");

        c.setBalance(c.getBalance() - amount);
        return new ApiResponse("Withdraw successful, new balance = " + c.getBalance(), "200");
    }


}
