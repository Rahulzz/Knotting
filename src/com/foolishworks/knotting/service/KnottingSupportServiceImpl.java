package com.foolishworks.knotting.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foolishworks.knotting.constants.KnottingConstants;
import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.MemberServicesStaging;
import com.foolishworks.knotting.intf.KnottingServicesDao;
import com.foolishworks.knotting.intf.KnottingSupportDao;
import com.foolishworks.knotting.intf.KnottingSupportService;
import com.foolishworks.knotting.transformer.KnottingTransformer;
import com.foolishworks.knotting.utils.CloudinaryImageUtil;
import com.foolishworks.knotting.utils.MailerUtil;

@Service
@Transactional
public class KnottingSupportServiceImpl extends KnottingConstants implements KnottingSupportService {

	@Autowired
	private KnottingSupportDao knottingSupportDao;

	@Autowired
	private KnottingServicesDao knottingServicesDao;

	@Autowired
	private CloudinaryImageUtil cloudinaryImageUtil;

	@Autowired
	private MailerUtil mailerUtil;

	private final Logger slf4jLogger = LoggerFactory
			.getLogger(KnottingSupportServiceImpl.class);

	public List<MemberEntriesStaging> fetchPendingEntries() {

		slf4jLogger.debug("Entering function {}", "fetchPendingEntries");

		List<MemberEntriesStaging> entriesStagings = knottingSupportDao
				.fetchPendingEntries();

		for (MemberEntriesStaging entriesStaging : entriesStagings) {
			MemberEntries memberEntry = knottingServicesDao
					.fetchMemberEntriesRecord(entriesStaging.getMemberEntryId());
			if (memberEntry != null) {
				entriesStaging.setPreExistingRecord(true);
			}
		}

		slf4jLogger.debug("Exiting function {}", "fetchPendingEntries");

		return entriesStagings;

	}

	public MemberEntriesStaging fetchPendingEntry(Long entryId) {

		slf4jLogger.debug("Entering function {}", "fetchPendingEntry");

		MemberEntriesStaging entriesStaging = knottingServicesDao
				.fetchMemberEntriesStagingRecord(entryId);

		slf4jLogger.debug("Exiting function {}", "fetchPendingEntry");

		return entriesStaging;

	}

	public boolean applyActionOnStagingRecord(HttpServletRequest request, Long entryId, String actionType,
			String rejectionDetail) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {

		slf4jLogger.debug("Entering function {}", "applyActionOnStagingRecord");

		boolean skipApproval = false;

		MemberEntriesStaging memberEntriesStaging = knottingServicesDao
				.fetchMemberEntriesStagingRecord(entryId);

		if(memberEntriesStaging.getEmailAddress() != null && !memberEntriesStaging.getEmailAddress().trim().equals("") && memberEntriesStaging.getEmailVerified().equals(verificationNotDone)){
			request.getSession().setAttribute(errorMessage, "Service Entry Email address is not verified.");
			skipApproval = true;
		}

		MemberEntries memberEntries = null;

		if (supportApproveEntry.equals(actionType)) {
			if(!skipApproval){
				if (memberEntriesStaging.getMemberEntryId() != null) {
					memberEntries = knottingServicesDao
							.fetchMemberEntriesRecord(memberEntriesStaging
									.getMemberEntryId());

					if (memberEntries != null) {
						// Remove images associated with the approved entry record
						removeServiceImages(memberEntries);
						// Delete all service records associated with the entry
						// record
						knottingSupportDao
						.deleteAllMembberServiceRecords(memberEntries
								.getMemberEntryId());
					} else {
						for (MemberServicesStaging servicesStaging : memberEntriesStaging
								.getServicesRecords()) {
							servicesStaging.setServiceStatus(serviceActive);
						}
					}
				}

				memberEntries = KnottingTransformer.transformStagingEntryToFinal(
						memberEntries, memberEntriesStaging);

				// save entry record
				knottingSupportDao.addOrUpdateMemberEntry(memberEntries);
			}
		}

		if (!skipApproval && supportApproveEntry.equals(actionType)) {
			if(memberEntries.getMember().getEmailId() != null){
				mailerUtil.sendApprovedMail(memberEntries.getMember().getEmailId(), memberEntries.getName());
			}
			knottingSupportDao.deleteStagingEntry(memberEntriesStaging);
		} else if (supportRejectEntry.equals(actionType)) {
			if(memberEntriesStaging.getMember().getEmailId() != null){
				mailerUtil.sendRejectionMail(memberEntriesStaging.getMember().getEmailId(), memberEntriesStaging.getName(), rejectionDetail);
			}
			knottingSupportDao.deleteStagingEntry(memberEntriesStaging);
		}

		slf4jLogger.debug("Exiting function {}", "applyActionOnStagingRecord");
		
		return !skipApproval;

	}

	private void removeServiceImages(MemberEntries memberEntries)
			throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		slf4jLogger.debug("Exiting function {}", "removeServiceImages");

		for (MemberServices servicesStaging : memberEntries
				.getServicesRecords()) {
			for (int counter = 1; counter <= serviceMaxImageCount; counter++) {
				Method method = servicesStaging.getClass().getMethod(
						"getImagePath" + counter);
				String imageUrl = (String) method.invoke(servicesStaging);
				if (imageUrl != null) {
					cloudinaryImageUtil.deleteImage(imageUrl);
					method = servicesStaging.getClass().getMethod(
							"setImagePath" + counter, String.class);
					method.invoke(servicesStaging, KnottingConstants.NULLSTRING);
				}
			}
		}

		slf4jLogger.debug("Exiting function {}", "removeServiceImages");
	}

	public List<Coupon> fetchCoupons(){

		slf4jLogger.debug("Entering function {}", "fetchCoupons");

		List<Coupon> coupons = knottingSupportDao.fetchCoupons();

		slf4jLogger.debug("Exiting function {}", "fetchCoupons");

		return coupons;

	}

	public Coupon fetchCoupon(){

		slf4jLogger.debug("Entering function {}", "fetchCoupon");

		Coupon coupon = knottingSupportDao.fetchCoupon();

		slf4jLogger.debug("Exiting function {}", "fetchCoupon");

		return coupon;

	}

	public List<Member> fetchMembers(){

		slf4jLogger.debug("Entering function {}", "fetchMembers");

		List<Member> members = knottingSupportDao.fetchMembers();

		slf4jLogger.debug("Exiting function {}", "fetchMembers");

		return members;

	}

}
