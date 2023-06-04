package com.test.demo.api.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.demo.handler.ResponseSuccessHandler;
import com.test.demo.models.Evoucher;
import com.test.demo.service.EvoucherServic;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/evouchers")
public class EvoucherController {
	
	private EvoucherServic evoucherServic;

	public EvoucherController(EvoucherServic evoucherServic) {
		super();
		this.evoucherServic = evoucherServic;
	}
	
	
	@GetMapping
	public ResponseEntity<?> listEvouchers() {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		res.setBody(evoucherServic.getAllEvouchers());
		return ResponseEntity.ok(res);
	}
	
	
	@PostMapping
	public ResponseEntity<?> saveEvoucher(@RequestBody Evoucher evoucher) throws ParseException {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		evoucher.setCreatedDate(new Date());
		Date expDate=new SimpleDateFormat("dd/MM/yyyy").parse("30/7/2023");  
		evoucher.setExpireDate(expDate);
		res.setBody(evoucherServic.saveEvoucher(evoucher));
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> editCustomerForm(@PathVariable Long id, Model model) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		res.setBody(evoucherServic.getEvoucherById(id));
		return ResponseEntity.ok(res); 
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> updateEvoucher(@PathVariable Long id,
			@RequestBody Evoucher evoucher,
			Model model) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		Evoucher existingEvoucher = evoucherServic.getEvoucherById(id);
		existingEvoucher.setEvoucherId(id);
		existingEvoucher.setTitle(evoucher.getTitle());
		existingEvoucher.setAmount(evoucher.getAmount());
		existingEvoucher.setCreatedDate(new Date());
		existingEvoucher.setDescription(evoucher.getDescription());
		existingEvoucher.setImage(null);
		existingEvoucher.setQty(evoucher.getQty());
		
		res.setBody(evoucherServic.updateEvoucher(existingEvoucher));
		return ResponseEntity.ok(res); 
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvoucher(@PathVariable Long id) {
		ResponseSuccessHandler res = new ResponseSuccessHandler();
		evoucherServic.deleteEvoucherById(id);
		res.setBody("Deleted Successfully!!!");
		return ResponseEntity.ok(res); 
		
	}
	
}
