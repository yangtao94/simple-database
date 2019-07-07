package jsonDB.data;

import java.util.List;
import java.util.Map;

public class Field {
	private String fieldName;
	private DataType type;
	//private Map<String,String> fieldNames;
	
	/* Field Constructors */
	public Field()
	{
		
	}
	
	public Field(String fName, DataType t)
	{
		fieldName = fName;
		type = t;
	}
	
	/* Getters and setters */
	public String getFieldName()
	{
		return fieldName;
	}

	//public Map<String,String> getFieldNames() { return fieldNames; }

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}
	
	
}
