package com.foolishworks.knotting.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupon")
public class Coupon implements Serializable {

	private static final long serialVersionUID = -3445569392937810031L;

	@Id
	@Column(name="COUPID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;

	@Column(name="COUPCODE")
	private String couponCode;

	@Column(name="COUPTYPE")
	private String couponType;

	@Column(name="COUPDISCPRCNT")
	private String couponDiscountPercent;

	@Column(name="COUPDISCAMT")
	private String couponDiscountAmount;

	@Column(name="COUPFRMDT")
	private Date couponFromDate;

	@Column(name="COUPEXPDT")
	private Date couponExpiryDate;

	@Column(name="COUPSTATUS")
	private String couponStatus;

	@Column(name="COUPCRTDBY")
	private Long couponCreatedBy;

	@Column(name="COUPCRTDDT")
	private Timestamp couponCreatedDate;

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponDiscountPercent() {
		return couponDiscountPercent;
	}

	public void setCouponDiscountPercent(String couponDiscountPercent) {
		this.couponDiscountPercent = couponDiscountPercent;
	}

	public String getCouponDiscountAmount() {
		return couponDiscountAmount;
	}

	public void setCouponDiscountAmount(String couponDiscountAmount) {
		this.couponDiscountAmount = couponDiscountAmount;
	}

	public Date getCouponFromDate() {
		return couponFromDate;
	}

	public void setCouponFromDate(Date couponFromDate) {
		this.couponFromDate = couponFromDate;
	}

	public Date getCouponExpiryDate() {
		return couponExpiryDate;
	}

	public void setCouponExpiryDate(Date couponExpiryDate) {
		this.couponExpiryDate = couponExpiryDate;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public Long getCouponCreatedBy() {
		return couponCreatedBy;
	}

	public void setCouponCreatedBy(Long couponCreatedBy) {
		this.couponCreatedBy = couponCreatedBy;
	}

	public Timestamp getCouponCreatedDate() {
		return couponCreatedDate;
	}

	public void setCouponCreatedDate(Timestamp couponCreatedDate) {
		this.couponCreatedDate = couponCreatedDate;
	}

}
