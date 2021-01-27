package ro.upet.parking.system.management.data.api.base;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "base_entites")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
