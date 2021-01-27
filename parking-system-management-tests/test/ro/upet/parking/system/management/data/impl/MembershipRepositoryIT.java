package ro.upet.parking.system.management.data.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.data.DataTests;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.membership.MembershipRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.MembershipType;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MembershipRepositoryIT extends DataTests {
	
		private static final MembershipType MEMBERSHIP_TYPE1 = MembershipType.PERMANENT;
	    private static final MembershipType MEMBERSHIP_TYPE2 = MembershipType.YEAR;;
	    
	    private static final String MEMBERSHIP_USER_USERNAME1 = "username1";
	    
	    private static final String MEMBERSHIP_PARKING_SPOT_NUMBER1 = "A1";
	    private static final String MEMBERSHIP_PARKING_SPOT_NUMBER2 = "A2";
	    
	    
	    private static final MembershipEntity MEMBERSHIP_1 = new MembershipEntity();
	    private static final MembershipEntity MEMBERSHIP_2 = new MembershipEntity();
	    
	    @Inject
	    private MembershipRepository membershipRepo;
	    
	    @Inject
	    private UserRepository userRepo;
	    
	    @Inject
	    private ParkingSpotRepository parkingSpotRepo;


	    
	    @Before
	    public void init() {
	    	UserEntity ue = new UserEntity();
	    	ue.setUsername(MEMBERSHIP_USER_USERNAME1);
	    	ue = userRepo.save(ue);
	    	
	    	ParkingSpotEntity pse = new ParkingSpotEntity();
	    	pse.setNumber(MEMBERSHIP_PARKING_SPOT_NUMBER1);
	    	pse = parkingSpotRepo.save(pse);
	    	
	    	MEMBERSHIP_1.setMembershipType(MEMBERSHIP_TYPE1);
	    	MEMBERSHIP_1.setUser(ue);
	    	MEMBERSHIP_1.setParkingSpot(pse);

	    	pse = new ParkingSpotEntity();
	    	pse.setNumber(MEMBERSHIP_PARKING_SPOT_NUMBER2);
	    	pse = parkingSpotRepo.save(pse);
	    	
	    	MEMBERSHIP_2.setMembershipType(MEMBERSHIP_TYPE2);
	    	MEMBERSHIP_2.setUser(ue);
	    	MEMBERSHIP_2.setParkingSpot(pse);
	    }
	    


	    @Test
	    @Transactional
	    public void addMembership_test_success() {
	        final MembershipEntity testEntity = membershipRepo.save(MEMBERSHIP_1);
	        final Optional<MembershipEntity> expectedEntity = membershipRepo.findById(testEntity.getBase().getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getUser().getUsername()).isEqualTo(MEMBERSHIP_USER_USERNAME1);
	    }
	    
	    @Test
	    @Transactional
	    public void deleteMembership_test_success() {
	        final MembershipEntity testEntity = membershipRepo.save(MEMBERSHIP_1);
	        membershipRepo.delete(testEntity);
	        final Optional<MembershipEntity> expectedEntity = membershipRepo.findById(testEntity.getBase().getId());
	        assertThat(expectedEntity.isEmpty());
	    }
	    
	
	    
	    @Test
	    @Transactional
	    public void findAllMemberships_test_success() {
	        membershipRepo.save(MEMBERSHIP_1);
	        membershipRepo.save(MEMBERSHIP_2);
	        final List<MembershipEntity> expectedList = membershipRepo.findAll();
	        assertThat(expectedList).isNotNull();
	        assertThat(expectedList.size()).isEqualTo(2);
	    }
	    
	    
	    @Test
	    @Transactional
	    public void updateMembership_test_success() {
	        final MembershipEntity testEntity = membershipRepo.save(MEMBERSHIP_1);
	        testEntity.setMembershipType(MEMBERSHIP_TYPE2);
	        membershipRepo.save(testEntity);
	        final Optional<MembershipEntity> expectedEntity = membershipRepo.findById(testEntity.getBase().getId());
	        assertThat(expectedEntity).isNotNull();
	        assertThat(expectedEntity.get().getMembershipType()).isEqualTo(MEMBERSHIP_TYPE2);
	    }
}
