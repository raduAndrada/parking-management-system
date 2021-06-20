package ro.upet.parking.system.management.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class RestUtil {

    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String asJsonString(final Object obj) {
        try {
            OBJECT_MAPPER.registerModule(new JSR310Module());
            OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
