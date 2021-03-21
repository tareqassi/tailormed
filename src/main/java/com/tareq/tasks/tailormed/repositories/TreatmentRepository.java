/**
 * 
 */
package com.tareq.tasks.tailormed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tareq.tasks.tailormed.entities.Treatment;


/**
 * @author tareq
 *
 */
@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, String> {

}
