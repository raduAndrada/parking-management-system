package ro.upet.parking.system.management.rest.stripe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.upet.parking.system.management.business.api.user.UserService;
import ro.upet.parking.system.management.model.user.UserCreate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class UserCreateStripeService {

    private final StripeConfig config;

    private final UserService userService;

    public Customer createCustomer(final String email, final String name) {
        Stripe.apiKey = config.getSecret().getKey();
        Map<String, Object> params =Maps.newHashMap();
        params.put("description", "My First Test Customer (created for API docs)");
        params.put("name", name);
        params.put("email", email);

        try {
            Customer customer = Customer.create(params);
            userService.updateStripe(email, customer.getId());
            log.info("Successfully created customer: {}", customer);
            return customer;
        } catch (StripeException e) {
            log.info("Couldn't create Stripe customer for stripe email {}", email, e);
        }
        return null;
    }

    public Card createCard(final String customerId, final UserCreate userCreate) {
        Map<String, Object> retrieveParams = Maps.newHashMap();
        List<String> expandList = Lists.newArrayList();
        expandList.add("sources");
        retrieveParams.put("expand", expandList);
        Customer customer;
        try {
            customer = Customer.retrieve(
                    customerId,
                    retrieveParams,
                    null
            );
            Map<String, Object> sourceParams = Maps.newHashMap();
            sourceParams.put("object", "card");
            sourceParams.put("number", userCreate.getCreditCardNumber());
            sourceParams.put("exp_month", userCreate.getCreditCardExpMonth());
            sourceParams.put("exp_year", userCreate.getCreditCardExpYear());
            sourceParams.put("cvc", userCreate.getCreditCardCCV());
            sourceParams.put("name", customer.getName());
            Map<String, Object> params = new HashMap<>();
           // params.put("source", sourceParams);
            params.put("source", "tok_amex");
            Card card =
                    (Card) customer.getSources().create(params);
            log.info("Successfully created Stripe card: {}", card);
            return card;
        } catch (StripeException e) {
            log.info("Couldn't find customer for stripe id {}", customerId, e);
        }

        return null;
    }
}
