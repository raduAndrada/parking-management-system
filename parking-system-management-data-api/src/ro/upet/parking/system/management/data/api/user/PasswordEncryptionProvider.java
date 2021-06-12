package ro.upet.parking.system.management.data.api.user;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Secure the password of the user
 *
 * @author Andrada
 * This class could be an interface on a business level but i want to try out the converter from java.persistance
 */
public class PasswordEncryptionProvider {

    private static final String ENCRYPTION_ALGORITHM = "PBKDF2WithHmacSHA1";

    private static final Logger LOGGER = Logger.getLogger(PasswordEncryptionConverter.class.getName());

    public static byte[] getEncryptedPassword(String password, byte[] salt) {

        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = ENCRYPTION_ALGORITHM;

        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;

        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory f;
        byte[] secret = null;
        try {
            f = SecretKeyFactory.getInstance(algorithm);
            secret = f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.log(Level.SEVERE, String.format("Encryption error for %s", password));
        }
        return secret;

    }
}
