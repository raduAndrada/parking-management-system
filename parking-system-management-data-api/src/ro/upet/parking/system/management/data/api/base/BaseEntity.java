package ro.upet.parking.system.management.data.api.base;

import java.time.Instant;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class BaseEntity {



	/**
	 * unique code for the entity
	 */
	@Column(unique = true, updatable = false)
	private String code;

	/**
	 * creation time
	 */
	private Instant createdAt;

	/**
	 * last update time
	 */
	private Instant updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 3;
		Random random = new Random();

		final String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		
		code = generatedString + random.nextInt(1000);
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

}
