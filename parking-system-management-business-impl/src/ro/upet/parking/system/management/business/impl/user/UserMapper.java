package ro.upet.parking.system.management.business.impl.user;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.user.ImtUser;
import ro.upet.parking.system.management.model.user.User;

/**
 * 
 * @author Andrada
 * Mapper for the user entity and model
 */
public class UserMapper {

	/**
	 * @param user model for the user
	 * @return the corresponding entity
	 */
	public static UserEntity toUserEntity(final User user) {
		final UserEntity entity = new UserEntity();
		entity.getBase().setCode(user.getCode());
		entity.setId(user.getId());
		entity.setEmail(user.getEmail());
		entity.setName(user.getName());
		entity.setPhoneNumber(user.getPhoneNumber());
		entity.setUsername(user.getUsername());
		entity.setPassword(user.getPassword());
		return entity;
	}
	
	/**
	 * @param entity database level user
	 * @return the model for the entity
	 */
	public static User toUser(final UserEntity entity) {
		return ImtUser.builder()
				.code(entity.getBase().getCode())
				.id(entity.getId())
				.email(entity.getEmail())
				.name(entity.getName())
				.phoneNumber(entity.getPhoneNumber())
				.username(entity.getUsername())
				.password(entity.getPassword())
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<User> toUserList (final List<UserEntity> entityList) {
		return entityList.stream().map(UserMapper::toUser).collect(Collectors.toList());
	}
}
