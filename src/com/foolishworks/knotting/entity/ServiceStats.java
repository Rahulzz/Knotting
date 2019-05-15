package com.foolishworks.knotting.entity;

import java.io.Serializable;

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
@Table(name="servicestats")
public class ServiceStats implements Serializable {

	private static final long serialVersionUID = 4063082250806073069L;

	@Id
	@Column(name="STATID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statsId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STATMMBRID", referencedColumnName="MMBRID")
	private Member member;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="STATMMENTID", referencedColumnName="MMENTID")
	private MemberEntriesStaging memberEntriesStaging;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="STATMMENTID", referencedColumnName="MMENTID", insertable = false, updatable = false)
	private MemberEntries memberEntries;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="STATSRVCID", referencedColumnName="SRVCID")
	private Services services;

	@Column(name="STATVEWCNT")
	private String statsViewCount;

	@Column(name="STATINFCNT")
	private String statsInfoCount;

	@Column(name="STATSHRCNT")
	private String statsShareCount;

	public Long getStatsId() {
		return statsId;
	}

	public void setStatsId(Long statsId) {
		this.statsId = statsId;
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

	public String getStatsViewCount() {
		return statsViewCount;
	}

	public void setStatsViewCount(String statsViewCount) {
		this.statsViewCount = statsViewCount;
	}

	public String getStatsInfoCount() {
		return statsInfoCount;
	}

	public void setStatsInfoCount(String statsInfoCount) {
		this.statsInfoCount = statsInfoCount;
	}

	public String getStatsShareCount() {
		return statsShareCount;
	}

	public void setStatsShareCount(String statsShareCount) {
		this.statsShareCount = statsShareCount;
	}
	
}
