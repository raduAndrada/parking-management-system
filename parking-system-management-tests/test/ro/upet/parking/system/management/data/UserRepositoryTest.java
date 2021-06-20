package ro.upet.parking.system.management.data;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import ro.upet.parking.system.management.data.api.user.PasswordEncryptionProvider;
import ro.upet.parking.system.management.data.api.user.SaltGenerator;
import ro.upet.parking.system.management.data.api.user.UserEntity;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest extends DataTests {

    private static final String USER_EMAIL1 = "email@example.com";
    private static final String USER_EMAIL2 = "email2@example.com";
    private static final String USER_USERNAME1 = "username1";
    private static final String USER_USERNAME2 = "username2";
    private static final String USER_PASSWORD1 = "password1";
    private static final String USER_ENCRYPTED_PASSWORD1 =
            PasswordEncryptionProvider.getEncryptedPassword("password1", SaltGenerator.generateSalt()).toString();
    private static final String USER_PASSWORD2 = "password2";
    private static final String USER_ENCRYPTED_PASSWORD2 =
            PasswordEncryptionProvider.getEncryptedPassword("password2", SaltGenerator.generateSalt()).toString();
    private static final String USER_ADDRESS1 = "adress 1";
    private static final String USER_ADDRESS2 = "adress 2";
    private static final String USER_PHONENUMBER1 = "1234567890";
    private static final String USER_PHONENUMBER2 = "0123456789";


    private static final UserEntity USER_1 = new UserEntity();
    private static final UserEntity USER_2 = new UserEntity();

    @Inject
    private ro.upet.parking.system.management.data.impl.user.UserRepository userRepo;

    @Before
    public void init() {
        USER_1.setUsername(USER_USERNAME1);
        USER_1.setEmail(USER_EMAIL1);
        USER_1.setPassword(USER_PASSWORD1);
        USER_1.setAddress(USER_ADDRESS1);
        USER_1.setPhoneNumber(USER_PHONENUMBER1);

        USER_2.setUsername(USER_USERNAME2);
        USER_2.setEmail(USER_EMAIL2);
        USER_2.setPassword(USER_PASSWORD2);
        USER_2.setAddress(USER_ADDRESS2);
        USER_2.setPhoneNumber(USER_PHONENUMBER2);
    }

    @Test
    public void addUser_test_success() {
        val testEntity = userRepo.save(USER_1);
        val expectedEntity = userRepo.findById(testEntity.getId());

        assertThat(expectedEntity).isNotNull();

        assertThat(expectedEntity.get().getUsername()).isEqualTo(USER_USERNAME1);
    }

    @Test
    public void deleteUser_test_success() {
        final UserEntity testEntity = userRepo.save(USER_1);
        userRepo.delete(testEntity);

        val expectedEntity = userRepo.findById(testEntity.getId());
        assertThat(expectedEntity.isEmpty());
    }

    @Test
    public void findUserByUsername_test_success() {
        val testEntity1 = userRepo.save(USER_1);
        userRepo.save(USER_2);

        val expectedEntity1 = userRepo.findByUsername(USER_USERNAME1);

        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getId()).isEqualTo(testEntity1.getId());
    }

    @Test
    public void findUserByEmail_test_success() {
        val testEntity1 = userRepo.save(USER_1);
        val expectedEntity1 = userRepo.findByEmail(USER_EMAIL1);
        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getId()).isEqualTo(testEntity1.getId());
    }


    @Test
    public void findUserByUsernameAndPassword_test_success() {
        val testEntity1 = userRepo.save(USER_1);

        val expectedEntity1 = userRepo.findByUsernameAndPassword(USER_USERNAME1, USER_ENCRYPTED_PASSWORD1);
        assertThat(expectedEntity1.isPresent());
    }

    @Test
    public void findUserByEmailAndPassword_test_success() {
        val testEntity1 = userRepo.save(USER_1);
        val expectedEntity1 = userRepo.findByEmailAndPassword(USER_EMAIL1, USER_ENCRYPTED_PASSWORD1);

        assertThat(expectedEntity1.isPresent());
    }

    @Test
    public void findAllUsers_test_success() {
        userRepo.save(USER_1);
        userRepo.save(USER_2);
        final List<UserEntity> expectedList = userRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }


    @Test
    public void updateUser_test_success() {
        val testEntity = userRepo.save(USER_1);
        testEntity.setPassword(USER_PASSWORD2);
        userRepo.save(testEntity);
        val expectedEntity = userRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getPassword()).isEqualTo(USER_PASSWORD2);
    }


}
