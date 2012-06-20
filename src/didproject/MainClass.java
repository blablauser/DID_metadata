package didproject;

import com.hp.hpl.jena.query.* ;
import java.io.*;
import com.hp.hpl.jena.rdf.model.*;

public class MainClass {
	public static void main (String args[]){
		  // YAAAAAY!!!
	    String sparqlQueryString1= "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
	    		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
	    		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
	    		"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
	    		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>" +
	    		"PREFIX dc: <http://purl.org/dc/elements/1.1/>" +
	    		"PREFIX : <http://dbpedia.org/resource/>" +
	    		"PREFIX dbpedia2: <http://dbpedia.org/property/>" +
	    		"PREFIX dbpedia: <http://dbpedia.org/>" +
	    		"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
	    		"PREFIX dbo: <http://dbpedia.org/ontology/>" +
	    		"SELECT ?name ?birth ?description ?person WHERE {" +
	    		"     <http://dbpedia.org/resource/Debbie_Harry> foaf:name ?name . " +
	    //		"     ?person foaf:name 'Debbie Harry'@en ." +
	    		"     ?person foaf:name ?name ." +
	    		"     ?person dbo:birthDate ?birth ." +
	    		"     ?person rdfs:comment ?description ." +
	    		"     FILTER (LANG(?description) = 'en') ." +
	    		"}" +
	    		"ORDER BY ?name";
	    
	    String emptyQuery= "<?xml version=\"1.0\"?>" +
	    		"<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">" +
	    		"<head>" +
	    		"<variable name=\"name\">" +
	    		"<variable name=\"birth\"/>" +
	    		"<variable name=\"description\"/>" +
	    		"<variable name=\"person\"/></head><results></results></sparql>";
	   
	    	      Query query = QueryFactory.create(sparqlQueryString1);
	    	      QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

	    	      ResultSet results = qexec.execSelect();
	    	      
	    	      //ResultSetFormatter.out(System.out, results, query);   
	    	      // or print XML ! :D
	    	      //if (results.getResultVars() == null)
	    	    	//	  System.out.println("!!! "+results.getRowNumber());
	    	      //else System.out.println("Has results");
	    	      
	    	     String xmlStr = ResultSetFormatter.asXMLString( results );
	    	     if (xmlStr.equals(emptyQuery)) System.out.println("!!! No results");
	    	      else System.out.println("Has results"); 
	    	      
	    	     qexec.close() ;
	    	     System.out.println(xmlStr.trim());
	    	     System.out.println(emptyQuery);
	}
}

