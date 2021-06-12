/**
 * @author Andrada
 *
 */

@Value.Style(passAnnotations = { MapperIgnore.class, MapperNonNull.class },
		typeImmutable = "Imt*", typeModifiable = "Mdf*", depluralize = true, additionalJsonAnnotations = {
		JsonIgnore.class}, visibility = ImplementationVisibility.PUBLIC,  
		validationMethod = org.immutables.value.Value.Style.ValidationMethod.NONE, deepImmutablesDetection = true)
package ro.upet.parking.system.management.model;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ro.upet.parking.system.management.model.base.annotations.*;
	