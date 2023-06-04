package com.test.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.models.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	List<Customer> findByPhoneNo(String phoneNo);
}
