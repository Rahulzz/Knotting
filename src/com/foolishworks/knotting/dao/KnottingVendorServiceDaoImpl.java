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

import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.intf.KnottingVendorServiceDao;

@Repository
@Transactional
public class KnottingVendorServiceDaoImpl implements KnottingVendorServiceDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingVendorServiceDaoImpl.class);

	public MemberServices fetchMemberService(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchMemberService");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberServices.class);
		MemberServices memberServices = (MemberServices)criteria.add(Restrictions.eq("memberServiceId", servicesId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchMemberService");
		
		return memberServices;
		
	}

	public MemberServices fetchMemberService(String serviceCode){

		slf4jLogger.debug("Entering function {}", "fetchMemberService");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberServices.class);
		MemberServices memberServices = (MemberServices)criteria.add(Restrictions.eq("memberServiceCode", serviceCode)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchMemberService");
		
		return memberServices;
		
	}

	@SuppressWarnings("unchecked")
	public List<ServiceDetails> fetchServiceDetails(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchServiceDetails");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceDetails.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ServiceDetails> serviceDetails = (List<ServiceDetails>)criteria.add(Restrictions.eq("services.servicesId", servicesId)).list();

		slf4jLogger.debug("Exiting function {}", "fetchServiceDetails");
		
		return serviceDetails;
		
	}

}
