package didproject;

import java.util.ArrayList;

public class MainClass {
	public static void main(String args[]) {
		// YAAAAAY!!!
		DBManager manager = new DBManager();
		Person castaway;
		String queryPrefix = "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
				+ "PREFIX : <http://dbpedia.org/resource/>"
				+ "PREFIX dbpedia2: <http://dbpedia.org/property/>"
				+ "PREFIX dbpedia: <http://dbpedia.org/>"
				+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
				+ "PREFIX dcterms: <http://purl.org/dc/terms/>"
				+ "PREFIX dbo: <http://dbpedia.org/ontology/>";

		// TODO read resources from database!

		ArrayList<String> castawayIDs = new ArrayList<String>();
		// get all persons in DB - to query for them
		castawayIDs = manager.getIds("castaway", "castawayID");

		for (int i = 0; i < 2; i++) {
			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getCastaway("castaway", castawayIDs.get(i));
			castaway = new Person(Integer.parseInt(fields.get(0)),
					fields.get(1), fields.get(2), fields.get(3), fields.get(4));

			String resource = castaway.createDBpediaLink(castaway.getLink());
			castaway.setDBlink(resource);

			String sparqlQueryForName = queryPrefix
					+ "SELECT ?name ?person WHERE {" + "<" + resource
					+ "> foaf:name ?name . " + "     ?person foaf:name ?name ."
					+ "}" + "ORDER BY ?name";

			String sparqlQueryForCategories = queryPrefix
					+ "SELECT ?subject WHERE {" + "<" + resource
					+ "> foaf:name ?name . " + "     ?person foaf:name ?name ."
					+ "     ?person dcterms:subject ?subject" + "}";

			SparqlQueryProcesser.getMetadata(sparqlQueryForName, castaway);
			
			if (castaway.isOnDBpedia()) {
				// if the resource exists then look for metadata in categories
				SparqlQueryProcesser.getCategories(sparqlQueryForCategories,
						castaway);

				// TODO 
				// for each category, look for it on DB. if exists, then add the
				
				// FK to classifiedIn with guest and category. If not, add the
				// category and then the FK
				for (int j=0;j<castaway.getCategories().size();j++) {
					int categoryID = manager.exists("category", "name", castaway.getCategories().get(j)); 
					if ( categoryID > 0) {
						
						// id exists in db, then add FK for "classifiedIn"
					manager.addClassifiedIn(castaway.getId(), categoryID);
					} else {
						// add category in the DB
						manager.addCategory(castaway.getCategories().get(j));
						if (manager.exists("category", "name", castaway.getCategories().get(j)) > 0) {
							manager.addClassifiedIn(castaway.getId(), manager.exists("category", "name", castaway.getCategories().get(j)));
						}						
					}
				}
				
				castaway.addDateOfBirthToDatabase(manager);
				// TODO: also, adjust the occupations so that they get separated by ";"
				// and get put in the
				// db accordingly, as the categories above.
				castaway.addOccupations(manager);
			}
		}



	}
}
