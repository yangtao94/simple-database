package jsonDB.db;

import jsonDB.data.DataType;
import jsonDB.data.Field;
import jsonDB.parser.JSON_RowIterator;
import jsonDB.validation.*;


import java.util.*;

import jsonDB.data.Row;
import jsonDB.validation.Table;
import jsonDB.validation.Constraint;
import static jsonDB.parser.Parser.parseTable;
import static jsonDB.parser.Parser.parseUsers;

public class ValidationInterface {


	/*
	 * Given a list of Table Names, use the Parser to read them into memory. The result
	 * should be a Map{Table Name => Table}. You may assume that a table name uniquely identifies
	 * a single table - there will be no duplicate names.
	 */
	public static Map<String, Table> readTables(List<String> tableNames) {
		//TODO
		Map<String,Table> tableList = new HashMap<>();

		for (String tableName : tableNames) {
			Table hi = parseTable(tableName);
			tableList.put(tableName,hi);


		}

		return tableList;
	}

	/*
	 * Given the name of a file containing the User information, use the Parser to read them into
	 * memory. Invalid Users should be discarded. The result should be a Map{User Name => User}.
	 * 
	 * A User is considered to be invalid if either have a subschema defined for a table that
	 * does not exist in the database, or if they have fields listed in a subschema that are not
	 * part of the table for that subschema.
	 * 
	 * You may assume that a user name uniquely identifies a single user - there will be no duplicates.
	 */
	public static Map<String, User> readAndValidateUsers(String userFile) {
		//TODO
		Map<String,User> usersList = new HashMap<>();

		//retrieve from Database

		Map<String,Table> tableList = SimpleDB.db.getTableCatalog();

		List<User> users = parseUsers(userFile);

		for (User user : users) {
				String username = user.getUserName();
				boolean isNull = true;


			for (String tableName : tableList.keySet()) {
				List<String> subSchema = user.getSubSchema(tableName);
				if (subSchema != null) {
					isNull = false;
					boolean valid = true;
					Map<String, Integer> tableSchema = tableList.get(tableName).getSchema().getFieldNames();
					//test for fields listed in a subschema that are not part of the table for that subschema
					for (String field : subSchema) {
						//if field does not exist in the schema , then it is invalid
						if (!tableSchema.containsKey(field))
							valid = false;
					}
					if (valid) usersList.put(username, user);
					break;
				}
			}
			if(isNull) {
				//discard invalid user / do nothing
			}

		}


		return usersList;
	}

	/*
	 * Given a Schema and the raw row data for a Row (given as a List of Strings, provided from the
	 * JSON_RowIterator.getNext() method call), validate the given row data on the schema.
	 * If the row data is valid, create and return a Row object of that data (make sure you 
	 * cast each value to the proper type). If the Row is invalid, throw an IllegalArgumentException.
	 * 
	 * A Row is valid iff the data types of all of its values match the data types defined in the
	 * Schema. The row must include exactly the same number of fields as the Schema, and the order
	 * of the values cannot be changed.
	 * 
	 * REMEMBER TO HANDLE NULL VALUES - as long as the constraints allow NULL values the Row should
	 * be accepted as valid, but remember that you have to handle these values when converting to
	 * the Row. The provided raw row_data from the Parser will store these as the null value - it
	 * will not be a String representation. The returned Row object should also store the null data
	 * as the null value.
	 * 
	 * For example, for the Schema:
	 * Student(puid INTEGER, name STRING, gpa FLOAT)
	 * 
	 * Valid row: ["4", "VALID", "3.7"]
	 * Invalid row: ["3.2", "INVALID", "4"]
	 */
	public static Row readAndValidateRow(Schema s, List<String> row_data) throws IllegalArgumentException {
		// TODO
		int fieldSize = s.getFields().size();

		if (row_data.size() != fieldSize) throw  new IllegalArgumentException();
		Row validRow = new Row();
		List<Object> valueStorage = new LinkedList<>();

		for (int i = 0; i < row_data.size(); i++) {
			DataType type = s.getFieldType(i);
			if (row_data.get(i) == null) {
				valueStorage.add(null);

			} else {

				String data = row_data.get(i);
				//DataType currentType;
				//boolean valid = false;
				switch (type) {
					case STRING:
						valueStorage.add(data);
						//currentType = DataType.STRING;
						break; //always valid
					case INTEGER:
						try {

							int hi = Integer.parseInt(data);
							valueStorage.add(hi);
							//currentType = DataType.INTEGER;

						} catch (Exception e) {
							//invalid
							throw new IllegalArgumentException();
						}
						break;
					case LONG:
						try {
							long hi = Long.parseLong(data);
							valueStorage.add(hi);
							//currentType = DataType.LONG;
						} catch (Exception e) {
							//invalid
							throw new IllegalArgumentException();
						}

						break;
					case FLOAT:
						try {
							float hi = Float.parseFloat(data);
							valueStorage.add(hi);
							//currentType = DataType.FLOAT;
						} catch (Exception e) {
							throw new IllegalArgumentException();
							//invalid
						}

						break;
					case DOUBLE:
						try {
							 double hi = Double.parseDouble(data);
							valueStorage.add(hi);
							//currentType = DataType.DOUBLE;
						} catch (Exception e) {
							throw new IllegalArgumentException(); // invalid
						}
						break;
					case BOOLEAN:
						boolean hi = Boolean.parseBoolean(data);
						if (hi) {
							valueStorage.add(hi);
							//currentType = DataType.BOOLEAN;
						} else throw new IllegalArgumentException();

						break;
					default:    //wont happen since it is from existing schema
						break;

				}


			}
		}
		//add in the entire row
		validRow.setValues(valueStorage);

		return validRow;
	}
	
