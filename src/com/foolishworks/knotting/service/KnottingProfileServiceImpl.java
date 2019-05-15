package com.foolishworks.knotting.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.Subscription;
import com.foolishworks.knotting.intf.KnottingProfileDao;
import com.foolishworks.knotting.intf.KnottingProfileService;
import com.foolishworks.knotting.intf.KnottingServicesDao;
import com.foolishworks.knotting.intf.KnottingVendorServiceDao;
import com.foolishworks.knotting.model.UpcomingPayment;
import com.foolishworks.knotting.utils.GeneralUtils;
import com.foolishworks.knotting.utils.MailerUtil;
import com.foolishworks.knotting.utils.SendMessageUtil;

@Service
@Transactional
public class KnottingProfileServiceImpl extends KnottingConstants implements KnottingProfileService {

	@Autowired
	private KnottingProfileDao knottingProfileDao;

	@Autowired
	private KnottingServicesDao knottingServicesDao;
	
	@Autowired
	private KnottingVendorServiceDao knottingVendorServiceDao;

	@Autowired
	private GeneralUtils generalUtils;

	@Autowired
	private MailerUtil mailerUtil;
	
	@Autowired
	private SendMessageUtil messageUtil;

	private final Logger slf4jLogger = LoggerFactory.getLogger(KnottingProfileServiceImpl.class);

	public void savePersonalInfo(Member member) throws NoSuchAlgorithmException {

		slf4jLogger.debug("Entering function {}", "savePersonalInfo");

		Timestamp todaysDate = new Timestamp(new Date().getTime());

		member.setLastUpdatedDate(todaysDate);
		member.setLastUpdatedBy(member.getMemberId());

		//		if(member.getChangeUserPassword().length() > 0){
		//			member.setUserPassword(generalUtils.encryptString(member.getChangeUserPassword()));
		//		}

		knottingProfileDao.savePersonalInfo(member);

		slf4jLogger.debug("Exiting function {}", "savePersonalInfo");

	}

	public List<MemberEntries> fetchApprovedMemberServicesForThisUser(Long userId){

		slf4jLogger.debug("Entering function {}", "fetchApprovedMemberServicesForThisUser");

		List<MemberEntries> memberServicesList = knottingProfileDao.fetchApprovedMemberServicesForThisUser(userId);

		slf4jLogger.debug("Exiting function {}", "fetchApprovedMemberServicesForThisUser");

		return memberServicesList;
	}

	public List<MemberEntriesStaging> fetchNotApprovedMemberServicesForThisUser(Long userId){

		slf4jLogger.debug("Entering function {}", "fetchNotApprovedMemberServicesForThisUser");

		List<MemberEntriesStaging> memberServicesStagingList = knottingProfileDao.fetchNotApprovedMemberServicesForThisUser(userId);

		slf4jLogger.debug("Exiting function {}", "fetchNotApprovedMemberServicesForThisUser");

		return memberServicesStagingList;
	}

	public Member fetchMemberRecord(Long memberId){

		slf4jLogger.debug("Entering function {}", "fetchMemberRecord");

		Member member = knottingProfileDao.fetchMemberRecord(memberId);

		slf4jLogger.debug("Exiting function {}", "fetchMemberRecord");

		return member;
	}

	public void sendUserVerificationEmail(Long memberId, String email) throws Exception{

		slf4jLogger.debug("Entering function {}", "sendUserVerificationEmail");

		mailerUtil.verifyEmail(verifyEmailForUser, email, memberId);

		slf4jLogger.debug("Exiting function {}", "sendUserVerificationEmail");

	}

	public boolean verifyUserEmail(String userId, String email){

		slf4jLogger.debug("Entering function {}", "verifyUserEmail");

		boolean emailVerificationStatus = true;

		String unencryptedId = generalUtils.decryptStringUsingPassword(userId);
		String unencryptedEmail = generalUtils.decryptStringUsingPassword(email);

		Member member = knottingProfileDao.fetchMemberRecord(Long.valueOf(unencryptedId));

		emailVerificationStatus = (member.getEmailId().equals(unencryptedEmail))?true:false;

		if(emailVerificationStatus){
			Timestamp currentTimestamp = new Timestamp((new Date()).getTime());
			member.setLastUpdatedDate(currentTimestamp);
			member.setEmailVerified(verificationDone);

			knottingProfileDao.savePersonalInfo(member);
		}

		slf4jLogger.debug("Exiting function {}", "verifyUserEmail");

		return emailVerificationStatus;

	}

