package inventory.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<Name, String> {

	private int annotationMin, annotationMax;
	
	@Override
	public void initialize(Name nameAnnotation) {
		this.annotationMin = nameAnnotation.min();
		this.annotationMax = nameAnnotation.max();
	}
	
	@Override
	public boolean isValid(String nameField, ConstraintValidatorContext context) {
		if (nameField != null 
				&& nameField.trim().length() >= annotationMin
				&& nameField.trim().length() <= annotationMax)
			return true;
		return false;
	}
	
}
