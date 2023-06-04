package com.test.demo.service.impl;


import java.util.List;
import org.springframework.stereotype.Service;
import com.test.demo.models.Evoucher;
import com.test.demo.repository.EvoucherRepository;
import com.test.demo.service.EvoucherServic;


@Service
public class EvoucherServiceImpl implements EvoucherServic{

	private EvoucherRepository evoucherRepository;
	
	public EvoucherServiceImpl(EvoucherRepository evoucherRepository) {
		super();
		this.evoucherRepository = evoucherRepository;
	}

	@Override
	public List<Evoucher> getAllEvouchers() {
		return evoucherRepository.findAll();
	}

	@Override
	public Evoucher saveEvoucher(Evoucher evoucher) {
		return evoucherRepository.save(evoucher);
	}

	@Override
	public Evoucher getEvoucherById(Long id) {
		return evoucherRepository.findById(id).get();
	}

	@Override
	public Evoucher updateEvoucher(Evoucher evoucher) {
		return evoucherRepository.save(evoucher);
	}

	@Override
	public void deleteEvoucherById(Long id) {
		evoucherRepository.deleteById(id);	
	}
	
}
