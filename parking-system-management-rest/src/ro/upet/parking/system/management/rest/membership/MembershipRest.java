package ro.upet.parking.system.management.rest.membership;

import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.upet.parking.system.management.business.api.core.BaseService;
import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.model.membership.Membership;
import ro.upet.parking.system.management.model.membership.MembershipCreate;
import ro.upet.parking.system.management.rest.base.BaseRest;

/**
 * @author Andrada
 * Rest controller for the memberships
 */
@RestController
@RequestMapping(value = "/v1/memberships")
@CrossOrigin(maxAge = 3600)
@Slf4j
public class MembershipRest extends BaseRest<Membership>{

	@Inject
	private MembershipService service;
	
	
	@Override
	@Inject
	public void setService(BaseService<Membership> service) {
		super.setService(this.service);
	}

	/**
	 * @param userId the user
	 * @return the list with all the memberships of a user
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Membership>> getMembershipsForUser(@PathVariable final Long userId) {
		log.info("REST request to GET membership by userId : {}", userId);
		final List<Membership> membershipList= service.getMembershipListByUserId(userId);
		if (membershipList == null) {
			log.info("No memberships found for the user with id: {}", userId);
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(membershipList);
		}
	}

	/**
	 * 
	 * @param membershipCreate the membership to be added
	 * @return the created entity
	 */
	@PostMapping("/create")
	public ResponseEntity<Membership> postMembership(@RequestBody final MembershipCreate membershipCreate) {
		log.info("REST request to POST membershipCreate : {}", membershipCreate);
		final Membership createdMembership;
		try {
			createdMembership = service.addMembership(membershipCreate);
		} catch (final Exception e) {
			log.info("Something went wrong creating the membershipCreate : {}", membershipCreate);
			return null;
		}
		return ResponseEntity.ok(createdMembership);
	}
	
	


}
