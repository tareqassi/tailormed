/**
 * 
 */
package com.tareq.tasks.tailormed.services;

import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

/**
 * @author tareq
 *
 */
@Service
public class PropertiesService {

	public Properties getHospitalConfig(String hospitalName) {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(hospitalName + ".properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return properties;
	}

}
