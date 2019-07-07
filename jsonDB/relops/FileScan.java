package jsonDB.relops;

import jsonDB.data.Row;
import jsonDB.db.ValidationInterface;
import jsonDB.parser.JSON_RowIterator;
import jsonDB.validation.Constraint;
import jsonDB.validation.ConstraintValidator;
import jsonDB.validation.Schema;
import jsonDB.validation.Table;

import java.util.LinkedList;
import java.util.List;

public class FileScan extends Iterator {


	private  JSON_RowIterator ok;
	private   List<Constraint> constraints;
	private  String schemaFilePath;
	private  Schema s;
	private  List<Row> Rows = new LinkedList<>();
	private int currIndex = 0;
	private int called = 0;

	private static boolean opened = false;

	/*
	 * Constructor for class FileScan. Given a Table, initialize the Iterator schema field.
	 * Create and open a new JSON_RowIterator() for the given Table data.
	 * 
	 * Initialize the state of the Iterator and the set of ConstraintValidators
	 */
	public FileScan(Table table) {
		//initialize the JSON_RowIterator
		s = table.getSchema();
		schemaFilePath = table.getSchema().getName();
		ok = new JSON_RowIterator(schemaFilePath);
		ok.open();
		opened = true;


		setSchema(table.getSchema());
		constraints = table.getConstraints();
		//TODO

	}

	public void close() {
		//TODO
		ok.close();
		opened = false;
	}

	public boolean isOpen() {
		//TODO
		return opened;
	}

	public void reset() {
		//TODO

		ok = new JSON_RowIterator(schemaFilePath);
		ok.open();
		opened = true;
	}

	/*
	 * (non-Javadoc)
	 * @see jsonDB.relops.Iterator#hasNext()
	 * 
	 * Compute the next valid row read from the underlying JSON_RowIterator on the table data.
	 * A row should be validated on schema as well as on whether it satisfies the constraints of the
	 * table. If a row is not valid, it should be skipped by the Iterator and the hasNext() function
	 * should continue attempting to find the next valid row.
	 * 
	 * Return true if a valid row has been found, else return false.
	 */
	public boolean hasNext() throws IllegalStateException, IllegalArgumentException {
		//TODO

		//no more rows/tuples remain
		while (ok.hasNext()) {
			boolean valid = true;

			List<String> row_data = ok.getNext();

			List<ConstraintValidator> cvs = new LinkedList<>();


			for (Constraint k : constraints) {
				//add type with schema
				cvs.add(k.getConstraintValidator(s));

			}

			try {
				Row r = ValidationInterface.readAndValidateRow(s, row_data);

				if (!ValidationInterface.validateConstraints(cvs, r)) {
					//skip row

					valid = false;
				}
				if (valid) Rows.add(r);


			} catch (Exception e) {
				//skip row

			}

		}

		if (Rows.isEmpty() || Rows == null || currIndex >= Rows.size()) return false;
		else return true;

		//valid row has been found, return false if no more valid rows left


	}

	public Row getNext() {
		//TODO
		//check for validity
		System.out.print("FileScan: ");
		if (hasNext()) {

			System.out.println(currIndex + "  "+Rows.get(currIndex));
			return (Rows.get(currIndex++));
		}
		else {
			System.out.println(currIndex-1 + "  "+Rows.get(currIndex-1));
			return Rows.get(currIndex-1);
		}
	}
}
