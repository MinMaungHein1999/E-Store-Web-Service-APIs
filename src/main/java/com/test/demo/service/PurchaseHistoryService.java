package com.test.demo.service;



import java.util.List;
import com.test.demo.models.PurchaseHistory;
import com.test.demo.form.PurchaseRegisterForm;
public interface PurchaseHistoryService {
	List<PurchaseHistory> getAllPurchaseHistorys();
	
	PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);
	
	PurchaseHistory getPurchaseHistoryById(Long id);
	
	PurchaseHistory updatePurchaseHistory(PurchaseHistory purchaseHistory);
	
	void deletePurchaseHistoryById(Long id);
	
	public PurchaseHistory savePurchesHistoryAndCustomer(PurchaseRegisterForm purchaseRegisterForm);
}
