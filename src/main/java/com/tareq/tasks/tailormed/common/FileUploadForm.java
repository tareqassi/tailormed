/**
 * 
 */
package com.tareq.tasks.tailormed.common;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tareq
 * 
 */
public class FileUploadForm {

	private MultipartFile file;

	private String hospitalName;

	public FileUploadForm() {
		super();
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
