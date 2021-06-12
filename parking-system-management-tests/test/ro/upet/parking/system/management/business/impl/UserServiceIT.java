package ro.upet.parking.system.management.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.user.ImtUser;
import ro.upet.parking.system.management.model.user.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceIT  {

	private static final String USER_EMAIL1 = "email@example.com";

	private static final String USER_USERNAME1 = "username1";
	private static final String USER_USERNAME2 = "username2";

	private static final String USER_NAME1 = "name 1";

	private static final String USER_PASSWORD1 = "password1";

	private static final String USER_ADDRESS1 = "adress 1";

	private static final String USER_PHONENUMBER1 = "1234567890";

	private static final String USER_BIRTHDAY1 = "1996-06-07";

	@Inject
	protected UserRepository userRepo;

	@Inject
	protected UserValidator userValidator;

	@Inject
	protected UserService userService;

	private static final User USER_1 = ImtUser.builder()
			// .address(USER_ADDRESS1)
			.email(USER_EMAIL1).username(USER_USERNAME1).password(USER_PASSWORD1).phoneNumber(USER_PHONENUMBER1)
			// .birthday(USER_BIRTHDAY1)
			.name(USER_NAME1).build();

	@Test
	@Transactional
	public void addUser_test_success() {
		final User actualResult = userService.add(USER_1);
		final User expectedResult = ImtUser.copyOf(USER_1)
				.withCode(actualResult.getCode())
				.withId(actualResult.getId()).withCreatedAt(actualResult.getCreatedAt())
				.withUpdatedAt(actualResult.getUpdatedAt());
		assertThat(actualResult).isNotNull();
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test(expected = BusinessException.class)
	@Transactional
	public void addUser_test_fail_usernameTaken() {
		userService.add(USER_1);
		userService.add(USER_1);
	}

	@Test
	@Transactional
	public void deleteUserById_test_success() {
		UserEntity ue = new UserEntity();
		ue.setUsername(USER_USERNAME1);
		ue = userRepo.save(ue);
		final List<User> actualResult = userService.getList();
		userService.removeById(ue.getId());
		final List<User> expectedResult = userService.getList();
		assertThat(actualResult).isNotNull();
		assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
	}

	@Test
	@Transactional
	public void findUserById_test_success() {
		UserEntity ue = new UserEntity();
		ue.setUsername(USER_USERNAME1);
		ue = userRepo.save(ue);
		final User actualResult = userService.getById(ue.getId());
		final User expectedResult = ImtUser.builder().id(actualResult.getId()).username(USER_USERNAME1)
				.createdAt(actualResult.getCreatedAt()).updatedAt(actualResult.getUpdatedAt()).build();
		assertThat(actualResult).isNotNull();
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	@Transactional
	public void findAllUsers_test_success() {
		UserEntity ue = new UserEntity();
		ue.setUsername(USER_USERNAME1);
		userRepo.save(ue);

		ue = new UserEntity();
		ue.setUsername(USER_USERNAME2);
		userRepo.save(ue);

		final List<User> actualResult = userService.getList();
		assertThat(actualResult).isNotNull();
		assertThat(actualResult.size()).isEqualTo(2);
	}

	@Test
	@Transactional
	public void updateUser_test_success() {
		UserEntity ue = new UserEntity();
		ue.setUsername(USER_USERNAME1);
		ue = userRepo.save(ue);
		final User actualResult = userService.update(ImtUser.copyOf(USER_1).withId(ue.getId()));
		final User expectedResult = ImtUser.copyOf(USER_1).withId(actualResult.getId())
				.withCreatedAt(actualResult.getCreatedAt()).withUpdatedAt(actualResult.getUpdatedAt());
		assertThat(actualResult).isNotNull();
		assertThat(actualResult).isEqualTo(expectedResult);
	}

}
