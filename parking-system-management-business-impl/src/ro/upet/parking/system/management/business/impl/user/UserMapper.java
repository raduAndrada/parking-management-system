package ro.upet.parking.system.management.business.impl.user;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.model.user.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrada Mapper for the user entity and model
 */
public class UserMapper {

	private static final ModelMapper MAPPER = new ModelMapper();

	/**
	 * @param model for the user
	 * @return the corresponding entity
	 */
	public static UserEntity toUserEntity(final User model) {
		return MAPPER.map(model, UserEntity.class);
	}

	/**
	 * @param entity database level user
	 * @return the model for the entity
	 */
	public static User toUser(final UserEntity entity) {
		final User user = MAPPER.map(entity, User.class);
		user.setId(entity.getId());
		user.setCode(Objects.nonNull(entity.getBase())? entity.getBase().getCode() : "");
		return user;
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<User> toUserList(final List<UserEntity> entityList) {
		return entityList.stream().map(UserMapper::toUser).collect(Collectors.toList());
	}
}
