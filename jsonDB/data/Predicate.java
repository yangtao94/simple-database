package jsonDB.data;

import jsonDB.validation.Schema;

public class Predicate {

	private String fieldName;
	private Object rhs;
	private Op operator;
	
	/*
	 * Evaluates a Predicate with the given LHS data pulled from the row of the table.
	 * A value of null will always evaluate to true for any Predicate.
	 */
	public boolean evaluate(Schema s, Row row) throws IllegalArgumentException
	{
		int compare_val = 0;
		int col = s.getColumnNumber(fieldName);
		Object lhs = row.getColumn(col);
		//Comparisons on NULL always return true
		if(lhs == null)
			return true;
		//Validate type of LHS operand with RHS type and perform compareTo()
		switch(s.getFieldType(col))
		{
		case STRING:
			if(!(lhs instanceof String))
				throw new IllegalArgumentException("lhs not of type String");
			compare_val = ((String)lhs).compareTo((String)rhs);
			break;
		case INTEGER:
			if(!(lhs instanceof Integer))
				throw new IllegalArgumentException("lhs not of type Integer");
			compare_val = ((Integer)lhs).compareTo((Integer)rhs);
			break;
		case LONG:
			if(!(lhs instanceof Long))
				throw new IllegalArgumentException("lhs not of type Long");
			compare_val = ((Long)lhs).compareTo((Long)rhs);
			break;
		case FLOAT:
			if(!(lhs instanceof Float))
				throw new IllegalArgumentException("lhs not of type Float");
			compare_val = ((Float)lhs).compareTo((Float)rhs);
			break;
		case DOUBLE:
			if(!(lhs instanceof Double))
				throw new IllegalArgumentException("lhs not of type Double");
			compare_val = ((Double)lhs).compareTo((Double)rhs);
			break;
		case BOOLEAN:
			if(!(lhs instanceof Boolean))
				throw new IllegalArgumentException("lhs not of type Boolean");
			compare_val = ((Boolean)lhs).compareTo((Boolean)rhs);
			break;
		}
		//Return appropriate compareTo() comparison based on operator
		switch(operator) {
		case GT:
			return compare_val > 0;
		case GTE:
			return compare_val >= 0;
		case EQ:
			return compare_val == 0;
		case NE:
			return compare_val != 0;
		case LT:
			return compare_val < 0;
		case LTE:
			return compare_val <= 0;
		}
		return false;
	}
	
	/* Constructor, getters and setters */
	
	public Predicate()
	{
		
	}
	
	public String getFieldName()
	{
		return fieldName;
	}
	
	public void setFieldName(String fname)
	{
		fieldName = fname;
	}

	public Object getRhs() {
		return rhs;
	}

	public void setRhs(Object rhs) {
		this.rhs = rhs;
	}
	
	public Op getOperator()
	{
		return operator;
	}
	
	public void setOperator(Op operator) {
		this.operator = operator;
	}
	
}
