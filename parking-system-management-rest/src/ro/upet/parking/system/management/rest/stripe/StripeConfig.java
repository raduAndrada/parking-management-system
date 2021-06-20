package ro.upet.parking.system.management.rest.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stripe")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class StripeConfig {

    private StripeKey secret;

    private StripeKey publishable;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class StripeKey{
    String key;
}