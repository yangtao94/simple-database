package jsonDB.validation;


import jsonDB.data.Predicate;
import jsonDB.data.Row;

import java.util.List;


public class CheckConstraintValidator implements ConstraintValidator{

	/*
	 * Constructor for class CheckConstraintValidator, given the Constraint and the
	 * Schema the constraint applies on. Initializes ConstraintValidator state.
	 */
	private List<Predicate> predicates;
	private Schema schema;
	public CheckConstraintValidator(Constraint constraint, Schema s) throws IllegalConstraintException {
			predicates = constraint.getPredicates();
			schema = s;
	}
	
	/*
	 * Checks to see if the given Row satisfies the given CHECK constraint. Returns true if all
	 * predicates are satisfied, otherwise returns false (assume for simplicity that no OR
	 * boolean operators are allowed in CHECK Constraints - all Predicates must be connected
	 * with AND).
	 */
	public boolean validateConstraint(Row r) {
		// TODO

		for (Predicate p : predicates) {

			if (!p.evaluate(schema,r)) {
				System.out.println("PREDICATES NOT SATISIFIED");
				return false;
			}


		}
		return true;
	}

	
}
