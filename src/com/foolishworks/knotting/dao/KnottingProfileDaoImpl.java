package com.foolishworks.knotting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.intf.KnottingProfileDao;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class KnottingProfileDaoImpl implements KnottingProfileDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingProfileDaoImpl.class);

	public void savePersonalInfo(Member member){

		slf4jLogger.debug("Entering function {}", "savePersonalInfo");

		sessionFactory.getCurrentSession().saveOrUpdate(member);

		slf4jLogger.debug("Exiting function {}", "savePersonalInfo");

	}
	
	public Member fetchMemberRecord(Long memberId){
		
		slf4jLogger.debug("Entering function {}", "fetchMemberRecord");

		Criteria criteria = null;

		criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Member member = (Member)criteria.add(Restrictions.eq("memberId", memberId)).uniqueResult();
		
		slf4jLogger.debug("Exiting function {}", "fetchMemberRecord");
		
		return member;
	}
	
	public List<MemberEntries> fetchApprovedMemberServicesForThisUser(Long userId){
		
		slf4jLogger.debug("Entering function {}", "fetchApprovedMemberServicesForThisUser");

		Criteria criteria = null;

		criteria = sessionFactory.getCurrentSession().createCriteria(MemberEntries.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<MemberEntries> memberServicesList = (List<MemberEntries>)criteria.add(Restrictions.eq("member.memberId", userId)).list();
		
		slf4jLogger.debug("Exiting function {}", "fetchApprovedMemberServicesForThisUser");
		
		return memberServicesList;
	}
	
	public List<MemberEntriesStaging> fetchNotApprovedMemberServicesForThisUser(Long userId){
		
		slf4jLogger.debug("Entering function {}", "fetchNotApprovedMemberServicesForThisUser");

		Criteria criteria = null;

		criteria = sessionFactory.getCurrentSession().createCriteria(MemberEntriesStaging.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<MemberEntriesStaging> memberServicesStagingList = (List<MemberEntriesStaging>)criteria.add(Restrictions.eq("member.memberId", userId)).list();
		
		slf4jLogger.debug("Exiting function {}", "fetchNotApprovedMemberServicesForThisUser");
		
		return memberServicesStagingList;
	}

	public Long addOrUpdateServices(MemberServices memberServices){

		slf4jLogger.debug("Entering function {}", "addOrUpdateServices");

		sessionFactory.getCurrentSession().saveOrUpdate(memberServices);

		slf4jLogger.debug("Exiting function {}", "addOrUpdateServices");
		
		return memberServices.getMemberServiceId();
		
	}

}
