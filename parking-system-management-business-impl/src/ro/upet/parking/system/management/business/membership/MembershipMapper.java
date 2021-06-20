package ro.upet.parking.system.management.business.membership;

import org.modelmapper.ModelMapper;
import ro.upet.parking.system.management.business.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.model.membership.Membership;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrada Mapper for the membership entity and model
 */
public class MembershipMapper {
    private static final ModelMapper MAPPER = new ModelMapper();

    /**
     * @param model for the membership
     * @return the corresponding entity
     */
    public static MembershipEntity toMembershipEntity(final Membership model) {
        return MAPPER.map(model, MembershipEntity.class);
    }

    /**
     * @param entity database level membership
     * @return the model for the entity
     */
    public static Membership toMembership(final MembershipEntity entity) {
        final Membership membership = MAPPER.map(entity, Membership.class);
        membership.setParkingSpot(ParkingSpotMapper.toParkingSpot(entity.getParkingSpot()));
        return membership;
    }

    /**
     * @param entityList the list with the database entities
     * @return the models for the list
     */
    public static List<Membership> toMembershipList(final List<MembershipEntity> entityList) {
        return entityList.stream().map(MembershipMapper::toMembership).collect(Collectors.toList());
    }
}
