package com.foolishworks.knotting.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 7357576248125537642L;

	@Id
	@Column(name="TRANID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="TRANMMENTID", referencedColumnName="MMENTID")
	private MemberEntriesStaging memberEntriesStaging;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="TRANMMENTID", referencedColumnName="MMENTID", insertable = false, updatable = false)
	private MemberEntries memberEntries;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TRANMMBRID", referencedColumnName="MMBRID")
	private Member member;

	@Column(name="TRANDATE")
	private Timestamp transactionDate;

	@Column(name="TRANTOTAMT")
	private String totalAmount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TRANCOUPON", referencedColumnName="COUPID")
	private Coupon coupon;

	@Column(name="TRANCOUPDISC")
	private String couponDiscountedAmount;

	@Column(name="TRANPGTRANID")
	private String pgTransactionId;

	@Column(name="TRANPYMODE")
	private String paymentMode;

	@Column(name="TRANPYSTAT")
	private String paymentStatus;

	@Column(name="TRANAMT")
	private String paymentAmount;

	@Column(name="TRANDISC")
	private String discount;

	@Column(name="TRANERROR")
	private String errorCode;

	@Column(name="TRANPGTYP")
	private String pgType;

	@Column(name="TRANBNKREFNMBR")
	private String bankReferenceNumber;

	@Column(name="TRANPGSTAT")
	private String pgStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TRANEXECID", referencedColumnName="MMBRID")
	private Member executive;

	@Column(name="TRANEXECSTAT")
	private String executivePaymentStatus;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction")
	private Set<SubscriptionPayment> subscriptionPayments = new HashSet<SubscriptionPayment>();

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public MemberEntriesStaging getMemberEntriesStaging() {
		if(memberEntriesStaging == null){
			memberEntriesStaging = new MemberEntriesStaging();
		}
		return memberEntriesStaging;
	}

	public void setMemberEntriesStaging(MemberEntriesStaging memberEntriesStaging) {
		this.memberEntriesStaging = memberEntriesStaging;
	}

	public MemberEntries getMemberEntries() {
		if(memberEntries == null){
			memberEntries = new MemberEntries();
		}
		return memberEntries;
	}

	public void setMemberEntries(MemberEntries memberEntries) {
		this.memberEntries = memberEntries;
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

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Coupon getCoupon() {
		if(coupon == null) coupon = new Coupon();
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public String getCouponDiscountedAmount() {
		return couponDiscountedAmount;
	}

	public void setCouponDiscountedAmount(String couponDiscountedAmount) {
		this.couponDiscountedAmount = couponDiscountedAmount;
	}

	public String getPgTransactionId() {
		return pgTransactionId;
	}

	public void setPgTransactionId(String pgTransactionId) {
		this.pgTransactionId = pgTransactionId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getPgType() {
		return pgType;
	}

	public void setPgType(String pgType) {
		this.pgType = pgType;
	}

	public String getBankReferenceNumber() {
		return bankReferenceNumber;
	}

	public void setBankReferenceNumber(String bankReferenceNumber) {
		this.bankReferenceNumber = bankReferenceNumber;
	}

	public String getPgStatus() {
		return pgStatus;
	}

	public void setPgStatus(String pgStatus) {
		this.pgStatus = pgStatus;
	}

	public Member getExecutive() {
		if(executive == null){
			executive = new Member();
		}
		return executive;
	}

	public void setExecutive(Member executive) {
		this.executive = executive;
	}

	public String getExecutivePaymentStatus() {
		return executivePaymentStatus;
	}

	public void setExecutivePaymentStatus(String executivePaymentStatus) {
		this.executivePaymentStatus = executivePaymentStatus;
	}

	public Set<SubscriptionPayment> getSubscriptionPayments() {
		return subscriptionPayments;
	}

	public void setSubscriptionPayments(
			Set<SubscriptionPayment> subscriptionPayments) {
		this.subscriptionPayments = subscriptionPayments;
	}
	
}
