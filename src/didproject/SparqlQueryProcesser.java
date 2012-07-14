package didproject;

import java.util.ArrayList;

import org.w3c.dom.*;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlQueryProcesser {
	public static void getMetadata(String queryString, Person castaway) {

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();

		String xmlStr = ResultSetFormatter.asXMLString(results);
		// System.out.println(xmlStr);
		Document xmlFile = StringToXML.parse(xmlStr);

		if (xmlFile == null)
			System.out.println("NULL DOCUMENT ERROR.");
		else {
			NodeList resultList = xmlFile.getElementsByTagName("result");
			if (resultList.getLength() == 0) {
				System.out.println("No such resourse on DBpedia.");
				// TODO Do something with these results - so that you'll know
				// further on!!!
				castaway.setOnDBpedia(false);
				System.out.println("Try the link:" + castaway.getDBlink());
			} else {
				castaway.setOnDBpedia(true);
				// for (int i = 0; i < resultList.getLength(); i++) {
				// Get element
				Element element = (Element) resultList.item(0);
				NodeList bindingsList = element.getElementsByTagName("binding");

				for (int j = 0; j < bindingsList.getLength(); j++) {
					Element binding = (Element) bindingsList.item(j);
					String attribute = binding.getAttribute("name");

					if (attribute.equals("name")) {
						NodeList childList = binding
								.getElementsByTagName("literal").item(0)
								.getChildNodes();
						Node child = childList.item(0);

						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();

						String name = child.getNodeValue();
						castaway.setName(name);
						// System.out.println(value);

					} else if (attribute.equals("page")) {
						NodeList childList = binding
								.getElementsByTagName("uri").item(0)
								.getChildNodes();
						Node child = childList.item(0);

						while (child.getNodeType() != Node.TEXT_NODE)
							child = child.getNextSibling();

						String key = child.getNodeValue();
						castaway.setKey(key);
						// System.out.println(personUri);
					}
				}
				// }
			}
		}

		qexec.close();
	}

	public static void getCategories(String queryString, Person castaway) {

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);

		ResultSet results = qexec.execSelect();

		String xmlStr = ResultSetFormatter.asXMLString(results);
		// System.out.println(xmlStr);
		Document xmlFile = StringToXML.parse(xmlStr);
		ArrayList<String> categoriesList = new ArrayList<String>();

		if (xmlFile == null)
			System.out.println("NULL DOCUMENT ERROR.");
		else {
			NodeList resultList = xmlFile.getElementsByTagName("result");
			if (resultList.getLength() == 0) {
				System.out.println("ERROR getting subjectOf.");
				// TODO Do something with these results - so that you'll know
				// further on!!!
				castaway.setOnDBpedia(false);
			} else {
				castaway.setOnDBpedia(true);

				for (int i = 0; i < resultList.getLength(); i++) {
					// Get element
					Element element = (Element) resultList.item(i);
					NodeList bindingsList = element
							.getElementsByTagName("binding");

					for (int j = 0; j < bindingsList.getLength(); j++) {
						Element binding = (Element) bindingsList.item(j);
						String attribute = binding.getAttribute("name");

						if (attribute.equals("subject")) {
							NodeList childList = binding
									.getElementsByTagName("uri").item(0)
									.getChildNodes();
							Node child = childList.item(0);

							while (child.getNodeType() != Node.TEXT_NODE)
								child = child.getNextSibling();

							String value = child.getNodeValue();
							// strip the heading of the results
							value = value
									.replaceAll(
											"http://dbpedia.org/resource/Category:",
											"");
							categoriesList.add(value);

							// get date of birth, if any:
							if (!value.endsWith("s_births")) {
								if (value.endsWith("_births")) {
									System.out.println(castaway.getId() + ":"
											+ castaway.getDBlink());
									System.out.println(value + " -> dob:"
											+ value.substring(0, 4));
									castaway.setDateOfBirth(value.substring(0,
											4));
								}
							}

						}
					}
				}
			}

			// add the array to the Person:
			castaway.setCategories(categoriesList);

		}

		qexec.close();
	}

	public static void getBoundArtistSongURIs(String queryStringDB) {

		Query queryDBpedia = QueryFactory.create(queryStringDB);
		//Query queryMusicBrainz = QueryFactory.create(queryStringMB);

		QueryExecution qexecDBpedia = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", queryDBpedia);
//		QueryExecution qexecMusicBrainz = QueryExecutionFactory.sparqlService(
//				"http://dbtune.org/musicbrainz/sparql", queryMusicBrainz);

		ResultSet resultsDBpedia = qexecDBpedia.execSelect();
//		ResultSet resultsMusicBrainz = qexecMusicBrainz.execSelect();

		String xmlStrDBpedia = ResultSetFormatter.asXMLString(resultsDBpedia);
//		String xmlStrMusicBrainz = ResultSetFormatter
//				.asXMLString(resultsMusicBrainz);

		System.out.println(xmlStrDBpedia);
//		System.out.println("-----------------");
//		System.out.println(xmlStrMusicBrainz);

		//Document xmlFile = StringToXML.parse(xmlStrDBpedia);

	}
}
