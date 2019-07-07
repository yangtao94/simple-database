package jsonDB.validation;

import java.util.List;

import jsonDB.data.Predicate;

public class Query {
	private String userName;
	private List<String> selectFields;
	private String fromTable;
	private List<List<Predicate>> wherePredicates;
	
	/* Constructor, getters and setters */
	
	public Query()
	{
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getSelectFields() {
		return selectFields;
	}

	public void setSelectFields(List<String> selectFields) {
		this.selectFields = selectFields;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public List<List<Predicate>> getWherePredicates() {
		return wherePredicates;
	}

	public void setWherePredicates(List<List<Predicate>> wherePredicates) {
		this.wherePredicates = wherePredicates;
	}
	
	
}
