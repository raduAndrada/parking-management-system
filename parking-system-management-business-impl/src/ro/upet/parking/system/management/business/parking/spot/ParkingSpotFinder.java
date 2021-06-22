package ro.upet.parking.system.management.business.parking.spot;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import ro.upet.parking.system.management.data.api.parking.spot.ParkingSpotEntity;
import ro.upet.parking.system.management.data.api.parking.zone.ParkingZoneEntity;
import ro.upet.parking.system.management.model.parking.level.ParkingLevel;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotLevelSortOptions;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotPreferenceOptions;
import ro.upet.parking.system.management.model.parking.spot.ParkingSpotZoneSortOptions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Andrada Utility class to find parking spots based on different
 * criteria
 */
@UtilityClass
public class ParkingSpotFinder {

    public static ParkingSpotEntity findParkingSpotForMembership(List<ParkingZoneEntity> parkingZoneEntityList) {
        parkingZoneEntityList = sortByZone(parkingZoneEntityList, ParkingSpotZoneSortOptions.ENTRY);
        final List<ParkingSpotEntity> parkingSpotList = parkingZoneEntityList.stream().findFirst().get().getParkingSpots();
        filterBySpotPreference(parkingSpotList, ParkingSpotPreferenceOptions.RENTABLE);
        return parkingSpotList.stream().findFirst().orElse(null);
    }

    private static void sortByLevel(final List<ParkingLevel> parkingLevelsList, final ParkingSpotLevelSortOptions pslso) {
        Collections.sort(parkingLevelsList, Comparator.comparing(ParkingLevel::getNumber));
        if (pslso.equals(ParkingSpotLevelSortOptions.TOP_DOWN)) {
            Collections.reverse(parkingLevelsList);
        }
    }

    private static List<ParkingZoneEntity> sortByZone(final List<ParkingZoneEntity> parkingZoneEntitysList, final ParkingSpotZoneSortOptions pszso) {
        if (Objects.nonNull(parkingZoneEntitysList)) {
            List<ParkingZoneEntity> sortedZones = Lists.newArrayList(parkingZoneEntitysList);
            Collections.sort(sortedZones, (pz1, pz2) -> pz1.getLetter().compareToIgnoreCase(pz2.getLetter()));
            if (pszso.equals(ParkingSpotZoneSortOptions.EXIT)) {
                Collections.reverse(sortedZones);
            }
            return sortedZones;
        }
        return parkingZoneEntitysList;
    }

    private static void filterBySpotPreference(List<ParkingSpotEntity> parkingSpotList, final ParkingSpotPreferenceOptions pspo) {
        if (pspo.equals(ParkingSpotPreferenceOptions.FREE)) {
            parkingSpotList = parkingSpotList.stream().filter(ParkingSpotEntity::isAvailable).collect(Collectors.toList());
        }
        if (pspo.equals(ParkingSpotPreferenceOptions.RENTABLE)) {
            parkingSpotList = parkingSpotList.stream().filter(ParkingSpotEntity::isRentable).collect(Collectors.toList());
        }
        if (pspo.equals(ParkingSpotPreferenceOptions.RENTED)) {
            parkingSpotList = parkingSpotList.stream().filter(ParkingSpotEntity::isRentable).collect(Collectors.toList());
        }
    }

}
