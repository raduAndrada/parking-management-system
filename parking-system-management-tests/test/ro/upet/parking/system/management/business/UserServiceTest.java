package ro.upet.parking.system.management.business;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.core.NotFoundException;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.business.user.UserMapper;
import ro.upet.parking.system.management.business.user.UserServiceImpl;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.base.UserType;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.user.UserUpdate;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ro.upet.parking.system.management.util.TestDataBuilder.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	private static final String EMAIL_1 = "email1@example.com";
	private static final String EMAIL_2 = "email2@example.com";
	private static final String USERNAME_1 = "Andrada";
	private static final String VALID_LICENCE_PLATE_1 = "XX10YYY";
	private static final String VALID_LICENCE_PLATE_2 = "XX11YYY";
	private static final String PHONE_NUMBER_1 = "0123456789";
	private static final String PASSWORD_1 = "password1";
	private static final String PASSWORD_2 = "password2";
	private static final String UNKNOWN = "uknown";

	private UserService userService;

	@Mock
	private UserRepository userRepo;

	@Mock
	private UserValidator userValidator;

	@Mock
	private VehicleRepository vehicleRepo;

	@Mock
	private VehicleValidator vehicleValidator;

	@Before
	public void setUp(){
		userService = new UserServiceImpl(userRepo , userValidator, vehicleRepo, vehicleValidator);
	}
	@Test
	public void testAdd_success(){
		val entity = buildUserEntity(USERNAME_1, EMAIL_2);
		val user = buildUser(USERNAME_1, EMAIL_2);
		doNothing().when(userValidator).validateUser(user);
		when(userRepo.save(any(UserEntity.class))).thenReturn(entity);

		val actual = userService.add(user);
		val expected = UserMapper.toUser(entity);

		assertThat(actual).isEqualTo(expected);
		verify(userValidator, times(1)).validateUser(user);
		verify(userRepo, times(1)).save(any(UserEntity.class));
	}

	@Test(expected = BusinessException.class)
	public void testAdd_fail(){
		val user = buildUser(USERNAME_1, EMAIL_2);
		doThrow(BusinessException.class).when(userValidator).validateUser(user);

		userService.add(user);

		verify(userValidator, times(1)).validateUser(user);
		verifyNoInteractions(userRepo);
	}

	@Test
	public void testUpdateEmailAndLicencePlateChanged_success(){
		val userEntity = buildUserEntity(EMAIL_1);
		val vehicleEntity = buildVehicleEntity(VALID_LICENCE_PLATE_1, USERNAME_1, userEntity);


		when(vehicleRepo.findAllByUserUsername(USERNAME_1)).thenReturn(ImmutableList.of(vehicleEntity));
		doNothing().when(userValidator).validateEmail(EMAIL_2);
		doNothing().when(vehicleValidator).validate(VALID_LICENCE_PLATE_2);
		when(vehicleRepo.save(any(VehicleEntity.class))).thenReturn(vehicleEntity);

		val actual = userService.update(buildUserUpdate());
		val expected = buildUserUpdated();

		assertThat(expected).isEqualTo(actual);
		verify(userValidator, times(1)).validateEmail(EMAIL_2);
		verify(vehicleRepo, times(1)).save(any(VehicleEntity.class));
		verify(vehicleValidator, times(1)).validate(VALID_LICENCE_PLATE_1);
		verify(vehicleRepo, times(1)).findAllByUserUsername(USERNAME_1);
	}

	@Test
	public void testUpdateEmailAndLicencePlateHaveNotChanged_success(){
		val userEntity = buildUserEntity(EMAIL_2);
		val vehicleEntity = buildVehicleEntity(VALID_LICENCE_PLATE_2, USERNAME_1, userEntity);


		when(vehicleRepo.findAllByUserUsername(USERNAME_1)).thenReturn(ImmutableList.of(vehicleEntity));
		when(vehicleRepo.save(any(VehicleEntity.class))).thenReturn(vehicleEntity);

		val actual = userService.update(buildUserUpdate());
		val expected = buildUserUpdated();

		assertThat(expected).isEqualTo(actual);

		verifyNoInteractions(userValidator);
		verifyNoInteractions(vehicleValidator);
		verify(vehicleRepo, times(1)).save(any(VehicleEntity.class));
		verify(vehicleRepo, times(1)).findAllByUserUsername(USERNAME_1);
	}

	@Test(expected = BusinessException.class)
	public void testUpdate_failEmailTaken(){
		val userEntity = buildUserEntity(EMAIL_1);
		val vehicleEntity = buildVehicleEntity(VALID_LICENCE_PLATE_2, USERNAME_1, userEntity);


		when(vehicleRepo.findAllByUserUsername(USERNAME_1)).thenReturn(ImmutableList.of(vehicleEntity));
		doThrow(BusinessException.class).when(userValidator).validateEmail(EMAIL_2);

		 userService.update(buildUserUpdate());

		verifyNoInteractions(vehicleValidator);
		verify(userValidator, times(1)).validateEmail(EMAIL_2);
		verify(vehicleRepo, times(0)).save(any(VehicleEntity.class));
		verify(vehicleRepo, times(1)).findAllByUserUsername(USERNAME_1);
	}



	@Test(expected = BusinessException.class)
	public void testUpdate_failInvalidLicencePlate(){
		val userEntity = buildUserEntity(EMAIL_2);
		val vehicleEntity = buildVehicleEntity(VALID_LICENCE_PLATE_1, USERNAME_1, userEntity);

		when(vehicleRepo.findAllByUserUsername(USERNAME_1)).thenReturn(ImmutableList.of(vehicleEntity));
		doThrow(BusinessException.class).when(vehicleValidator).validate(VALID_LICENCE_PLATE_1);

		userService.update(buildUserUpdate());

		verifyNoInteractions(userValidator);
		verify(vehicleValidator, times(1)).validate(VALID_LICENCE_PLATE_1);
		verify(vehicleRepo, times(0)).save(any(VehicleEntity.class));
		verify(vehicleRepo, times(1)).findAllByUserUsername(USERNAME_1);
	}

	@Test(expected = BusinessException.class)
	public void testUpdate_failInvalidLicencePlateAndEmailChanged(){
		val userEntity = buildUserEntity(EMAIL_1);
		val vehicleEntity = buildVehicleEntity(VALID_LICENCE_PLATE_1, USERNAME_1, userEntity);

		when(vehicleRepo.findAllByUserUsername(USERNAME_1)).thenReturn(ImmutableList.of(vehicleEntity));
		doNothing().when(userValidator).validateEmail(EMAIL_2);
		doThrow(BusinessException.class).when(vehicleValidator).validate(VALID_LICENCE_PLATE_1);

		userService.update(buildUserUpdate());

		verify(userValidator, times(1)).validateEmail(EMAIL_2);
		verify(vehicleValidator, times(1)).validate(VALID_LICENCE_PLATE_1);
		verify(vehicleRepo, times(0)).save(any(VehicleEntity.class));
		verify(vehicleRepo, times(1)).findAllByUserUsername(USERNAME_1);
	}

	@Test(expected = NotFoundException.class)
	public void testUpdate_failNotFound(){
		doThrow(NotFoundException.class).when(vehicleRepo).findAllByUserUsername(USERNAME_1);

		userService.update(buildUserUpdate());

		verifyNoInteractions(vehicleValidator);
		verifyNoInteractions(userValidator);
		verify(vehicleRepo, times(0)).save(any(VehicleEntity.class));
		verify(vehicleRepo, times(1)).findAllByUserUsername(USERNAME_1);
	}


	@Test
	public void testLoginWithUsernameAndPassword_success(){
		val entity = buildUserEntity(USERNAME_1, EMAIL_2);
		when(userRepo.findByUsernameOrEmailAndPassword(USERNAME_1, UNKNOWN, PASSWORD_1)).thenReturn(Optional.ofNullable(entity));

		val actual = userService.loginWithUsernameOrEmailAndPassword(USERNAME_1, UNKNOWN, PASSWORD_1);
		val expected = UserMapper.toUser(entity);

		assertThat(actual).isEqualTo(expected);
		verify(userRepo, times(1)).findByUsernameOrEmailAndPassword(USERNAME_1, UNKNOWN, PASSWORD_1);
	}

	@Test
	public void testLoginWithUsernameAndPassword_fail(){
		when(userRepo.findByUsernameOrEmailAndPassword(USERNAME_1, UNKNOWN, PASSWORD_1)).thenReturn(Optional.empty());

		val actual = userService.loginWithUsernameOrEmailAndPassword(USERNAME_1, UNKNOWN, PASSWORD_1);
		assertThat(actual).isNull();
		verify(userRepo, times(1)).findByUsernameOrEmailAndPassword(USERNAME_1, UNKNOWN, PASSWORD_1);
	}

	@Test
	public void testLoginWithEmailAndPassword_success(){
		val entity = buildUserEntity(UNKNOWN, EMAIL_2);
		when(userRepo.findByUsernameOrEmailAndPassword(UNKNOWN, EMAIL_2, PASSWORD_1)).thenReturn(Optional.ofNullable(entity));

		val actual = userService.loginWithUsernameOrEmailAndPassword(UNKNOWN, EMAIL_2, PASSWORD_1);
		val expected = UserMapper.toUser(entity);

		assertThat(actual).isEqualTo(expected);
		verify(userRepo, times(1)).findByUsernameOrEmailAndPassword(UNKNOWN, EMAIL_2, PASSWORD_1);
	}


	@Test
	public void testLoginWithEmailAndPassword_fail(){
		when(userRepo.findByUsernameOrEmailAndPassword(UNKNOWN, EMAIL_1, PASSWORD_1)).thenReturn(Optional.empty());

		val actual = userService.loginWithUsernameOrEmailAndPassword(UNKNOWN, EMAIL_1, PASSWORD_1);
		assertThat(actual).isNull();
		verify(userRepo, times(1)).findByUsernameOrEmailAndPassword(UNKNOWN, EMAIL_1, PASSWORD_1);
	}


	private static UserUpdate buildUserUpdate(){
		return UserUpdate.builder()
				.username(USERNAME_1)
				.email(EMAIL_2)
				.licencePlate(VALID_LICENCE_PLATE_2)
				.phoneNumber(PHONE_NUMBER_1)
				.password(PASSWORD_1)
				.passwordConfirm(PASSWORD_1)
				.build();
	}

	private static User buildUserUpdated(){
		return User.builder()
				.username(USERNAME_1)
				.email(EMAIL_2)
				.phoneNumber(PHONE_NUMBER_1)
				.password(PASSWORD_1)
				.name("Name1")
				.birthday(LocalDate.of(1980,01,01))
				.userType(UserType.CUSTOMER)
				.build();
	}



}
