package jsonDB.relops;

import java.util.LinkedList;
import java.util.List;

import jsonDB.data.Predicate;
import jsonDB.data.Row;
import jsonDB.validation.Schema;

public class Selection extends Iterator {
	
	/*
	 * Constructor for class Selection. Initialize state for Selection Iterator.
	 * 
	 * Predicates are implemented in Conjunctive Normal Form, with each separate clause of
	//the CNF being represented by one Selection Iterator.
	//i.e. Implement AND with separate Selection Operators chained together. Implement OR by passing
	//all ORed predicates in a CNF clause into one Selection Operator.
	 */
	private   Iterator ok;
	private   List<Predicate> predicate;
	private  List<Row> validRows = new LinkedList<>();
	private int currIndex = 0;
	public Selection(Iterator child, List<Predicate> predicates) {
		//TODO
		//System.out.println("I'm in");
		//System.out.println(child.getSchema().toString());

		ok = child;
		//ok.setSchema(child.getSchema());
		predicate = predicates;

	}

	public void close() {
		//TODO
		ok.close();
	}
	
	public boolean isOpen() {
		//TODO
		return ok.isOpen();
	}
	
	public void reset() {
		ok.reset();
	}

	/*
	 * (non-Javadoc)
	 * @see jsonDB.relops.Iterator#hasNext()
	 * 
	 * Compute the next row from the underlying Iterator that satisfies the provided Predicates.
	 * All Predicates within a single Selection operator should be ORed together - if the row
	 * satisfies at least one Predicate than the row is accepted as a valid result. Implement AND
	 * by chaining together two (or more) Selection Iterators.
	 */
	public boolean hasNext() {
		//TODO
		while (ok.hasNext()) {
			Row r = ok.getNext();
			Schema s = ok.getSchema();

			//test against predicates
			boolean anyTrue = false;
			for (Predicate p : predicate) {
				//if any is true
				if (p.evaluate(s,r)) {

					anyTrue = true;
				}

			}
			if (anyTrue) {
				validRows.add(r);


			}

		}
		System.out.println("valid rows"+ validRows.toString());
		if (validRows.isEmpty() || validRows == null || currIndex >= validRows.size()) return false;
		else return true;


	}

	
	public Row getNext() {
		System.out.print("Selection: ");
		if (hasNext()) {
			System.out.println(currIndex +"   "+validRows.get(currIndex));
			return validRows.get(currIndex++);

		}

		else {
			System.out.println(currIndex-1 +"   "+validRows.get(currIndex-1));
			return validRows.get(currIndex-1);
		}
	}

}
