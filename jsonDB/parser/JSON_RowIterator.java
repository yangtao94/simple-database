package jsonDB.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON_RowIterator {
	
	List<List<String>> data;
	String filepath = null;
	int currIndex;
	
	public JSON_RowIterator()
	{
		
	}
	
	public JSON_RowIterator(String filePath)
	{		
		this.filepath = filePath;		
				
	}
	
	public void open()
	{		
		ObjectMapper mapper = new ObjectMapper();		
		try {
			FileInputStream is = new FileInputStream(Parser.pathPrefix + filepath + Parser.dataPathSuffix);
			data = mapper.readValue(is, new TypeReference<List<List<String>>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		currIndex = 0;
	}
	
	public void close()
	{
		data = null;
	}
	
	public boolean hasNext()
	{
		if (data == null || currIndex >= data.size())
			return false;
		else
			return true;
	}
	
	public List<String> getNext()
	{
		return data.get(currIndex++);		
	}

}
