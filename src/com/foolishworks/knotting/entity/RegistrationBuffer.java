package com.foolishworks.knotting.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="registration_buffer")
public class RegistrationBuffer implements Serializable {

	private static final long serialVersionUID = -5962233927096172828L;

	@Id
	@Column(name="REGID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bufferId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="REGMMBRID", referencedColumnName="MMBRID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="REGMMENTID", referencedColumnName="MMBUFID")
	private MemberEntriesBuffer memberEntriesBuffer;

	@Column(name="REGTOTAMT")
	private String totalAmount;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="REGCOUPON", referencedColumnName="COUPCODE")
	private Coupon coupon;

	@Column(name="REGDISCAMT")
	private String discountAmount;

	public Long getBufferId() {
		return bufferId;
	}

	public void setBufferId(Long bufferId) {
		this.bufferId = bufferId;
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

	public MemberEntriesBuffer getMemberEntriesBuffer() {
		return memberEntriesBuffer;
	}

	public void setMemberEntriesBuffer(MemberEntriesBuffer memberEntriesBuffer) {
		this.memberEntriesBuffer = memberEntriesBuffer;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
