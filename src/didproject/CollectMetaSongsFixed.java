package didproject;

import java.util.ArrayList;

public class CollectMetaSongsFixed {
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
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getRecord("record", recordIDs.get(i));
			record = new Record(Integer.parseInt(fields.get(0)), fields.get(1),
					fields.get(2), fields.get(3), fields.get(4), fields.get(5),
					fields.get(6), fields.get(7), fields.get(8),
					Double.parseDouble(fields.get(9)), fields.get(10),
					fields.get(11), fields.get(12), Integer.parseInt(fields
							.get(13)), Integer.parseInt(fields.get(14)));

			if (record.getBound() != 0) {

				if (record.getBound() == 1) {
					if (record.getReleasedOn().equals("0")) {
						System.out.println("Try to get date for records: "
								+ record.getRecordID());
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);

					}
					if (record.getArtistComment().length() == 0) {
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
					}
				} else if (record.getBound() == 2) {
					// System.out.println(Record.getIndividualSongQuery(record));
					System.out.println("Try to fix records: "
							+ record.getRecordID());
					SparqlQueryProcesser
							.getIndividualClassicalArtist(
									queryPrefix
											+ Record.getIndividualClassicalArtistQuery(record),
									record);

					if (record.getArtistURI().equals("")) {
						SparqlQueryProcesser
								.getIndividualModernClassicalArtist(
										queryPrefix
												+ Record.getIndividualModernClassicalArtistQuery(record),
										record);
						if (record.getArtistURI().equals("")) {
							SparqlQueryProcesser.getAnyArtist(queryPrefix
									+ Record.getAnyArtistQuery(record), record);
						}
					}

					if (!record.getArtistURI().equals("")) {

						if (record.getBound() != 9)
							record.setBound(8);
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
						record.addGenre(manager);

					}

					if (record.getReleasedOn().equals("0")) {
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);
					}

				} else if (record.getBound() == 3) {
					System.out.println("Try to fix records: "
							+ record.getRecordID());

					if (record.getArtistComment().length() == 0) {
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
					}

					SparqlQueryProcesser.getIndividualClassicalSong(queryPrefix
							+ Record.getIndividualClassicalSongQuery(record),
							record);

					if (!record.getSongURI().equals("")) {
						record.setBound(8);

						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);

						// TODO
						record.updateRecordInfo(manager);
						record.addGenre(manager);
					}

					if (record.getReleasedOn().equals("0")) {

						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);
						record.updateRecordInfo(manager);

					}
				} else if (record.getBound() == 4) {

					if (record.getArtistComment().length() == 0) {
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
					}

					if (record.getReleasedOn().equals("0")) {
						System.out.println("Categories for records: "
								+ record.getRecordID());
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);

					}
				} else if (record.getBound() == 5) {
					System.out.println("Categories for records: "
							+ record.getRecordID());

					SparqlQueryProcesser.getAnyArtist(
							queryPrefix + Record.getAnyArtistQuery(record),
							record);
					if (!record.getArtistURI().equals("")) {
						record.setBound(9);
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);

					}

					if (record.getReleasedOn().equals("0")) {
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);

					}
				} else if (record.getBound() == 6) {

					if (record.getArtistComment().length() == 0) {
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
					}

					if (record.getReleasedOn().equals("0")) {
						System.out.println("Categories for records: "
								+ record.getRecordID());
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);

					}
				} else if (record.getBound() == 7) {
					if (record.getArtistComment().length() == 0) {
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
					}
					if (record.getReleasedOn().equals("0")) {
						System.out.println("Categories for records: "
								+ record.getRecordID());
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);

					}
				} else if (record.getBound() == 8) {
					
					if (record.getArtistComment().length() == 0) {
						SparqlQueryProcesser.getArtistComment(queryPrefix
								+ Record.getArtistCommentQuery(record), record);

						// TODO update
						if (record.getArtistComment().length() != 0)
							Record.calculateGender(record);
						record.updateRecordInfo(manager);
					}
					if (record.getReleasedOn().equals("0")) {
						System.out.println("Categories for records: "
								+ record.getRecordID());
						SparqlQueryProcesser.getReleaseDate(queryPrefix
								+ Record.getReleaseDateQuery(record), record);
						SparqlQueryProcesser.getCategoriesForSong(queryPrefix
								+ Record.getSubjectOfSongQuery(record), record);
						SparqlQueryProcesser.getCategoriesForArtist(queryPrefix
								+ Record.getSubjectOfArtistQuery(record),
								record);

						record.updateRecordInfo(manager);

					}
				}

			}
		}
	}

}
