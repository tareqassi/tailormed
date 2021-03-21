/**
 * 
 */
package com.tareq.tasks.tailormed.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tareq.tasks.tailormed.entities.Patient;
import com.tareq.tasks.tailormed.repositories.PatientRepository;

/**
 * @author tareq
 *
 */
@Service
public class PatientService {

	private static final String PATIENT_ID = "patient.id";
	private static final String PATIENT_CITY = "patient.city";
	private static final String PATIENT_ZIP_CODE = "patient.zipCode";
	private static final String PATIENT_STATE = "patient.state";
	private static final String PATIENT_SEX = "patient.sex";
	private static final String PATIENT_MRN = "patient.mrn";
	private static final String PATIENT_LAST_NAME = "patient.lastName";
	private static final String PATIENT_LAST_MODIFIED_DATE_FORMAT = "patient.lastModifiedDate.format";
	private static final String PATIENT_LAST_MODIFIED_DATE = "patient.lastModifiedDate";
	private static final String PATIENT_GENDER = "patient.gender";
	private static final String PATIENT_FIRST_NAME = "patient.firstName";
	private static final String PATIENT_DECEASED = "patient.deceased";
	private static final String PATIENT_DATE_OF_DEATH_FORMAT = "patient.dateOfDeath.format";
	private static final String PATIENT_DATE_OF_DEATH = "patient.dateOfDeath";
	private static final String PATIENT_DATE_OF_BIRTH_FORMAT = "patient.dateOfBirth.format";
	private static final String PATIENT_DATE_OF_BIRTH = "patient.dateOfBirth";
	private static final String PATIENT_ADDRESS = "patient.address";

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PropertiesService probService;
	
	@Autowired
	private CSVFileService fileService;

