package com.foolishworks.knotting.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesBuffer;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServicesBuffer;
import com.foolishworks.knotting.entity.MemberServicesStaging;
import com.foolishworks.knotting.entity.RegistrationBuffer;
import com.foolishworks.knotting.entity.SequenceEntriesStaging;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.entity.ServiceStats;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.entity.Subscription;
import com.foolishworks.knotting.entity.SubscriptionPayment;
import com.foolishworks.knotting.intf.KnottingServicesDao;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class KnottingServicesDaoImpl implements KnottingServicesDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingServicesDaoImpl.class);

	public List<Services> fetchAvailableListOfServices(){

		slf4jLogger.debug("Entering function {}", "fetchAvailableListOfServices");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Services.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Services> servicesList = (List<Services>)criteria.list();

		slf4jLogger.debug("Exiting function {}", "fetchAvailableListOfServices");

		return servicesList;

	}

	public Services fetchServices(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchServices");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Services.class);
		Services services = (Services)criteria.add(Restrictions.eq("servicesId", servicesId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchServices");

		return services;

	}

	public List<SubscriptionPayment> fetchSubscriptionPayment(Long memberEntryId){

		slf4jLogger.debug("Entering function {}", "fetchSubscriptionPayment");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SubscriptionPayment> subscriptionPaymentList = (List<SubscriptionPayment>)criteria.add(Restrictions.eq("memberEntriesStaging.memberEntryId", memberEntryId)).list();

		slf4jLogger.debug("Exiting function {}", "fetchSubscriptionPayment");

		return subscriptionPaymentList;

	}

	public List<SubscriptionPayment> fetchSubscriptionPayment(Long memberEntryId, Long serviceId){

		slf4jLogger.debug("Entering function {}", "fetchSubscriptionPayment");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubscriptionPayment.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<SubscriptionPayment> subscriptionPaymentList = (List<SubscriptionPayment>)criteria.add(Restrictions.eq("memberEntriesStaging.memberEntryId", memberEntryId)).add(Restrictions.eq("services.servicesId", serviceId)).list();

		slf4jLogger.debug("Exiting function {}", "fetchSubscriptionPayment");

		return subscriptionPaymentList;

	}

	public void addSubscriptionPayment(SubscriptionPayment subscriptionPayment){

		slf4jLogger.debug("Entering function {}", "addSubscriptionPayment");

		sessionFactory.getCurrentSession().saveOrUpdate(subscriptionPayment);

		slf4jLogger.debug("Exiting function {}", "addSubscriptionPayment");

	}

	public List<ServiceDetails> fetchAvailableListOfServiceQuestions(){

		slf4jLogger.debug("Entering function {}", "fetchAvailableListOfServiceQuestions");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceDetails.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ServiceDetails> serviceDetailList = (List<ServiceDetails>)criteria.list();

		slf4jLogger.debug("Exiting function {}", "fetchAvailableListOfServiceQuestions");

		return serviceDetailList;

	}

	public List<ServiceDetails> fetchListOfServiceQuestions(List<Long> serviceIdList){

		slf4jLogger.debug("Entering function {}", "fetchListOfServiceQuestions");

		String hql = "from ServiceDetails where services.servicesId in (:serviceIdList)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("serviceIdList", serviceIdList);
		List<ServiceDetails> serviceDetailList = query.list();

		slf4jLogger.debug("Exiting function {}", "fetchListOfServiceQuestions");

		return serviceDetailList;

	}

	public List<Subscription> fetchAvailableListOfSubscriptions(){

		slf4jLogger.debug("Entering function {}", "fetchAvailableListOfSubscriptions");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Subscription.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Subscription> subscriptionList = (List<Subscription>)criteria.list();

		slf4jLogger.debug("Exiting function {}", "fetchAvailableListOfSubscriptions");

		return subscriptionList;

	}

	public Subscription fetchSubscription(Long servicesId){

		slf4jLogger.debug("Entering function {}", "fetchSubscription");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Subscription.class);
		Subscription subscription = (Subscription)criteria.add(Restrictions.eq("services.servicesId", servicesId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchSubscription");

		return subscription;

	}

	public List<Subscription> fetchSelectiveSubscriptions(List<Long> serviceIdList){

		slf4jLogger.debug("Entering function {}", "fetchSelectiveSubscriptions");

		String hql = "from Subscription where services.servicesId in (:serviceIdList)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("serviceIdList", serviceIdList);
		List<Subscription> subscriptionList = query.list();

		slf4jLogger.debug("Exiting function {}", "fetchSelectiveSubscriptions");

		return subscriptionList;

	}

	public MemberEntries fetchMemberEntriesRecord(Long entryId){

		slf4jLogger.debug("Entering function {}", "fetchMemberEntriesRecord");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberEntries.class);
		MemberEntries memberEntries = (MemberEntries)criteria.add(Restrictions.eq("memberEntryId", entryId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchMemberEntriesRecord");

		return memberEntries;

	}

	public MemberEntriesStaging fetchMemberEntriesStagingRecord(Long entryId){

		slf4jLogger.debug("Entering function {}", "fetchMemberEntriesStagingRecord");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberEntriesStaging.class);
		MemberEntriesStaging memberEntriesStaging = (MemberEntriesStaging)criteria.add(Restrictions.eq("memberEntryStagingId", entryId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchMemberEntriesStagingRecord");

		return memberEntriesStaging;

	}

	public Long addOrUpdateMemberEntry(MemberEntriesStaging memberEntriesStaging){

		slf4jLogger.debug("Entering function {}", "addOrUpdateMemberEntry");

		sessionFactory.getCurrentSession().saveOrUpdate(memberEntriesStaging);

		slf4jLogger.debug("Exiting function {}", "addOrUpdateMemberEntry");

		return memberEntriesStaging.getMemberEntryStagingId();

	}

	public Long addOrUpdateServices(MemberServicesStaging memberServicesStaging){

		slf4jLogger.debug("Entering function {}", "addOrUpdateServices");

		sessionFactory.getCurrentSession().saveOrUpdate(memberServicesStaging);

		slf4jLogger.debug("Exiting function {}", "addOrUpdateServices");

		return memberServicesStaging.getMemberServiceId();

	}

	public Integer fetchCountOfServiceRecords(Long memberEntryId){

		slf4jLogger.debug("Entering function {}", "fetchCountOfServiceRecords");

		String queryString = "select count(*) as counter from memberservices_staging where MMSRMMENTID = "+memberEntryId.intValue();  
		Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString).addScalar("counter", StandardBasicTypes.INTEGER); 
		Integer recordCount = (Integer)query.uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchCountOfServiceRecords");

		return recordCount;
	}

	public void deleteAllMembberServiceRecords(Long memberEntryId){

		slf4jLogger.debug("Entering function {}", "deleteAllMembberServiceRecords");

		Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from memberservices_staging where MMSRMMENTID = "+memberEntryId.intValue());
		query.executeUpdate();

		slf4jLogger.debug("Exiting function {}", "deleteAllMembberServiceRecords");

	}

	public List<Coupon> fetchCoupons(String couponCode){

		slf4jLogger.debug("Entering function {}", "fetchCoupons");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Coupon.class);
		List<Coupon> coupons = (List<Coupon>)criteria.add(Restrictions.eq("couponCode", couponCode)).add(Restrictions.eq("couponStatus", KnottingConstants.couponActive)).list();

		slf4jLogger.debug("Exiting function {}", "fetchCoupons");

		return coupons;

	}

	public void saveBufferServices(MemberServicesBuffer memberServicesBuffer){

		slf4jLogger.debug("Entering function {}", "saveBufferServices");

		sessionFactory.getCurrentSession().saveOrUpdate(memberServicesBuffer);

		slf4jLogger.debug("Exiting function {}", "saveBufferServices");

	}

	public void saveBufferEntries(MemberEntriesBuffer memberEntriesBuffer){

		slf4jLogger.debug("Entering function {}", "saveBufferServices");

		sessionFactory.getCurrentSession().saveOrUpdate(memberEntriesBuffer);

		slf4jLogger.debug("Exiting function {}", "saveBufferServices");

	}

	public void saveRegistrationBuffer(RegistrationBuffer registrationBuffer){

		slf4jLogger.debug("Entering function {}", "saveRegistrationBuffer");

		sessionFactory.getCurrentSession().saveOrUpdate(registrationBuffer);

		slf4jLogger.debug("Exiting function {}", "saveRegistrationBuffer");

	}

	public RegistrationBuffer fetchRegistrationBuffer(Long bufferId){

		slf4jLogger.debug("Entering function {}", "fetchRegistrationBuffer");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RegistrationBuffer.class);
		RegistrationBuffer registrationBuffer = (RegistrationBuffer)criteria.add(Restrictions.eq("bufferId", bufferId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchRegistrationBuffer");

		return registrationBuffer;

	}

	public void deleteAllBufferforUser(Long userId){

		slf4jLogger.debug("Entering function {}", "deleteAllBufferforUser");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RegistrationBuffer.class);
		List<RegistrationBuffer> registrationBuffers = (List<RegistrationBuffer>)criteria.add(Restrictions.eq("member.memberId", userId)).list();

		for(RegistrationBuffer registrationBuffer : registrationBuffers){
			sessionFactory.getCurrentSession().delete(registrationBuffer);
		}

		//		Query query = sessionFactory.getCurrentSession().createQuery("delete from RegistrationBuffer where member.memberId = :memberId");
		//		query.setParameter("memberId", userId);
		//		query.executeUpdate();

		slf4jLogger.debug("Exiting function {}", "deleteAllBufferforUser");

	}

	public void deleteBuffer(Long bufferId){

		slf4jLogger.debug("Entering function {}", "deleteBuffer");

		Query query = sessionFactory.getCurrentSession().createQuery("delete from RegistrationBuffer where bufferId = :bufferId");
		query.setParameter("bufferId", bufferId);
		query.executeUpdate();

		slf4jLogger.debug("Exiting function {}", "deleteBuffer");

	}

	public MemberEntriesStaging fetchMemberEntriesStagingByEntryId(Long entryId){

		slf4jLogger.debug("Entering function {}", "fetchMemberEntriesStagingByEntryId");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MemberEntriesStaging.class);
		MemberEntriesStaging entriesStaging = (MemberEntriesStaging)criteria.add(Restrictions.eq("memberEntryId", entryId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchMemberEntriesStagingByEntryId");
		
		return entriesStaging;

	}

	public SequenceEntriesStaging fetchEntriesStagingNextNumber(){

		slf4jLogger.debug("Entering function {}", "fetchEntriesStagingNextNumber");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SequenceEntriesStaging.class);
		SequenceEntriesStaging sequence = (SequenceEntriesStaging)criteria.uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchEntriesStagingNextNumber");
		
		return sequence;

	}

	public void saveEntriesStagingNextNumber(SequenceEntriesStaging sequence){

		slf4jLogger.debug("Entering function {}", "saveEntriesStagingNextNumber");

		sessionFactory.getCurrentSession().save(sequence);

		slf4jLogger.debug("Exiting function {}", "saveEntriesStagingNextNumber");

	}

	public ServiceStats fetchServiceStats(Long memberId, Long entryId, Long serviceId){

		slf4jLogger.debug("Entering function {}", "fetchServiceStats");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ServiceStats.class);
		ServiceStats serviceStats = (ServiceStats)criteria.add(Restrictions.eq("member.memberId", memberId)).add(Restrictions.eq("memberEntries.memberEntryId", entryId)).add(Restrictions.eq("services.servicesId", serviceId)).uniqueResult();

		slf4jLogger.debug("Exiting function {}", "fetchServiceStats");
		
		return serviceStats;

	}

	public void saveServiceStats(ServiceStats serviceStats){

		slf4jLogger.debug("Entering function {}", "saveServiceStats");

		sessionFactory.getCurrentSession().save(serviceStats);

		slf4jLogger.debug("Exiting function {}", "saveServiceStats");

	}

}
