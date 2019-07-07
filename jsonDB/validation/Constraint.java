package jsonDB.validation;

import java.util.List;

import jsonDB.data.Predicate;

public class Constraint {	
	public enum ConstraintType{
		NOT_NULL,
		UNIQUE,
		CHECK;
	}
	private String fieldName;
	private ConstraintType type;
	//Assume for simplicity that predicates for CHECK constraints are always AND - no OR allowed
	private List<Predicate> predicates;
	
	/*
	 * Constructs and returns a ConstraintValidator object of the type appropriate for the type
	 * of this Constraint
	 */
	public ConstraintValidator getConstraintValidator(Schema s){
		
		try {
			switch (type) {
			case NOT_NULL:
				return new NotNullConstraintValidator(this, s);			
			case UNIQUE:
				return new UniqueConstraintValidator(this, s);
			case CHECK:
				return new CheckConstraintValidator(this, s);
			}
		} catch(IllegalConstraintException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/* Default Constructor, getters and setters */
	
	public Constraint() {}
	
	
	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public ConstraintType getType() {
		return type;
	}


	public void setType(ConstraintType type) {
		this.type = type;
	}
	
	public List<Predicate> getPredicates() {
		return predicates;
	}
	
	public void setPredicates(List<Predicate> p)
	{
		predicates = p;
	}
}