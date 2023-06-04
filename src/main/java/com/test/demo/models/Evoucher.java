package com.test.demo.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="evouchers")
public class Evoucher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "evoucher_id", unique = true, nullable = false)
	private Long evoucherId;
	
	@Column(name="title",nullable=false)
	private String title;
	
	@Column(name="expire_date",nullable=false)
	private Date expireDate;
	
	@Column(name="image",nullable=true)
	private String image;
	
	@Column(name="amount",nullable=false)
	private Double amount;
	
	@Column(name="qty",nullable=false)
	private Integer qty;
	
	@Column(name="return_amount",nullable=false)
	private Double returnAmount;
	
	@Column(name="description",nullable=true)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date", nullable = false, length = 10)
	private Date createdDate;
	
}
