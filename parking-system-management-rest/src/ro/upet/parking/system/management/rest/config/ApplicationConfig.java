package ro.upet.parking.system.management.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.upet.parking.system.management.business.api.membership.MembershipService;
import ro.upet.parking.system.management.business.api.parking.ParkingService;
import ro.upet.parking.system.management.business.api.parking.level.ParkingLevelService;
import ro.upet.parking.system.management.business.api.parking.spot.ParkingSpotService;
import ro.upet.parking.system.management.business.api.parking.zone.ParkingZoneService;
import ro.upet.parking.system.management.business.api.payment.options.PaymentOptionsService;
import ro.upet.parking.system.management.business.api.reservation.ReservationService;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.business.api.vehicle.VehicleService;
import ro.upet.parking.system.management.business.impl.membership.MembershipServiceImpl;
import ro.upet.parking.system.management.business.impl.parking.ParkingServiceImpl;
import ro.upet.parking.system.management.business.impl.parking.level.ParkingLevelServiceImpl;
import ro.upet.parking.system.management.business.impl.parking.spot.ParkingSpotServiceImpl;
import ro.upet.parking.system.management.business.impl.parking.zone.ParkingZoneServiceImpl;
import ro.upet.parking.system.management.business.impl.payment.options.PaymentOptionsServiceImpl;
import ro.upet.parking.system.management.business.impl.reservation.ReservationServiceImpl;
import ro.upet.parking.system.management.business.impl.user.UserServiceImpl;
import ro.upet.parking.system.management.business.impl.vehicle.VehicleServiceImpl;

@Configuration
public class ApplicationConfig {
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public MembershipService membershipService() {
		return new MembershipServiceImpl();
	}

	@Bean
	public ParkingService parkingService() {
		return new ParkingServiceImpl();
	}

	@Bean
	public ParkingLevelService parkingLevelService() {
		return new ParkingLevelServiceImpl();
	}
	
	@Bean
	public ParkingSpotService parkingSpotService() {
		return new ParkingSpotServiceImpl();
	}

	@Bean
	public ParkingZoneService parkingZoneService() {
		return new ParkingZoneServiceImpl();
	}

	@Bean
	public PaymentOptionsService paymentOptionsService() {
		return new PaymentOptionsServiceImpl();
	}
	
	@Bean
	public ReservationService reservationService() {
		return new ReservationServiceImpl();
	}

	@Bean
	public VehicleService vehicleService() {
		return new VehicleServiceImpl();
	}


}
