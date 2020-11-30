export interface ActionAudit {
    id?: number;
  
    code?: number;
  
    createdAt?: string;
  
    updatedAt?: string;
}

export enum MembershipType {
    PERMANENT, // does not expire
	YEAR, // availability 1 year
	HALF_YEAR, // availability 6 months
	SEASON, // availability 3 months
	MONTH // availability 1 month  
}

export enum PaymentStatus {
	PAID, // payment has been processed successfully 
	PREPAID, // reservation paid in advance
	NOT_PAID, // reservation was not paid
	ERROR // error with the payment occurred
}

export enum ReservationStatus {
	CLAIMED, // vehicle parked successfully
	EXPIRED, // reservation expired 
	ONGOING, // reservation count down timer ongoing
	UNCLAIMED // reservation count down timer reached 0 before user claimed the spot
}

export enum Size {
	LARGE, // for buses
	SMALL, // for motorcycles
	MEDIUM // for cars
}


export enum UserType {
	ADMIN,
	CUSTOMER
}


export interface Membership extends ActionAudit {
  
    membershipType: MembershipType;
  
    user: User;
  
    parkingSpot: ParkingSpot;
}

export interface Parking extends ActionAudit {
  
    name: string;
    
    location: string;
  
    opensAt: string;

    closesAt: string;

}


export interface ParkingCreate {
  
    parking: Parking;

    numberOfLevels: string;
  
    parkingZoneStartingLetter: string;

    parkingZoneEndingLetter: string;

    parkingZoneSpotNumber: number;
}


export interface ParkingLevel extends ActionAudit {
  
    number: string;
  
    parkingZones: ParkingSpot[];

}

export interface ParkingZone extends ActionAudit {
  
    letter: string;
  
    parkingSpots: ParkingSpot[];

}

export interface ParkingSpot extends ActionAudit {
  
    number: string;
  
    available?: boolean;

    rentable?: boolean;

}

export interface PaymentOptions extends ActionAudit {
  
    paymentStatus: PaymentStatus;
  
    startPeriod: string;
  
    endPeriod: string;

    userId: number;

    usercode: string;

}

export interface Reservation extends ActionAudit {
  
    reservationStatus: ReservationStatus;
  
    startTime: string;
  
    endTime: string;

    notes?: string;

    vehicle: Vehicle;

    parkingSpotId: number;

    parkingSpotCode: string;

}

export interface User extends ActionAudit {
  
    name: string;
  
    birthday: string;
  
    username: string;

    password: string;

    email: string;

    phoneNumber: string;
  
    address: string;

    userType: UserType;
}

export interface Vehicle extends ActionAudit {
  
    name: string;
  
    licencePlate: string;
  
    size: Size;

    user: User;
}