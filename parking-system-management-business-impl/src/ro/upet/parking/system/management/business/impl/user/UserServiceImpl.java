package ro.upet.parking.system.management.business.impl.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.core.NotFoundException;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;
import ro.upet.parking.system.management.model.user.User;
import ro.upet.parking.system.management.model.user.UserUpdate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrada
 * Business level logic implementation for users 
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepo;
	
	private final UserValidator userValidator;
	
	private final VehicleRepository vehicleRepo;


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
	@Transactional
	public User add(final User user) {
		final UserEntity entity = UserMapper.toUserEntity(user);
		if (userValidator.validate(user)) {
		final UserEntity savedEntity = userRepo.save(entity);;
		return UserMapper.toUser(savedEntity);
		}
		throw new BusinessException("The username or the email are already taken");
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User update(final User user) {
		final UserEntity entity = UserMapper.toUserEntity(user);
		final UserEntity savedEntity = userRepo.save(entity);
		return UserMapper.toUser(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User loginWithUsernameAndPassword(final String username, final String password) {
		final Optional<UserEntity> ue = userRepo.findByUsernameAndPassword(username, password);
		return ue.isPresent() ? UserMapper.toUser(ue.get()) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User loginWithEmailAndPassword(String email, String password) {
		final Optional<UserEntity> ue = userRepo.findByEmailAndPassword(email, password);
		return ue.isPresent() ? UserMapper.toUser(ue.get()) : null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public User update(UserUpdate userUpdate) {
		VehicleEntity ve =vehicleRepo.findAllByUserUsername(userUpdate.getUsername())
							.stream().findFirst().orElseThrow(BusinessException :: new);
		UserEntity ue = ve.getUser();
		ue.setEmail(userUpdate.getEmail());
		ue.setPhoneNumber(userUpdate.getPhoneNumber());
		ue.setPassword(userUpdate.getPassword().equals(userUpdate.getPasswordConfirm()) ? 
												userUpdate.getPassword() : ue.getPassword());	
		
		ve.setLicencePlate(userUpdate.getLicencePlate());
		ve.setUser(ue);
		if (userValidator.validate(UserMapper.toUser(ue))) {
		vehicleRepo.save(ve);
		}
		return UserMapper.toUser(ue);
	}

	@Override
	public User getByUsername(String username) {
		return UserMapper.toUser(userRepo.findByUsername(username).orElseThrow(BusinessException::new));
	}

	@Override
	public User updateStripe(final String email, final String stripeId) {
		final UserEntity userEntity = userRepo.findByEmail(email).orElseThrow(NotFoundException::new);
		userEntity.setStripeId(stripeId);
		return UserMapper.toUser(userEntity);
	}


}
