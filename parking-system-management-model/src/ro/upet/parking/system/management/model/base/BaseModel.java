package ro.upet.parking.system.management.model.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {
	
	/**
	 * @return identifier for the entity
	 */
	Long id;
	
	/**
	 * @return unique code for the entity
	 */
	String code;

	/**
	 * @return creation time
	 */
	Instant createdAt;

	/**
	 * @return last update time
	 */
	Instant updatedAt;
}
