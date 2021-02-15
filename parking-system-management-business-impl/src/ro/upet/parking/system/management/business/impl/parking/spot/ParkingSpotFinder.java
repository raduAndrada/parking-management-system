package ro.upet.parking.system.management.business.impl.parking.spot;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpot;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotLevelSortOptions;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotPreferenceOptions;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotSearchCriteria;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotZoneSortOptions;
import ro.upet.parking.system.management.model.parking.zone.ParkingZone;

/**
 * @author Andrada Utility class to find parking spots based on different
 * criteria
 */
public class ParkingSpotFinder {

    public static ParkingSpot findParkingSpot(final ParkingSpotSearchCriteria pssc,
                                              final List<ParkingLevel> parkingLevelsList) {
        sortByLevel(parkingLevelsList, pssc.getParkingSpotLevelSortOptions());
        final List<ParkingZone> parkingZoneList = parkingLevelsList.stream().findFirst().get().getParkingZones();
        sortByZone(parkingZoneList, pssc.getParkingSpotZoneSortOptions());
        final List<ParkingSpot> parkingSpotList = parkingZoneList.stream().findFirst().get().getParkingSpots();
        filterBySpotPreference(parkingSpotList, pssc.getParkingSpotPreferenceOptions());
        return parkingSpotList.stream().findFirst().orElse(null);
    }

    public static ParkingSpot findParkingSpotForMembership(List<ParkingZone> parkingZoneList) {
        parkingZoneList = sortByZone(parkingZoneList, ParkingSpotZoneSortOptions.ENTRY);
        final List<ParkingSpot> parkingSpotList = parkingZoneList.stream().findFirst().get().getParkingSpots();
        filterBySpotPreference(parkingSpotList, ParkingSpotPreferenceOptions.RENTABLE);
        return parkingSpotList.stream().findFirst().orElse(null);
    }

    private static void sortByLevel(final List<ParkingLevel> parkingLevelsList, final ParkingSpotLevelSortOptions pslso) {
        Collections.sort(parkingLevelsList, (pl1, pl2) -> pl1.getNumber().compareTo(pl2.getNumber()));
        if (pslso.equals(ParkingSpotLevelSortOptions.TOP_DOWN)) {
            Collections.reverse(parkingLevelsList);
        }
    }

    private static List<ParkingZone> sortByZone(final List<ParkingZone> parkingZonesList, final ParkingSpotZoneSortOptions pszso) {
        if (Objects.nonNull(parkingZonesList)) {
            List<ParkingZone> sortedZones = Lists.newArrayList(parkingZonesList);
            Collections.sort(sortedZones, (pz1, pz2) -> pz1.getLetter().compareToIgnoreCase(pz2.getLetter()));
            if (pszso.equals(ParkingSpotZoneSortOptions.EXIT)) {
                Collections.reverse(sortedZones);
            }
            return sortedZones;
        }
        return  parkingZonesList;
    }

    private static void filterBySpotPreference(List<ParkingSpot> parkingSpotList, final ParkingSpotPreferenceOptions pspo) {
        if (pspo.equals(ParkingSpotPreferenceOptions.FREE)) {
            parkingSpotList = parkingSpotList.stream().filter(ParkingSpot::getAvailable).collect(Collectors.toList());
        }
        if (pspo.equals(ParkingSpotPreferenceOptions.RENTABLE)) {
            parkingSpotList = parkingSpotList.stream().filter(ParkingSpot::getRentable).collect(Collectors.toList());
        }
        if (pspo.equals(ParkingSpotPreferenceOptions.RENTED)) {
            parkingSpotList = parkingSpotList.stream().filter(ParkingSpot::getRentable).collect(Collectors.toList());
        }
    }

}
