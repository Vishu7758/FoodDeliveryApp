package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entities.Bill;
import com.capgemini.repo.IBillRepository;

@Service
public class IBillServiceImpl implements IBillService {

	@Autowired
	IBillRepository repo;

	@Override
	public Bill addBill(Bill bill) {
		return repo.save(bill);
	}

	@Override
	public Bill updateBill(Bill bill) {

		Bill updateBill = null;
		if (repo.existsById(bill.getBillId()))
			updateBill = repo.save(bill);
		return updateBill;
	}

	@Override
	public Bill removeBill(Bill bill) {
		Bill deleteBill = bill;
		if (repo.existsById(bill.getBillId())) {
			repo.delete(bill);
			return deleteBill;
		}
		return null;
	}

	@Override
	public Bill viewBill(Bill bill) {
		return repo.findById(bill.getBillId()).get();
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) {
//		List<Bill> filteredBills = repo.findAll();
//		for(Bill b: filteredBills)
//		{
//			if(!((b.getBillDate().toLocalDate().isAfter(startDate))&&(b.getBillDate().toLocalDate().isBefore(endDate))))
//			{
//				filteredBills.remove(b);
//			}
//		}
//		return filteredBills;
//		return repo.viewBills(startDate, endDate);
		return null;
	}

	@Override
	public List<Bill> viewBills(String custId) {
		return null;
	}

	@Override
	public double calculateTotalCost(Bill bill) {
//		return repo.findById(bill.getBillId()).get().getTotalCost();
//		return repo.calculateTotalCost(bill);
		return 0.0;
	}
}
