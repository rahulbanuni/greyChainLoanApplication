package com.example.greychain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.greychain.exception.LoanValidationException;
import com.example.greychain.model.Loan;

@Service
public class LoanStore {
    private List<Loan> loans = new ArrayList<>();

    public void addLoan(Loan loan) throws LoanValidationException {
        if (loan.getPaymentDate().after(loan.getDueDate())) {
            throw new LoanValidationException("Payment date can't be greater than Due Date.");
        }
        loans.add(loan);
    }

    public Map<String, Double> aggregateRemainingAmountByLender() {
        Map<String, Double> result = new HashMap<>();
        for (Loan loan : loans) {
            result.put(loan.getLenderId(), result.getOrDefault(loan.getLenderId(), 0.0) + loan.getRemainingAmount());
        }
        return result;
    }

    public Map<Double, Double> aggregateInterestByInterestRate() {
        Map<Double, Double> result = new HashMap<>();
        for (Loan loan : loans) {
            result.put(loan.getInterestPerDay(), result.getOrDefault(loan.getInterestPerDay(), 0.0) + loan.getInterest());
        }
        return result;
    }

    public Map<String, Double> aggregateRemainingAmountByCustomerId() {
        Map<String, Double> result = new HashMap<>();
        for (Loan loan : loans) {
            result.put(loan.getCustomerId(), result.getOrDefault(loan.getCustomerId(), 0.0) + loan.getRemainingAmount());
        }
        return result;
    }

    public void checkDueDateAlert() {
        Date currentDate = new Date();
        for (Loan loan : loans) {
            if (currentDate.after(loan.getDueDate())) {
                System.out.println("Loan ID " + loan.getLoanId() + " has crossed the due date.");
            }
        }
    }
}



