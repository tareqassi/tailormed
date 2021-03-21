/**
 * 
 */
package com.tareq.tasks.tailormed.entities;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tareq
 *
 */
@Access(AccessType.FIELD)
@Entity
@Table(name = "treatments")
public class Treatment extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7522921165923431704L;

	@Id
	@Column
	private String id;

	@Column
	private Long patientId;

	@Column
	private String status;

	@Column
	private String cyclesNumber;

	@Column
	private String displayName;

	@Column
	private String diagnosis;

	@Column
	private String treatmentLine;

	@Column
	private String protocolId;

	@Column
	private LocalDate startDate;

	@Column
	private LocalDate endDate;

	/**
	 * 
	 */
	public Treatment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the patientId
	 */
	public Long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the cyclesNumber
	 */
	public String getCyclesNumber() {
		return cyclesNumber;
	}

	/**
	 * @param cyclesNumber the cyclesNumber to set
	 */
	public void setCyclesNumber(String cyclesNumber) {
		this.cyclesNumber = cyclesNumber;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the treatmentLine
	 */
	public String getTreatmentLine() {
		return treatmentLine;
	}

	/**
	 * @param treatmentLine the treatmentLine to set
	 */
	public void setTreatmentLine(String treatmentLine) {
		this.treatmentLine = treatmentLine;
	}

	/**
	 * @return the protocolId
	 */
	public String getProtocolId() {
		return protocolId;
	}

	/**
	 * @param protocolId the protocolId to set
	 */
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Treatment [id=" + id + ", patientId=" + patientId + ", status=" + status + ", cyclesNumber="
				+ cyclesNumber + ", displayName=" + displayName + ", diagnosis=" + diagnosis + ", treatmentLine="
				+ treatmentLine + ", protocolId=" + protocolId + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}

}
