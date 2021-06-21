package ro.upet.parking.system.management.business.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.core.NotFoundException;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.user.UserValidator;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
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
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final UserValidator userValidator;

    private final VehicleRepository vehicleRepo;

    private final VehicleValidator vehicleValidator;


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
        userValidator.validateUser(user);
        return UserMapper.toUser(userRepo.save(UserMapper.toUserEntity(user)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User update(final User user) {
        return UserMapper.toUser(userRepo.save(UserMapper.toUserEntity(user)));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User removeById(final Long userId) throws BusinessException {
        final UserEntity entity = userRepo.getOne(userId);
        if (entity == null) {
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
    public User loginWithUsernameOrEmailAndPassword(final String username, final String email, final String password) {
        final Optional<UserEntity> ue = userRepo.findByUsernameOrEmailAndPassword(username, email, password);
        return ue.isPresent() ? UserMapper.toUser(ue.get()) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public User update(UserUpdate userUpdate) {
        VehicleEntity ve = vehicleRepo.findAllByUserUsername(userUpdate.getUsername())
                .stream().findFirst().orElseThrow(NotFoundException::new);
        UserEntity ue = ve.getUser();
        if (hasChanged(userUpdate.getEmail(), ue.getEmail())) {
            userValidator.validateEmail(userUpdate.getEmail());
        }
        if (hasChanged(ve.getLicencePlate(), userUpdate.getLicencePlate())) {
            vehicleValidator.validate(ve.getLicencePlate());
        }
        ue.setEmail(userUpdate.getEmail());
        ue.setPhoneNumber(userUpdate.getPhoneNumber());
        ue.setPassword(userUpdate.getPassword().equals(userUpdate.getPasswordConfirm()) ?
                userUpdate.getPassword() : ue.getPassword());

        ve.setLicencePlate(userUpdate.getLicencePlate());
        ve.setUser(ue);
        vehicleRepo.save(ve);
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

    private void updateValues() {

    }

    private static boolean hasChanged(final String arg1, final String arg2) {
        return !arg1.equals(arg2);
    }


}
