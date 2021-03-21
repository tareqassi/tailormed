/**
 * 
 */
package com.tareq.tasks.tailormed.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tareq.tasks.tailormed.entities.Patient;
import com.tareq.tasks.tailormed.entities.Treatment;
import com.tareq.tasks.tailormed.services.PatientService;
import com.tareq.tasks.tailormed.services.TreatmentService;

/**
 * @author tareq
 *
 */
@Service
public class CSVETLService {

	@Autowired
	private KafkaTemplate<String, HCSVRecord> csvRecordKafkaTemplate;

	@Autowired
	private PatientService patientService;

	@Autowired
	private TreatmentService treatmentService;

	public void extract(InputStream inputStream, String hospitalName, String topicName) {
		Properties properties = getHospitalConfig(hospitalName);
		
		BOMInputStream is = new BOMInputStream(inputStream);
		Reader reader = new InputStreamReader(is);

		try {
			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withNullString("NULL"));

			csvParser.forEach(record -> {
				// this is streaming not reading all records at one time
				try {
					pushToKafka(record, properties, topicName);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			});
			csvParser.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void pushToKafka(CSVRecord record, Properties properties, String topicName) {

		csvRecordKafkaTemplate.send(topicName, new HCSVRecord(record.toMap(), properties));

	}
	
	@KafkaListener(topics = "PATIENTS", containerFactory = "csvRecordKafkaListenerContainerFactory")
	public void patientsListener(HCSVRecord record) {

		try {
			Patient patient = patientService.transform(record.getRecord(), record.getProperties());
			patientService.load(patient);
			
		} catch (Exception e) {
			// TODO: handle exception
			//log invalid record
		}
		
	}

	@KafkaListener(topics = "TREATMENTS", containerFactory = "csvRecordKafkaListenerContainerFactory")
	public void treatmentsListener(HCSVRecord record) {
		Treatment treatment = treatmentService.transform(record.getRecord(), record.getProperties());
		treatmentService.load(treatment);
	}

	
	private Properties getHospitalConfig(String hospitalName) {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(hospitalName + ".properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return properties;
	}
}
