package com.tareq.tasks.tailormed.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CSVFileService {

	public ResponseEntity<InputStreamResource> printRecords(List<CSVRecord> records, List<String> headers,
			String csvFileName) {

		ByteArrayInputStream byteArrayOutputStream;
		
		String [] headersArray=headers.stream().toArray(String[]::new);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(headersArray));) {
			for (CSVRecord record : records)
				csvPrinter.printRecord(record);

			csvPrinter.flush();

			byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

		InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

		// setting HTTP headers
		HttpHeaders httpheaders = new HttpHeaders();
		httpheaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
		// defining the custom Content-Type
		httpheaders.set(HttpHeaders.CONTENT_TYPE, "text/csv");

		ResponseEntity<InputStreamResource> result = new ResponseEntity<InputStreamResource>(fileInputStream,
				httpheaders, HttpStatus.OK);

		return result;

	}

}
