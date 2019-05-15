package com.foolishworks.knotting.transformer;

import java.util.ArrayList;
import java.util.List;

import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.MemberServicesBuffer;
import com.foolishworks.knotting.entity.MemberServicesStaging;
import com.foolishworks.knotting.entity.RegistrationBuffer;
import com.foolishworks.knotting.model.ServicesModelStaging;

public class KnottingTransformer {

	public static ServicesModelStaging transformBuffertoStaging(RegistrationBuffer buffer){

		ServicesModelStaging modelStaging = new ServicesModelStaging();

		if(buffer.getCoupon() != null){
			modelStaging.setCoupon(buffer.getCoupon().getCouponCode());
		}
		modelStaging.setDiscount(buffer.getDiscountAmount());
		modelStaging.setTotal(buffer.getTotalAmount());

		MemberEntriesStaging memberEntriesStaging = new MemberEntriesStaging();

		memberEntriesStaging.setMemberEntryId(buffer.getMemberEntriesBuffer().getMemberEntryId());
		memberEntriesStaging.setMember(buffer.getMemberEntriesBuffer().getMember());
		memberEntriesStaging.setName(buffer.getMemberEntriesBuffer().getName());
		memberEntriesStaging.setEmailAddress(buffer.getMemberEntriesBuffer().getEmailAddress());
		memberEntriesStaging.setEmailVerified(buffer.getMemberEntriesBuffer().getEmailVerified());
		memberEntriesStaging.setLocationAddress(buffer.getMemberEntriesBuffer().getLocationAddress());
		memberEntriesStaging.setLocationPoint(buffer.getMemberEntriesBuffer().getLocationPoint());
		memberEntriesStaging.setPhone1(buffer.getMemberEntriesBuffer().getPhone1());
		memberEntriesStaging.setPhone1verified(buffer.getMemberEntriesBuffer().getPhone1verified());
		memberEntriesStaging.setPhone2(buffer.getMemberEntriesBuffer().getPhone2());
		memberEntriesStaging.setPhone3(buffer.getMemberEntriesBuffer().getPhone3());
		memberEntriesStaging.setPhone4(buffer.getMemberEntriesBuffer().getPhone4());

		memberEntriesStaging.setTwitterUrl(buffer.getMemberEntriesBuffer().getTwitterUrl());
		memberEntriesStaging.setWebsiteUrl(buffer.getMemberEntriesBuffer().getWebsiteUrl());
		memberEntriesStaging.setFacebookUrl(buffer.getMemberEntriesBuffer().getFacebookUrl());
		memberEntriesStaging.setInstagramUrl(buffer.getMemberEntriesBuffer().getInstagramUrl());
		memberEntriesStaging.setYoutubeUrl(buffer.getMemberEntriesBuffer().getYoutubeUrl());
		memberEntriesStaging.setGoogleplusUrl(buffer.getMemberEntriesBuffer().getGoogleplusUrl());

		memberEntriesStaging.setEmergencyPhoneNumber(buffer.getMemberEntriesBuffer().getEmergencyPhoneNumber());
		memberEntriesStaging.setEmergencyPhoneNumberVerified(buffer.getMemberEntriesBuffer().getEmergencyPhoneNumberVerified());
		memberEntriesStaging.setEmergencyRequestAccepted(buffer.getMemberEntriesBuffer().getEmergencyRequestAccepted());

		memberEntriesStaging.setAdditionalAreasServiced(buffer.getMemberEntriesBuffer().getAdditionalAreasServiced());
		memberEntriesStaging.setDocumentPath(buffer.getMemberEntriesBuffer().getDocumentPath());
		memberEntriesStaging.setSubscriptionAmount(buffer.getMemberEntriesBuffer().getSubscriptionAmount());
		memberEntriesStaging.setSubscriptionExpiry(buffer.getMemberEntriesBuffer().getSubscriptionExpiry());
		memberEntriesStaging.setSubscriptionTerm(buffer.getMemberEntriesBuffer().getSubscriptionTerm());

		memberEntriesStaging.setCreatedBy(buffer.getMemberEntriesBuffer().getCreatedBy());
		memberEntriesStaging.setCreatedDate(buffer.getMemberEntriesBuffer().getCreatedDate());
		memberEntriesStaging.setLastUpdatedBy(buffer.getMemberEntriesBuffer().getLastUpdatedBy());
		memberEntriesStaging.setLastUpdatedDate(buffer.getMemberEntriesBuffer().getLastUpdatedDate());

		List<MemberServicesStaging> memberServicesStagings = new ArrayList<MemberServicesStaging>();

		for(MemberServicesBuffer memberServicesBuffer : buffer.getMemberEntriesBuffer().getServicesRecords()){
			MemberServicesStaging memberServicesStaging = new MemberServicesStaging();

			memberServicesStaging.setMemberServiceCode(memberServicesBuffer.getMemberServiceCode());

			memberServicesStaging.setExperience(memberServicesBuffer.getExperience());
			memberServicesStaging.setCommunitiesServed(memberServicesBuffer.getCommunitiesServed());
			memberServicesStaging.setAdvancePercentage(memberServicesBuffer.getAdvancePercentage());
			memberServicesStaging.setPriceRange(memberServicesBuffer.getPriceRange());
			memberServicesStaging.setServices(memberServicesBuffer.getServices());

			memberServicesStaging.setImagePath1(memberServicesBuffer.getImagePath1());
			memberServicesStaging.setImagePath2(memberServicesBuffer.getImagePath2());
			memberServicesStaging.setImagePath3(memberServicesBuffer.getImagePath3());
			memberServicesStaging.setImagePath4(memberServicesBuffer.getImagePath4());
			memberServicesStaging.setImagePath5(memberServicesBuffer.getImagePath5());
			memberServicesStaging.setImagePath6(memberServicesBuffer.getImagePath6());
			memberServicesStaging.setImagePath7(memberServicesBuffer.getImagePath7());
			memberServicesStaging.setImagePath8(memberServicesBuffer.getImagePath8());
			memberServicesStaging.setImagePath9(memberServicesBuffer.getImagePath9());
			memberServicesStaging.setImagePath10(memberServicesBuffer.getImagePath10());
			memberServicesStaging.setImagePath11(memberServicesBuffer.getImagePath11());
			memberServicesStaging.setImagePath12(memberServicesBuffer.getImagePath12());
			memberServicesStaging.setImagePath13(memberServicesBuffer.getImagePath13());
			memberServicesStaging.setImagePath14(memberServicesBuffer.getImagePath14());
			memberServicesStaging.setImagePath15(memberServicesBuffer.getImagePath15());
			memberServicesStaging.setImagePath16(memberServicesBuffer.getImagePath16());
			memberServicesStaging.setImagePath17(memberServicesBuffer.getImagePath17());
			memberServicesStaging.setImagePath18(memberServicesBuffer.getImagePath18());
			memberServicesStaging.setImagePath19(memberServicesBuffer.getImagePath19());
			memberServicesStaging.setImagePath20(memberServicesBuffer.getImagePath20());

			memberServicesStaging.setServiceStatus(memberServicesBuffer.getServiceStatus());

			memberServicesStaging.setServicesCriteria1(memberServicesBuffer.getServicesCriteria1());
			memberServicesStaging.setServicesCriteria2(memberServicesBuffer.getServicesCriteria2());
			memberServicesStaging.setServicesCriteria3(memberServicesBuffer.getServicesCriteria3());
			memberServicesStaging.setServicesCriteria4(memberServicesBuffer.getServicesCriteria4());
			memberServicesStaging.setServicesCriteria5(memberServicesBuffer.getServicesCriteria5());
			memberServicesStaging.setServicesCriteria6(memberServicesBuffer.getServicesCriteria6());
			memberServicesStaging.setServicesCriteria7(memberServicesBuffer.getServicesCriteria7());
			memberServicesStaging.setServicesCriteria8(memberServicesBuffer.getServicesCriteria8());
			memberServicesStaging.setServicesCriteria9(memberServicesBuffer.getServicesCriteria9());
			memberServicesStaging.setServicesCriteria10(memberServicesBuffer.getServicesCriteria10());
			memberServicesStaging.setServicesCriteria11(memberServicesBuffer.getServicesCriteria11());
			memberServicesStaging.setServicesCriteria12(memberServicesBuffer.getServicesCriteria12());
			memberServicesStaging.setServicesCriteria13(memberServicesBuffer.getServicesCriteria13());
			memberServicesStaging.setServicesCriteria14(memberServicesBuffer.getServicesCriteria14());
			memberServicesStaging.setServicesCriteria15(memberServicesBuffer.getServicesCriteria15());
			memberServicesStaging.setServicesCriteria16(memberServicesBuffer.getServicesCriteria16());
			memberServicesStaging.setServicesCriteria17(memberServicesBuffer.getServicesCriteria17());
			memberServicesStaging.setServicesCriteria18(memberServicesBuffer.getServicesCriteria18());
			memberServicesStaging.setServicesCriteria19(memberServicesBuffer.getServicesCriteria19());
			memberServicesStaging.setServicesCriteria20(memberServicesBuffer.getServicesCriteria20());
			memberServicesStaging.setServicesCriteria21(memberServicesBuffer.getServicesCriteria21());
			memberServicesStaging.setServicesCriteria22(memberServicesBuffer.getServicesCriteria22());
			memberServicesStaging.setServicesCriteria23(memberServicesBuffer.getServicesCriteria23());
			memberServicesStaging.setServicesCriteria24(memberServicesBuffer.getServicesCriteria24());
			memberServicesStaging.setServicesCriteria25(memberServicesBuffer.getServicesCriteria25());
			memberServicesStaging.setServicesCriteria26(memberServicesBuffer.getServicesCriteria26());
			memberServicesStaging.setServicesCriteria27(memberServicesBuffer.getServicesCriteria27());
			memberServicesStaging.setServicesCriteria28(memberServicesBuffer.getServicesCriteria28());
			memberServicesStaging.setServicesCriteria29(memberServicesBuffer.getServicesCriteria29());
			memberServicesStaging.setServicesCriteria30(memberServicesBuffer.getServicesCriteria30());

			memberServicesStaging.setMemberEntriesStaging(memberEntriesStaging);

			memberServicesStaging.setCreatedBy(memberServicesBuffer.getCreatedBy());
			memberServicesStaging.setCreatedDate(memberServicesBuffer.getCreatedDate());
			memberServicesStaging.setLastUpdatedBy(memberServicesBuffer.getLastUpdatedBy());
			memberServicesStaging.setLastUpdatedDate(memberServicesBuffer.getLastUpdatedDate());

			memberServicesStagings.add(memberServicesStaging);
		}

		modelStaging.setMemberEntriesStaging(memberEntriesStaging);
		modelStaging.setMemberServicesStagingList(memberServicesStagings);

		return modelStaging;

	}

