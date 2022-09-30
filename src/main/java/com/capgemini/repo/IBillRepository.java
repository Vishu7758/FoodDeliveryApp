package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Bill;

@Repository
public interface IBillRepository extends JpaRepository<Bill, String> {

//	@Query(value = "select b from Bill b")
//	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate);
//
//	@Query(value = "select b from Bill b")
//	public List<Bill> viewBills(String custId);
//
//	@Query(value = "select b from Bill b")
//	public double calculateTotalCost(Bill bill);
}