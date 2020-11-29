package ro.upet.parking.system.management.model.base;

/** 
 * @author Andrada
 * Enum used to differentiate between the types of memberships
 */
public enum MembershipType {

	PERMANENT, // does not expire
	YEAR, // availability 1 year
	HALF_YEAR, // availability 6 months
	SEASON, // availability 3 months
	MONTH // availability 1 month
}
