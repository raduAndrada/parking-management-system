package ro.upet.parking.system.management.business.api.membership;

import java.util.List;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.model.membership.Membership;
import ro.upet.parking.system.management.model.membership.MembershipCreate;

/**
 * @author Andrada
 * Business level logic for memberships
 */
public interface MembershipService extends BaseService<Membership> {


    /**
     * @param userId the user owning the memberships
     * @return the is of memberships belonging to the user with the corresponding id
     */
    List<Membership> getMembershipListByUserId(final Long userId);


    /**
     * @param membershipCreate the details of the membership to be created
     * @return the created membership
     */
    Membership addMembership(final MembershipCreate membershipCreate);


}
