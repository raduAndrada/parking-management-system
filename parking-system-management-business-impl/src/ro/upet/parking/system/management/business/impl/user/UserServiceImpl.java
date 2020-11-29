package ro.upet.parking.system.management.business.impl.user;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.payment.options.PaymentOptionsRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Business level logic implementation for users 
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	UserRepository userRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserById(final Long userId) {
		return UserMapper.toUser(userRepo.getOne(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserByCode(final String userCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> getUserList() {
		return UserMapper.toUserList(userRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User addUser(final User user) {
		final UserEntity entity = UserMapper.toUserEntity(user);
		final UserEntity savedEntity = userRepo.save(entity);
		return UserMapper.toUser(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User updateUser(final User user) {
		final UserEntity entity = UserMapper.toUserEntity(user);
		final UserEntity savedEntity = userRepo.save(entity);
		return UserMapper.toUser(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User removeUserById(final Long userId) throws Exception {
		final UserEntity entity = userRepo.getOne(userId);
		if (entity == null ) {
			throw new Exception();
		}
		userRepo.deleteById(userId);
		return UserMapper.toUser(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public User removeUserByCode(final String userCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
