package didproject;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class getMetaFromMusicBrainz {

	public static void run() {
		// read all songs from DB
		DBManager manager = new DBManager();
		Record record;
		ArrayList<String> recordIDs = new ArrayList<String>();
		// get all songs in DB - to query for them
		recordIDs = manager.getIds("record", "recordID");
		String baseURL = "http://musicbrainz.org/ws/2/";

		for (int i = 0; i < recordIDs.size(); i++) {

			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getRecord("record", recordIDs.get(i));
			record = new Record(Integer.parseInt(fields.get(0)), fields.get(1),
					fields.get(2), fields.get(3), fields.get(4));
			try {
				String urlResponse = StringToXML.UrlToString(baseURL
						+ "artist/?query=artist:"
						+ StringEscape.escapeUrlSearch(record.getArtist())
						+ "/");
				Document xmlFile = StringToXML.parse(urlResponse);

				if (xmlFile == null)
					System.out.println("Could not create XML file! Ups.. ");
				else {
					NodeList resultList = xmlFile
							.getElementsByTagName("artist");
					if (resultList.getLength() == 0) {
						System.out.println("No results");

					} else {
						for (int j = 0; j < resultList.getLength(); j++) {
							Element artist = (Element) resultList.item(j);

							String score = artist.getAttribute("ext:score");
							String type = artist.getAttribute("type");
							String id = artist.getAttribute("id");

							if (score.equals("100")) {
								// get the artist and update the record
								record.setMbz_type(type);
								record.setMbz_arid(id);
								NodeList artist_gender = artist
										.getElementsByTagName("gender");
								if (artist_gender.getLength() != 0) {
									NodeList gender = artist_gender.item(0)
											.getChildNodes();
									Node child = gender.item(0);
									while (child.getNodeType() != Node.TEXT_NODE)
										child = child.getNextSibling();
									System.out.println("Gender:"
											+ child.getNodeValue());
									record.setMbz_gender(child.getNodeValue());
								}

								NodeList artist_country = artist
										.getElementsByTagName("country");
								if (artist_country.getLength() != 0) {
									NodeList country = artist_country.item(0)
											.getChildNodes();
									Node child = country.item(0);
									while (child.getNodeType() != Node.TEXT_NODE)
										child = child.getNextSibling();
									System.out.println("Country:"
											+ child.getNodeValue());
									record.setMbz_country(child.getNodeValue());
								}

								NodeList artist_disambiguation = artist
										.getElementsByTagName("disambiguation");
								if (artist_disambiguation.getLength() != 0) {
									NodeList list = artist_disambiguation.item(
											0).getChildNodes();
									Node child = list.item(0);
									while (child.getNodeType() != Node.TEXT_NODE)
										child = child.getNextSibling();
									System.out.println("disambiguation:"
											+ child.getNodeValue());
									record.setMbz_disambiguation(child
											.getNodeValue());
								}

								NodeList artistLifeSpan = artist
										.getElementsByTagName("life-span");
								if (artistLifeSpan.getLength() != 0) {
									Element lifeSpan = (Element) artistLifeSpan
											.item(0);

									NodeList begin_life_span = lifeSpan
											.getElementsByTagName("begin");
									if (begin_life_span.getLength() != 0) {
										NodeList list = begin_life_span.item(0)
												.getChildNodes();
										Node child = list.item(0);
										while (child.getNodeType() != Node.TEXT_NODE)
											child = child.getNextSibling();
										System.out.println("begin life-span:"
												+ child.getNodeValue());
										record.setMbz_life_begin(child
												.getNodeValue());
									}

									NodeList end_life_span = lifeSpan
											.getElementsByTagName("end");
									if (end_life_span.getLength() != 0) {
										NodeList list = end_life_span.item(0)
												.getChildNodes();
										Node child = list.item(0);
										while (child.getNodeType() != Node.TEXT_NODE)
											child = child.getNextSibling();
										System.out.println("end life-span:"
												+ child.getNodeValue());
										record.setMbz_life_end(child
												.getNodeValue());
									}
									
									NodeList ended_life_span = lifeSpan
											.getElementsByTagName("ended");
									if (ended_life_span.getLength() != 0) {
										NodeList list = ended_life_span.item(0)
												.getChildNodes();
										Node child = list.item(0);
										while (child.getNodeType() != Node.TEXT_NODE)
											child = child.getNextSibling();
										System.out.println("ended life-span:"
												+ child.getNodeValue());
										record.setMbz_life_ended(child
												.getNodeValue());
									}
								}
							}
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//TODO update DB!!!
			//record.updateMbzArtistinfo(manager);
		}

	}
}
