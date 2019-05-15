package com.foolishworks.knotting.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.Configuration;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.intf.KnottingCommonDao;

@Repository
@Transactional
public class KnottingCommonDaoImpl implements KnottingCommonDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingCommonDaoImpl.class);

	public Member fetchMemberRecord(Long userId, String mailId, Long phoneNumber){

		slf4jLogger.debug("Entering function {}", "fetchMemberRecord");

		Member member = null;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		if(userId != null){
			member = (Member)criteria.add(Restrictions.eq("memberId", userId)).uniqueResult();
		}
		else if(mailId != null){
			member = (Member)criteria.add(Restrictions.eq("emailId", mailId)).uniqueResult();
		}
		else if(phoneNumber != null){
			member = (Member)criteria.add(Restrictions.eq("phoneNumber", phoneNumber)).uniqueResult();
		}

		slf4jLogger.debug("Exiting function {}", "fetchMemberRecord");

		return member;
	}

	public Integer verifyIfPhoneAlreadyExists(Long phoneNumber){

		slf4jLogger.debug("Entering function {}", "verifyIfPhoneAlreadyExists");

		Criteria criteria = null;

		criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		criteria.add(Restrictions.eq("phoneNumber", phoneNumber));
		criteria.setProjection(Projections.rowCount());

		slf4jLogger.debug("Exiting function {}", "verifyIfPhoneAlreadyExists");

		return ((Long)criteria.uniqueResult()).intValue();
	}

	public Integer verifyIfEmailAlreadyExists(String emailId){

		slf4jLogger.debug("Entering function {}", "verifyIfEmailAlreadyExists");

		Criteria criteria = null;

		criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		criteria.add(Restrictions.eq("emailId", emailId));
		criteria.setProjection(Projections.rowCount());

		slf4jLogger.debug("Exiting function {}", "verifyIfEmailAlreadyExists");

		return ((Long)criteria.uniqueResult()).intValue();
	}

	public Configuration fetchConfigurationByCode(String configCode){

		slf4jLogger.debug("Entering function {}", "fetchConfigurationByCode");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Configuration.class);
		Configuration configuration = (Configuration)criteria.add(Restrictions.eq("configCode", configCode)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchConfigurationByCode");

		return configuration;
	}

}
