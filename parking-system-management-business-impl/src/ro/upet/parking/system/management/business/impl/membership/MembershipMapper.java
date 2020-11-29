package ro.upet.parking.system.management.business.impl.membership;

import java.util.List;
import java.util.stream.Collectors;

import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.model.membership.ImtMembership;
import ro.upet.parking.system.management.model.membership.Membership;

/**
 * 
 * @author Andrada
 * Mapper for the membership entity and model
 */
public class MembershipMapper {

	/**
	 * @param membership model for the membership
	 * @return the corresponding entity
	 */
	public static MembershipEntity toMembershipEntity(final Membership membership) {
		final MembershipEntity entity = new MembershipEntity();
		entity.setCode(membership.getCode());
		entity.setId(membership.getId());
		entity.setCreatedAt(membership.getCreatedAt());
		entity.setMembershipType(membership.getMembershipType());
		entity.setUpdatedAt(membership.getUpdatedAt());
		entity.setUser(UserMapper.toUserEntity(membership.getUser()));
		entity.setParkingSpot(ParkingSpotMapper.toParkingSpotEntity(membership.getParkingSpot()));
		return entity;
	}
	
	/**
	 * @param entity database level membership
	 * @return the model for the entity
	 */
	public static Membership toMembership(final MembershipEntity entity) {
		return ImtMembership.builder()
				.code(entity.getCode())
				.createdAt(entity.getCreatedAt())
				.id(entity.getId())
				.membershipType(entity.getMembershipType())
				.parkingSpot(ParkingSpotMapper.toParkingSpot(entity.getParkingSpot()))
				.updatedAt(entity.getUpdatedAt())
				.user(UserMapper.toUser(entity.getUser()))
				.build();
	}
	
	/**
	 * @param entityList the list with the database entities
	 * @return the models for the list
	 */
	public static List<Membership> toMembershipList (final List<MembershipEntity> entityList) {
		return entityList.stream().map(MembershipMapper::toMembership).collect(Collectors.toList());
	}
}