	public static MemberEntries transformStagingEntryToFinal(MemberEntries memberEntries, MemberEntriesStaging memberEntriesStaging){
		
		if(memberEntries == null){
			memberEntries = new MemberEntries();
		}

		memberEntries.setMemberEntryId(memberEntriesStaging.getMemberEntryId());
		memberEntries.setMember(memberEntriesStaging.getMember());

		memberEntries.setName(memberEntriesStaging.getName());
		memberEntries.setLocationAddress(memberEntriesStaging.getLocationAddress());
		memberEntries.setLocationPoint(memberEntriesStaging.getLocationPoint());
		memberEntries.setPhone1(memberEntriesStaging.getPhone1());
		memberEntries.setPhone1verified(memberEntriesStaging.getPhone1verified());
		memberEntries.setPhone2(memberEntriesStaging.getPhone2());
		memberEntries.setPhone3(memberEntriesStaging.getPhone3());
		memberEntries.setPhone4(memberEntriesStaging.getPhone4());
		memberEntries.setEmailAddress(memberEntriesStaging.getEmailAddress());
		memberEntries.setEmailVerified(memberEntriesStaging.getEmailVerified());

		memberEntries.setEmergencyPhoneNumber(memberEntriesStaging.getEmergencyPhoneNumber());
		memberEntries.setEmergencyPhoneNumberVerified(memberEntriesStaging.getEmergencyPhoneNumberVerified());
		memberEntries.setEmergencyRequestAccepted(memberEntriesStaging.getEmergencyRequestAccepted());

		memberEntries.setDocumentPath(memberEntriesStaging.getDocumentPath());
		memberEntries.setAdditionalAreasServiced(memberEntriesStaging.getAdditionalAreasServiced());

		memberEntries.setSubscriptionAmount(memberEntriesStaging.getSubscriptionAmount());
		memberEntries.setSubscriptionExpiry(memberEntriesStaging.getSubscriptionExpiry());
		memberEntries.setSubscriptionTerm(memberEntriesStaging.getSubscriptionTerm());

		memberEntries.setTwitterUrl(memberEntriesStaging.getTwitterUrl());
		memberEntries.setWebsiteUrl(memberEntriesStaging.getWebsiteUrl());
		memberEntries.setFacebookUrl(memberEntriesStaging.getFacebookUrl());
		memberEntries.setInstagramUrl(memberEntriesStaging.getInstagramUrl());
		memberEntries.setYoutubeUrl(memberEntriesStaging.getYoutubeUrl());
		memberEntries.setGoogleplusUrl(memberEntriesStaging.getGoogleplusUrl());

		memberEntries.setCreatedBy(memberEntriesStaging.getCreatedBy());
		memberEntries.setCreatedDate(memberEntriesStaging.getCreatedDate());
		memberEntries.setLastUpdatedBy(memberEntriesStaging.getLastUpdatedBy());
		memberEntries.setLastUpdatedDate(memberEntriesStaging.getLastUpdatedDate());

		for(MemberServicesStaging memberServicesStaging : memberEntriesStaging.getServicesRecords()){
			MemberServices memberServices = transformStagingServiceToFinal(memberServicesStaging);
			memberServices.setMemberEntries(memberEntries);
			memberEntries.getServicesRecords().add(memberServices);
		}

		return memberEntries;

	}

