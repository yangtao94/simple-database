package jsonDB.validation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jsonDB.data.DataType;
import jsonDB.data.Field;

public class Schema {

	private String name;
	private List<Field> fields;	
	
	/*
	 * Constructor for class Schema. Creates a temporary Schema definition with no name and
	 * with the provided set of fields. For use with the Projection operator.
	 */
	public Schema(List<Field> s_fields)
	{
		name = "";
		fields = s_fields;
	}
	
	/*
	 * Get column number of field with given field name, or return -1 if field name is not present
	 * in schema
	 */
	public int getColumnNumber(String fieldName)
	{
		for(int col = 0; col < fields.size(); col++)
		{
			if(fields.get(col).getFieldName().equalsIgnoreCase(fieldName))
				return col;
		}
		return -1;
	}
	
	/*
	 * Get field name of given column.
	 * It is the caller's responsibility to ensure that the column number is within the range
	 * of the number of fields in the Schema.
	 */
	public String getFieldName(int col)
	{
		return fields.get(col).getFieldName();
	}

	public Map<String,Integer> getFieldNames() {
		Map<String,Integer> fieldNames = new HashMap<>();
		for (int col = 0; col < fields.size(); col++) {

			fieldNames.put(fields.get(col).getFieldName(),col);
		}

		return  fieldNames;

	}
	/*
	 * Get data type of given column.
	 * It is the caller's responsibility to ensure that the column number is within the range
	 * of the number of fields in the Schema.
	 */
	public DataType getFieldType(int colNum)
	{
		return fields.get(colNum).getType();
	}
	
	/* Default constructor, getters and setters */
	
	public Schema()
	{
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}

	public List<Field> getFields() {
		return fields;
	}
	
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public String toString()
	{
		String out = "";
		for(Field f : fields)
		{
			out = out + f.getFieldName() + " ";
		}
		out = out + "\n";
		int separator_length = out.length() - (name + ":\n").length();
		for(int i = 0; i < separator_length; i++)
		{
			out = out + "-";
		}
		return out;
	}

}
