package ro.upet.parking.system.management.business.impl.user;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Business level logic implementation for users 
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserRepository userRepo;
	
	@Inject
	private UserValidator userValidator;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getById(final Long userId) {
		return UserMapper.toUser(userRepo.getOne(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getByCode(final String userCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getList() {
		return UserMapper.toUserList(userRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User add(final User user) {
		final UserEntity entity = UserMapper.toUserEntity(user);
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		if (userValidator.validate(user)) {
		final UserEntity savedEntity = userRepo.save(entity);
		return UserMapper.toUser(savedEntity);
		}
		throw new BusinessException("The username or the email are already taken");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User update(final User user) {
		final UserEntity entity = UserMapper.toUserEntity(user);
		entity.setUpdatedAt(Instant.now());
		final UserEntity savedEntity = userRepo.save(entity);
		return UserMapper.toUser(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User removeById(final Long userId) throws BusinessException {
		final UserEntity entity = userRepo.getOne(userId);
		if (entity == null ) {
			throw new BusinessException("The user does not exist");
		}
		userRepo.deleteById(userId);
		return UserMapper.toUser(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User removeByCode(final String userCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