	private static MemberServices transformStagingServiceToFinal(MemberServicesStaging memberServicesStaging){

		MemberServices memberServices = new MemberServices();

		memberServices.setMemberServiceCode(memberServicesStaging.getMemberServiceCode());
		
		memberServices.setExperience(memberServicesStaging.getExperience());
		memberServices.setCommunitiesServed(memberServicesStaging.getCommunitiesServed());
		memberServices.setAdvancePercentage(memberServicesStaging.getAdvancePercentage());		
		memberServices.setPriceRange(memberServicesStaging.getPriceRange());
		
		memberServices.setServices(memberServicesStaging.getServices());
		memberServices.setServiceStatus(memberServicesStaging.getServiceStatus());
		
		memberServices.setImagePath1(memberServicesStaging.getImagePath1());
		memberServices.setImagePath2(memberServicesStaging.getImagePath2());
		memberServices.setImagePath3(memberServicesStaging.getImagePath3());
		memberServices.setImagePath4(memberServicesStaging.getImagePath4());
		memberServices.setImagePath5(memberServicesStaging.getImagePath5());
		memberServices.setImagePath6(memberServicesStaging.getImagePath6());
		memberServices.setImagePath7(memberServicesStaging.getImagePath7());
		memberServices.setImagePath8(memberServicesStaging.getImagePath8());
		memberServices.setImagePath9(memberServicesStaging.getImagePath9());
		memberServices.setImagePath10(memberServicesStaging.getImagePath10());
		memberServices.setImagePath11(memberServicesStaging.getImagePath11());
		memberServices.setImagePath12(memberServicesStaging.getImagePath12());
		memberServices.setImagePath13(memberServicesStaging.getImagePath13());
		memberServices.setImagePath14(memberServicesStaging.getImagePath14());
		memberServices.setImagePath15(memberServicesStaging.getImagePath15());
		memberServices.setImagePath16(memberServicesStaging.getImagePath16());
		memberServices.setImagePath17(memberServicesStaging.getImagePath17());
		memberServices.setImagePath18(memberServicesStaging.getImagePath18());
		memberServices.setImagePath19(memberServicesStaging.getImagePath19());
		memberServices.setImagePath20(memberServicesStaging.getImagePath20());	

		memberServices.setServicesCriteria1(memberServicesStaging.getServicesCriteria1());
		memberServices.setServicesCriteria2(memberServicesStaging.getServicesCriteria2());
		memberServices.setServicesCriteria3(memberServicesStaging.getServicesCriteria3());
		memberServices.setServicesCriteria4(memberServicesStaging.getServicesCriteria4());
		memberServices.setServicesCriteria5(memberServicesStaging.getServicesCriteria5());
		memberServices.setServicesCriteria6(memberServicesStaging.getServicesCriteria6());
		memberServices.setServicesCriteria7(memberServicesStaging.getServicesCriteria7());
		memberServices.setServicesCriteria8(memberServicesStaging.getServicesCriteria8());
		memberServices.setServicesCriteria9(memberServicesStaging.getServicesCriteria9());
		memberServices.setServicesCriteria10(memberServicesStaging.getServicesCriteria10());
		memberServices.setServicesCriteria11(memberServicesStaging.getServicesCriteria11());
		memberServices.setServicesCriteria12(memberServicesStaging.getServicesCriteria12());
		memberServices.setServicesCriteria13(memberServicesStaging.getServicesCriteria13());
		memberServices.setServicesCriteria14(memberServicesStaging.getServicesCriteria14());
		memberServices.setServicesCriteria15(memberServicesStaging.getServicesCriteria15());
		memberServices.setServicesCriteria16(memberServicesStaging.getServicesCriteria16());
		memberServices.setServicesCriteria17(memberServicesStaging.getServicesCriteria17());
		memberServices.setServicesCriteria18(memberServicesStaging.getServicesCriteria18());
		memberServices.setServicesCriteria19(memberServicesStaging.getServicesCriteria19());
		memberServices.setServicesCriteria20(memberServicesStaging.getServicesCriteria20());
		memberServices.setServicesCriteria21(memberServicesStaging.getServicesCriteria21());
		memberServices.setServicesCriteria22(memberServicesStaging.getServicesCriteria22());
		memberServices.setServicesCriteria23(memberServicesStaging.getServicesCriteria23());
		memberServices.setServicesCriteria24(memberServicesStaging.getServicesCriteria24());
		memberServices.setServicesCriteria25(memberServicesStaging.getServicesCriteria25());
		memberServices.setServicesCriteria26(memberServicesStaging.getServicesCriteria26());
		memberServices.setServicesCriteria27(memberServicesStaging.getServicesCriteria27());
		memberServices.setServicesCriteria28(memberServicesStaging.getServicesCriteria28());
		memberServices.setServicesCriteria29(memberServicesStaging.getServicesCriteria29());
		memberServices.setServicesCriteria30(memberServicesStaging.getServicesCriteria30());
		
		memberServices.setCreatedBy(memberServicesStaging.getCreatedBy());
		memberServices.setCreatedDate(memberServicesStaging.getCreatedDate());
		memberServices.setLastUpdatedBy(memberServicesStaging.getLastUpdatedBy());
		memberServices.setLastUpdatedDate(memberServicesStaging.getLastUpdatedDate());

		return memberServices;

	}

}