	/*
	 * Given a List of ConstraintValidator objects and a Row, validate the Row against all of the
	 * constraints. Return true if the row satisfies all constraints, false otherwise.
	 */
	public static boolean validateConstraints(List<ConstraintValidator> cvs, Row r)
	{
		for(ConstraintValidator cv : cvs)
		{
			if(!cv.validateConstraint(r))
				return false;
		}
		return true;
	}

	/*
	 * Given a Table, use the JSON_RowIterator to iterate through the row data. Validate each row
	 * based on the Schema and constraints - the row must be valid on the Schema of the given Table,
	 * and must satisfy all constraints that exist on the Table.
	 * 
	 * For every invalid row, print an error message to System.out specifying whether the error was
	 * due to the row being invalid for the Schema or invalid on the constraints of the Table.
	 * 
	 * Return true if all data is valid - otherwise return false.
	 */
	public static boolean validateData(Table table) {
		//TODO
		Schema s = table.getSchema();
		String fileName = s.getName();
		boolean invalid = true;


		List <ConstraintValidator> cvs = new LinkedList<>();
		for (Constraint k : table.getConstraints()) {
			//add type with schema
			cvs.add(k.getConstraintValidator(s));



		}
		//initialize JSON_RowIterator
		JSON_RowIterator rowIterator = new JSON_RowIterator(fileName);
		rowIterator.open();

		while (rowIterator.hasNext()) {
			List<String> rowData = rowIterator.getNext();

			try {
				Row r = readAndValidateRow(s,rowData);
				System.out.println(r.toString());

				if (!validateConstraints(cvs,r)) {
					System.out.println("Row does not satisfy constraints");
					invalid = false;
				}


			} catch (IllegalArgumentException e) {
				System.out.println("Row is invalid for the Schema");
				invalid = false;
			}




		}
		rowIterator.close();
		//entire table passes with no problem
		return invalid;
	}

	/*
	 * Given a Query, validate the User's access rights and whether the fields/schemas accessed
	 * in the Query are valid.
	 * 
	 * A Query is valid if the User has permission to access all fields accessed by the query (i.e.
	 * those schemas/fields are present in the User's set of subschemas) and if the schemas/fields
	 * accessed by the query actually exist in the database.
	 * 
	 * Return true if the Query is valid, false otherwise.
	 */
	public static boolean validateQuery(Query q) {
		//TODO
		//retrieve users

		User queryUser = SimpleDB.db.getUser(q.getUserName());

		if (queryUser == null) return false;

		//check if fields/schemas accessed in the Query are valid
		List<String> selectFields = q.getSelectFields();
		List<String> existingFields = queryUser.getSubSchema(q.getFromTable());

		//no access to the table / schema does not exists
		if(existingFields == null) return  false;

		//query requires  fields than the User has no access to
		else if (!existingFields.containsAll(selectFields)) return false;


		return true;
	}
}
