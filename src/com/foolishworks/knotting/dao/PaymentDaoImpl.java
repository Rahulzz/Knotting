package com.foolishworks.knotting.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.Transaction;
import com.foolishworks.knotting.intf.PaymentDao;

@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(PaymentDaoImpl.class);

	public Long addOrUpdateATransaction(Transaction transaction){

		slf4jLogger.debug("Entering function {}", "addOrUpdateATransaction");

		sessionFactory.getCurrentSession().saveOrUpdate(transaction);

		slf4jLogger.debug("Exiting function {}", "addOrUpdateATransaction");
		
		return transaction.getTransactionId();
		
	}

	public Transaction fetchTransaction(Long transactionId){

		slf4jLogger.debug("Entering function {}", "fetchTransaction");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Transaction.class);
		Transaction transaction = (Transaction)criteria.add(Restrictions.eq("transactionId", transactionId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchTransaction");
		
		return transaction;
		
	}

}
