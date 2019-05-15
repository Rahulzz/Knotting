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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="services")
public class Services implements Serializable {

	private static final long serialVersionUID = 8641895171824965083L;

	@Id
	@Column(name="SRVCID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long servicesId;

	@Column(name="SRVCCODE")
	private String serviceCode;

	@Column(name="SRVCNAME")
	private String name;

	@Column(name="SRVCDESC")
	private String description;

	@Column(name="SRVCPRCDTL")
	private String pricingDetails;

	@Column(name="SRVCLWRPRC")
	private String lowerPrice;

	@Column(name="SRVCHGRPRC")
	private String higherPrice;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "services")
	@JsonIgnore
	private Set<MemberServices> memberServices = new HashSet<MemberServices>();

	@Column(name="SRVCCRTDDT")
	private Timestamp createdDate;

	@Column(name="SRVCCRTDBY")
	private Long createdBy;

	@Column(name="SRVCUPTDDT")
	private Timestamp lastUpdatedDate;

	@Column(name="SRVCUPTDBY")
	private Long lastUpdatedBy;

	public Long getServicesId() {
		return servicesId;
	}

	public void setServicesId(Long servicesId) {
		this.servicesId = servicesId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPricingDetails() {
		return pricingDetails;
	}

	public void setPricingDetails(String pricingDetails) {
		this.pricingDetails = pricingDetails;
	}

	public String getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(String lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public String getHigherPrice() {
		return higherPrice;
	}

	public void setHigherPrice(String higherPrice) {
		this.higherPrice = higherPrice;
	}

	public Set<MemberServices> getMemberServices() {
		return memberServices;
	}

	public void setMemberServices(Set<MemberServices> memberServices) {
		this.memberServices = memberServices;
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
