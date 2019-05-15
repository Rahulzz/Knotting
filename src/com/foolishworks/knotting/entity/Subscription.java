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

@Entity
@Table(name="subscription")
public class Subscription implements Serializable {

	private static final long serialVersionUID = 4935296846299295923L;

	@Id
	@Column(name="SBSPTNID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SBSPTNSRVCID", referencedColumnName="SRVCID")
	private Services services;

	@Column(name="SBSPTNTERM")
	private Integer subscriptionTerm;

	@Column(name="SBSPTNRATE")
	private Integer subscriptionRate;

	@Column(name="SBSPTNCRTDDT")
	private Timestamp createdDate;

	@Column(name="SBSPTNCRTDBY")
	private Long createdBy;

	@Column(name="SBSPTNUPTDDT")
	private Timestamp lastUpdatedDate;

	@Column(name="SBSPTNUPTDBY")
	private Long lastUpdatedBy;

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public Integer getSubscriptionTerm() {
		return subscriptionTerm;
	}

	public void setSubscriptionTerm(Integer subscriptionTerm) {
		this.subscriptionTerm = subscriptionTerm;
	}

	public Integer getSubscriptionRate() {
		return subscriptionRate;
	}

	public void setSubscriptionRate(Integer subscriptionRate) {
		this.subscriptionRate = subscriptionRate;
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
	
}
