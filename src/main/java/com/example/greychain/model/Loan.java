package com.example.greychain.model;
import java.util.Date;
import java.util.logging.Logger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Loan {
    public Loan(String loanId, String customerId, String lenderId, double amount, double remainingAmount,
			Date paymentDate, double interestPerDay, Date dueDate, double penaltyPerDay) {
		super();
		this.loanId = loanId;
		this.customerId = customerId;
		this.lenderId = lenderId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
	}


	private static final Logger LOGGER = Logger.getLogger(Loan.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loanId;
    private String customerId;
    private String lenderId;
    private double amount;
    private double remainingAmount;
    private Date paymentDate;
    private double interestPerDay;
    private Date dueDate;
    private double penaltyPerDay;

    
    public double getInterest() {
        long daysLate = (new Date().getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
        if (daysLate > 0) {
            return (amount * interestPerDay * daysLate) + (remainingAmount * penaltyPerDay * daysLate);
        }
        return 0;
    }
    // Constructors, getters, setters, and validation logic


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLoanId() {
		return loanId;
	}


	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getLenderId() {
		return lenderId;
	}


	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getRemainingAmount() {
		return remainingAmount;
	}


	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public double getInterestPerDay() {
		return interestPerDay;
	}


	public void setInterestPerDay(double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public double getPenaltyPerDay() {
		return penaltyPerDay;
	}


	public void setPenaltyPerDay(double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}
}

