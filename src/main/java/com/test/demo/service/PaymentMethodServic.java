package com.test.demo.service;



import java.util.List;
import com.test.demo.models.PaymentMethod;
import com.test.demo.models.PurchaseHistory;

public interface PaymentMethodServic {
	List<PaymentMethod> getAllCustomers();
	
	PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);
	
	PaymentMethod getPaymentMethodById(Long id);
	
	PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);
	
	void deletePaymentMethodById(Long id);
	
    void savePurchesHistoryAndPaymentMethod(PaymentMethod paymentMethod,PurchaseHistory purchaseHistory);
}
