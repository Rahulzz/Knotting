package com.foolishworks.knotting.model;

import java.io.Serializable;
import java.util.Date;

public class UpcomingPayment implements Serializable {

	private static final long serialVersionUID = 9084343106049779115L;

	private String entryName;
	
	private Date paymentDate;
	
	private Integer paymentAmount;

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
}
