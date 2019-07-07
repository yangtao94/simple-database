package jsonDB.validation;

import jsonDB.data.Predicate;
import jsonDB.data.Row;

import java.util.List;


public class NotNullConstraintValidator implements ConstraintValidator{

	/*
	 * Constructor for class NotNull, given the Constraint and the
	 * Schema the constraint applies on. Initializes ConstraintValidator state.
	 */

	private List<Predicate> predicates;
	private int  fieldNumber;
	public NotNullConstraintValidator(Constraint constraint, Schema s) throws IllegalConstraintException {
		fieldNumber = s.getColumnNumber(constraint.getFieldName());
		predicates = constraint.getPredicates();

		//TODO

	}	
	
	/*
	 * Checks to see if the specified field in the given Row is not NULL. Returns true if
	 * it is not null, false otherwise.
	 * 
	 */
	public boolean validateConstraint(Row r) {
		//TODO
		if (r.getColumn(fieldNumber) != null) return true;

		else {
			System.out.println("NOT NULL??? IS NULL DUDE");
			return false;
		}
	}

}
