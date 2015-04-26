package inventory.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantityConstraintValidator implements ConstraintValidator<Quantity, Integer> {

	@Override
	public void initialize(Quantity qtyAnnotation) { }

	@Override
	public boolean isValid(Integer qtyField, ConstraintValidatorContext context) {
		if (qtyField == null)
			return false;
		return qtyField.toString().matches("[1-9]\\d{0,2}|1000");
	}

}
