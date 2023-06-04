package com.test.demo.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.test.demo.models.Customer;
import com.test.demo.models.Evoucher;
import com.test.demo.models.PaymentMethod;
import com.test.demo.models.PurchaseHistory;
import com.test.demo.form.PurchaseRegisterForm;
import com.test.demo.repository.CustomerRepository;
import com.test.demo.repository.EvoucherRepository;
import com.test.demo.repository.PaymentMethodRepository;
import com.test.demo.repository.PurchaseHistoryRepository;
import com.test.demo.service.PurchaseHistoryService;
@Service
public class PurchaseServiceImpl implements PurchaseHistoryService{

	   private PaymentMethodRepository paymentMethodRepository;
		private CustomerRepository customerRepository;
		private EvoucherRepository evoucherRepository;
		private PurchaseHistoryRepository purchaseHistoryRepository;
		public PurchaseServiceImpl(CustomerRepository customerRepository,
				                   PaymentMethodRepository paymentMethodRepository,
				                   EvoucherRepository evoucherRepository,
				                   PurchaseHistoryRepository purchaseHistoryRepository) {
			super();
			this.paymentMethodRepository=paymentMethodRepository;
			this.customerRepository = customerRepository;
			this.evoucherRepository=evoucherRepository;
			this.purchaseHistoryRepository=purchaseHistoryRepository;
			
		}


	@Override
	public List<PurchaseHistory> getAllPurchaseHistorys() {
		return purchaseHistoryRepository.findAll();
	}

	@Override
	public PurchaseHistory savePurchaseHistory(PurchaseHistory evoucher) {
		return purchaseHistoryRepository.save(evoucher);
	}

	@Override
	public PurchaseHistory getPurchaseHistoryById(Long id) {
		return purchaseHistoryRepository.findById(id).get();
	}

	@Override
	public PurchaseHistory updatePurchaseHistory(PurchaseHistory evoucher) {
		return purchaseHistoryRepository.save(evoucher);
	}

	@Override
	public void deletePurchaseHistoryById(Long id) {
		purchaseHistoryRepository.deleteById(id);	
	}
	@Override
	public PurchaseHistory savePurchesHistoryAndCustomer(PurchaseRegisterForm purchaseRegisterForm){
		String phoneNo=purchaseRegisterForm.getPhoneNo();
		Customer customer=null;
		if(customerRepository.findByPhoneNo(phoneNo)!=null&&customerRepository.findByPhoneNo(phoneNo).size()!=0) {
		 customer=customerRepository.findByPhoneNo(phoneNo).get(0);
		}
		if(customer==null) {
		customer=new Customer();
		customer.setCreatedDate(new Date());
		customer.setMaxVoucherLimit(3);
		customer.setName(purchaseRegisterForm.getCustomerName());
		customer.setPhoneNo(purchaseRegisterForm.getPhoneNo());
		customer=customerRepository.save(customer);
		}
		PaymentMethod paymentMethod=paymentMethodRepository.findByName(purchaseRegisterForm.getPaymentMethodName().toUpperCase());
		System.out.println("paymentMethod="+paymentMethod.getName());
		List<Evoucher> evouchers=evoucherRepository.findAll();
		Long tempId=(long) 0;
		for(Evoucher e:evouchers) {
			if(e.getAmount()<=purchaseRegisterForm.getOrderAmount()) {
				tempId=e.getEvoucherId();
			}
		}
		PurchaseHistory purchaseHistory=null;
		System.out.println("tempId="+tempId);
		if(tempId!=0) {
		purchaseHistory=new PurchaseHistory();
		Evoucher evoucher=evoucherRepository.findById((long)tempId).get();
		purchaseHistory.setPaymentMethod(paymentMethod);
		purchaseHistory.setEvoucher(evoucher);
		purchaseHistory.setCustomer(customer);
		
		purchaseHistory.setCreatedDate(new Date());
		System.out.println("Original Amount="+ purchaseRegisterForm.getOrderAmount());
		double discountedAmount=purchaseRegisterForm.getOrderAmount()-(purchaseRegisterForm.getOrderAmount()*evoucher.getReturnAmount());
		purchaseHistory.setOrderAmount(discountedAmount);
		Random rnd = new Random();
		String alp="";
		for(int i=0;i<6;i++) {
		char c = (char) ('a' + rnd.nextInt(26));
		alp+=c;
		}
		purchaseHistory.setPromoCode(customer.getPhoneNo()+alp);
		purchaseHistory=purchaseHistoryRepository.save(purchaseHistory);
		System.out.println("Name="+ purchaseHistory.getCustomer().getName());
		System.out.println("Discount Amount="+ discountedAmount); 
		System.out.println("Discount Rate="+ evoucher.getReturnAmount()); 
		}else {
		System.out.println("You won't get any discount e-voucher because the amount you ordered amount is less than min amount of e-voucher amount!!"); 	
		}
		return purchaseHistory;
		
	}

}