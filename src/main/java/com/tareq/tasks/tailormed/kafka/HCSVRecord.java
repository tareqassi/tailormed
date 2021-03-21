/**
 * 
 */
package com.tareq.tasks.tailormed.kafka;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

/**
 * @author tareq
 *
 */
public class HCSVRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4605252254725712851L;
	private Map<String, String> record;
	private Properties properties;

	/**
	 * 
	 */
	public HCSVRecord() {
		super();
	}

	/**
	 * @param record
	 * @param properties
	 */
	public HCSVRecord(Map<String, String> record, Properties properties) {
		super();
		this.record = record;
		this.properties = properties;
	}

	/**
	 * @return the record
	 */
	public Map<String, String> getRecord() {
		return record;
	}

	/**
	 * @param record the record to set
	 */
	public void setRecord(Map<String, String> record) {
		this.record = record;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
