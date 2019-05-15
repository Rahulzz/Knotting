package com.foolishworks.knotting.intf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.foolishworks.knotting.entity.Coupon;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.entity.MemberServices;
import com.foolishworks.knotting.entity.MemberServicesStaging;
import com.foolishworks.knotting.entity.RegistrationBuffer;
import com.foolishworks.knotting.entity.ServiceDetails;
import com.foolishworks.knotting.entity.Services;
import com.foolishworks.knotting.entity.Subscription;
import com.foolishworks.knotting.model.ServicesModelStaging;

public interface KnottingServicesService {

	public List<Services> fetchAvailableListOfServices();
	public List<Subscription> fetchAvailableListOfSubscriptions();
	public List<ServiceDetails> fetchListOfServiceQuestionsStaging(Set<MemberServicesStaging> memberServicesStagingSet);
	public List<ServiceDetails> fetchAvailableListOfServiceQuestions();
	public MemberEntries fetchMemberEntriesRecord(String entryId);
	public MemberEntriesStaging fetchMemberEntriesStagingRecord(String entryId);
	public boolean addOrUpdateMemberEntry(ServicesModelStaging servicesModel, String transactionId) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException;
	public Map<String, Object> fetchSubscriptionFeesAndDetails(String serviceIdArray[]);
	public Integer calculateSubscriptionFees(List<MemberServicesStaging> memberServicesStagings);
	public boolean verifyServiceEmail(String id, String email);
	public JSONObject prepareSubscriptionPaymentData(Long memberEntryId, List<String> servicesId, String couponCode);
	public boolean saveEntry(Long userId, ServicesModelStaging servicesModel, String transactionId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException;
	public void createImageCopyInServer(Long memberId, ServicesModelStaging servicesModel) throws IOException;
	public List<ServiceDetails> fetchListOfServiceQuestions(Set<MemberServices> memberServicesSet);
	public void calculateDaysSinceCreation(MemberEntries memberEntries);
	public void calculateDaysSinceCreation(MemberEntriesStaging memberEntriesStaging);
	public void prepareThumbnailImage(MemberEntries memberEntries);
	public void prepareThumbnailImage(MemberEntriesStaging memberEntriesStaging);
	public void prepareThumbnailImage(MemberServices memberServices);
	public Integer applyCoupon(int totalAmount, String couponCode);
	public JSONObject prepareCouponData(String totalAmount, String couponCode) throws JSONException;
	public void deleteAllBufferforUser(Long userId);
	public Long createAndSaveBufferRecord(ServicesModelStaging modelStaging);
	public RegistrationBuffer fetchBufferRecord(Long bufferId);
	public Coupon fetchActiveCoupon(String couponCode);

}
