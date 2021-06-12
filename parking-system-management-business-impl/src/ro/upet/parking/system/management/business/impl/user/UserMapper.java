package ro.upet.parking.system.management.business.impl.user;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.user.MdfUser;
import ro.upet.parking.system.management.model.user.User;

/**
 * 
 * @author Andrada Mapper for the user entity and model
 */
public class UserMapper {

	private static final GenericMapper<UserEntity, User> MAPPER = new GenericMapper();

	/**
	 * @param model for the user
	 * @return the corresponding entity
	 */
	public static UserEntity toUserEntity(final User model) {
		final UserEntity entity = new UserEntity();
		MAPPER.mapToEntity(model, entity);
		return entity;
	}

	/**
	 * @param entity database level user
	 * @return the model for the entity
	 */
	public static User toUser(final UserEntity entity) {
		MdfUser model = MdfUser.create();
		MAPPER.mapToModel(entity, model);
		return model.toImmutable();
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<User> toUserList(final List<UserEntity> entityList) {
		return entityList.stream().map(UserMapper::toUser).collect(Collectors.toList());
	}
}
