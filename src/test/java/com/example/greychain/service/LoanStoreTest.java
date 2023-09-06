package com.example.greychain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.greychain.model.Loan;

@SpringBootTest
public class LoanStoreTest {
	 private LoanStore loanStore;
	    private SimpleDateFormat dateFormat;

	    @BeforeEach
	    void setUp() {
	        loanStore = new LoanStore();
	        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    }

    @Test
    public void testAddLoanWithInvalidPaymentDate() {
        Date paymentDate = new Date();
        Date dueDate = new Date(System.currentTimeMillis() - 10000); // Set a past due date
        Loan invalidLoan = new Loan("L1", "C1", "LEN1", 10000, 10000, paymentDate, 0.01, dueDate, 0.01);

        Exception exception = assertThrows(Exception.class, () -> {
            loanStore.addLoan(invalidLoan);
        });

        assertTrue(exception.getMessage().contains("Payment date cannot be greater than the due date."));
    }

    @Test
    public void testAggregateByLender() {
        Map<String, Double> aggregation = loanStore.aggregateRemainingAmountByLender();
        assertEquals(2, aggregation.size()); // Assuming 2 lenders in the test data
        assertEquals(15000.0, aggregation.get("LEN1"));
        assertEquals(30000.0, aggregation.get("LEN2"));
    }

    @Test
    public void testAggregateByInterest() {
        Map<Double, Double> aggregation = loanStore.aggregateInterestByInterestRate();
        assertEquals(2, aggregation.size()); // Assuming 2 interest rates in the test data
        assertEquals(15000.0, aggregation.get(0.01));
        assertEquals(30000.0, aggregation.get(0.02));
    }

    @Test
    public void testAggregateByCustomerId() {
        Map<String, Double> aggregation = loanStore.aggregateRemainingAmountByCustomerId();
        assertEquals(2, aggregation.size()); // Assuming 2 customer IDs in the test data
        assertEquals(15000.0, aggregation.get("C1"));
        assertEquals(30000.0, aggregation.get("C2"));
    }
}
