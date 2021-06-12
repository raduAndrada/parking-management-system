package ro.upet.parking.system.management.data.api.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * @author Andrada
 * Convert a user inputed password to a secure encrypted password on a db level
 */
@Converter
public class PasswordEncryptionConverter implements AttributeConverter<String, byte[]> {
	

	@Override
	public byte[] convertToDatabaseColumn(final String attribute) {
		return  PasswordEncryptionProvider.getEncryptedPassword(attribute, SaltGenerator.generateSalt());
	}

	@Override
	public String convertToEntityAttribute(byte[] dbData) {
		return new String(dbData);
	}

	



}
