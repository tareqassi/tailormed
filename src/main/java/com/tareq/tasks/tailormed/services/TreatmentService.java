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

import com.tareq.tasks.tailormed.entities.Treatment;
import com.tareq.tasks.tailormed.repositories.TreatmentRepository;


/**
 * @author tareq
 *
 */
@Service
public class TreatmentService {

	private static final String TREATMENT_END_DATE_FORMAT = "treatment.endDate.format";
	private static final String TREATMENT_END_DATE = "treatment.endDate";
	private static final String TREATMENT_START_DATE_FORMAT = "treatment.startDate.format";
	private static final String TREATMENT_START_DATE = "treatment.startDate";
	private static final String TREATMENT_PROTOCOL_ID = "treatment.protocolId";
	private static final String TREATMENT_TREATMENT_LINE = "treatment.treatmentLine";
	private static final String TREATMENT_DIAGNOSIS = "treatment.diagnosis";
	private static final String TREATMENT_DISPLAY_NAME = "treatment.displayName";
	private static final String TREATMENT_CYCLES_NUMBER = "treatment.cyclesNumber";
	private static final String TREATMENT_STATUS = "treatment.status";
	private static final String TREATMENT_PATIENT_ID = "treatment.patientId";
	private static final String TREATMENT_ID = "treatment.id";

	@Autowired
	private TreatmentRepository treatmentRepository;

	@Autowired
	private PropertiesService probService;
	
	@Autowired
	private CSVFileService fileService;

	public  ResponseEntity<InputStreamResource> extract(InputStream inputStream, String hospitalName) {

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
			e.printStackTrace();
		}

		
		return fileService.printRecords(invalidRecords, headers, "treatments.csv");
	}
	
	private void processRecord(CSVRecord record,Properties properties) {
		Treatment tmnt = transform(record.toMap(), properties);
		load(tmnt);
	}



	public Treatment transform(Map<String, String> record, Properties properties) {

		Treatment treatment = new Treatment();

		if (properties.getProperty(TREATMENT_ID) != null && record.get(properties.getProperty(TREATMENT_ID)) != null
				&& !record.get(properties.getProperty(TREATMENT_ID)).isEmpty()) {
			treatment.setId(record.get(properties.getProperty(TREATMENT_ID)));
		}
		if (properties.getProperty(TREATMENT_PATIENT_ID) != null
				&& record.get(properties.getProperty(TREATMENT_PATIENT_ID)) != null
				&& !record.get(properties.getProperty(TREATMENT_PATIENT_ID)).isEmpty()) {
			treatment.setPatientId(Long.parseLong(record.get(properties.getProperty(TREATMENT_PATIENT_ID))));
		}
		if (properties.getProperty(TREATMENT_STATUS) != null
				&& record.get(properties.getProperty(TREATMENT_STATUS)) != null
				&& !record.get(properties.getProperty(TREATMENT_STATUS)).isEmpty()) {
			treatment.setStatus(record.get(properties.getProperty(TREATMENT_STATUS)));
		}
		if (properties.getProperty(TREATMENT_CYCLES_NUMBER) != null
				&& record.get(properties.getProperty(TREATMENT_CYCLES_NUMBER)) != null
				&& !record.get(properties.getProperty(TREATMENT_CYCLES_NUMBER)).isEmpty()) {
			treatment.setCyclesNumber(record.get(properties.getProperty(TREATMENT_CYCLES_NUMBER)));
		}
		if (properties.getProperty(TREATMENT_DISPLAY_NAME) != null
				&& record.get(properties.getProperty(TREATMENT_DISPLAY_NAME)) != null
				&& !record.get(properties.getProperty(TREATMENT_DISPLAY_NAME)).isEmpty()) {
			treatment.setDisplayName(record.get(properties.getProperty(TREATMENT_DISPLAY_NAME)));
		}
		if (properties.getProperty(TREATMENT_DIAGNOSIS) != null
				&& record.get(properties.getProperty(TREATMENT_DIAGNOSIS)) != null
				&& !record.get(properties.getProperty(TREATMENT_DIAGNOSIS)).isEmpty()) {
			treatment.setDiagnosis(record.get(properties.getProperty(TREATMENT_DIAGNOSIS)));
		}
		if (properties.getProperty(TREATMENT_TREATMENT_LINE) != null
				&& record.get(properties.getProperty(TREATMENT_TREATMENT_LINE)) != null
				&& !record.get(properties.getProperty(TREATMENT_TREATMENT_LINE)).isEmpty()) {
			treatment.setTreatmentLine(record.get(properties.getProperty(TREATMENT_TREATMENT_LINE)));
		}
		if (properties.getProperty(TREATMENT_PROTOCOL_ID) != null
				&& record.get(properties.getProperty(TREATMENT_PROTOCOL_ID)) != null
				&& !record.get(properties.getProperty(TREATMENT_PROTOCOL_ID)).isEmpty()) {
			treatment.setProtocolId(record.get(properties.getProperty(TREATMENT_PROTOCOL_ID)));
		}

		if (properties.getProperty(TREATMENT_START_DATE) != null
				&& record.get(properties.getProperty(TREATMENT_START_DATE)) != null
				&& !record.get(properties.getProperty(TREATMENT_START_DATE)).isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(properties.getProperty(TREATMENT_START_DATE_FORMAT));
			String stringDate = record.get(properties.getProperty(TREATMENT_START_DATE));
			treatment.setStartDate(LocalDate.parse(stringDate, formatter));
		}

		if (properties.getProperty(TREATMENT_END_DATE) != null
				&& record.get(properties.getProperty(TREATMENT_END_DATE)) != null
				&& !record.get(properties.getProperty(TREATMENT_END_DATE)).isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern(properties.getProperty(TREATMENT_END_DATE_FORMAT));
			String stringDate = record.get(properties.getProperty(TREATMENT_END_DATE));
			treatment.setEndDate(LocalDate.parse(stringDate, formatter));
		}

		return treatment;
	}

	public void load(Treatment treatment) {
		System.out.println(treatment.toString());
		treatmentRepository.saveAndFlush(treatment);
	}

}
