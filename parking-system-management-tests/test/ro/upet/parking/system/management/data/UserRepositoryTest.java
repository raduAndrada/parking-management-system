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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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


    private static UserEntity USER_1 = new UserEntity();
    private static UserEntity USER_2 = new UserEntity();

    @Inject
    private ro.upet.parking.system.management.data.impl.user.UserRepository userRepo;

    @Before
    public void setUp() {
        USER_1 = UserEntity.builder()
                .username(USER_USERNAME1)
                .email(USER_EMAIL1)
                .password(USER_PASSWORD1)
                .address(USER_ADDRESS1)
                .phoneNumber(USER_PHONENUMBER1)
                .build();

        USER_2 = UserEntity.builder()
                .username(USER_USERNAME2)
                .email(USER_EMAIL2)
                .password(USER_PASSWORD2)
                .address(USER_ADDRESS2)
                .phoneNumber(USER_PHONENUMBER2)
                .build();
    }

    @Test
    public void testTestAddUser_success() {
        val testEntity = userRepo.save(USER_1);
        val expectedEntity = userRepo.findById(testEntity.getId());

        assertThat(expectedEntity).isNotNull();

        assertThat(expectedEntity.get().getUsername()).isEqualTo(USER_USERNAME1);
    }

    @Test
    public void testDeleteUser_success() {
        final UserEntity testEntity = userRepo.save(USER_1);
        userRepo.delete(testEntity);

        val expectedEntity = userRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isEmpty();
    }

    @Test
    public void testFindUserByUsername_success() {
        val testEntity1 = userRepo.save(USER_1);
        userRepo.save(USER_2);

        val expectedEntity1 = userRepo.findByUsername(USER_USERNAME1);

        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getId()).isEqualTo(testEntity1.getId());
    }

    @Test
    public void testFindUserByEmail_success() {
        val testEntity1 = userRepo.save(USER_1);
        val expectedEntity1 = userRepo.findByEmail(USER_EMAIL1);
        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getId()).isEqualTo(testEntity1.getId());
    }

    @Test
    public void testFindUserByPasswordAndUsernameOrEmail_success() {
        userRepo.save(USER_1);
        assertTrue(userRepo.findByUsernameOrEmailAndPassword(USER_USERNAME1, "unknown", USER_PASSWORD1).isPresent());
        assertTrue(userRepo.findByUsernameOrEmailAndPassword("unknown", USER_EMAIL1, USER_PASSWORD1).isPresent());
        assertFalse(userRepo.findByUsernameOrEmailAndPassword("unknown", "unknown", USER_PASSWORD1).isPresent());
    }

    @Test
    public void testFindAllUsers_success() {
        userRepo.save(USER_1);
        userRepo.save(USER_2);
        final List<UserEntity> expectedList = userRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }


    @Test
    public void testUpdateUser_success() {
        val testEntity = userRepo.save(USER_1);
        testEntity.setPassword(USER_PASSWORD2);
        userRepo.save(testEntity);
        val expectedEntity = userRepo.findById(testEntity.getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getPassword()).isEqualTo(USER_PASSWORD2);
    }


}
