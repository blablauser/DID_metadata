package didproject;


public class MainClass {
	public static void main (String args[]){
		  // YAAAAAY!!!
		String resource = "<http://dbpedia.org/resource/Debbie_Harry>";
	    
		String sparqlQueryString1= 
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
	    		
	    		"SELECT ?name ?birth ?description ?person ?subject WHERE {" +
	    		resource +" foaf:name ?name . " +
	    		"     ?person foaf:name ?name ." +
	    		"     ?person dbo:birthDate ?birth ." +
	    		"     ?person rdfs:comment ?description ." +
	    		"     ?person dcterms:subject ?subject" +
	    		"     FILTER (LANG(?description) = 'en') ." +
	    		"}" +
	    		"ORDER BY ?name";
	    
//	    String emptyQuery= "<?xml version=\"1.0\"?>" +
//	    		"<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">" +
//	    		"<head>" +
//	    		"<variable name=\"name\">" +
//	    		"<variable name=\"birth\"/>" +
//	    		"<variable name=\"description\"/>" +
//	    		"<variable name=\"person\"/></head><results></results></sparql>";
//	    
	    SparqlQueryProcesser.runQuery(sparqlQueryString1);

	    	     
//	    	     if (xmlStr.equals(emptyQuery)) System.out.println("!!! No results");
//	    	      else System.out.println("Has results"); 
	    	      
	    
	    	     //System.out.println(xmlStr.trim());
	    	     //System.out.println(emptyQuery);
	}
}

