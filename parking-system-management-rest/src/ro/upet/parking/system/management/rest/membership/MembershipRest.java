package ro.upet.parking.system.management.rest.membership;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.model.membership.Membership;
import ro.upet.parking.system.management.model.membership.MembershipCreate;

/**
 * @author Andrada
 * Rest controller for the memberships
 */
@RestController
@RequestMapping(value = "/v1/memberships")
@CrossOrigin(maxAge = 3600)
public class MembershipRest {
	
	private static final Logger LOGGER  = Logger.getLogger(MembershipRest.class.getName());

	@Inject
	private MembershipService membershipService;
	
	/**
	 * @param code unique for each membership
	 * @return the membership with the corresponding code
	 */
	@GetMapping(path = "/code/{code}")
	public ResponseEntity<Membership> getMembership(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to GET membership by code: %s", code));
		final Membership membership = membershipService.getMembershipByCode(code);
		if (membership == null) {
			LOGGER.info(String.format("Membership with code: %s does not exist", code));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(membership);
		}
	}
	
	/**
	 * @param id of the membership
	 * @return the membership with the corresponding id
	 */
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Membership> getMembership(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to GET membership by id: %s", id));
		final Membership membership = membershipService.getMembershipById(id);
		if (membership == null) {
			LOGGER.info(String.format("Membership with id: %s does not exist", id));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(membership);
		}
	}


	/**
	 * @return the list with all the memberships
	 */
	@GetMapping
	public ResponseEntity<List<Membership>> getMemberships() {
		LOGGER.info(String.format("REST request to GET all memberships"));
		final List<Membership> membershipList= membershipService.getMembershipList();
		if (membershipList == null) {
			LOGGER.info(String.format("No memberships found"));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(membershipList);
		}
	}
	
	/**
	 * @param userId the user
	 * @return the list with all the memberships of a user
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Membership>> getMembershipsForUser(@PathVariable final Long userId) {
		LOGGER.info(String.format("REST request to GET membership by userId : %s", userId));
		final List<Membership> membershipList= membershipService.getMembershipListByUserId(userId);
		if (membershipList == null) {
			LOGGER.info(String.format("No memberships found for the user with id: %s", userId));
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(membershipList);
		}
	}

	/**
	 * 
	 * @param membership the membership to be added
	 * @return the created entity
	 */
	@PostMapping
	@Transactional
	public ResponseEntity<Membership> postMembership(@RequestBody final Membership membership) {
		LOGGER.info(String.format("REST request to POST membership : %s", membership));
		final Membership createdMembership;
		try {
			createdMembership = membershipService.addMembership(membership);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the membership : %s", membership));
			return null;
		}
		return ResponseEntity.ok(createdMembership);
	}
	
	

	/**
	 * 
	 * @param membership the membership to be added
	 * @return the created entity
	 */
	@PostMapping("/create")
	public ResponseEntity<Membership> postMembership(@RequestBody final MembershipCreate membershipCreate) {
		LOGGER.info(String.format("REST request to POST membershipCreate : %s", membershipCreate));
		final Membership createdMembership;
		try {
			createdMembership = membershipService.addMembership(membershipCreate);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong creating the membershipCreate : %s", membershipCreate));
			return null;
		}
		return ResponseEntity.ok(createdMembership);
	}
	
	
	/**
	 * 
	 * @param membership the membership to be added
	 * @return the created entity
	 */
	@PutMapping
	@Transactional
	public ResponseEntity<Membership> putMembership(@RequestBody final Membership membership) {
		LOGGER.info(String.format("REST request to PUT membership : %s", membership));
		final Membership updatedMembership;
		try {
			updatedMembership = membershipService.updateMembership(membership);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong updating the membership: %s", membership));
			return null;
		}
		return ResponseEntity.ok(updatedMembership);
	}

	
	/**
	 * 
	 * @param id the identifier for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/id/{id}")
	@Transactional
	public ResponseEntity<Membership> deleteMembership(@PathVariable final Long id) {
		LOGGER.info(String.format("REST request to DELETE membership with id : %s", id));
		final Membership deletedMembership;
		try {
			deletedMembership = membershipService.removeMembershipById(id);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the membership with the id: %s", id));
			return null;
		}
		return ResponseEntity.ok(deletedMembership);
	}
	
	/**
	 * 
	 * @param code the unique code for the entity to be deleted
	 * @return the deleted entity
	 */
	@DeleteMapping(path = "/code/{code}")
	@Transactional
	public ResponseEntity<Membership> deleteMembership(@PathVariable final String code) {
		LOGGER.info(String.format("REST request to DELETE membership with code : %s", code));
		final Membership deletedMembership;
		try {
			deletedMembership = membershipService.removeMembershipByCode(code);
		} catch (final Exception e) {
			LOGGER.info(String.format("Something went wrong deleting the membership with the code: %s", code));
			return null;
		}
		return ResponseEntity.ok(deletedMembership);
	}

}
