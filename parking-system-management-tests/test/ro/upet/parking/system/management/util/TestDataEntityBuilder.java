package ro.upet.parking.system.management.util;

import com.google.common.collect.ImmutableList;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotMapper;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.data.api.membership.MembershipEntity;
import ro.upet.parking.system.management.data.api.parking.ParkingEntity;
import ro.upet.parking.system.management.data.api.parking.level.ParkingLevelEntity;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.data.api.reservation.ReservationEntity;
import ro.upet.parking.system.management.data.api.user.UserEntity;
import ro.upet.parking.system.management.data.api.vehicle.VehicleEntity;
import ro.upet.parking.system.management.model.base.MembershipType;
import ro.upet.parking.system.management.model.base.ReservationStatus;
import ro.upet.parking.system.management.model.base.UserType;
import ro.upet.parking.system.management.model.reservation.Reservation;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import static ro.upet.parking.system.management.util.TestUtil.*;


public class TestDataEntityBuilder {

    /**
     * Get an user entity
     *
     * @param args username, email, password, id, address, birthday, name, stripeId
     * @return the entity
     */
    public static UserEntity buildUserEntity(final Object... args) {
        return UserEntity.builder()
                .username("Andrada")
                .email(changeNullStringToDefault(args, 0, "email1@test.com"))
                .password(changeNullStringToDefault(args, 1, "Password1"))
                .id(Long.getLong(changeNullStringToDefault(args, 2, null)))
                .address(changeNullStringToDefault(args, 3, "Address1"))
                .birthday(LocalDate.parse(changeNullStringToDefault(args, 4, "1980-01-01")))
                .name(changeNullStringToDefault(args, 5, "Name1"))
                .stripeId(changeNullStringToDefault(args, 6, "StripeID1"))
                .base(new BaseEntity())
                .userType(UserType.CUSTOMER)
                .build();
    }
    /**
     * Get a vehicle entity
     *
     * @param args licence plate, name, user, id
     * @return the entity
     */
    public static VehicleEntity buildVehicleEntity(final Object... args) {
        return VehicleEntity.builder()
                .licencePlate(changeNullStringToDefault(args, 0, "AB10ABC"))
                .name(changeNullStringToDefault(args, 1, "Renault Clio"))
                .user((UserEntity) changeNullToDefault(args, 2, () -> buildUserEntity()))
                .id(Long.getLong(changeNullStringToDefault(args, 3, null)))
                .base(new BaseEntity())
                .build();
    }

    /**
     * Get a membership entity
     *
     * @param args cost, start hour, end hour, parking spot, user, id
     * @return the entity
     */
    public static MembershipEntity buildMembershipEntity(final Object... args) {
        return MembershipEntity.builder()
                .cost(BigDecimal.valueOf(Long.parseLong(changeNullStringToDefault(args, 0, "10"))))
                .startHour(Integer.parseInt(changeNullStringToDefault(args, 1, "7")))
                .endHour(Integer.parseInt(changeNullStringToDefault(args, 2, "10")))
                .membershipType(MembershipType.MONTH)
                .parkingSpot((ParkingSpotEntity) changeNullToDefault(args, 3, () -> buildParkingSpotEntity(1l)))
                .user((UserEntity) changeNullToDefault(args, 4, () -> buildUserEntity()))
                .id(Long.getLong(changeNullStringToDefault(args, 5, null)))
                .base(new BaseEntity())
                .build();
    }

    /**
     * Get a parking spot entity
     *
     * @param args number, rentable, rented, id, parkingZone
     * @return the entity
     */
    public static ParkingSpotEntity buildParkingSpotEntity(final Long id, final Object... args) {
        return ParkingSpotEntity.builder()
                .number(changeNullStringToDefault(args, 5, "1"))
                .available(changeNullBooleanToDefault(args, 1, true))
                .rentable(changeNullBooleanToDefault(args, 2, true))
                .rented(changeNullBooleanToDefault(args, 3, false))
                .parkingZone((ParkingZoneEntity) changeNullToDefault(args, 4, () -> buildParkingZoneEntity()))
                .id(id)
                .base(new BaseEntity())
                .build();
    }

    /**
     * Get a parking zone entity
     *
     * @param args letter, parking level, id
     * @return the entity
     */
    public static ParkingZoneEntity buildParkingZoneEntity(final Object... args) {
        return ParkingZoneEntity.builder()
                .letter(changeNullStringToDefault(args, 0, "A1"))
                .parkingLevel((ParkingLevelEntity) changeNullToDefault(args, 1, () -> buildParkingLevelEntity()))
                .id(Long.getLong(changeNullStringToDefault(args, 2, null)))
                .base(new BaseEntity())
                .build();
    }

    /**
     * Get a parking level entity
     *
     * @param args number, parking, id
     * @return the entity
     */
    public static ParkingLevelEntity buildParkingLevelEntity(final Object... args) {
        return ParkingLevelEntity.builder()
                .number(changeNullStringToDefault(args, 0, "0"))
                .parking((ParkingEntity) changeNullToDefault(args, 1, () -> buildParkingEntity()))
                .parkingZones(ImmutableList.of())
                .id(Long.getLong(changeNullStringToDefault(args, 2, null)))
                .base(new BaseEntity())
                .build();
    }

    /**
     * Get a parking entity
     *
     * @param args name, price per hour, location, opensAt, closesAt, id
     * @return the entity
     */
    public static ParkingEntity buildParkingEntity(final Object... args) {
        return ParkingEntity.builder()
                .name(changeNullStringToDefault(args, 0, "Parking1"))
                .pricePerHour(Double.parseDouble(changeNullStringToDefault(args, 1, "10")))
                .location(changeNullStringToDefault(args, 2, "Location1"))
                .opensAt(changeNullStringToDefault(args, 3, "07:00"))
                .closesAt(changeNullStringToDefault(args, 4, "23:00"))
                .id(Long.getLong(changeNullStringToDefault(args, 5, null)))
                .base(new BaseEntity())
                .build();
    }

    public static ReservationEntity buildReservationEntity(final String startTime, final String endTime, final ReservationStatus status,
                                                           final Object... args){
        return ReservationEntity.builder()
                .cost(Double.parseDouble(changeNullStringToDefault(args, 0, "10")))
                .parkingSpot((ParkingSpotEntity) changeNullToDefault(args, 1, () -> buildParkingSpotEntity(1l)))
                .startTime(Instant.parse(startTime))
                .endTime(Instant.parse(endTime))
                .reservationStatus(status)
                .id(Long.getLong(changeNullStringToDefault(args, 0, null)))
                .base(new BaseEntity())
                .build();
    }


    public static Reservation buildReservation(final String startTime, final String endTime, final ReservationStatus status,
                                                           final Object... args){
        return Reservation.builder()
                .cost(BigDecimal.TEN)
                .parkingSpot(ParkingSpotMapper.toParkingSpot(
                            (ParkingSpotEntity) changeNullToDefault(args, 1, () -> buildParkingSpotEntity(1l))))
                .startTime(Instant.parse(startTime))
                .endTime(Instant.parse(endTime))
                .reservationStatus(status)
                .id(Long.getLong(changeNullStringToDefault(args, 0, "1")))
                .build();
    }
}
