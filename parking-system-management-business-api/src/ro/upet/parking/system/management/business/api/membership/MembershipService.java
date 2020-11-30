package ro.upet.parking.system.management.business.api.membership;

import java.util.List;

import ro.upet.parking.system.management.model.membership.Membership;

/**
 * @author Andrada
 * Business level logic for memberships 
 */
public interface MembershipService{
	
	/**
	 * @param membershipId the id of the membership searched
	 * @return the requested membership
	 */
	public Membership getMembershipById(final Long membershipId);
	
	/**
	 * @param membershipCode the code of the membership searched 
	 * @return the requested membership
	 */
	public Membership getMembershipByCode(final String membershipCode);
	
	/**
	 * @return the list of all the memberships
	 */
	public List<Membership> getMembershipList();
	
	/**
	 * @param membership the entity to be added
	 * @return the added entity
	 */
	public Membership addMembership(final Membership membership);
	
	/**
	 * @param membership the updated membership
	 * @return the updated entity
	 */
	public Membership updateMembership(final Membership membership);
	
	/**
	 * @param membershipId the id of the entity that will be deleted
	 * @return the deleted entity
	 * @throws Exception if the membership doesn't exist
	 */
	public Membership removeMembershipById(final Long membershipId) throws Exception;
	
	/**
	 * @param membershipCode the code of the entity that will be deleted
	 * @return the deleted entity
	 */
	public Membership removeMembershipByCode(final String membershipCode);

	/**
	 * @param userId the user owning the memberships
	 * @return the is of memberships belonging to the user with the corresponding id
	 */
	public List<Membership> getMembershipListByUserId(final Long userId);
}
