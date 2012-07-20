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

	public static void getBoundArtistSongURIs(String queryStringDB,
			Record record) {

		try {

			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					System.out.println("ERROR getting bound Resources.");
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
					record.setBound(0);
				} else {
					record.setBound(1);

					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("song")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String songURI = child.getNodeValue();
								record.setSongURI(songURI);

							} else if (attribute.equals("artist")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String artistURI = child.getNodeValue();
								record.setArtistURI(artistURI);
								// System.out.println(personUri);
							}
						}
					}
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getIndividualSong(String queryStringDB, Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {

					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					record.setBound(2);

					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("song")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String songURI = child.getNodeValue();
								record.setSongURI(songURI);

							}
						}
					}
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getIndividualArtist(String queryStringDB, Record record) {

		try {

			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					System.out
							.println("ERROR getting individual getIndividualArtist.");
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					if (record.getBound() == 0)
						record.setBound(3);
					else if (record.getBound() == 2)
						record.setBound(4);
					else if (record.getBound() == 5)
						record.setBound(11);

					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("artist")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String artistURI = child.getNodeValue();
								record.setArtistURI(artistURI);
							}
						}
					}
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getIndividualClassicalSong(String queryStringDB,
			Record record) {

		try {

			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {

					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					record.setBound(5);
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("song")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String songURI = child.getNodeValue();
								record.setSongURI(songURI);

							} else if (attribute.equals("genre")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String genre = child.getNodeValue();
								// the Yago string!!!
								genre = genre.replaceAll(
										"http://dbpedia.org/class/yago/", "");
								genre = genre.substring(0, genre.length() - 9);
								ArrayList<String> genreList = new ArrayList<String>();
								genreList.add(genre);
								record.setGenreList(genreList);
							}
						}
					}
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getIndividualClassicalArtist(String queryStringDB,
			Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
		
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					if (record.getBound() == 0)
						record.setBound(6);
					else if (record.getBound() == 2)
						record.setBound(11);
					else if (record.getBound() == 5)
						record.setBound(7);

					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("artist")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String artistURI = child.getNodeValue();
								record.setArtistURI(artistURI);

							}
						}
					}
					if (record.getGenreList().size() == 0) {

						ArrayList<String> genre = new ArrayList<String>();
						genre.add("ClassicalMusic");
						record.setGenreList(genre);
					}
				}
			}
			qexec.close();
			record.setTimed_out(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getIndividualModernClassicalArtist(String queryStringDB,
			Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					System.out
							.println("ERROR getting individual getIndividualModernClassicalArtist.");
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					if (record.getBound() == 0)
						record.setBound(6);
					else if (record.getBound() == 2)
						record.setBound(11);
					else if (record.getBound() == 5)
						record.setBound(7);

					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("artist")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String artistURI = child.getNodeValue();
								record.setArtistURI(artistURI);

							}
						}
					}
					if (record.getGenreList().size() == 0) {

						ArrayList<String> genre = new ArrayList<String>();
						genre.add("Classical_Composers_general");
						record.setGenreList(genre);
					}
				}
			}
			qexec.close();
			record.setTimed_out(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getAnyArtist(String queryStringDB, Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("artist")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String artistURI = child.getNodeValue();
								if (artistURI
										.contains("http://dbpedia.org/resource/")) {
									record.setArtistURI(artistURI);
									if (record.getBound() == 8)
										record.setBound(10);
									else record.setBound(9);
								}
							}
						}
					}
				}
			}
			qexec.close();
			record.setTimed_out(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getAnySong(String queryStringDB, Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {

					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("song")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String songURI = child.getNodeValue();
								if (songURI
										.contains("http://dbpedia.org/resource/")) {
									record.setSongURI(songURI);
									record.setBound(8);
								}
							}
						}
					}
				}
			}
			qexec.close();
			record.setTimed_out(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getReleaseDate(String queryStringDB, Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);

			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					System.out.println("NO getReleaseDate.");
					
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("releaseDate")) {
								NodeList childList = binding
										.getElementsByTagName("literal")
										.item(0).getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String releaseDate = child.getNodeValue();
								record.setReleasedOn(releaseDate);

							}
						}
					}
				}
			}
			qexec.close();
			record.setTimed_out(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getGenre(String queryStringDB, Record record) {

		try {

			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					System.out.println("NO genre.");
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {

					ArrayList<String> genreList = new ArrayList<String>();
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("genre")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String genre = child.getNodeValue();
								genre = genre.replaceAll(
										"http://dbpedia.org/resource/", "");
								genreList.add(genre);
							}
						}
					}
					record.setGenreList(genreList);
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getArtistGenre(String queryStringDB, Record record) {

		try {

			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					System.out.println("NO getArtistGenre.");
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {

					ArrayList<String> genreList = new ArrayList<String>();
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("genre")) {
								NodeList childList = binding
										.getElementsByTagName("uri").item(0)
										.getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String genre = child.getNodeValue();
								genre = genre.replaceAll(
										"http://dbpedia.org/resource/", "");
								genreList.add(genre);
							}
						}
					}
					record.setGenreList(genreList);
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getArtistComment(String queryStringDB, Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					if (record.getArtistURI().length() != 0)
						System.out.println("NO getArtistComment.");
						System.out.println(queryStringDB);
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {
					for (int i = 0; i < resultList.getLength(); i++) {
						// Get element
						Element element = (Element) resultList.item(i);
						NodeList bindingsList = element
								.getElementsByTagName("binding");

						for (int j = 0; j < bindingsList.getLength(); j++) {
							Element binding = (Element) bindingsList.item(j);
							String attribute = binding.getAttribute("name");

							if (attribute.equals("artistComment")) {
								NodeList childList = binding
										.getElementsByTagName("literal")
										.item(0).getChildNodes();
								Node child = childList.item(0);

								while (child.getNodeType() != Node.TEXT_NODE)
									child = child.getNextSibling();

								String artistComment = child.getNodeValue();
								record.setArtistComment(StringEscape
										.escapeSql(artistComment));

							}
						}
					}
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getCategoriesForSong(String queryStringDB, Record record) {

		try {
			Query query = QueryFactory.create(queryStringDB);

			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					
					if (record.getSongURI().length() != 0)
						System.out.println("NO getCategoriesForSong.");
						System.out.println(queryStringDB);
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {

					ArrayList<String> categories_record = new ArrayList<String>();

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
								categories_record.add(value);

								// get date of birth, if any:
							}
						}
					}
					record.setCategories_record(categories_record);
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}

	public static void getCategoriesForArtist(String queryStringDB,
			Record record) {

		try {

			Query query = QueryFactory.create(queryStringDB);

			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			ResultSet resultsDBpedia = qexec.execSelect();

			String xmlStrDBpedia = ResultSetFormatter
					.asXMLString(resultsDBpedia);
			// System.out.println(xmlStrDBpedia);
			Document xmlFile = StringToXML.parse(xmlStrDBpedia);

			if (xmlFile == null)
				System.out.println("NULL DOCUMENT ERROR.");
			else {
				NodeList resultList = xmlFile.getElementsByTagName("result");
				if (resultList.getLength() == 0) {
					
					if (record.getArtistURI().length() != 0)
						System.out.println("NO getCategoriesForArtist.");
						System.out.println(queryStringDB);
					// TODO Do something with these results - so that you'll
					// know
					// further on!!!
				} else {

					ArrayList<String> categories_artist = new ArrayList<String>();

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
								categories_artist.add(value);

								// get date of birth, if any:
							}
						}
					}

					record.setCategories_artist(categories_artist);
				}
			}
			record.setTimed_out(0);
			qexec.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			record.setTimed_out(1);
		}
	}
}
