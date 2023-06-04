package com.test.demo.models;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="customers")
@Getter @Setter @NoArgsConstructor
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	private Long customerId;

	
	@Column(name="name",nullable=false)
	private String name;

	
	@Column(name="phone_no",nullable=false)
	private String phoneNo;

	
	@Column(name="max_voucher_limit",nullable=false)
	private int maxVoucherLimit;


	@Temporal(TemporalType.DATE)
	@Column(name="created_date", nullable = false, length = 10)
	private Date createdDate;



}
