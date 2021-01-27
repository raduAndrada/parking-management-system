package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.data.DataTests;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.user.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIT extends DataTests {

    private static final String USER_EMAIL1 = "email@example.com";
    private static final String USER_EMAIL2 = "email2@example.com";
    private static final String USER_USERNAME1 = "username1";
    private static final String USER_USERNAME2 = "username2";
    private static final String USER_PASSWORD1 = "password1";
    private static final String USER_PASSWORD2 = "password2";
    private static final String USER_ADDRESS1 = "adress 1";
    private static final String USER_ADDRESS2 = "adress 2";
    private static final String USER_PHONENUMBER1 = "1234567890";
    private static final String USER_PHONENUMBER2 = "0123456789";
    
    
    private static final UserEntity USER_1 = new UserEntity();
    private static final UserEntity USER_2 = new UserEntity();
    
    @Inject
    private UserRepository userRepo;

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
    @Transactional
    public void addUser_test_success() {
        final UserEntity testEntity = userRepo.save(USER_1);
        final Optional<UserEntity> expectedEntity = userRepo.findById(testEntity.getBase().getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getUsername()).isEqualTo(USER_USERNAME1);
    }
    
    @Test
    @Transactional
    public void deleteUser_test_success() {
        final UserEntity testEntity = userRepo.save(USER_1);
        userRepo.delete(testEntity);
        final Optional<UserEntity> expectedEntity = userRepo.findById(testEntity.getBase().getId());
        assertThat(expectedEntity.isEmpty());
    }
    
    @Test
    @Transactional
    public void findUserByUsername_test_success() {
        final UserEntity testEntity1 = userRepo.save(USER_1);
        userRepo.save(USER_2);
        final Optional<UserEntity> expectedEntity1 = userRepo.findByUsername(USER_USERNAME1);
        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getBase().getId()).isEqualTo(testEntity1.getBase().getId());
    }
    
    @Test
    @Transactional
    public void findUserByEmail_test_success() {
        final UserEntity testEntity1 = userRepo.save(USER_1);
        userRepo.save(USER_2);
        final Optional<UserEntity> expectedEntity1 = userRepo.findByEmail(USER_EMAIL1);
        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getBase().getId()).isEqualTo(testEntity1.getBase().getId());
    }
    
    
    @Test
    @Transactional
    public void findUserByUsernameAndPassword_test_success() {
        final UserEntity testEntity1 = userRepo.save(USER_1);
        userRepo.save(USER_2);
        final Optional<UserEntity> expectedEntity1 = userRepo.findByUsernameAndPassword(USER_USERNAME1, USER_PASSWORD1);
        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getAddress()).isEqualTo(testEntity1.getAddress());
        assertThat(expectedEntity1.get().getBase().getId()).isEqualTo(testEntity1.getBase().getId());
    }
    
    @Test
    @Transactional
    public void findUserByEmailAndPassword_test_success() {
        final UserEntity testEntity1 = userRepo.save(USER_1);
        userRepo.save(USER_2);
        final Optional<UserEntity> expectedEntity1 = userRepo.findByEmailAndPassword(USER_EMAIL1, USER_PASSWORD1);
        assertThat(expectedEntity1.isPresent());
        assertThat(expectedEntity1.get().getAddress()).isEqualTo(testEntity1.getAddress());
        assertThat(expectedEntity1.get().getBase().getId()).isEqualTo(testEntity1.getBase().getId());
    }
    
    @Test
    @Transactional
    public void findAllUsers_test_success() {
        userRepo.save(USER_1);
        userRepo.save(USER_2);
        final List<UserEntity> expectedList = userRepo.findAll();
        assertThat(expectedList).isNotNull();
        assertThat(expectedList.size()).isEqualTo(2);
    }
    
    
    @Test
    @Transactional
    public void updateUser_test_success() {
        final UserEntity testEntity = userRepo.save(USER_1);
        testEntity.setPassword(USER_PASSWORD2);
        userRepo.save(testEntity);
        final Optional<UserEntity> expectedEntity = userRepo.findById(testEntity.getBase().getId());
        assertThat(expectedEntity).isNotNull();
        assertThat(expectedEntity.get().getPassword()).isEqualTo(USER_PASSWORD2);
    }
    

}
