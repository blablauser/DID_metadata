package didproject;

import org.w3c.dom.Document;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlQueryProcesser {
	public static void runQuery(String queryString) {

	      Query query = QueryFactory.create(queryString);
	      QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

	      ResultSet results = qexec.execSelect();
	      
	     String xmlStr = ResultSetFormatter.asXMLString(results );
	     Document xmlFile = StringToXML.parse(xmlStr);
	     
	     if (xmlFile == null) System.out.println("Doesn't work.");
	     else  System.out.println("YAY!.");
	      
	     qexec.close() ;
	     //System.out.println(xmlStr.trim());
	     //System.out.println(emptyQuery);
	}
}
