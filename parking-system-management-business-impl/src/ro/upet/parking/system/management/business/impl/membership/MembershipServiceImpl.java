package ro.upet.parking.system.management.business.impl.membership;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.core.NotFoundException;
import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.business.impl.parking.level.ParkingLevelMapper;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotFinder;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.impl.membership.MembershipRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.membership.ImtMembership;
import ro.upet.parking.system.management.model.membership.Membership;
import ro.upet.parking.system.management.model.membership.MembershipCreate;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.user.User;

/**
 * @author Andrada
 * Business level logic implementation for memberships 
 */
@Service
public class MembershipServiceImpl implements MembershipService{
	
	final
	MembershipRepository membershipRepo;
	
	final
	ParkingLevelRepository parkingLevelRepo;
	
	final
	UserRepository userRepo;

	public MembershipServiceImpl(MembershipRepository membershipRepo, ParkingLevelRepository parkingLevelRepo, UserRepository userRepo) {
		this.membershipRepo = membershipRepo;
		this.parkingLevelRepo = parkingLevelRepo;
		this.userRepo = userRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership getById(final Long membershipId) {
		return MembershipMapper.toMembership(membershipRepo.findById(membershipId).orElseThrow(BusinessException::new));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership getByCode(final String membershipCode) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Membership> getList() {
		return MembershipMapper.toMembershipList(membershipRepo.findAll());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership add(final Membership membership) {
		final MembershipEntity entity = MembershipMapper.toMembershipEntity(membership);
		final MembershipEntity savedEntity = membershipRepo.save(entity);
		return MembershipMapper.toMembership(savedEntity);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership update(final Membership membership) {
		final MembershipEntity entity = MembershipMapper.toMembershipEntity(membership);
		if (membershipRepo.findById(entity.getId()).isPresent()) {
			return MembershipMapper.toMembership(membershipRepo.save(entity));
		}
		throw new NotFoundException(String.format("Entity with id %d not found" , membership.getId()));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership removeById(final Long membershipId) throws BusinessException {
		final Optional<MembershipEntity> entity = membershipRepo.findById(membershipId);
		if (!entity.isPresent() ) {
			throw new BusinessException("Membership does not exist");
		}
		membershipRepo.deleteById(membershipId);
		return MembershipMapper.toMembership(entity.get());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership removeByCode(final String membershipCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Membership> getMembershipListByUserId(final Long userId) {
		return getList().stream().filter(m -> m.getUser().getId().equals(userId)).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Membership addMembership(final MembershipCreate membershipCreate) {
		final ParkingLevel pl = ParkingLevelMapper.toParkingLevel(parkingLevelRepo.findById(membershipCreate.getParkingLevelId()).orElse(null));
		final ParkingSpot ps = ParkingSpotFinder.findParkingSpotForMembership(pl.getParkingZones());
		final User u = UserMapper.toUser(userRepo.findById(membershipCreate.getUserId()).orElse(null));
		return add(ImtMembership.builder()
									.parkingSpot(ps)
									.user(u)
									.membershipType(membershipCreate.getMembershipType())
									.build()
				);
	}
	
	
}
