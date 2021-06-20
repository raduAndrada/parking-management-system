package ro.upet.parking.system.management.model;

import java.time.Instant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseModel {
	
	/**
	 * @return identifier for the entity
	 */
	Long id;
	
	/**
	 * @return unique code for the entity
	 */
	String code;

}
