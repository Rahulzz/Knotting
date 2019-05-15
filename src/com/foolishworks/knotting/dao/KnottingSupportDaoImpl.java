package com.foolishworks.knotting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.intf.KnottingSupportDao;

@Repository
@Transactional
public class KnottingSupportDaoImpl implements KnottingSupportDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingSupportDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<MemberEntriesStaging> fetchPendingEntries(){
		
		slf4jLogger.debug("Entering function {}", "fetchPendingEntries");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberEntriesStaging.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<MemberEntriesStaging> entriesStagings = (List<MemberEntriesStaging>)criteria.list();
		
		slf4jLogger.debug("Exiting function {}", "fetchPendingEntries");
		
		return entriesStagings;
		
	}

	public void deleteAllMembberServiceRecords(Long memberEntryId){

		slf4jLogger.debug("Entering function {}", "deleteAllMembberServiceRecords");

		Query query = sessionFactory.getCurrentSession().createQuery("delete MemberServices where memberEntries.memberEntryId = :memberEntryId");
		query.setParameter("memberEntryId", memberEntryId);
		query.executeUpdate();

		slf4jLogger.debug("Exiting function {}", "deleteAllMembberServiceRecords");
		
	}

	public void addOrUpdateMemberEntry(MemberEntries memberEntries){

		slf4jLogger.debug("Entering function {}", "addOrUpdateMemberEntry");

		sessionFactory.getCurrentSession().saveOrUpdate(memberEntries);

		slf4jLogger.debug("Exiting function {}", "addOrUpdateMemberEntry");
		
	}

	public void deleteStagingEntry(MemberEntriesStaging memberEntriesStaging){

		slf4jLogger.debug("Entering function {}", "deleteStagingEntry");

		sessionFactory.getCurrentSession().delete(memberEntriesStaging);

		slf4jLogger.debug("Exiting function {}", "deleteStagingEntry");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Coupon> fetchCoupons(){
		
		slf4jLogger.debug("Entering function {}", "fetchCoupons");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Coupon.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Coupon> coupons = (List<Coupon>)criteria.list();
		
		slf4jLogger.debug("Exiting function {}", "fetchCoupons");
		
		return coupons;
		
	}
	
	public Coupon fetchCoupon(){
		
		slf4jLogger.debug("Entering function {}", "fetchCoupons");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Coupon.class);
		Coupon coupon = (Coupon)criteria.uniqueResult();
		
		slf4jLogger.debug("Exiting function {}", "fetchCoupons");
		
		return coupon;
		
	}

	public void addOrUpdateCoupon(Coupon coupon){

		slf4jLogger.debug("Entering function {}", "addOrUpdateCoupon");

		sessionFactory.getCurrentSession().saveOrUpdate(coupon);

		slf4jLogger.debug("Exiting function {}", "addOrUpdateCoupon");
		
	}

	@SuppressWarnings("unchecked")
	public List<Member> fetchMembers(){

		slf4jLogger.debug("Entering function {}", "fetchMembers");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Member.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("userType", KnottingConstants.memberTypeVendor)).addOrder(Order.desc("createdDate"));
		List<Member> members = (List<Member>)criteria.list();

		slf4jLogger.debug("Exiting function {}", "fetchMembers");

		return members;

	}
}
