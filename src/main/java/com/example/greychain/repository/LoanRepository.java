package com.example.greychain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.greychain.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
