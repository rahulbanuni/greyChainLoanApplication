package com.example.greychain.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.greychain.model.Loan;
import com.example.greychain.service.LoanStore;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanStore loanStore;

    @Autowired
    public LoanController(LoanStore loanStore) {
        this.loanStore = loanStore;
    }

    @PostMapping
    public void addLoan(@RequestBody Loan loan) throws Exception {
        loanStore.addLoan(loan);
    }

    @GetMapping("/aggregate/lender")
    public Map<String, Double> aggregateRemainingAmountByLender() {
        return loanStore.aggregateRemainingAmountByLender();
    }

    @GetMapping("/aggregate/interest")
    public Map<Double, Double> aggregateInterestByInterestRate() {
        return loanStore.aggregateInterestByInterestRate();
    }

    @GetMapping("/aggregate/customer")
    public Map<String, Double> aggregateRemainingAmountByCustomerId() {
        return loanStore.aggregateRemainingAmountByCustomerId();
    }
}