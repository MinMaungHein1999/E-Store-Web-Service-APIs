package com.test.demo.service;

import java.util.List;
import com.test.demo.models.Evoucher;



public interface EvoucherServic {
	List<Evoucher> getAllEvouchers();
	
	Evoucher saveEvoucher(Evoucher customer);
	
	Evoucher getEvoucherById(Long id);
	
	Evoucher updateEvoucher(Evoucher customer);
	
	void deleteEvoucherById(Long id);
}
