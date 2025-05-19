package com.FleetX.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentModel {
    private int paymentId;
    private int rentalId;
    private BigDecimal amount;
    private Timestamp paymentDate;
    
    
    public PaymentModel() {
	}
    
	public PaymentModel(int paymentId, int rentalId, BigDecimal amount, Timestamp paymentDate) {
		this.paymentId = paymentId;
		this.rentalId = rentalId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

    // Constructor, Getters, Setters
}
