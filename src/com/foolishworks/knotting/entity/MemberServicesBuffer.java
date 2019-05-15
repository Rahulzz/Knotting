package com.foolishworks.knotting.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="memberservices_buffer")
public class MemberServicesBuffer implements Serializable {
	
	private static final long serialVersionUID = -7330968091983438275L;

	@Id
	@Column(name="MMSRID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberServiceId;
	
	@Column(name="MMSRCODE")
	private String memberServiceCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MMSRMMENTID", referencedColumnName="MMBUFID")
	private MemberEntriesBuffer memberEntriesBuffer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MMSRSRVCID", referencedColumnName="SRVCID")
	private Services services;
	
	@Column(name="MMSRPRCERNGE")
	private String priceRange;
	
	@Column(name="MMSRADVNC")
	private String advancePercentage;
	
	@Column(name="MMSREXP")
	private String experience;
	
	@Column(name="MMSRCOMMS")
	private String communitiesServed;
	
	@Column(name="MMSRCRTDDT")
	private Timestamp createdDate;
	
	@Column(name="MMSRCRTDBY")
	private Long createdBy;
	
	@Column(name="MMSRUPTDDT")
	private Timestamp lastUpdatedDate;
	
	@Column(name="MMSRUPTDBY")
	private Long lastUpdatedBy;
	
	@Column(name="MMSRIMGPTH01")
	private String imagePath1;
	
	@Column(name="MMSRIMGPTH02")
	private String imagePath2;
	
	@Column(name="MMSRIMGPTH03")
	private String imagePath3;
	
	@Column(name="MMSRIMGPTH04")
	private String imagePath4;
	
	@Column(name="MMSRIMGPTH05")
	private String imagePath5;
	
	@Column(name="MMSRIMGPTH06")
	private String imagePath6;
	
	@Column(name="MMSRIMGPTH07")
	private String imagePath7;
	
	@Column(name="MMSRIMGPTH08")
	private String imagePath8;
	
	@Column(name="MMSRIMGPTH09")
	private String imagePath9;
	
	@Column(name="MMSRIMGPTH10")
	private String imagePath10;
	
	@Column(name="MMSRIMGPTH11")
	private String imagePath11;
	
	@Column(name="MMSRIMGPTH12")
	private String imagePath12;
	
	@Column(name="MMSRIMGPTH13")
	private String imagePath13;
	
	@Column(name="MMSRIMGPTH14")
	private String imagePath14;
	
	@Column(name="MMSRIMGPTH15")
	private String imagePath15;
	
	@Column(name="MMSRIMGPTH16")
	private String imagePath16;
	
	@Column(name="MMSRIMGPTH17")
	private String imagePath17;
	
	@Column(name="MMSRIMGPTH18")
	private String imagePath18;
	
	@Column(name="MMSRIMGPTH19")
	private String imagePath19;
	
	@Column(name="MMSRIMGPTH20")
	private String imagePath20;
	@Transient
	private String imagePath1Thumbnail;
	
	@Transient
	private String imagePath2Thumbnail;
	
	@Transient
	private String imagePath3Thumbnail;
	
	@Transient
	private String imagePath4Thumbnail;
	
	@Transient
	private String imagePath5Thumbnail;
	
	@Transient
	private String imagePath6Thumbnail;
	
	@Transient
	private String imagePath7Thumbnail;
	
	@Transient
	private String imagePath8Thumbnail;
	
	@Transient
	private String imagePath9Thumbnail;
	
	@Transient
	private String imagePath10Thumbnail;
	
	@Transient
	private String imagePath11Thumbnail;
	
	@Transient
	private String imagePath12Thumbnail;
	
	@Transient
	private String imagePath13Thumbnail;
	
	@Transient
	private String imagePath14Thumbnail;
	
	@Transient
	private String imagePath15Thumbnail;
	
	@Transient
	private String imagePath16Thumbnail;
	
	@Transient
	private String imagePath17Thumbnail;
	
	@Transient
	private String imagePath18Thumbnail;
	
	@Transient
	private String imagePath19Thumbnail;
	
	@Transient
	private String imagePath20Thumbnail;
	
	@Column(name="MMSRCRTA01")
	private String servicesCriteria1;
	
	@Column(name="MMSRCRTA02")
	private String servicesCriteria2;
	
	@Column(name="MMSRCRTA03")
	private String servicesCriteria3;
	
	@Column(name="MMSRCRTA04")
	private String servicesCriteria4;
	
	@Column(name="MMSRCRTA05")
	private String servicesCriteria5;
	
	@Column(name="MMSRCRTA06")
	private String servicesCriteria6;
	
	@Column(name="MMSRCRTA07")
	private String servicesCriteria7;
	
	@Column(name="MMSRCRTA08")
	private String servicesCriteria8;
	
	@Column(name="MMSRCRTA09")
	private String servicesCriteria9;
	
	@Column(name="MMSRCRTA10")
	private String servicesCriteria10;
	
	@Column(name="MMSRCRTA11")
	private String servicesCriteria11;
	
	@Column(name="MMSRCRTA12")
	private String servicesCriteria12;
	
	@Column(name="MMSRCRTA13")
	private String servicesCriteria13;
	
	@Column(name="MMSRCRTA14")
	private String servicesCriteria14;
	
	@Column(name="MMSRCRTA15")
	private String servicesCriteria15;
	
	@Column(name="MMSRCRTA16")
	private String servicesCriteria16;
	
	@Column(name="MMSRCRTA17")
	private String servicesCriteria17;
	
	@Column(name="MMSRCRTA18")
	private String servicesCriteria18;
	
	@Column(name="MMSRCRTA19")
	private String servicesCriteria19;
	
	@Column(name="MMSRCRTA20")
	private String servicesCriteria20;
	
	@Column(name="MMSRCRTA21")
	private String servicesCriteria21;
	
	@Column(name="MMSRCRTA22")
	private String servicesCriteria22;
	
	@Column(name="MMSRCRTA23")
	private String servicesCriteria23;
	
	@Column(name="MMSRCRTA24")
	private String servicesCriteria24;
	
	@Column(name="MMSRCRTA25")
	private String servicesCriteria25;
	
	@Column(name="MMSRCRTA26")
	private String servicesCriteria26;
	
	@Column(name="MMSRCRTA27")
	private String servicesCriteria27;
	
	@Column(name="MMSRCRTA28")
	private String servicesCriteria28;
	
	@Column(name="MMSRCRTA29")
	private String servicesCriteria29;
	
	@Column(name="MMSRCRTA30")
	private String servicesCriteria30;
	
	@Column(name="MMSRSTUS")
	private String serviceStatus;

	public Long getMemberServiceId() {
		return memberServiceId;
	}

	public void setMemberServiceId(Long memberServiceId) {
		this.memberServiceId = memberServiceId;
	}	
	
	public String getMemberServiceCode() {
		return memberServiceCode;
	}

	public void setMemberServiceCode(String memberServiceCode) {
		this.memberServiceCode = memberServiceCode;
	}

	public MemberEntriesBuffer getMemberEntriesBuffer() {
		if(memberEntriesBuffer == null){
			memberEntriesBuffer = new MemberEntriesBuffer();
		}
		return memberEntriesBuffer;
	}

	public void setMemberEntriesBuffer(MemberEntriesBuffer memberEntriesBuffer) {
		this.memberEntriesBuffer = memberEntriesBuffer;
	}

	public Services getServices() {
		if(services == null){
			services = new Services();
		}
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getAdvancePercentage() {
		return advancePercentage;
	}

	public void setAdvancePercentage(String advancePercentage) {
		this.advancePercentage = advancePercentage;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCommunitiesServed() {
		return communitiesServed;
	}

	public void setCommunitiesServed(String communitiesServed) {
		this.communitiesServed = communitiesServed;
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

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public String getImagePath3() {
		return imagePath3;
	}

	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}

	public String getImagePath4() {
		return imagePath4;
	}

	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}

	public String getImagePath5() {
		return imagePath5;
	}

	public void setImagePath5(String imagePath5) {
		this.imagePath5 = imagePath5;
	}
	
	public String getImagePath6() {
		return imagePath6;
	}

	public void setImagePath6(String imagePath6) {
		this.imagePath6 = imagePath6;
	}

	public String getImagePath7() {
		return imagePath7;
	}

	public void setImagePath7(String imagePath7) {
		this.imagePath7 = imagePath7;
	}

	public String getImagePath8() {
		return imagePath8;
	}

	public void setImagePath8(String imagePath8) {
		this.imagePath8 = imagePath8;
	}

	public String getImagePath9() {
		return imagePath9;
	}

	public void setImagePath9(String imagePath9) {
		this.imagePath9 = imagePath9;
	}

	public String getImagePath10() {
		return imagePath10;
	}

	public void setImagePath10(String imagePath10) {
		this.imagePath10 = imagePath10;
	}

	public String getImagePath11() {
		return imagePath11;
	}

	public void setImagePath11(String imagePath11) {
		this.imagePath11 = imagePath11;
	}

	public String getImagePath12() {
		return imagePath12;
	}

	public void setImagePath12(String imagePath12) {
		this.imagePath12 = imagePath12;
	}

	public String getImagePath13() {
		return imagePath13;
	}

	public void setImagePath13(String imagePath13) {
		this.imagePath13 = imagePath13;
	}

	public String getImagePath14() {
		return imagePath14;
	}

	public void setImagePath14(String imagePath14) {
		this.imagePath14 = imagePath14;
	}

	public String getImagePath15() {
		return imagePath15;
	}

	public void setImagePath15(String imagePath15) {
		this.imagePath15 = imagePath15;
	}

	public String getImagePath16() {
		return imagePath16;
	}

	public void setImagePath16(String imagePath16) {
		this.imagePath16 = imagePath16;
	}

	public String getImagePath17() {
		return imagePath17;
	}

	public void setImagePath17(String imagePath17) {
		this.imagePath17 = imagePath17;
	}

	public String getImagePath18() {
		return imagePath18;
	}

	public void setImagePath18(String imagePath18) {
		this.imagePath18 = imagePath18;
	}

	public String getImagePath19() {
		return imagePath19;
	}

	public void setImagePath19(String imagePath19) {
		this.imagePath19 = imagePath19;
	}

	public String getImagePath20() {
		return imagePath20;
	}

	public void setImagePath20(String imagePath20) {
		this.imagePath20 = imagePath20;
	}

	public String getImagePath1Thumbnail() {
		return imagePath1Thumbnail;
	}

	public void setImagePath1Thumbnail(String imagePath1Thumbnail) {
		this.imagePath1Thumbnail = imagePath1Thumbnail;
	}

	public String getImagePath2Thumbnail() {
		return imagePath2Thumbnail;
	}

	public void setImagePath2Thumbnail(String imagePath2Thumbnail) {
		this.imagePath2Thumbnail = imagePath2Thumbnail;
	}

	public String getImagePath3Thumbnail() {
		return imagePath3Thumbnail;
	}

	public void setImagePath3Thumbnail(String imagePath3Thumbnail) {
		this.imagePath3Thumbnail = imagePath3Thumbnail;
	}

	public String getImagePath4Thumbnail() {
		return imagePath4Thumbnail;
	}

	public void setImagePath4Thumbnail(String imagePath4Thumbnail) {
		this.imagePath4Thumbnail = imagePath4Thumbnail;
	}

	public String getImagePath5Thumbnail() {
		return imagePath5Thumbnail;
	}

	public void setImagePath5Thumbnail(String imagePath5Thumbnail) {
		this.imagePath5Thumbnail = imagePath5Thumbnail;
	}

	public String getImagePath6Thumbnail() {
		return imagePath6Thumbnail;
	}

	public void setImagePath6Thumbnail(String imagePath6Thumbnail) {
		this.imagePath6Thumbnail = imagePath6Thumbnail;
	}

	public String getImagePath7Thumbnail() {
		return imagePath7Thumbnail;
	}

	public void setImagePath7Thumbnail(String imagePath7Thumbnail) {
		this.imagePath7Thumbnail = imagePath7Thumbnail;
	}

	public String getImagePath8Thumbnail() {
		return imagePath8Thumbnail;
	}

	public void setImagePath8Thumbnail(String imagePath8Thumbnail) {
		this.imagePath8Thumbnail = imagePath8Thumbnail;
	}

	public String getImagePath9Thumbnail() {
		return imagePath9Thumbnail;
	}

	public void setImagePath9Thumbnail(String imagePath9Thumbnail) {
		this.imagePath9Thumbnail = imagePath9Thumbnail;
	}

	public String getImagePath10Thumbnail() {
		return imagePath10Thumbnail;
	}

	public void setImagePath10Thumbnail(String imagePath10Thumbnail) {
		this.imagePath10Thumbnail = imagePath10Thumbnail;
	}

	public String getImagePath11Thumbnail() {
		return imagePath11Thumbnail;
	}

	public void setImagePath11Thumbnail(String imagePath11Thumbnail) {
		this.imagePath11Thumbnail = imagePath11Thumbnail;
	}

	public String getImagePath12Thumbnail() {
		return imagePath12Thumbnail;
	}

	public void setImagePath12Thumbnail(String imagePath12Thumbnail) {
		this.imagePath12Thumbnail = imagePath12Thumbnail;
	}

	public String getImagePath13Thumbnail() {
		return imagePath13Thumbnail;
	}

	public void setImagePath13Thumbnail(String imagePath13Thumbnail) {
		this.imagePath13Thumbnail = imagePath13Thumbnail;
	}

	public String getImagePath14Thumbnail() {
		return imagePath14Thumbnail;
	}

	public void setImagePath14Thumbnail(String imagePath14Thumbnail) {
		this.imagePath14Thumbnail = imagePath14Thumbnail;
	}

	public String getImagePath15Thumbnail() {
		return imagePath15Thumbnail;
	}

	public void setImagePath15Thumbnail(String imagePath15Thumbnail) {
		this.imagePath15Thumbnail = imagePath15Thumbnail;
	}

	public String getImagePath16Thumbnail() {
		return imagePath16Thumbnail;
	}

	public void setImagePath16Thumbnail(String imagePath16Thumbnail) {
		this.imagePath16Thumbnail = imagePath16Thumbnail;
	}

	public String getImagePath17Thumbnail() {
		return imagePath17Thumbnail;
	}

	public void setImagePath17Thumbnail(String imagePath17Thumbnail) {
		this.imagePath17Thumbnail = imagePath17Thumbnail;
	}

	public String getImagePath18Thumbnail() {
		return imagePath18Thumbnail;
	}

	public void setImagePath18Thumbnail(String imagePath18Thumbnail) {
		this.imagePath18Thumbnail = imagePath18Thumbnail;
	}

	public String getImagePath19Thumbnail() {
		return imagePath19Thumbnail;
	}

	public void setImagePath19Thumbnail(String imagePath19Thumbnail) {
		this.imagePath19Thumbnail = imagePath19Thumbnail;
	}

	public String getImagePath20Thumbnail() {
		return imagePath20Thumbnail;
	}

	public void setImagePath20Thumbnail(String imagePath20Thumbnail) {
		this.imagePath20Thumbnail = imagePath20Thumbnail;
	}

	public String getServicesCriteria1() {
		return servicesCriteria1;
	}

	public void setServicesCriteria1(String servicesCriteria1) {
		this.servicesCriteria1 = servicesCriteria1;
	}

	public String getServicesCriteria2() {
		return servicesCriteria2;
	}

	public void setServicesCriteria2(String servicesCriteria2) {
		this.servicesCriteria2 = servicesCriteria2;
	}

	public String getServicesCriteria3() {
		return servicesCriteria3;
	}

	public void setServicesCriteria3(String servicesCriteria3) {
		this.servicesCriteria3 = servicesCriteria3;
	}

	public String getServicesCriteria4() {
		return servicesCriteria4;
	}

	public void setServicesCriteria4(String servicesCriteria4) {
		this.servicesCriteria4 = servicesCriteria4;
	}

	public String getServicesCriteria5() {
		return servicesCriteria5;
	}

	public void setServicesCriteria5(String servicesCriteria5) {
		this.servicesCriteria5 = servicesCriteria5;
	}

	public String getServicesCriteria6() {
		return servicesCriteria6;
	}

	public void setServicesCriteria6(String servicesCriteria6) {
		this.servicesCriteria6 = servicesCriteria6;
	}

	public String getServicesCriteria7() {
		return servicesCriteria7;
	}

	public void setServicesCriteria7(String servicesCriteria7) {
		this.servicesCriteria7 = servicesCriteria7;
	}

	public String getServicesCriteria8() {
		return servicesCriteria8;
	}

	public void setServicesCriteria8(String servicesCriteria8) {
		this.servicesCriteria8 = servicesCriteria8;
	}

	public String getServicesCriteria9() {
		return servicesCriteria9;
	}

	public void setServicesCriteria9(String servicesCriteria9) {
		this.servicesCriteria9 = servicesCriteria9;
	}

	public String getServicesCriteria10() {
		return servicesCriteria10;
	}

	public void setServicesCriteria10(String servicesCriteria10) {
		this.servicesCriteria10 = servicesCriteria10;
	}

	public String getServicesCriteria11() {
		return servicesCriteria11;
	}

	public void setServicesCriteria11(String servicesCriteria11) {
		this.servicesCriteria11 = servicesCriteria11;
	}

	public String getServicesCriteria12() {
		return servicesCriteria12;
	}

	public void setServicesCriteria12(String servicesCriteria12) {
		this.servicesCriteria12 = servicesCriteria12;
	}

	public String getServicesCriteria13() {
		return servicesCriteria13;
	}

	public void setServicesCriteria13(String servicesCriteria13) {
		this.servicesCriteria13 = servicesCriteria13;
	}

	public String getServicesCriteria14() {
		return servicesCriteria14;
	}

	public void setServicesCriteria14(String servicesCriteria14) {
		this.servicesCriteria14 = servicesCriteria14;
	}

	public String getServicesCriteria15() {
		return servicesCriteria15;
	}

	public void setServicesCriteria15(String servicesCriteria15) {
		this.servicesCriteria15 = servicesCriteria15;
	}

	public String getServicesCriteria16() {
		return servicesCriteria16;
	}

	public void setServicesCriteria16(String servicesCriteria16) {
		this.servicesCriteria16 = servicesCriteria16;
	}

	public String getServicesCriteria17() {
		return servicesCriteria17;
	}

	public void setServicesCriteria17(String servicesCriteria17) {
		this.servicesCriteria17 = servicesCriteria17;
	}

	public String getServicesCriteria18() {
		return servicesCriteria18;
	}

	public void setServicesCriteria18(String servicesCriteria18) {
		this.servicesCriteria18 = servicesCriteria18;
	}

	public String getServicesCriteria19() {
		return servicesCriteria19;
	}

	public void setServicesCriteria19(String servicesCriteria19) {
		this.servicesCriteria19 = servicesCriteria19;
	}

	public String getServicesCriteria20() {
		return servicesCriteria20;
	}

	public void setServicesCriteria20(String servicesCriteria20) {
		this.servicesCriteria20 = servicesCriteria20;
	}

	public String getServicesCriteria21() {
		return servicesCriteria21;
	}

	public void setServicesCriteria21(String servicesCriteria21) {
		this.servicesCriteria21 = servicesCriteria21;
	}

	public String getServicesCriteria22() {
		return servicesCriteria22;
	}

	public void setServicesCriteria22(String servicesCriteria22) {
		this.servicesCriteria22 = servicesCriteria22;
	}

	public String getServicesCriteria23() {
		return servicesCriteria23;
	}

	public void setServicesCriteria23(String servicesCriteria23) {
		this.servicesCriteria23 = servicesCriteria23;
	}

	public String getServicesCriteria24() {
		return servicesCriteria24;
	}

	public void setServicesCriteria24(String servicesCriteria24) {
		this.servicesCriteria24 = servicesCriteria24;
	}

	public String getServicesCriteria25() {
		return servicesCriteria25;
	}

	public void setServicesCriteria25(String servicesCriteria25) {
		this.servicesCriteria25 = servicesCriteria25;
	}

	public String getServicesCriteria26() {
		return servicesCriteria26;
	}

	public void setServicesCriteria26(String servicesCriteria26) {
		this.servicesCriteria26 = servicesCriteria26;
	}

	public String getServicesCriteria27() {
		return servicesCriteria27;
	}

	public void setServicesCriteria27(String servicesCriteria27) {
		this.servicesCriteria27 = servicesCriteria27;
	}

	public String getServicesCriteria28() {
		return servicesCriteria28;
	}

	public void setServicesCriteria28(String servicesCriteria28) {
		this.servicesCriteria28 = servicesCriteria28;
	}

	public String getServicesCriteria29() {
		return servicesCriteria29;
	}

	public void setServicesCriteria29(String servicesCriteria29) {
		this.servicesCriteria29 = servicesCriteria29;
	}

	public String getServicesCriteria30() {
		return servicesCriteria30;
	}

	public void setServicesCriteria30(String servicesCriteria30) {
		this.servicesCriteria30 = servicesCriteria30;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

}
