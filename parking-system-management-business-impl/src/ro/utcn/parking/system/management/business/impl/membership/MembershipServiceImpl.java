package ro.utcn.parking.system.management.business.impl.membership;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.utcn.parking.system.management.business.api.membership.MembershipService;
import ro.utcn.parking.system.management.data.api.membership.MembershipEntity;
import ro.utcn.parking.system.management.data.impl.membership.MembershipRepository;
import ro.utcn.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.utcn.parking.system.management.data.impl.user.UserRepository;
import ro.utcn.parking.system.management.model.membership.Membership;

/**
 * @author Andrada
 * Business level logic implementation for memberships 
 */
@Service
public class MembershipServiceImpl implements MembershipService{
	
	@Inject
	MembershipRepository membershipRepo;
	
	@Inject
	UserRepository userRepo;
	
	@Inject
	ParkingSpotRepository parkingSpotRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership getMembershipById(final Long membershipId) {
		return MembershipMapper.toMembership(membershipRepo.getOne(membershipId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership getMembershipByCode(final String membershipCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Membership> getMembershipList() {
		return MembershipMapper.toMembershipList(membershipRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership addMembership(final Membership membership) {
		final MembershipEntity entity = MembershipMapper.toMembershipEntity(membership);
		entity.setUser(userRepo.getOne(membership.getUserId()));
		entity.setParkingSpot(parkingSpotRepo.getOne(membership.getParkingSpotId()));
		final MembershipEntity savedEntity = membershipRepo.save(entity);
		return MembershipMapper.toMembership(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership updateMembership(final Membership membership) {
		final MembershipEntity entity = MembershipMapper.toMembershipEntity(membership);
		entity.setUser(userRepo.getOne(membership.getUserId()));
		entity.setParkingSpot(parkingSpotRepo.getOne(membership.getParkingSpotId()));
		final MembershipEntity savedEntity = membershipRepo.save(entity);
		return MembershipMapper.toMembership(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership removeMembershipById(final Long membershipId) throws Exception {
		final MembershipEntity entity = membershipRepo.getOne(membershipId);
		if (entity == null ) {
			throw new Exception();
		}
		membershipRepo.deleteById(membershipId);
		return MembershipMapper.toMembership(entity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership removeMembershipByCode(final String membershipCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
