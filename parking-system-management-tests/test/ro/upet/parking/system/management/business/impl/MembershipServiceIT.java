package ro.upet.parking.system.management.business.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ro.upet.parking.system.management.business.BusinessTests;
import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.business.impl.user.UserMapper;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.impl.membership.MembershipRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.MembershipType;
import ro.upet.parking.system.management.model.membership.ImtMembership;
import ro.upet.parking.system.management.model.membership.Membership;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.user.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MembershipServiceIT extends BusinessTests {
    
    @Inject
    protected MembershipRepository membershipsRepo;
    
    @Inject
    protected MembershipService membershipsService;
    
    @Inject
    private UserRepository userRepo;
    
    @Inject
    private ParkingSpotRepository parkingSpotRepo;
    
    private static final Membership MEMBERSHIP_1 = ImtMembership.builder()
    		.membershipType(MembershipType.HALF_YEAR)
			.build();
	
    @Test
    @Transactional
    public void addMembership_test_success() {
      	UserEntity ue = new UserEntity();	
    	ue.setUsername("Andrada");
    	ue = userRepo.save(ue);
    	
     	ParkingSpotEntity pse = new ParkingSpotEntity();	
     	pse.setNumber("A1");
     	pse.setRentable(false);
     	pse.setAvailable(true);
     	pse = parkingSpotRepo.save(pse);
     	
     	ParkingSpot ps = ParkingSpotMapper.toParkingSpot(pse);
     	User u = UserMapper.toUser(ue);
    	
        final Membership actualResult = membershipsService.addMembership(ImtMembership.copyOf(MEMBERSHIP_1)
        																					.withParkingSpot(ps)
        																					.withUser(u));
        final Membership expectedResult = ImtMembership.copyOf(MEMBERSHIP_1)
        											.withUser(u)
        											.withParkingSpot(ps)
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    @Test
    @Transactional
    public void deleteMembershipById_test_success() {
    	MembershipEntity me = new MembershipEntity();
      	UserEntity ue = new UserEntity();	
    	ue.setUsername("Andrada");
    	ue = userRepo.save(ue);
    	me.setUser(ue);
    	
     	ParkingSpotEntity pse = new ParkingSpotEntity();	
     	pse.setNumber("A1");
     	pse = parkingSpotRepo.save(pse);
     	me.setParkingSpot(pse);
     	
    	me = membershipsRepo.save(me);
    	
    	final List<Membership> actualResult = membershipsService.getMembershipList();	
        membershipsService.removeMembershipById(me.getId());
        final List<Membership> expectedResult = membershipsService.getMembershipList();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size() - 1).isEqualTo(expectedResult.size());
    }
    
    @Test
    @Transactional
    public void findMembershipById_test_success() {
    	MembershipEntity me = new MembershipEntity();
      	UserEntity ue = new UserEntity();	
    	ue.setUsername("Andrada");
    	ue = userRepo.save(ue);
    	me.setUser(ue);
    	
     	ParkingSpotEntity pse = new ParkingSpotEntity();	
     	pse.setNumber("A1");
     	pse = parkingSpotRepo.save(pse);
     	me.setParkingSpot(pse);
    	me = membershipsRepo.save(me);
        final Membership actualResult = membershipsService.getMembershipById(me.getId());
        final Membership expectedResult = ImtMembership.builder()
    											.id(actualResult.getId())
    											.createdAt(actualResult.getCreatedAt())
    											.updatedAt(actualResult.getUpdatedAt())
    											.user(UserMapper.toUser(ue))
    											.parkingSpot(ParkingSpotMapper.toParkingSpot(pse))
    											.build();
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    
    @Test
    @Transactional
    public void findAllMemberships_test_success() {
    	MembershipEntity me = new MembershipEntity();
     	UserEntity ue = new UserEntity();
     	ue = userRepo.save(ue);
    	me.setUser(ue);
       	ParkingSpotEntity pse = new ParkingSpotEntity();	
     	pse.setNumber("A1");
     	pse = parkingSpotRepo.save(pse);
     	me.setParkingSpot(pse);
    	membershipsRepo.save(me);
    	
    	me = new MembershipEntity();
    	me.setUser(ue);      	
    	pse = new ParkingSpotEntity();	
     	pse.setNumber("A2");
     	pse = parkingSpotRepo.save(pse);
     	me.setParkingSpot(pse);
    	membershipsRepo.save(me);
    	
        final List<Membership> actualResult = membershipsService.getMembershipList();	
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.size()).isEqualTo(2);
    }
    
    @Test
    @Transactional
    public void updateMembership_test_success() {
       	MembershipEntity me = new MembershipEntity();
      	UserEntity ue = new UserEntity();	
    	ue.setUsername("Andrada");
    	ue = userRepo.save(ue);
    	me.setUser(ue);
    	
     	ParkingSpotEntity pse = new ParkingSpotEntity();	
     	pse.setNumber("A1");
     	pse = parkingSpotRepo.save(pse);
     	me.setParkingSpot(pse);
    	me = membershipsRepo.save(me);
    	
     	
     	ParkingSpot ps = ParkingSpotMapper.toParkingSpot(pse);
     	User u = UserMapper.toUser(ue);
    	
        final Membership actualResult = membershipsService.updateMembership(ImtMembership.copyOf(MEMBERSHIP_1)
        																							.withId(me.getId())
        																							.withUser(u)
        																							.withParkingSpot(ps));
        final Membership expectedResult = ImtMembership.copyOf(MEMBERSHIP_1)
        											.withUser(u)
        											.withParkingSpot(ps)
        											.withId(actualResult.getId())
        											.withCreatedAt(actualResult.getCreatedAt())
        											.withUpdatedAt(actualResult.getUpdatedAt());
        assertThat(actualResult).isNotNull();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
    
    

    
   
}
