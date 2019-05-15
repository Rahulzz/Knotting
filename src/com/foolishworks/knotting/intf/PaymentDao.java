package com.foolishworks.knotting.intf;

import com.foolishworks.knotting.entity.Transaction;

public interface PaymentDao {
	
	public Transaction fetchTransaction(Long transactionId);
	public Long addOrUpdateATransaction(Transaction transaction);

}
