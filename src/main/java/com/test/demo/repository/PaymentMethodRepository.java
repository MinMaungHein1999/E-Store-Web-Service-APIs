package com.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.demo.models.PaymentMethod;
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>{
	PaymentMethod findByName(String name);
}
