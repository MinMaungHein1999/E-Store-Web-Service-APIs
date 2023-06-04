package com.test.demo.models;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "purchase_histories", catalog = "sms")
public class PurchaseHistory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id", unique = true, nullable = false)
	private Long purchaseId;

	
	@Column(name="promo_code",nullable=true)
	private String promoCode;

	
	@Column(name="order_amount",nullable=false)
	private Double orderAmount;

	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date", nullable = false, length = 10)
	private Date createdDate;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "evoucher_id", nullable = false)
	private Evoucher evoucher;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod paymentMethod;

}
