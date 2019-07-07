package jsonDB.validation;

import java.util.List;

public class Table {

	protected Schema schema;
	
	protected List<Constraint> constraints;	
	
	/* Constructor, getters and setters */
	
	public Table() {
		
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}
	
	
}
