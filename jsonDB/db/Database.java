package jsonDB.db;

import java.util.HashMap;
import java.util.Map;

import jsonDB.parser.Parser;
import jsonDB.relops.Iterator;
import jsonDB.validation.Query;
import jsonDB.validation.Table;
import jsonDB.validation.User;public class Database {
	
	private Map<String, Table> tableCatalog;
	private Map<String, User> userCatalog;
	
	/* Database Constructors */
	public Database()
	{
		tableCatalog = new HashMap<String, Table>();
		userCatalog = new HashMap<String, User>();
	}
	
	
	public void runQuery(String queryName)
	{
		System.out.println("\nAttempting to run query " + queryName + "...");
		Query q = Parser.parseQuery(queryName);
		if(ValidationInterface.validateQuery(q))
		{
			Iterator plan = Iterator.createPlan(q);
			plan.execute();
		}
		else
			System.out.println("Query " + queryName + " invalid");
	}
	
	/*
	 * Lookup specific Table
	 */	
	public Table getTable(String tableName)
	{
		return tableCatalog.get(tableName);
	}
	/*
	 * Lookup specific User given name of User
	 */
	public User getUser(String userName)
	{
		return userCatalog.get(userName);
	}

	/* Getters and setters */
	public Map<String, Table> getTableCatalog() {
		return tableCatalog;
	}
	
	public void setTableCatalog(Map<String, Table> tCat)
	{
		tableCatalog = tCat;
	}

	public Map<String, User> getUserCatalog() {
		return userCatalog;
	}
	
	public void setUserCatalog(Map<String, User> uCat)
	{
		userCatalog = uCat;
	}
	
}
