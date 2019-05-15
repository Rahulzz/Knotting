package com.foolishworks.knotting.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="subscriptionpayment")
public class SubscriptionPayment implements Serializable {

	private static final long serialVersionUID = -118247111978653677L;

	@Id
	@Column(name="SUBPYID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionPaymentId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUBPYMMBRID", referencedColumnName="MMBRID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="SUBPYMMENTID", referencedColumnName="MMENTID")
	private MemberEntriesStaging memberEntriesStaging;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="SUBPYMMENTID", referencedColumnName="MMENTID", insertable = false, updatable = false)
	private MemberEntries memberEntries;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUBPYSRVCID", referencedColumnName="SRVCID")
	private Services services;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUBPYTRANID", referencedColumnName="TRANID")
	private Transaction transaction;

	@Column(name="SUBPYAMT")
	private String paymentAmount;
	
	@Column(name="SUBPYSTRTDT")
	private Date termStartDate;
	
	@Column(name="SUBPYENDDT")
	private Date termEndDate;

	public Long getSubscriptionPaymentId() {
		return subscriptionPaymentId;
	}

	public void setSubscriptionPaymentId(Long subscriptionPaymentId) {
		this.subscriptionPaymentId = subscriptionPaymentId;
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
	
	public Services getServices() {
		if(services == null){
			services = new Services();
		}
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public Transaction getTransaction() {
		if(transaction == null){
			transaction = new Transaction();
		}
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getTermStartDate() {
		return termStartDate;
	}

	public void setTermStartDate(Date termStartDate) {
		this.termStartDate = termStartDate;
	}

	public Date getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(Date termEndDate) {
		this.termEndDate = termEndDate;
	}
	
}
