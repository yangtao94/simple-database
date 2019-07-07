package jsonDB.validation;

import jsonDB.data.Row;

public interface ConstraintValidator {
		
	/*
	 * Validates the Constraint conditions on the given Row. Returns true if valid,
	 * false otherwise.
	 */
	public boolean validateConstraint(Row r);
}
