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
@Table(name = "patients")
public class Patient extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9118551675776708254L;

	@Id
	@Column
	private Long id;

	@Column
	private Long mrn;

	@Column
	private Boolean deceased;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String gender;

	@Column
	private String sex;

	@Column
	private String address;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String zipCode;

	@Column
	private LocalDate dateOfBirth;

	@Column
	private LocalDate dateOfDeath;

	@Column
	private LocalDate lastModifiedDate;

	/**
	 * 
	 */
	public Patient() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the mrn
	 */
	public Long getMrn() {
		return mrn;
	}

	/**
	 * @param mrn the mrn to set
	 */
	public void setMrn(Long mrn) {
		this.mrn = mrn;
	}

	/**
	 * @return the deceased
	 */
	public Boolean getDeceased() {
		return deceased;
	}

	/**
	 * @param deceased the deceased to set
	 */
	public void setDeceased(Boolean deceased) {
		this.deceased = deceased;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the dateOfDeath
	 */
	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	/**
	 * @param dateOfDeath the dateOfDeath to set
	 */
	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", mrn=" + mrn + ", deceased=" + deceased + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", sex=" + sex + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zipCode=" + zipCode + ", dateOfBirth=" + dateOfBirth + ", dateOfDeath="
				+ dateOfDeath + ", lastModifiedDate=" + lastModifiedDate + "]";
	}

}
