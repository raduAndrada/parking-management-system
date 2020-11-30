package ro.upet.parking.system.management.business.impl.membership;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.impl.membership.MembershipRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.membership.Membership;

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
		entity.setCreatedAt(Instant.now());
		entity.setUpdatedAt(Instant.now());
		final MembershipEntity savedEntity = membershipRepo.save(entity);
		return MembershipMapper.toMembership(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership updateMembership(final Membership membership) {
		final MembershipEntity entity = MembershipMapper.toMembershipEntity(membership);
		entity.setUpdatedAt(Instant.now());
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Membership> getMembershipListByUserId(Long userId) {
		return getMembershipList().stream().filter(m -> m.getUser().getId().equals(userId)).collect(Collectors.toList());
	}
	
	
}
