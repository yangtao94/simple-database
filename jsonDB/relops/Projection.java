package jsonDB.relops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jsonDB.data.DataType;
import jsonDB.data.Field;
import jsonDB.data.Row;
import jsonDB.validation.Schema;

public class Projection extends Iterator {
	
	/*
	 * Constructor for class Projection. Given an underlying Iterator and a set of fields to project
	 * on, initialize the state of the Projection Iterator and set the schema field to the appropriate
	 * projected schema.
	 */
	//private static boolean opened = false;
	private  Iterator initial;
	//private static Iterator ok;
	private  List<String> pField;
	private  static  ArrayList<Integer> rowIndex;
	private  List<Row> validRows = new LinkedList<>();
	private int currIndex = 0;
	public Projection(Iterator child, List<String> projectFields) {
		//TODO
		//System.out.println("IM HERE LOL");

		pField = projectFields;
		initial = child;

		Schema s = project(child.getSchema(),projectFields);
		child.setSchema(s);


	}

	public void close() {
		//TODO
		initial.close();

	}
	
	public boolean isOpen() {
		//TODO
		return initial.isOpen();
	}

	public void reset() {
		//TODO
		initial.reset();
	}

	/*
	 * (non-Javadoc)
	 * @see jsonDB.relops.Iterator#hasNext()
	 * 
	 * Compute the next row returned by the underlying Iterator and project it so that it is under
	 * the proper projected Schema.
	 * 
	 * Return true if rows remain, false otherwise.
	 */
	public boolean hasNext() {
		//TODO
		while (initial.hasNext()) {
			//projecting
			boolean valid = true;
			Row r = initial.getNext();
			List<Object> values = r.getValues();
			List<Object> newValues = new LinkedList<>();
			for (int i = 0; i <values.size();i++) {
				if (rowIndex.contains(i)) {
					//only preserve rows that remain
					newValues.add(r.getColumn(i));
				}


			}
			//project
			if (newValues.isEmpty()) {
				valid = false;
			}
			if(valid)  {

				r.setValues(newValues);
				validRows.add(r);
				return true;
			}

		}


		if (validRows.isEmpty() || validRows == null || currIndex >= validRows.size()) return false;
		else return true;


	}

	
	public Row getNext() {
		//System.out.print("Projection: ");
		if(hasNext()) {
			//System.out.println(currIndex +"   "+validRows.get(currIndex));
			return validRows.get(currIndex++);


		}
		else  {
			//System.out.println(currIndex-1 +"   "+validRows.get(currIndex-1));
			return validRows.get(currIndex-1);
		}
	}
	
	/*
	 * Create a new Schema that contains only the columns that are specified in the list of 
	 * projected columns. This schema should not be stored permanently in the
	 * schema catalog and should only be used as a temporary schema for the output of a query.
	 * 
	 * It is the caller's responsibility to validate the given field names under this Schema.
	 */
	public static Schema project(Schema s, List<String> fieldNames)
	{
		//TODO
		//new schema, temporary
		Schema sch = new Schema();
		System.out.println("I only need these fields: "+ fieldNames.toString());
		List<Field> fields = new LinkedList<>();
		ArrayList<Integer> rows = new ArrayList<>();
		for (String field : fieldNames) {
			int index = s.getColumnNumber(field);
			if (index == -1) return null;
			rows.add(index);

			DataType type = s.getFieldType(index);
			Field  a = new Field();
			a.setFieldName(field);
			a.setType(type);
			fields.add(a);

		}
		System.out.println("The field indexes are: "+ Arrays.toString(rows.toArray()));

		sch.setName(s.getName());
		sch.setFields(fields);

		rowIndex = new ArrayList<>(rows);
		return sch;
	}
}
