package com.test.demo.api.controller;

import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.handler.ResponseSuccessHandler;
import com.test.demo.models.Customer;
import com.test.demo.service.CustomerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService customerService;

	public CustomerController(CustomerService customerServic) {
		super();
		this.customerService = customerServic;
	}
	
	@GetMapping
	public ResponseEntity<?> listCustomers() {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		res.setBody(customerService.getAllCustomers());
		return ResponseEntity.ok(res);
	}
	
	
	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		customer.setCreatedDate(new Date());
		res.setBody(customerService.saveCustomer(customer));
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getCustomerForm(@PathVariable Long id) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		res.setBody(customerService.getCustomerById(id));
		return ResponseEntity.ok(res);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id,@RequestBody Customer customer) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		Customer existingCustomer = customerService.getCustomerById(id);
		existingCustomer.setCustomerId(id);
		existingCustomer.setName(customer.getName());
		existingCustomer.setPhoneNo(customer.getPhoneNo());
		res.setBody(customerService.updateCustomer(existingCustomer));
		return ResponseEntity.ok(res);
	}
	

	
}
