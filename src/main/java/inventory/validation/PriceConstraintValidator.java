package inventory.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceConstraintValidator implements ConstraintValidator<Price, BigDecimal> {

	@Override
	public void initialize(Price constraintAnnotation) { }

	@Override
	public boolean isValid(BigDecimal priceField, ConstraintValidatorContext context) {
		if (priceField == null)
			return false;
		return priceField.toString().matches("0\\.0[1-9]|0\\.[1-9](\\d)?|[1-9]\\d{0,3}(\\.\\d{1,2})?");
	}

}
