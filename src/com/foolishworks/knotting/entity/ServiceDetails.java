package com.foolishworks.knotting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="servicedetails")
public class ServiceDetails implements Serializable {

	private static final long serialVersionUID = 4511258466194445790L;

	@Id
	@Column(name="SRVCDTLID")
	private Long serviceDetailId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SRVCDTLSRVCID", referencedColumnName="SRVCID")
	private Services services;

	@Column(name="SRVCDTLCODE")
	private String serviceDetailCode;

	@Column(name="SRVCDTLSUBCODE")
	private String serviceDetailSubCode;

	@Column(name="SRVCDTLQSTN")
	private String serviceDetailQuestion;

	@Column(name="SRVCDTLFLDTYP")
	private String serviceDetailType;

	@Column(name="SRVCDTLVALIDTR")
	private String serviceDetailFieldValidator;

	@Column(name="SRVCDTLPLACEHLDR")
	private String serviceDetailPlaceHolder;

	@Column(name="SRVCDTLANSLST")
	private String serviceDetailAnswerList;

	@Column(name="SRVCDTLTOOLTIPREQD")
	private String serviceDetailTooltipRequired;

	@Column(name="SRVCDTLTOOLTIPLST")
	private String serviceDetailTooltipList;

	@Column(name="SRVCDTLFLDTOMAP")
	private String serviceDetailFieldName;

	@Column(name="SRVCDTLCONDNFLD")
	private String serviceDetailPreConditionField;

	@Column(name="SRVCDTLCONDNANS")
	private String serviceDetailPreConditionAnswer;

	@Column(name="SRVCDTLINITSTATE")
	private String serviceDetailInitialState;

	@Column(name="SRVCDTLTITLE")
	private String serviceDetailTitle;

	@Column(name="SRVCDTLVWTAG")
	private String serviceDetailViewTag;

	@Column(name="SRVCDTLFLTRTITLE")
	private String serviceDetailFilterTitle;

	@Column(name="SRVCDTLFLTRPLCHLDR")
	private String serviceDetailFilterPlaceHolder;

	public Long getServiceDetailId() {
		return serviceDetailId;
	}

	public void setServiceDetailId(Long serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	public String getServiceDetailCode() {
		return serviceDetailCode;
	}

	public void setServiceDetailCode(String serviceDetailCode) {
		this.serviceDetailCode = serviceDetailCode;
	}

	public String getServiceDetailSubCode() {
		return serviceDetailSubCode;
	}

	public void setServiceDetailSubCode(String serviceDetailSubCode) {
		this.serviceDetailSubCode = serviceDetailSubCode;
	}

	public String getServiceDetailQuestion() {
		return serviceDetailQuestion;
	}

	public void setServiceDetailQuestion(String serviceDetailQuestion) {
		this.serviceDetailQuestion = serviceDetailQuestion;
	}

	public String getServiceDetailType() {
		return serviceDetailType;
	}

	public void setServiceDetailType(String serviceDetailType) {
		this.serviceDetailType = serviceDetailType;
	}

	public String getServiceDetailFieldValidator() {
		return serviceDetailFieldValidator;
	}

	public void setServiceDetailFieldValidator(String serviceDetailFieldValidator) {
		this.serviceDetailFieldValidator = serviceDetailFieldValidator;
	}

	public String getServiceDetailPlaceHolder() {
		return serviceDetailPlaceHolder;
	}

	public void setServiceDetailPlaceHolder(String serviceDetailPlaceHolder) {
		this.serviceDetailPlaceHolder = serviceDetailPlaceHolder;
	}

	public String getServiceDetailAnswerList() {
		return serviceDetailAnswerList;
	}

	public void setServiceDetailAnswerList(String serviceDetailAnswerList) {
		this.serviceDetailAnswerList = serviceDetailAnswerList;
	}

	public String getServiceDetailTooltipRequired() {
		return serviceDetailTooltipRequired;
	}

	public void setServiceDetailTooltipRequired(String serviceDetailTooltipRequired) {
		this.serviceDetailTooltipRequired = serviceDetailTooltipRequired;
	}

	public String getServiceDetailTooltipList() {
		return serviceDetailTooltipList;
	}

	public void setServiceDetailTooltipList(String serviceDetailTooltipList) {
		this.serviceDetailTooltipList = serviceDetailTooltipList;
	}

	public String getServiceDetailFieldName() {
		return serviceDetailFieldName;
	}

	public void setServiceDetailFieldName(String serviceDetailFieldName) {
		this.serviceDetailFieldName = serviceDetailFieldName;
	}

	public String getServiceDetailPreConditionField() {
		return serviceDetailPreConditionField;
	}

	public void setServiceDetailPreConditionField(
			String serviceDetailPreConditionField) {
		this.serviceDetailPreConditionField = serviceDetailPreConditionField;
	}

	public String getServiceDetailPreConditionAnswer() {
		return serviceDetailPreConditionAnswer;
	}

	public void setServiceDetailPreConditionAnswer(
			String serviceDetailPreConditionAnswer) {
		this.serviceDetailPreConditionAnswer = serviceDetailPreConditionAnswer;
	}

	public String getServiceDetailInitialState() {
		return serviceDetailInitialState;
	}

	public void setServiceDetailInitialState(String serviceDetailInitialState) {
		this.serviceDetailInitialState = serviceDetailInitialState;
	}

	public String getServiceDetailTitle() {
		return serviceDetailTitle;
	}

	public void setServiceDetailTitle(String serviceDetailTitle) {
		this.serviceDetailTitle = serviceDetailTitle;
	}

	public String getServiceDetailViewTag() {
		return serviceDetailViewTag;
	}

	public void setServiceDetailViewTag(String serviceDetailViewTag) {
		this.serviceDetailViewTag = serviceDetailViewTag;
	}

	public String getServiceDetailFilterTitle() {
		return serviceDetailFilterTitle;
	}

	public void setServiceDetailFilterTitle(String serviceDetailFilterTitle) {
		this.serviceDetailFilterTitle = serviceDetailFilterTitle;
	}

	public String getServiceDetailFilterPlaceHolder() {
		return serviceDetailFilterPlaceHolder;
	}

	public void setServiceDetailFilterPlaceHolder(
			String serviceDetailFilterPlaceHolder) {
		this.serviceDetailFilterPlaceHolder = serviceDetailFilterPlaceHolder;
	}

}
