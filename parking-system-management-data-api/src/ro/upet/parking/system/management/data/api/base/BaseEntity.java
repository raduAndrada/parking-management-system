package ro.upet.parking.system.management.data.api.base;

import java.time.Instant;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class BaseEntity  {


	/**
	 *  identifier for the entity
	 */
	@Id
	@GeneratedValue		
	protected Long id;
	
	/**
	 *  unique code for the entity
	 */
	@GeneratedValue
	protected String code;	

	/**
	 *  creation time
	 */
	@CreationTimestamp
	protected Instant createdAt;

	/**
	 *  last update time
	 */
	@UpdateTimestamp
	protected Instant updatedAt;
	

}
