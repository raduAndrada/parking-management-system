package ro.upet.parking.system.management.data.api.user;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

import java.util.logging.*;

/**
 * 
 * @author Andrada
 * Salt generator  for password encryption
 */
public class SaltGenerator {
	
	
	private static final Logger LOGGER  = Logger.getLogger(SaltGenerator.class.getName());
	
	private static final String SECURE_RANDOM = "SHA1PRNG";
	
	/**
	 * @return salt using a random
	 */
	public static byte[] generateSalt()  {
		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = null;
		
		try {
			random = SecureRandom.getInstance(SECURE_RANDOM);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, "Failed to generate salt for password encyption");
		}
		
		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}
	

}
