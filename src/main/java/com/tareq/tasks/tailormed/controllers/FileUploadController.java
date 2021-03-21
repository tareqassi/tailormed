/**
 * 
 */
package com.tareq.tasks.tailormed.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tareq.tasks.tailormed.common.FileUploadForm;
import com.tareq.tasks.tailormed.services.PatientService;
import com.tareq.tasks.tailormed.services.TreatmentService;


/**
 * @author tareq
 *
 */
@Controller
@RequestMapping("files")
public class FileUploadController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private TreatmentService treatmentService;
	
//	@Autowired
//	private CSVETLService kafkaEtlService;

	@PostMapping(path = "/upload/patients")
	@ResponseBody
	public ResponseEntity<InputStreamResource> uploadPatientsFile(@ModelAttribute("upload_form") FileUploadForm form, HttpServletRequest request) {

		MultipartFile patientsFile = form.getFile();
		String hospitalName = form.getHospitalName();
		
		ResponseEntity<InputStreamResource> results=new ResponseEntity<InputStreamResource>(HttpStatus.OK);

		try {
			results= patientService.extract(patientsFile.getInputStream(), hospitalName);
			
//			kafkaEtlService.extract(patientsFile.getInputStream(), hospitalName, "PATIENTS");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;

	}

	@PostMapping(path = "/upload/treatments")
	@ResponseBody
	public ResponseEntity<InputStreamResource> uploadTreatmentsFile(@ModelAttribute("upload_form") FileUploadForm form, HttpServletRequest request) {

		MultipartFile treatmentsFile = form.getFile();
		String hospitalName = form.getHospitalName();
		
		ResponseEntity<InputStreamResource> results=new ResponseEntity<InputStreamResource>(HttpStatus.OK);

		try {
			results=treatmentService.extract(treatmentsFile.getInputStream(), hospitalName);
//			kafkaEtlService.extract(treatmentsFile.getInputStream(), hospitalName, "TREATMENTS");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

}
