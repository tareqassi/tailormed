/**
 * 
 */
package com.tareq.tasks.tailormed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tareq.tasks.tailormed.entities.Patient;


/**
 * @author tareq
 *
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
