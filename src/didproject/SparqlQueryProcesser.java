package didproject;

import org.w3c.dom.*;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlQueryProcesser {
	public static void getMetadata(String queryString) {

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();

		String xmlStr = ResultSetFormatter.asXMLString(results);
		Document xmlFile = StringToXML.parse(xmlStr);

		if (xmlFile == null)
			System.out.println("NULL DOCUMENT ERROR.");
		else {
			NodeList resultList = xmlFile.getElementsByTagName("result");
			for (int i = 0; i < resultList.getLength(); i++) {
				// Get element
				Element element = (Element) resultList.item(i);
				NodeList bindingsList = element.getElementsByTagName("binding");

				for (int j = 0; j < bindingsList.getLength(); j++) {
					Element binding = (Element) bindingsList.item(j);
					String attribute = binding.getAttribute("name");

					if (attribute.equals("name")) {
						NodeList childList = binding.getElementsByTagName("literal").item(0).getChildNodes();
						Node child = childList.item(0);
						
						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();
						
						String value = child.getNodeValue();
						System.out.println(value);
						
					} else if (attribute.equals("birth")) {
						NodeList childList = binding.getElementsByTagName("literal").item(0).getChildNodes();
						Node child = childList.item(0);
						
						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();
						
						String value = child.getNodeValue();
						System.out.println(value);
					} else if (attribute.equals("person")) {
						NodeList childList = binding.getElementsByTagName("uri").item(0).getChildNodes();
						Node child = childList.item(0);
						
						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();
						
						String value = child.getNodeValue();
						System.out.println(value);
					} else if (attribute.equals("subject")) {
						NodeList childList = binding.getElementsByTagName("uri").item(0).getChildNodes();
						Node child = childList.item(0);
						
						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();
						
						String value = child.getNodeValue();
						System.out.println(value);
					}

				}
			}

		}

		qexec.close();
	}
	
	public static void getCategories(String queryString) {

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();

		String xmlStr = ResultSetFormatter.asXMLString(results);
		Document xmlFile = StringToXML.parse(xmlStr);

		if (xmlFile == null)
			System.out.println("NULL DOCUMENT ERROR.");
		else {
			NodeList resultList = xmlFile.getElementsByTagName("result");
			for (int i = 0; i < resultList.getLength(); i++) {
				// Get element
				Element element = (Element) resultList.item(i);
				NodeList bindingsList = element.getElementsByTagName("binding");

				for (int j = 0; j < bindingsList.getLength(); j++) {
					Element binding = (Element) bindingsList.item(j);
					String attribute = binding.getAttribute("name");
                   
					if (attribute.equals("subject")) {
						NodeList childList = binding.getElementsByTagName("uri").item(0).getChildNodes();
						Node child = childList.item(0);
						
						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();
						
						String value = child.getNodeValue();
						System.out.println(value);
					}

				}
			}

		}

		qexec.close();
	}
	// public static boolean checkAttribute(Element binding, String attribute,
	// String value, String tag){
	// if (attribute.equals(value)) {
	// Node literal = binding.getElementsByTagName(tag).item(0);
	// String output = literal.getNodeValue();
	// System.out.println(output);
	// return true;
	// }
	// return false;
	// }

	// public void
}
