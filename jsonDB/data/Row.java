package jsonDB.data;

import java.util.List;

public class Row {

	private List<Object> values;
	

	/*
	 * Get the Object stored in the given column
	 */
	public Object getColumn(int col) {
		return values.get(col);
	}
	
	/* Constructor, getters and setters */
	public Row()
	{
		
	}
	
	public List<Object> getValues() {
		return values;
	}
	
	public void setValues(List<Object> values) {
		this.values = values;
	}
	
	public String toString()
	{
		String out = "";
		for(Object val : values)
		{
			if(val == null)
				out = out + "null" + " ";
			else
				out = out +  val.toString() + " ";
		}
		return out;
	}
}
