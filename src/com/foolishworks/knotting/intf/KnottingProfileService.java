package com.foolishworks.knotting.intf;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.foolishworks.knotting.entity.Member;
import com.foolishworks.knotting.entity.MemberEntries;
import com.foolishworks.knotting.entity.MemberEntriesStaging;
import com.foolishworks.knotting.model.UpcomingPayment;

public interface KnottingProfileService {
	
	public void savePersonalInfo(Member member) throws NoSuchAlgorithmException;
	public List<MemberEntries> fetchApprovedMemberServicesForThisUser(Long userId);
	public List<MemberEntriesStaging> fetchNotApprovedMemberServicesForThisUser(Long userId);
	public Member fetchMemberRecord(Long memberId);
	public void sendUserVerificationEmail(Long memberId, String email) throws Exception;
	public boolean verifyUserEmail(String userId, String email);
	public String changeUserPassword(Long memberId, String password) throws NoSuchAlgorithmException;
	public void calculateDaysSinceCreation(Member member);
	public List<UpcomingPayment> calculateUpcomingPayments(List<MemberEntries> approvedEntries);
	public void changeStatus(Long serviceId, boolean status);
	
}