	public ResponseEntity<InputStreamResource> extract(InputStream inputStream, String hospitalName) {

		Properties properties = probService.getHospitalConfig(hospitalName);
		
		List<String>  headers=new ArrayList<String>();
		List<CSVRecord> invalidRecords=new ArrayList<CSVRecord>();

		BOMInputStream is = new BOMInputStream(inputStream);
		Reader reader = new InputStreamReader(is);

		try {
			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withNullString("NULL"));
			
			headers=csvParser.getHeaderNames();
			csvParser.forEach(record -> {

				try {
					processRecord(record, properties);
				} catch (Exception e) {
					// TODO: add this row to invalid set of rows
					invalidRecords.add(record);
					e.printStackTrace();
				}

			});
			csvParser.close();
		} catch (IOException e) {

		}
		return fileService.printRecords(invalidRecords, headers, "patients.csv");

	}
	
	private void processRecord(CSVRecord record,Properties properties) {
		Patient patient = transform(record.toMap(), properties);
		load(patient);
	}


	public Patient transform(Map<String, String> record, Properties properties) {

		Patient patient = new Patient();
		if (properties.getProperty(PATIENT_ADDRESS) != null
				&& record.get(properties.getProperty(PATIENT_ADDRESS)) != null
				&& !record.get(properties.getProperty(PATIENT_ADDRESS)).isEmpty()) {
			patient.setAddress(record.get(properties.getProperty(PATIENT_ADDRESS)));
		}

		if (properties.getProperty(PATIENT_DATE_OF_BIRTH) != null
				&& record.get(properties.getProperty(PATIENT_DATE_OF_BIRTH)) != null
				&& !record.get(properties.getProperty(PATIENT_DATE_OF_BIRTH)).isEmpty()) {

			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(properties.getProperty(PATIENT_DATE_OF_BIRTH_FORMAT));
			String stringDate = record.get(properties.getProperty(PATIENT_DATE_OF_BIRTH));
			patient.setDateOfBirth(LocalDate.parse(stringDate, formatter));
		}
		if (properties.getProperty(PATIENT_DATE_OF_DEATH) != null
				&& record.get(properties.getProperty(PATIENT_DATE_OF_DEATH)) != null
				&& !record.get(properties.getProperty(PATIENT_DATE_OF_DEATH)).isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(properties.getProperty(PATIENT_DATE_OF_DEATH_FORMAT));
			String stringDate = record.get(properties.getProperty(PATIENT_DATE_OF_DEATH));
			patient.setDateOfDeath(LocalDate.parse(stringDate, formatter));
		}
		if (properties.getProperty(PATIENT_DECEASED) != null
				&& record.get(properties.getProperty(PATIENT_DECEASED)) != null
				&& !record.get(properties.getProperty(PATIENT_DECEASED)).isEmpty()) {
			patient.setDeceased(Boolean.valueOf(record.get(properties.getProperty(PATIENT_DECEASED))));
		}
		if (properties.getProperty(PATIENT_FIRST_NAME) != null
				&& record.get(properties.getProperty(PATIENT_FIRST_NAME)) != null
				&& !record.get(properties.getProperty(PATIENT_FIRST_NAME)).isEmpty()) {
			patient.setFirstName(record.get(properties.getProperty(PATIENT_FIRST_NAME)));
		}
		if (properties.getProperty(PATIENT_GENDER) != null && record.get(properties.getProperty(PATIENT_GENDER)) != null
				&& !record.get(properties.getProperty(PATIENT_GENDER)).isEmpty()) {
			patient.setGender(record.get(properties.getProperty(PATIENT_GENDER)));
		}
		if (properties.getProperty(PATIENT_LAST_MODIFIED_DATE) != null
				&& record.get(properties.getProperty(PATIENT_LAST_MODIFIED_DATE)) != null
				&& !record.get(properties.getProperty(PATIENT_LAST_MODIFIED_DATE)).isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(properties.getProperty(PATIENT_LAST_MODIFIED_DATE_FORMAT));
			String stringDate = record.get(properties.getProperty(PATIENT_LAST_MODIFIED_DATE));
			patient.setLastModifiedDate((LocalDate.parse(stringDate, formatter)));
		}
		if (properties.getProperty(PATIENT_LAST_NAME) != null
				&& record.get(properties.getProperty(PATIENT_LAST_NAME)) != null
				&& !record.get(properties.getProperty(PATIENT_LAST_NAME)).isEmpty()) {
			patient.setLastName(record.get(properties.getProperty(PATIENT_LAST_NAME)));
		}
		if (properties.getProperty(PATIENT_MRN) != null && record.get(properties.getProperty(PATIENT_MRN)) != null
				&& !record.get(properties.getProperty(PATIENT_MRN)).isEmpty()) {
			patient.setMrn(Long.parseLong(record.get(properties.getProperty(PATIENT_MRN))));
		}
		if (properties.getProperty(PATIENT_SEX) != null && record.get(properties.getProperty(PATIENT_SEX)) != null
				&& !record.get(properties.getProperty(PATIENT_SEX)).isEmpty()) {
			patient.setSex(record.get(properties.getProperty(PATIENT_SEX)));
		}
		if (properties.getProperty(PATIENT_STATE) != null && record.get(properties.getProperty(PATIENT_STATE)) != null
				&& !record.get(properties.getProperty(PATIENT_STATE)).isEmpty()) {
			patient.setState(record.get(properties.getProperty(PATIENT_STATE)));
		}
		if (properties.getProperty(PATIENT_ZIP_CODE) != null
				&& record.get(properties.getProperty(PATIENT_ZIP_CODE)) != null
				&& !record.get(properties.getProperty(PATIENT_ZIP_CODE)).isEmpty()) {
			patient.setZipCode(record.get(properties.getProperty(PATIENT_ZIP_CODE)));
		}
		if (properties.getProperty(PATIENT_CITY) != null && record.get(properties.getProperty(PATIENT_CITY)) != null
				&& !record.get(properties.getProperty(PATIENT_CITY)).isEmpty()) {
			patient.setCity(record.get(properties.getProperty(PATIENT_CITY)));
		}

		if (properties.getProperty(PATIENT_ID) != null && record.get(properties.getProperty(PATIENT_ID)) != null
				&& !record.get(properties.getProperty(PATIENT_ID)).isEmpty()) {
			patient.setId(Long.parseLong(record.get(properties.getProperty(PATIENT_ID))));
		}
		return patient;
	}

	public void load(Patient patient) {
		System.out.println(patient.toString());
		patientRepository.saveAndFlush(patient);
	}

}
