package jsonDB.validation;

import jsonDB.data.Row;

import java.util.*;


public class UniqueConstraintValidator implements ConstraintValidator{
	
	/*
	 * Constructor for class UniqueConstraintValidator, given the Constraint and the
	 * Schema the constraint applies on. Initializes ConstraintValidator state.
	 */
	private int fieldNumber;
	private  HashMap <Integer,ArrayList<Object>> checkUnique;

	public UniqueConstraintValidator(Constraint constraint, Schema s) throws IllegalConstraintException {
		//TODO
		//get specific field
		fieldNumber = s.getColumnNumber(constraint.getFieldName());
		checkUnique = new HashMap<>();
	}
	
	/*
	 * Checks to see if the given Row has a unique value in the field specified by the Constraint.
	 * Return true if this value is unique, false otherwise.
	 * 
	 * This is calculated lazily, and the first occurrence of a value will always be considered valid
	 * even if a duplicate value appears later. Only subsequent non-unique values should be considered
	 * to violate the constraint.
	 * 
	 * For example, given two Rows received in the following order:
	 * [1, "ABC"]
	 * [2, "ABC"]
	 * and a UNIQUE constraint on the second field, Row 1 would be accepted as valid, while Row 2
	 * would not.
	 */
	public boolean validateConstraint(Row r) {
		//TODO

		Object fieldCheck = r.getColumn(fieldNumber);

		if(!checkUnique.containsKey(fieldNumber)) {
			ArrayList<Object> numberList = new ArrayList<>();
			numberList.add(fieldCheck);
			checkUnique.put(fieldNumber,numberList);
			//System.out.println(checkUnique.get(fieldNumber));
			//System.out.println("PUT IT IN LOL");
			return true;
		}
		else if (checkUnique.get(fieldNumber).contains(fieldCheck)) {




			return false;
		}
		//does not exists, put into map
		else {
			checkUnique.get(fieldNumber).add(fieldCheck);
			return true;
		}

	}

}
