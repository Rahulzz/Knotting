package com.foolishworks.knotting.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingLoginDao;

@Repository
@Transactional
public class KnottingLoginDaoImpl implements KnottingLoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingLoginDaoImpl.class);

	public Member checkIfUserExist(String loginUsername){

		slf4jLogger.debug("Entering function {}", "checkIfUserExist");

		Member member = null;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		Long phoneNumber = null;
		if(loginUsername.matches("[0-9]+")){
			phoneNumber = Long.parseLong(loginUsername);
		}
		member = (Member)criteria.add(Restrictions.or(Restrictions.eq("phoneNumber", phoneNumber), Restrictions.eq("emailId", loginUsername))).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "checkIfUserExist");

		return member;
	}

	public Member loginUser(String loginUsername, String password){

		slf4jLogger.debug("Entering function {}", "loginUser");

		Member member = null;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		Long phoneNumber = null;
		if(loginUsername.matches("[0-9]+")){
			phoneNumber = Long.parseLong(loginUsername);
		}
		Criterion restrictionUsername = Restrictions.or(Restrictions.eq("phoneNumber", phoneNumber), Restrictions.eq("emailId", loginUsername));
		Criterion restrictionPassword = Restrictions.eq("userPassword", password);
		member = (Member)criteria.add(Restrictions.and(restrictionUsername, restrictionPassword)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "loginUser");

		return member;
	}

}
