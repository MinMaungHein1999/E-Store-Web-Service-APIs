package com.test.demo.form;

import java.util.Date;

public class ShowAllPurchesHistoryForm {
	private String customerName;
	private String phoneNo;
	private String paymentMethodName;
	private Double orderAmount;
	private Date ExpireDate;
	private String promoCode;
	private String voucherTitle;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPaymentMethodName() {
		return paymentMethodName;
	}
	public void setPaymentMethodName(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Date getExpireDate() {
		return ExpireDate;
	}
	public void setExpireDate(Date expireDate) {
		ExpireDate = expireDate;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getVoucherTitle() {
		return voucherTitle;
	}
	public void setVoucherTitle(String voucherTitle) {
		this.voucherTitle = voucherTitle;
	}
	
}
