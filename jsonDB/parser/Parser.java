package jsonDB.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jsonDB.validation.Query;
import jsonDB.validation.Table;
import jsonDB.validation.User;

public class Parser {
	
	public static final String pathPrefix = "src/resources/";
	public static final String schemaPathSuffix = ".json";
	public static final String dataPathSuffix = "Data.json";
	
	//Parse JSON and return a Table Schema for the given Schema JSON file
	public static Table parseTable(String tableName)
	{

		Table table = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			FileInputStream is = new FileInputStream(pathPrefix + tableName + schemaPathSuffix);
			table = mapper.readValue(is, Table.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		return table;
	}
	
	//Parse JSON and return a List of User objects from the given JSON file
	public static List<User> parseUsers(String filename)
	{
		User[] users= null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			FileInputStream is = new FileInputStream(pathPrefix + filename + schemaPathSuffix);
			users = mapper.readValue(is, User[].class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Arrays.asList(users);
	}
	
	//Parse JSON and return a Query (one query per file for simplicity)
	public static Query parseQuery(String filename)
	{
		Query query = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			FileInputStream is = new FileInputStream(pathPrefix + filename + schemaPathSuffix);
			query = mapper.readValue(is, Query.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return query;
	}
		
}
