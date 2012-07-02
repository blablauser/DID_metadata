package didproject;


public class MainClass {
	public static void main (String args[]){
		  // YAAAAAY!!!
		
		//TODO read resources from database!
		
		// maybe add ID as well ? .. 
		
		Person castaway = new Person("name","http://en.wikipedia.org/wiki/Debbie_Harry");
		
	    String resource = castaway.createDBpediaLink(castaway.getLink());
	    
		String sparqlQueryForName= 
	    	    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
	    		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
	    		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
	    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	    		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
	    		"PREFIX dc: <http://purl.org/dc/elements/1.1/>" +
	    		"PREFIX : <http://dbpedia.org/resource/>" +
	    		"PREFIX dbpedia2: <http://dbpedia.org/property/>" +
	    		"PREFIX dbpedia: <http://dbpedia.org/>" +
	    		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
	    		"PREFIX dcterms: <http://purl.org/dc/terms/>" +
	    		"PREFIX dbo: <http://dbpedia.org/ontology/>" +
	    		
	    		"SELECT ?name ?birth ?person WHERE {" +
	    		"<"+resource+"> foaf:name ?name . " +
	    		"     ?person foaf:name ?name ." +
	    		"     ?person dbo:birthDate ?birth ." +
	    		"}" +
	    		"ORDER BY ?name";
		
		String sparqlQueryForCategories= 
    	    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
    		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
    		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
    		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
    		"PREFIX dc: <http://purl.org/dc/elements/1.1/>" +
    		"PREFIX : <http://dbpedia.org/resource/>" +
    		"PREFIX dbpedia2: <http://dbpedia.org/property/>" +
    		"PREFIX dbpedia: <http://dbpedia.org/>" +
    		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
    		"PREFIX dcterms: <http://purl.org/dc/terms/>" +
    		"PREFIX dbo: <http://dbpedia.org/ontology/>" +
    		
    		"SELECT ?subject WHERE {" +
    		"<"+resource+"> foaf:name ?name . " +
    		"     ?person foaf:name ?name ." +
    		"     ?person dcterms:subject ?subject" +
    		"}";
	    
		SparqlQueryProcesser.getMetadata(sparqlQueryForName, castaway);
	    SparqlQueryProcesser.getCategories(sparqlQueryForCategories, castaway);

	    System.out.println(castaway.getDateOfBirth()+" occupations: "+castaway.getCategories().get(4));
	    
	    //TODO adjust the DB accordingly: add the categories to the DB (if they were not there already) 
	    //and assign the person to the right categories "classifiedIn" table!!!
	    
	    //TODO: also, adjust the occupations so that they get separated by ";" and get put in the 
	    //db accordingly, as the categories above.

	}
}

