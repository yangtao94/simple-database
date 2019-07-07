package jsonDB.validation;

import java.util.List;
import java.util.Map;

public class User {
	private String userName;
	private Map<String, List<String>> subSchemas;
	//What does the String represent here?
	// more than one subschemas for one user
	/*
	 * Retrieves the specific subschema for the given table name, or null if there is no
	 * subschema defined for that name.
	 */
	public List<String> getSubSchema(String tableName)
	{
		return subSchemas.get(tableName);
	}

	/* Constructor, getters and setters */
	
	public User()
	{
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, List<String>> getSubSchemas() {
		return subSchemas;
	}

	public void setSubSchemas(Map<String, List<String>> subSchemas) {
		this.subSchemas = subSchemas;
	}

}
