package com.foolishworks.knotting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staging_entry_sequence")
public class SequenceEntriesStaging implements Serializable {

	private static final long serialVersionUID = 4299671919931005454L;

	@Id
	@Column(name="SEQID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sequenceId;

	@Column(name="NXTNMBR")
	private Long nextNumber;

	public Long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Long getNextNumber() {
		return nextNumber;
	}

	public void setNextNumber(Long nextNumber) {
		this.nextNumber = nextNumber;
	}

}
