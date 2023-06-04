package com.test.demo.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.demo.models.PurchaseHistory;
import com.test.demo.form.PurchaseRegisterForm;
import com.test.demo.handler.ResponseSuccessHandler;
import com.test.demo.service.PurchaseHistoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/purchaseHistories")
public class PurchaseController {
	
	private PurchaseHistoryService purchaseHistoryService;

	public PurchaseController(PurchaseHistoryService purchaseHistoryService) {
		super();
		this.purchaseHistoryService=purchaseHistoryService;
	}
	
	@GetMapping
	public ResponseEntity<?> listPurchaseHistorys() {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		res.setBody(purchaseHistoryService.getAllPurchaseHistorys());
		return ResponseEntity.ok(res);
	}
	
	@PostMapping
	public ResponseEntity<?> savePurchaseHistory(@RequestBody PurchaseRegisterForm purchaseRegisterForm) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		res.setBody(purchaseHistoryService.savePurchesHistoryAndCustomer(purchaseRegisterForm));
		return ResponseEntity.ok(res);
	}
	

	@PutMapping("/{id}") 
	public ResponseEntity<?> updatePurchaseHistory(@PathVariable Long id,
			@RequestBody PurchaseHistory purchaseHistory) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		PurchaseHistory existingpurchaseHistory=purchaseHistoryService.getPurchaseHistoryById(id);
		existingpurchaseHistory.setOrderAmount(purchaseHistory.getOrderAmount());
		existingpurchaseHistory.setPromoCode(purchaseHistory.getPromoCode());
		res.setBody(purchaseHistoryService.updatePurchaseHistory(existingpurchaseHistory));
		return ResponseEntity.ok(res);		
	}
	
	
}
