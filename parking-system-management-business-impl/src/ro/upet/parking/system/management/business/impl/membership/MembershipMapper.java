package ro.upet.parking.system.management.business.impl.membership;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.base.GenericMapper;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.model.membership.MdfMembership;
import ro.upet.parking.system.management.model.membership.Membership;

/**
 * 
 * @author Andrada Mapper for the membership entity and model
 */
public class MembershipMapper {
	private static final GenericMapper<MembershipEntity, Membership> MAPPER = new GenericMapper();

	/**
	 * @param model for the membership
	 * @return the corresponding entity
	 */
	public static MembershipEntity toMembershipEntity(final Membership model) {
		final MembershipEntity entity = new MembershipEntity();
		MAPPER.mapToEntity(model, entity);
		if (Objects.nonNull(model.getUser())) {
			entity.setUser(UserMapper.toUserEntity(model.getUser()));
		}
		if (Objects.nonNull(entity.getParkingSpot())) {
			entity.setParkingSpot(ParkingSpotMapper.toParkingSpotEntity(model.getParkingSpot()));
		}
		return entity;
	}

	/**
	 * @param entity database level membership
	 * @return the model for the entity
	 */
	public static Membership toMembership(final MembershipEntity entity) {
		MdfMembership model = MdfMembership.create();
		MAPPER.mapToModel(entity, model);
		model.setParkingSpot(ParkingSpotMapper.toParkingSpot(entity.getParkingSpot()))
				.setUser(UserMapper.toUser(entity.getUser()));
		return model.toImmutable();
	}

	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Membership> toMembershipList(final List<MembershipEntity> entityList) {
		return entityList.stream().map(MembershipMapper::toMembership).collect(Collectors.toList());
	}
}
