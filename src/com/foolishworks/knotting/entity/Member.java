package com.foolishworks.knotting.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="member")
public class Member implements Serializable {

	private static final long serialVersionUID = 4935296846299295923L;

	@Id
	@Column(name="MMBRID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(name="MMBREMAIL")
	private String emailId;

	@Column(name="MMBRPHN")
	private Long phoneNumber;

	@Column(name="MMBRNAME")
	private String name;

	@Column(name="MMBRPWD")
	private String userPassword;

	@Transient
	private String confirmUserPassword;

	@Column(name="MMBRTYPE")
	private String userType;

	@Column(name="MMBRSCLAUTH")
	private String socialAuth;

	@Column(name="MMBRADMINPWD")
	private String adminPassword;

	@Transient
	private String confirmAdminPassword;

	@Column(name="MMBRCRTDDT")
	private Timestamp createdDate;

	@Column(name="MMBRCRTDBY")
	private Long createdBy;

	@Column(name="MMBRUPTDDT")
	private Timestamp lastUpdatedDate;

	@Column(name="MMBRUPTDBY")
	private Long lastUpdatedBy;

	@Column(name="MMBRSUBDUEDT")
	private Date subscriptionDueOn;

	@Column(name="MMBRTOTSUBPD")
	private Integer totalsubscriptionpaid;

	@Column(name="MMBRDUESUBAMT")
	private Integer duesubscriptionamount;

	@Column(name="MMBREMLVRFD")
	private String emailVerified;

	@Column(name="MMBRPHNVRFD")
	private String phoneVerified;

	@Column(name="MMBRRTNG")
	private BigDecimal rating;

	@Transient
	private String changeUserPassword;

	@Transient
	private String loginUsername;
	
	@Transient
	private Long daysSinceCreation;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getConfirmUserPassword() {
		return confirmUserPassword;
	}

	public void setConfirmUserPassword(String confirmUserPassword) {
		this.confirmUserPassword = confirmUserPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSocialAuth() {
		return socialAuth;
	}

	public void setSocialAuth(String socialAuth) {
		this.socialAuth = socialAuth;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getConfirmAdminPassword() {
		return confirmAdminPassword;
	}

	public void setConfirmAdminPassword(String confirmAdminPassword) {
		this.confirmAdminPassword = confirmAdminPassword;
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

	public Date getSubscriptionDueOn() {
		return subscriptionDueOn;
	}

	public void setSubscriptionDueOn(Date subscriptionDueOn) {
		this.subscriptionDueOn = subscriptionDueOn;
	}

	public Integer getTotalsubscriptionpaid() {
		return totalsubscriptionpaid;
	}

	public void setTotalsubscriptionpaid(Integer totalsubscriptionpaid) {
		this.totalsubscriptionpaid = totalsubscriptionpaid;
	}

	public Integer getDuesubscriptionamount() {
		return duesubscriptionamount;
	}

	public void setDuesubscriptionamount(Integer duesubscriptionamount) {
		this.duesubscriptionamount = duesubscriptionamount;
	}

	public String getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(String phoneVerified) {
		this.phoneVerified = phoneVerified;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getChangeUserPassword() {
		return changeUserPassword;
	}

	public void setChangeUserPassword(String changeUserPassword) {
		this.changeUserPassword = changeUserPassword;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public Long getDaysSinceCreation() {
		return daysSinceCreation;
	}

	public void setDaysSinceCreation(Long daysSinceCreation) {
		this.daysSinceCreation = daysSinceCreation;
	}

}
