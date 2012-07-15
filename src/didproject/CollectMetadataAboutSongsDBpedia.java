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
					Integer.parseInt(fields.get(9)), Integer.parseInt(fields
							.get(10)));

			String getLinkedResourcesQuery = Record
					.getLinkedResourcesQuery(record);

			SparqlQueryProcesser.getBoundArtistSongURIs(queryPrefix
					+ getLinkedResourcesQuery, record);

			// if there was no bound resource, then:

			if (record.getBound() == 0) {

				String getIndividualSongQuery = Record
						.getIndividualSongQuery(record);
				SparqlQueryProcesser.getIndividualSong(queryPrefix
						+ getIndividualSongQuery, record);
				// so here, bound can only be 0 or 2!!!

				String getIndividualArtistQuery = Record
						.getIndividualArtistQuery(record);
				SparqlQueryProcesser.getIndividualArtist(queryPrefix
						+ getIndividualArtistQuery, record);
			}
			// if bound is still 0, then get the Classical pieces! bound = 5
			if (record.getBound() == 0) {

				String getIndividualClassicalSongQuery = Record
						.getIndividualClassicalSongQuery(record);
				SparqlQueryProcesser.getIndividualClassicalSong(queryPrefix
						+ getIndividualClassicalSongQuery, record);
				// so here, bound can only be 0 or 5!!!

				String getIndividualClassicalArtistQuery = Record
						.getIndividualClassicalArtistQuery(record);
				SparqlQueryProcesser.getIndividualClassicalArtist(queryPrefix
						+ getIndividualClassicalArtistQuery, record);
			}

			// query for metadata!!!

			switch (record.getBound()) {
			case 1: {
				// bound resources

				SparqlQueryProcesser.getReleaseDate(
						queryPrefix + Record.getReleaseDateQuery(record),
						record);
				SparqlQueryProcesser.getGenre(
						queryPrefix + Record.getSongGenreQuery(record), record);
				SparqlQueryProcesser.getArtistComment(
						queryPrefix + Record.getArtistCommentQuery(record),
						record);
			}
			case 2: {
				// individual song

				SparqlQueryProcesser.getReleaseDate(
						queryPrefix + Record.getReleaseDateQuery(record),
						record);
				SparqlQueryProcesser.getGenre(
						queryPrefix + Record.getSongGenreQuery(record), record);
			}
			case 3: {
				// individual artist

				SparqlQueryProcesser.getArtistComment(
						queryPrefix + Record.getArtistCommentQuery(record),
						record);
			}

			case 4: {
				// separate song and artist, not bound

				SparqlQueryProcesser.getReleaseDate(
						queryPrefix + Record.getReleaseDateQuery(record),
						record);
				SparqlQueryProcesser.getGenre(
						queryPrefix + Record.getSongGenreQuery(record), record);
				SparqlQueryProcesser.getArtistComment(
						queryPrefix + Record.getArtistCommentQuery(record),
						record);
			}
			case 5: {
				// classical song
              // TODO get SubjectOf, to make DataMining
			}
			case 6: {
				// classical artist
				// TODO get SubjectOf, to make DataMining
			}

			case 7: {
				// classical both, not bound..
				// TODO get SubjectOf, to make DataMining
			}

			}
			
			if (record.getBound()!=0) {
				// TODO something changed in the Record, thus, UPDATE THE DATABASE
			}

		}

	}
}
