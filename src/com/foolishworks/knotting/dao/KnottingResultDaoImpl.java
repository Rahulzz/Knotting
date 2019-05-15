package com.foolishworks.knotting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.intf.KnottingResultDao;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class KnottingResultDaoImpl implements KnottingResultDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingResultDaoImpl.class);

	public Long totalVendorCount(String query){

		slf4jLogger.debug("Entering function {}", "totalVendorCount");

		Query countQuery = sessionFactory.getCurrentSession().createQuery(query);
		Long countResults = (Long) countQuery.uniqueResult();

		slf4jLogger.debug("Exiting function {}", "totalVendorCount");

		return countResults;

	}

	public List<MemberServices> fetchResultData(String query, int firstRecord, int lastRecord){

		slf4jLogger.debug("Entering function {}", "fetchResultData");

		Query resultQuery = sessionFactory.getCurrentSession().createQuery(query);
		resultQuery.setFirstResult(firstRecord);
		resultQuery.setMaxResults(lastRecord);
		List<MemberServices> memberServicesList = resultQuery.list();		

		slf4jLogger.debug("Exiting function {}", "fetchResultData");

		return memberServicesList;

	}

	public Long fetchServiceIdByCode(String serviceCode){

		slf4jLogger.debug("Entering function {}", "fetchServiceIdByCode");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Services.class);
		Long serviceId = (Long)criteria.add(Restrictions.eq("serviceCode", serviceCode))
				.setProjection(Projections.projectionList()
				.add(Projections.property("servicesId"), "servicesId")).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchServiceIdByCode");

		return serviceId;
	}

}
