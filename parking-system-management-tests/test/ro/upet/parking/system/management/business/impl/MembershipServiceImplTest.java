package ro.upet.parking.system.management.business.impl;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;
import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.business.impl.membership.MembershipServiceImpl;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.impl.membership.MembershipRepository;
import ro.upet.parking.system.management.data.impl.parking.level.ParkingLevelRepository;
import ro.upet.parking.system.management.data.impl.parking.spot.ParkingSpotRepository;
import ro.upet.parking.system.management.data.impl.user.UserRepository;
import ro.upet.parking.system.management.model.base.MembershipType;
import ro.upet.parking.system.management.model.membership.ImtMembership;
import ro.upet.parking.system.management.model.membership.Membership;
import ro.upet.parking.system.management.model.membership.MembershipCreate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ro.upet.parking.system.management.util.TestDataEntityBuilder.*;

@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceImplTest {

    private MembershipService membershipsService;

    @Mock
    private MembershipRepository membershipsRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private ParkingLevelRepository parkingLevelRepo;


    @Before
    public void setUp() {
        membershipsService = new MembershipServiceImpl(membershipsRepo, parkingLevelRepo, userRepo);
    }


    @Test
    public void addMembership_test_success() {
        val model = mock(MembershipCreate.class);

        val entity = buildMembershipEntity();
        val parkingLevel = buildParkingLevelEntity();
        val parkingZone = buildParkingZoneEntity();
        val parkingSpot = buildParkingSpotEntity();
        val user = buildUserEntity();

        parkingZone.setParkingSpots(ImmutableList.of(parkingSpot));
        parkingLevel.setParkingZones(ImmutableList.of(parkingZone));

        when(parkingLevelRepo.findById(any(Long.class))).thenReturn(Optional.ofNullable(parkingLevel));
        when(userRepo.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));
        when(membershipsRepo.save(any(MembershipEntity.class))).thenReturn(entity);

        val expectedResult = membershipsService.addMembership(model);

        assertThat(expectedResult).isNotNull();
        assertThat(expectedResult.getCost()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void deleteMembershipById_test_success() {
        val entity = buildMembershipEntity();

        when(membershipsRepo.findById(1L)).thenReturn(Optional.ofNullable(entity));

        doNothing().when(membershipsRepo).deleteById(1L);
        assertThatCode(() -> membershipsService.removeById(1L)).doesNotThrowAnyException();

    }

    @Test
    public void findAllMemberships_test_success() {
        val entity = buildMembershipEntity();

        when(membershipsRepo.findAll()).thenReturn(ImmutableList.of(entity));

        val expectedResult = membershipsService.getList();
        assertThat(expectedResult.size()).isEqualTo(1);
        assertThat(expectedResult.get(0).getCost()).isEqualTo(BigDecimal.TEN);
    }


    @Test
    public void findMembershipById_test_success() {
        val entity = buildMembershipEntity();
        when(membershipsRepo.findById(1L)).thenReturn(Optional.ofNullable(entity));

        val expectedResult = membershipsService.getById(1l);
        assertThat(expectedResult.getCost()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    public void updateMembership_test_success() {
        val entity = buildMembershipEntity(null, null, null, null, null, "1");
        val model = mock(Membership.class);

        when(membershipsRepo.save(any())).thenReturn(entity);

        val expectedResult = membershipsService.update(model);
        assertThat(expectedResult.getCost()).isEqualTo(BigDecimal.TEN);
    }


}
