package ro.upet.parking.system.management.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import ro.upet.parking.system.management.business.api.core.BusinessException;
import ro.upet.parking.system.management.business.api.vehicle.VehicleValidator;
import ro.upet.parking.system.management.business.vehicle.VehicleValidatorImpl;
import ro.upet.parking.system.management.data.impl.vehicle.VehicleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static ro.upet.parking.system.management.util.TestDataBuilder.buildVehicleEntity;


@RunWith(Parameterized.class)
class VehicleValidatorTest {

    private static final String VALID_LICENCE_PLATE_1 = "XX10YYY";
    private static final String VALID_LICENCE_PLATE_2 = "X10YYY";
    private static final String VALID_LICENCE_PLATE_3 = "FF99YYY";
    private static final String VALID_LICENCE_PLATE_4 = "GG08YYY";

    private static final String INVALID_LICENCE_PLATE_1 = "10YYY";
    private static final String INVALID_LICENCE_PLATE_2 = "-X10YYY";
    private static final String INVALID_LICENCE_PLATE_3 = "FF99YYYY";
    private static final String INVALID_LICENCE_PLATE_4 = "GG08Y";

    private VehicleValidator validator;

    @Mock
    VehicleRepository vehicleRepo;

    @BeforeEach
    void setUp() {
        openMocks(this);
        validator = new VehicleValidatorImpl(vehicleRepo);
    }

    @ParameterizedTest
    @ValueSource(strings = {VALID_LICENCE_PLATE_1, VALID_LICENCE_PLATE_2, VALID_LICENCE_PLATE_3, VALID_LICENCE_PLATE_4})
    void testValidateLicencePlate_success(final String arg) {
        when(vehicleRepo.findOptionalByLicencePlate(arg)).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> validator.validate(arg));
    }

    @ParameterizedTest
    @ValueSource(strings = {INVALID_LICENCE_PLATE_1, INVALID_LICENCE_PLATE_2, INVALID_LICENCE_PLATE_3, INVALID_LICENCE_PLATE_4})
    void testValidateLicencePlate_fail(final String arg) {
        when(vehicleRepo.findOptionalByLicencePlate(arg)).thenReturn(Optional.empty());
        assertDoesNotThrow(() ->  validator.validate(arg));
    }

    @Test
    void testValidate_failLicencePlateAlreadyExists() {
        when(vehicleRepo.findOptionalByLicencePlate(VALID_LICENCE_PLATE_1)).thenReturn(Optional.of(buildVehicleEntity(VALID_LICENCE_PLATE_1)));
        assertThrows(BusinessException.class, () -> validator.validate(VALID_LICENCE_PLATE_1));
    }
}