	public String changeUserPassword(Long memberId, String password) throws NoSuchAlgorithmException{

		slf4jLogger.debug("Entering function {}", "changeUserPassword");

		String changePasswordStatus = null;

		String encryptedPassword = generalUtils.encryptString(password);

		Member member = knottingProfileDao.fetchMemberRecord(memberId);
		member.setUserPassword(encryptedPassword);
		member.setLastUpdatedBy(memberId);
		member.setLastUpdatedDate(new Timestamp((new Date()).getTime()));

		try{
			knottingProfileDao.savePersonalInfo(member);
			changePasswordStatus = "Success";
		}
		catch(Exception exception){
			slf4jLogger.error("Exception in changeUserPassword function {}", exception.getMessage());
			exception.printStackTrace();
			changePasswordStatus = "Error";
		}

		slf4jLogger.debug("Exiting function {}", "changeUserPassword");

		return changePasswordStatus;

	}

	public void calculateDaysSinceCreation(Member member){

		slf4jLogger.debug("Entering function {}", "calculateDaysSinceCreation");

		DateTime todayDate = new DateTime();

		Long daysSinceCreation = new Long(Days.daysBetween(new DateTime(member.getCreatedDate()), todayDate).getDays());

		member.setDaysSinceCreation(daysSinceCreation);

		slf4jLogger.debug("Exiting function {}", "calculateDaysSinceCreation");

	}

	public List<UpcomingPayment> calculateUpcomingPayments(List<MemberEntries> approvedEntries){

		slf4jLogger.debug("Entering function {}", "calculateUpcomingPayments");

		List<UpcomingPayment> upcomingPayments = new ArrayList<UpcomingPayment>();		
		List<Subscription> subscriptions = knottingServicesDao.fetchAvailableListOfSubscriptions();

		for(MemberEntries memberEntry : approvedEntries){
			int subscriptionTotal = 0;

			for(MemberServices memberService : memberEntry.getServicesRecords()){
				for(Subscription subscription : subscriptions){
					if(subscription.getServices().getServicesId().equals(memberService.getServices().getServicesId())){
						subscriptionTotal += subscription.getSubscriptionRate();
					}
				}
			}

			UpcomingPayment upcomingPayment = new UpcomingPayment();			
			upcomingPayment.setEntryName(memberEntry.getName());			
			upcomingPayment.setPaymentAmount(subscriptionTotal);
			upcomingPayment.setPaymentDate((new DateTime(memberEntry.getSubscriptionExpiry()).plusDays(1)).toDate());			
			upcomingPayments.add(upcomingPayment);
		}

		Collections.sort(upcomingPayments, new Comparator<UpcomingPayment>() {
			@Override
			public int compare(UpcomingPayment upcomingPayment1, UpcomingPayment upcomingPayment2) {
				DateTime dateTime1 = new DateTime(upcomingPayment1.getPaymentDate());
				DateTime dateTime2 = new DateTime(upcomingPayment2.getPaymentDate());				
				return (dateTime1.isAfter(dateTime2)) ? 1 : -1;
			}
		});

		slf4jLogger.debug("Exiting function {}", "calculateUpcomingPayments");

		return upcomingPayments;

	}
	
	public void changeStatus(Long serviceId, boolean status){

		slf4jLogger.debug("Entering function {}", "changeStatus");
		
		MemberServices memberServices = knottingVendorServiceDao.fetchMemberService(serviceId);
		
		if(status){
			memberServices.setServiceStatus(serviceActive);
		}
		else{
			memberServices.setServiceStatus(serviceInActive);
		}

		Timestamp currentTimestamp = new Timestamp((new Date()).getTime());
		memberServices.setLastUpdatedDate(currentTimestamp);
		
		knottingProfileDao.addOrUpdateServices(memberServices);

		slf4jLogger.debug("Exiting function {}", "changeStatus");
		
	}
}
