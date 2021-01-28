package ro.upet.parking.system.management.data.api.base;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


public class InstantToTimestampConverter {

	
	public static Instant covertToInstant(Timestamp attribute) {
		return attribute.toInstant();
	}

	
	public static Timestamp covertToTimestamp(Instant dbData) {
		return Timestamp.from(dbData);
	}


}
