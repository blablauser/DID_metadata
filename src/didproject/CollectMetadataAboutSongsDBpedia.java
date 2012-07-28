package didproject;

import java.util.ArrayList;

public class CollectMetadataAboutSongsDBpedia {

	public static void run() {
		DBManager manager = new DBManager();
		Record record;
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
				+ "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
				+ "PREFIX umbel: <http://umbel.org/umbel/rc/>"
				+ "PREFIX schema: <http://schema.org/>"
				+ "PREFIX yago: <http://dbpedia.org/class/yago/>"
				+ "PREFIX category: <http://dbpedia.org/resource/Category:>";

		// TODO : GET THE LIST OF SONGS AND ARTIST FROM DATABASE (MAYBE PART_OF
		// INSTEAD OF TITLE)
		ArrayList<String> recordIDs = new ArrayList<String>();
		// get all songs in DB - to query for them
		recordIDs = manager.getIds("record", "recordID");

		for (int i = 0; i < recordIDs.size(); i++) {

			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getRecord("record", recordIDs.get(i));
			record = new Record(Integer.parseInt(fields.get(0)), fields.get(1),
					fields.get(2), fields.get(3), fields.get(4), fields.get(5),
					fields.get(6), fields.get(7), fields.get(8),
					Double.parseDouble(fields.get(9)), fields.get(10),
					fields.get(11), fields.get(12), Integer.parseInt(fields
							.get(13)), Integer.parseInt(fields.get(14)));

			if (record.getBound() == 0) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// try to get bound resources
				System.out.println("Record:" + record.getRecordID());
				SparqlQueryProcesser.getBoundArtistSongURIs(queryPrefix
						+ Record.getLinkedResourcesQuery(record), record);

				if (record.getSongURI().equals(""))
					SparqlQueryProcesser
							.getBoundArtistSongURIs(
									queryPrefix
											+ Record.getAnyLinkedResourcesQuery(record),
									record);
				// b=1/0

				if (record.getSongURI().equals("")) {
					// not found individual song, look for classical
					SparqlQueryProcesser.getIndividualClassicalSong(queryPrefix
							+ Record.getIndividualClassicalSongQuery(record),
							record);
					// b=2/0

					if (record.getSongURI().equals("")) {

						// get individual song
						SparqlQueryProcesser
								.getIndividualSong(
										queryPrefix
												+ Record.getIndividualSongQuery(record),
										record);
						// b = 5/0
						if (record.getSongURI().equals("")) {
							// not found individual or classical song, look for
							// ANY
							// SONG!
							SparqlQueryProcesser.getAnySong(queryPrefix
									+ Record.getAnySongQuery(record), record);

						}
					}
				}

				// b=0/1/2/5/8

				// b=0/2/5/8/3/4/11
				if (record.getArtistURI().equals("")) {
					// get classical artist
					SparqlQueryProcesser
							.getIndividualClassicalArtist(
									queryPrefix
											+ Record.getIndividualClassicalArtistQuery(record),
									record);
					if (record.getArtistURI().equals("")) {
						// get Modern Classical artist
						SparqlQueryProcesser
								.getIndividualModernClassicalArtist(
										queryPrefix
												+ Record.getIndividualModernClassicalArtistQuery(record),
										record);
						if (record.getArtistURI().equals("")) {

							// get indiv artist
							SparqlQueryProcesser
									.getIndividualArtist(
											queryPrefix
													+ Record.getIndividualArtistQuery(record),
											record);
							if (record.getArtistURI().equals("")) {
								// get ANY artist
								SparqlQueryProcesser.getAnyArtist(queryPrefix
										+ Record.getAnyArtistQuery(record),
										record);

							}
						}
					}
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (record.getArtistURI().length() != 0) {
					// query for resources for artist
					SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
							+ Record.getSubjectOfArtistQuery(record), record);
					SparqlQueryProcesser.getArtistComment(
							queryPrefix + Record.getArtistCommentQuery(record),
							record);
					if (record.getArtistComment().length() != 0)
						Record.calculateGender(record);
				}

				if (record.getSongURI().length() != 0) {
					// query for resources for songs
					SparqlQueryProcesser.getReleaseDate(
							queryPrefix + Record.getReleaseDateQuery(record),
							record);

					SparqlQueryProcesser.getCategoriesForSong(queryPrefix
							+ Record.getSubjectOfSongQuery(record), record);
				}

				if (record.getGenreList().size() == 0) {
					SparqlQueryProcesser.getArtistGenre(
							queryPrefix + Record.getArtistGenreQuery(record),
							record);

					if (record.getGenreList().size() == 0) {

						SparqlQueryProcesser.getGenre(
								queryPrefix + Record.getSongGenreQuery(record),
								record);
					}
				}
				if (record.getBound() != 0) {
					// the record changed somehow.

					// mine for release date, in case it's 0
					if (record.getReleasedOn().equals("0")) {
						// try from song
						Record.getReleaseDateFromArtist(record);

						if (record.getReleasedOn().equals("0")) {
							// try from artist

							Record.getReleaseDateFromSong(record);
						}

					}

					record.updateRecordInfo(manager);
					record.addGenre(manager);
				}
			}
		}
	}

	public static void lookForRedirect() {
		DBManager manager = new DBManager();
		Record record;
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
				+ "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
				+ "PREFIX umbel: <http://umbel.org/umbel/rc/>"
				+ "PREFIX schema: <http://schema.org/>"
				+ "PREFIX yago: <http://dbpedia.org/class/yago/>"
				+ "PREFIX category: <http://dbpedia.org/resource/Category:>";

		// TODO : GET THE LIST OF SONGS AND ARTIST FROM DATABASE (MAYBE PART_OF
		// INSTEAD OF TITLE)
		ArrayList<String> recordIDs = new ArrayList<String>();
		// get all songs in DB - to query for them
		recordIDs = manager.getIds("record", "recordID");

		for (int i = 0; i < recordIDs.size(); i++) {

			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getRecord("record", recordIDs.get(i));
			record = new Record(Integer.parseInt(fields.get(0)), fields.get(1),
					fields.get(2), fields.get(3), fields.get(4), fields.get(5),
					fields.get(6), fields.get(7), fields.get(8),
					Double.parseDouble(fields.get(9)), fields.get(10),
					fields.get(11), fields.get(12), Integer.parseInt(fields
							.get(13)), Integer.parseInt(fields.get(14)));

			int modified = 0;

			if (record.getSongURI().contains(
					"http://dbpedia.org/resource/Category:")) {
				modified = 1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				SparqlQueryProcesser
						.getAnySong(
								queryPrefix + Record.getAnySongFIXQuery(record),
								record);

				if (record.getSongURI().length() != 0) {
					// query for resources for songs
					SparqlQueryProcesser.getReleaseDate(
							queryPrefix + Record.getReleaseDateQuery(record),
							record);

					SparqlQueryProcesser.getCategoriesForSong(queryPrefix
							+ Record.getSubjectOfSongQuery(record), record);
				}

				if (record.getReleasedOn().equals("0")) {
					// try from song
					Record.getReleaseDateFromSong(record);

					if (record.getReleasedOn().equals("0")) {
						// try from artist
						Record.getReleaseDateFromArtist(record);
					}

				}

			}

			if (record.getArtistURI().contains(
					"http://dbpedia.org/resource/Category:")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				modified = 1;
				SparqlQueryProcesser.getAnyArtist(
						queryPrefix + Record.getAnyArtistFIXQuery(record),
						record);

				if (record.getArtistURI().length() != 0) {
					// query for resources for artist
					SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
							+ Record.getSubjectOfArtistQuery(record), record);
					SparqlQueryProcesser.getArtistComment(
							queryPrefix + Record.getArtistCommentQuery(record),
							record);
					if (record.getArtistComment().length() != 0)
						Record.calculateGender(record);
				}
				if (record.getReleasedOn().equals("0")) {
					// try from song
					Record.getReleaseDateFromSong(record);

					if (record.getReleasedOn().equals("0")) {
						// try from artist
						Record.getReleaseDateFromArtist(record);
					}

				}
			}

			if (modified == 1)
				record.updateRecordInfo(manager);

			if (!record.getArtistURI().equals("")
					&& record.getArtistComment().equals("")) {

				SparqlQueryProcesser.getAnyArtist(
						queryPrefix + Record.getAnyArtistRedirectQuery(record),
						record);
				SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
						+ Record.getSubjectOfArtistQuery(record), record);
				SparqlQueryProcesser.getArtistComment(
						queryPrefix + Record.getArtistCommentQuery(record),
						record);
				if (record.getArtistComment().length() != 0)
					Record.calculateGender(record);

				if (record.getReleasedOn().equals("0")) {
					// try from song
					Record.getReleaseDateFromSong(record);

					if (record.getReleasedOn().equals("0")) {
						// try from artist
						Record.getReleaseDateFromArtist(record);
					}

				}
				System.out.println("2nd");
				record.updateRecordInfo(manager);

			}

		}
	}
}
