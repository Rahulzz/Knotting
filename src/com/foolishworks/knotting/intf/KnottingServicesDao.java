package com.foolishworks.knotting.intf;

import java.util.List;

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

public interface KnottingServicesDao {

	public List<Services> fetchAvailableListOfServices();
	public Services fetchServices(Long servicesId);
	public List<Subscription> fetchAvailableListOfSubscriptions();
	public Subscription fetchSubscription(Long servicesId);
	public List<Subscription> fetchSelectiveSubscriptions(List<Long> serviceIdList);
	public List<ServiceDetails> fetchAvailableListOfServiceQuestions();
	public List<ServiceDetails> fetchListOfServiceQuestions(List<Long> serviceIdList);
	public MemberEntries fetchMemberEntriesRecord(Long entryId);
	public MemberEntriesStaging fetchMemberEntriesStagingRecord(Long entryId);
	public Long addOrUpdateMemberEntry(MemberEntriesStaging memberEntriesStaging);
	public Long addOrUpdateServices(MemberServicesStaging memberServicesStaging);
	public List<SubscriptionPayment> fetchSubscriptionPayment(Long memberEntryId);
	public List<SubscriptionPayment> fetchSubscriptionPayment(Long memberEntryId, Long serviceId);
	public void addSubscriptionPayment(SubscriptionPayment subscriptionPayment);
	public void deleteAllMembberServiceRecords(Long memberEntryId);
	public List<Coupon> fetchCoupons(String couponCode);
	public void saveBufferServices(MemberServicesBuffer memberServicesBuffer);
	public void saveBufferEntries(MemberEntriesBuffer memberEntriesBuffer);
	public void saveRegistrationBuffer(RegistrationBuffer registrationBuffer);
	public RegistrationBuffer fetchRegistrationBuffer(Long bufferId);
	public void deleteAllBufferforUser(Long userId);
	public void deleteBuffer(Long bufferId);
	public Integer fetchCountOfServiceRecords(Long memberEntryId);
	public MemberEntriesStaging fetchMemberEntriesStagingByEntryId(Long entryId);
	public SequenceEntriesStaging fetchEntriesStagingNextNumber();
	public void saveEntriesStagingNextNumber(SequenceEntriesStaging sequence);
	public ServiceStats fetchServiceStats(Long memberId, Long entryId, Long serviceId);
	public void saveServiceStats(ServiceStats serviceStats);
	
}
