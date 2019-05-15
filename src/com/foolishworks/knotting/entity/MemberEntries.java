package com.foolishworks.knotting.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="memberentries")
public class MemberEntries implements Serializable {

	private static final long serialVersionUID = 7813579918144014949L;

	@Id
	@Column(name="MMENTID")
	private Long memberEntryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MMENTMMBRID", referencedColumnName="MMBRID")
	private Member member;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "memberEntries")
	private Set<Transaction> transactions = new HashSet<Transaction>();
	
	@Column(name="MMENTNAME")
	private String name;
	
	@Column(name="MMENTADDRSS")
	private String locationAddress;
	
	@Column(name="MMENTLATLNG")
	private String locationPoint;
	
	@Column(name="MMENTPHN1")
	private Long phone1;
	
	@Column(name="MMSRPHN1VRFD")
	private String phone1verified;
	
	@Column(name="MMENTPHN2")
	private Long phone2;
	
	@Column(name="MMENTPHN3")
	private Long phone3;
	
	@Column(name="MMENTPHN4")
	private Long phone4;
	
	@Column(name="MMENTEMAIL")
	private String emailAddress;
	
	@Column(name="MMSREMAILVRFD")
	private String emailVerified;
	
	@Column(name="MMENTDOCPTH")
	private String documentPath;
	
	@Column(name="MMENTSUBAMNT")
	private Integer subscriptionAmount;
	
	@Column(name="MMENTSUBTERM")
	private Long subscriptionTerm;
	
	@Column(name="MMENTSUBEXPRDT")
	private Timestamp subscriptionExpiry;
	
	@Column(name="MMENTEMRGNCYACPTD")
	private String emergencyRequestAccepted;
	
	@Column(name="MMENTEMRGNCYPHN")
	private Long emergencyPhoneNumber;
	
	@Column(name="MMENTEMRGNCYPHNVRFD")
	private String emergencyPhoneNumberVerified;
	
	@Column(name="MMENTADDTNLAREAS")
	private String additionalAreasServiced;
	
	@Column(name="MMENTWBSTURL")
	private String websiteUrl;
	
	@Column(name="MMENTFCBKURL")
	private String facebookUrl;
	
	@Column(name="MMENTTWITRURL")
	private String twitterUrl;
	
	@Column(name="MMENTINSTAURL")
	private String instagramUrl;
	
	@Column(name="MMENTUTUBEURL")
	private String youtubeUrl;
	
	@Column(name="MMENTGPLUSURL")
	private String googleplusUrl;
	
	@Column(name="MMENTCRTDDT")
	private Timestamp createdDate;
	
	@Column(name="MMENTCRTDBY")
	private Long createdBy;
	
	@Column(name="MMENTUPTDDT")
	private Timestamp lastUpdatedDate;
	
	@Column(name="MMENTUPTDBY")
	private Long lastUpdatedBy;	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "memberEntries", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<MemberServices> servicesRecords = new HashSet<MemberServices>();
	
	@Transient
	private Long daysSinceCreation;

	public Long getMemberEntryId() {
		return memberEntryId;
	}

	public void setMemberEntryId(Long memberEntryId) {
		this.memberEntryId = memberEntryId;
	}

	public Member getMember() {
		if(member == null){
			member = new Member();
		}
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationPoint() {
		return locationPoint;
	}

	public void setLocationPoint(String locationPoint) {
		this.locationPoint = locationPoint;
	}

	public Long getPhone1() {
		return phone1;
	}

	public void setPhone1(Long phone1) {
		this.phone1 = phone1;
	}

	public Long getPhone2() {
		return phone2;
	}

	public void setPhone2(Long phone2) {
		this.phone2 = phone2;
	}

	public Long getPhone3() {
		return phone3;
	}

	public void setPhone3(Long phone3) {
		this.phone3 = phone3;
	}

	public Long getPhone4() {
		return phone4;
	}

	public void setPhone4(Long phone4) {
		this.phone4 = phone4;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public Integer getSubscriptionAmount() {
		return subscriptionAmount;
	}

	public void setSubscriptionAmount(Integer subscriptionAmount) {
		this.subscriptionAmount = subscriptionAmount;
	}

	public Long getSubscriptionTerm() {
		return subscriptionTerm;
	}

	public void setSubscriptionTerm(Long subscriptionTerm) {
		this.subscriptionTerm = subscriptionTerm;
	}

	public Timestamp getSubscriptionExpiry() {
		return subscriptionExpiry;
	}

	public void setSubscriptionExpiry(Timestamp subscriptionExpiry) {
		this.subscriptionExpiry = subscriptionExpiry;
	}

	public String getEmergencyRequestAccepted() {
		return emergencyRequestAccepted;
	}

	public void setEmergencyRequestAccepted(String emergencyRequestAccepted) {
		this.emergencyRequestAccepted = emergencyRequestAccepted;
	}

	public Long getEmergencyPhoneNumber() {
		return emergencyPhoneNumber;
	}

	public void setEmergencyPhoneNumber(Long emergencyPhoneNumber) {
		this.emergencyPhoneNumber = emergencyPhoneNumber;
	}

	public String getEmergencyPhoneNumberVerified() {
		return emergencyPhoneNumberVerified;
	}

	public void setEmergencyPhoneNumberVerified(String emergencyPhoneNumberVerified) {
		this.emergencyPhoneNumberVerified = emergencyPhoneNumberVerified;
	}

	public String getAdditionalAreasServiced() {
		return additionalAreasServiced;
	}

	public void setAdditionalAreasServiced(String additionalAreasServiced) {
		this.additionalAreasServiced = additionalAreasServiced;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public String getInstagramUrl() {
		return instagramUrl;
	}

	public void setInstagramUrl(String instagramUrl) {
		this.instagramUrl = instagramUrl;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public String getGoogleplusUrl() {
		return googleplusUrl;
	}

	public void setGoogleplusUrl(String googleplusUrl) {
		this.googleplusUrl = googleplusUrl;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getPhone1verified() {
		return phone1verified;
	}

	public void setPhone1verified(String phone1verified) {
		this.phone1verified = phone1verified;
	}

	public String getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Set<MemberServices> getServicesRecords() {
		if(servicesRecords == null) servicesRecords = new HashSet<MemberServices>();
		return servicesRecords;
	}

	public void setServicesRecords(Set<MemberServices> servicesRecords) {
		this.servicesRecords = servicesRecords;
	}

	public Long getDaysSinceCreation() {
		return daysSinceCreation;
	}

	public void setDaysSinceCreation(Long daysSinceCreation) {
		this.daysSinceCreation = daysSinceCreation;
	}

}
