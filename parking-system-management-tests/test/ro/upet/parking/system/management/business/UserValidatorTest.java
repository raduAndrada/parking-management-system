package ro.upet.parking.system.management.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.business.user.UserValidatorImpl;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.user.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildUser;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildUserEntity;

@RunWith(SpringRunner.class)
public class UserValidatorTest {

	private static final String EMAIL_1 = "email1@example.com";
	private static final String USERNAME_1 = "username1";

	private UserValidator userValidator;

	@Mock
	private UserRepository userRepo;


	@Before
	public void setUp(){
		userValidator = new UserValidatorImpl(userRepo);
	}

	@Test
	public void testIsValidUser_success(){
		when(userRepo.findByUsername(USERNAME_1)).thenReturn(Optional.empty());
		when(userRepo.findByEmail(EMAIL_1)).thenReturn(Optional.empty());

		assertDoesNotThrow(() -> userValidator.validateUser(buildUser(USERNAME_1, EMAIL_1)));

		verify(userRepo, times(1)).findByUsername(USERNAME_1);
		verify(userRepo, times(1)).findByEmail(EMAIL_1);
	}

	@Test
	public void testIsValidUser_fail(){
		UserEntity user  = buildUserEntity(USERNAME_1, EMAIL_1);
		when(userRepo.findByUsername(USERNAME_1)).thenReturn(Optional.of(user));
		when(userRepo.findByEmail(EMAIL_1)).thenReturn(Optional.empty());

		assertThrows(BusinessException.class, () -> userValidator.validateUser(buildUser(USERNAME_1, EMAIL_1)));

		verify(userRepo, times(1)).findByUsername(USERNAME_1);
		verify(userRepo, times(0)).findByEmail(EMAIL_1);
	}
}
