package ro.upet.parking.system.management.business.api.core;

import java.math.BigDecimal;

@FunctionalInterface
public interface CostCalculator {

	public BigDecimal comupteCost();
}
