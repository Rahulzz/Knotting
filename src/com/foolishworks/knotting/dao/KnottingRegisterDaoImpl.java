package com.foolishworks.knotting.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.intf.KnottingRegisterDao;

@Repository
@Transactional
public class KnottingRegisterDaoImpl implements KnottingRegisterDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingRegisterDaoImpl.class);

	public Long addMemberToOurSystem(Member member){

		slf4jLogger.debug("Entering function {}", "addMemberToOurSystem");		

		sessionFactory.getCurrentSession().saveOrUpdate(member);

		slf4jLogger.debug("Exiting function {}", "addMemberToOurSystem");

		return member.getMemberId();
	}

	public void addUpdateByInfo(Member member){

		slf4jLogger.debug("Entering function {}", "addUpdateByInfo");

		sessionFactory.getCurrentSession().saveOrUpdate(member);

		slf4jLogger.debug("Exiting function {}", "addUpdateByInfo");

	}

}
