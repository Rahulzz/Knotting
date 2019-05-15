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
import com.foolishworks.knotting.entity.Subscription;
import com.foolishworks.knotting.intf.KnottingHomeDao;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class KnottingHomeDaoImpl implements KnottingHomeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingHomeDaoImpl.class);

	public List<MemberServices> fetchActiveServices(){

		slf4jLogger.debug("Entering function {}", "fetchActiveServices");

		Query query = sessionFactory.getCurrentSession().createQuery("from MemberServices where serviceStatus = 'Y' group by services.servicesId");
		List<MemberServices> activeServices = query.list();

		slf4jLogger.debug("Exiting function {}", "fetchActiveServices");

		return activeServices;
	}

	public List<MemberServices> fetchEntriesBasedOnService(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchEntriesBasedOnService");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberServices.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<MemberServices> memberServicesList = (List<MemberServices>)criteria.add(Restrictions.eq("services.servicesId", servicesId)).list();

		slf4jLogger.debug("Exiting function {}", "fetchEntriesBasedOnService");

		return memberServicesList;
	}

	public List<MemberServices> fetchEntriesBasedOnServiceAndCity(Long servicesId, String cityName){

		slf4jLogger.debug("Entering function {}", "fetchEntriesBasedOnServiceAndCity");

		Query query = sessionFactory.getCurrentSession().createQuery("from MemberServices where services.servicesId = :servicesId and memberEntries.additionalAreasServiced like '%"+cityName+"%'");
		query.setParameter("servicesId", servicesId);
		List<MemberServices> memberServicesList = query.list();

		slf4jLogger.debug("Exiting function {}", "fetchEntriesBasedOnServiceAndCity");

		return memberServicesList;
	}

	public List<MemberServices> fetchRecentlyAddedServices(){

		slf4jLogger.debug("Entering function {}", "fetchRecentlyAddedServices");

		Query query = sessionFactory.getCurrentSession().createQuery("from MemberServices where imagePath1 is not null and imagePath1 != '' ORDER BY createdDate desc");
		query.setMaxResults(30);
		List<MemberServices> memberServicesList = query.list();

		slf4jLogger.debug("Exiting function {}", "fetchRecentlyAddedServices");

		return memberServicesList;
	}

	public Integer fetchCheapestSubscriptionRate(){

		slf4jLogger.debug("Entering function {}", "fetchCheapestSubscriptionRate");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Subscription.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Integer> rates = (List<Integer>)criteria.setProjection(Projections.min("subscriptionRate")).list();

		slf4jLogger.debug("Exiting function {}", "fetchCheapestSubscriptionRate");

		return rates.get(0);
	}

}
