package jsonDB.db;

import java.util.ArrayList;
import java.util.List;

import jsonDB.validation.Table;

public class SimpleDB {

	public static Database db;

	public static void main(String[] args) {
		List<String> tableNames = new ArrayList<String>();
		String userFileName = null;
		List<String> queryFileNames = new ArrayList<String>();
		for ( int i = 0; i < args.length; i+=2) {
			String arg = args[i];
			
			if ( arg.equals( "-t")) {
				tableNames.add(args[i+1]);
			}
			else if ( arg.equals( "-u")) {
				if(userFileName == null)
					userFileName = args[i+1];
				else
					System.out.println("WARNING: Multiple user files detected. Ignoring...");
			}
			else if ( arg.equals( "-q")) {
				queryFileNames.add(args[i+1]);
			}
		}
		
		SimpleDB.db = new Database();
		
		// ReadSchemas		
		SimpleDB.db.setTableCatalog(ValidationInterface.readTables(tableNames));
		SimpleDB.db.setUserCatalog(ValidationInterface.readAndValidateUsers(userFileName));
		for (Table t : SimpleDB.db.getTableCatalog().values()) {

			if (ValidationInterface.validateData(t)) {
				System.out.println("Table " + t.getSchema().getName() + " has valid data");
			} else {
				System.out.println("Table " + t.getSchema().getName() + " has invalid data");
			}
		}
		
		// Run all the queries
		 for ( int i = 0; i < queryFileNames.size(); i++) {
			SimpleDB.db.runQuery(queryFileNames.get(i));
		}
		
		System.out.println();
	}

}
