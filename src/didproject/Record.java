package didproject;

import java.util.ArrayList;

public class Record {
	private int recordID;
	private String artist;
	private String title;
	private String part_of;
	private String composer;
	private String releasedOn;
	private String artistURI;
	private String songURI;
	private String artistComment;
	private String gender;
	private double genderRatio;
	private ArrayList<String> genreList; // not on DB!!!
	private ArrayList<String> categories_record;
	private ArrayList<String> categories_artist;
	private int bound;

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPart_of() {
		return part_of;
	}

	public void setPart_of(String part_of) {
		this.part_of = part_of;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getReleasedOn() {
		return releasedOn;
	}

	public void setReleasedOn(String releasedOn) {
		this.releasedOn = releasedOn;
	}

	public String getArtistURI() {
		return artistURI;
	}

	public void setArtistURI(String artistURI) {
		this.artistURI = artistURI;
	}

	public String getSongURI() {
		return songURI;
	}

	public void setSongURI(String songURI) {
		this.songURI = songURI;
	}

	public String getArtistComment() {
		return artistComment;
	}

	public void setArtistComment(String artistComment) {
		this.artistComment = artistComment;
	}

	public double getGenderRatio() {
		return genderRatio;
	}

	public void setGenderRatio(double genderRatio) {
		this.genderRatio = genderRatio;
	}

	public int getBound() {
		return bound;
	}

	public void setBound(int bound) {
		this.bound = bound;
	}

	public void setGenreList(ArrayList<String> genreList) {
		this.genreList = genreList;
	}

	public ArrayList<String> getGenreList() {
		return genreList;
	}

	public void setCategories_record(ArrayList<String> categories_record) {
		this.categories_record = categories_record;
	}

	public ArrayList<String> getCategories_record() {
		return categories_record;
	}

	public void setCategories_artist(ArrayList<String> categories_artist) {
		this.categories_artist = categories_artist;
	}

	public ArrayList<String> getCategories_artist() {
		return categories_artist;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public Record(int recordID, String artist, String title, String part_of,
			String composer, String releasedOn, String artistURI,
			String songURI, String artistComment, double genderRatio, int bound) {
		this.setRecordID(recordID);
		this.setArtist(artist);
		this.setTitle(title);
		this.setPart_of(part_of);
		this.setComposer(composer);
		this.setReleasedOn(releasedOn);
		this.setArtistURI(artistURI);
		this.setSongURI(songURI);
		this.setArtistComment(artistComment);
		this.setGenderRatio(genderRatio);
		this.setBound(bound);
		this.setGenreList(new ArrayList<String>());
		this.setCategories_artist(new ArrayList<String>());
		this.setCategories_record(new ArrayList<String>());
	}

	/**
	 * Constructor with categories as well!!! For data mining!
	 * 
	 * */
	public Record(int recordID, String artist, String title, String part_of,
			String composer, String releasedOn, String artistURI,
			String songURI, String artistComment, double genderRatio,
			String categories_record, String categories_artist, int bound) {

		this.setRecordID(recordID);
		this.setArtist(artist);
		this.setTitle(title);
		this.setPart_of(part_of);
		this.setComposer(composer);
		this.setReleasedOn(releasedOn);
		this.setArtistURI(artistURI);
		this.setSongURI(songURI);
		this.setArtistComment(artistComment);
		this.setGenderRatio(genderRatio);
		this.setGenreList(new ArrayList<String>());

		categories_record = categories_record.replaceAll("[", "");
		categories_record = categories_record.replaceAll("]", "");

		String[] var = categories_record.split(",");
		ArrayList<String> varArray = new ArrayList<String>();
		for (String s : var) {
			varArray.add(s);
		}
		this.setCategories_record(varArray);

		categories_artist = categories_artist.replaceAll("[", "");
		categories_artist = categories_artist.replaceAll("]", "");
		var = categories_artist.split(",");
		varArray = new ArrayList<String>();
		for (String s : var) {
			varArray.add(s);
		}
		this.setCategories_artist(varArray);

		this.setBound(bound);
	}

	public static String getLinkedResourcesQuery(Record record) {
		String query = "SELECT DISTINCT *" + "WHERE {" + "{ "
				+ "?song rdf:type <http://dbpedia.org/ontology/Single> ."
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ "FILTER (LANG(?songTitle) = 'en') ."

				+ "?song dbpedia-owl:musicalArtist ?artist ."

				+ "?artist rdfs:label ?artistName ."
				+ "FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "} "

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/MusicalWork> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."

				+ " ?song dbpedia-owl:musicalArtist ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?artistName) = 'en') ."

				+ "} UNION"
				+ " {?song rdf:type <http://umbel.org/umbel/rc/MusicalComposition> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."

				+ " ?song dbpedia-owl:musicalArtist ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?artistName) = 'en') ."
				+ "}"
				// ##
				// ## change the artist link to :musicalBand
				// ##
				+ "UNION { ?song rdf:type <http://dbpedia.org/ontology/Single> ."
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ "FILTER (LANG(?songTitle) = 'en') ."

				+ "?song dbpedia-owl:musicalBand ?artist ."

				+ "?artist rdfs:label ?artistName ."
				+ "FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "} "

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/MusicalWork> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."

				+ " ?song dbpedia-owl:musicalBand ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?artistName) = 'en') ."
				+ " } "

				+ "UNION {"
				+ " ?song rdf:type <http://umbel.org/umbel/rc/MusicalComposition> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."

				+ " ?song dbpedia-owl:musicalBand ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?artistName) = 'en') ."
				+ "}"
				+ "" + "} LIMIT 1";

		return query;
	}

	public static String getIndividualSongQuery(Record record) {
		// bound = 2, if found
		String query = "SELECT DISTINCT *" + "WHERE {" + "{ "
				+ "?song rdf:type <http://dbpedia.org/ontology/Single> ."
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ "FILTER (LANG(?songTitle) = 'en') ."
				+ "} "

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/MusicalWork> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/Work> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."

				+ "} UNION"
				+ " {?song rdf:type <http://umbel.org/umbel/rc/MusicalComposition> ."
				+ " ?song rdfs:label ?songTitle ."
				+ " FILTER ( regex(?songTitle, "
				+ record.getPart_of()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?songTitle) = 'en') ."
				+ "} "
				+ "}LIMIT 1";
		// ##
		// ## change the artist link to :musicalBand
		// ##

		return query;
	}

	public static String getIndividualArtistQuery(Record record) {
		// bound = 3, if found
		String query = "SELECT DISTINCT *"
				+ "WHERE {"
				+ "{ "

				+ "?artist rdf:type <http://dbpedia.org/ontology/Artist> ."
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "} "

				+ "UNION {"
				+ " ?artist rdf:type schema:MusicGroup ."
				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( regex(?artistName, "
				+ record.getArtist()
				+ ", \"i\") ) ."
				+ " FILTER (LANG(?artistName) = 'en') ."
				+ " } "

				+ "UNION {"
				+ " ?artist rdf:type <http://umbel.org/umbel/rc/MusicalPerformer> ."
				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( regex(?artistName, " + record.getArtist()
				+ ", \"i\") ) ." + " FILTER (LANG(?artistName) = 'en') ." + "}"
				+ "} LIMIT 1";

		return query;
	}

	public static String getIndividualClassicalSongQuery(Record record) {
		// bound = 5, if found
		String query = "SELECT DISTINCT *" + "WHERE {" + "{ "
				+ "?song rdf:type [rdfs:subClassOf ?genre] ."
				+ "?genre rdfs:subClassOf yago:ClassicalMusic107025900 ."
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( regex(?songTitle, " + record.getPart_of()
				+ ", \"i\") ) ." + "FILTER (LANG(?songTitle) = 'en') ." + "} "
				+ "} " + "}LIMIT 1";
		return query;
	}

	public static String getIndividualClassicalArtistQuery(Record record) {

		String query = "SELECT DISTINCT *"
				+ "WHERE {"
				+ "{ "
				+ "?artist dcterms:subject [skos:broader category:Classical_music_era] ."
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER ( regex(?artistName, " + record.getPart_of()
				+ ", \"i\") ) ." + "FILTER (LANG(?artistName) = 'en') ." + "} "
				+ "} " + "}LIMIT 1";
		return query;
	}

	public static String getReleaseDateQuery(Record record) {
		String query = "SELECT DISTINCT *" + "WHERE " + "{ " + "<"
				+ record.getSongURI() + ">"
				+ " dbpedia-owl:releaseDate ?releaseDate} ";
		return query;
	}

	public static String getSongGenreQuery(Record record) {
		String query = "SELECT DISTINCT *"
				+ "WHERE {"
				+ "OPTIONAL {"
				+ "<"
				+ record.getSongURI()
				+ ">"

				+ " dbpedia-owl:genre ?genre} ."
				+ "  OPTIONAL {<http://dbpedia.org/resource/Like_a_Prayer_%28song%29> dbpedia2:genre ?genre} . "
				+ "}";
		return query;

	}

	public static String getArtistCommentQuery(Record record) {
		String query = "SELECT DISTINCT *" + "WHERE " + "{ " + "OPTIONAL {"
				+ "<" + record.getArtistURI()
				+ "> rdfs:comment ?artistComment ."
				+ "FILTER (LANG(?artistComment) = 'en') }" + "} ";
		return query;
	}

	public static String getSubjectOfSongQuery(Record record) {
		String query = "SELECT DISTINCT ?subject WHERE {" + " <"
				+ record.getSongURI() + "> dcterms:subject ?subject" + "}";
		return query;

	}

	public static String getSubjectOfArtistQuery(Record record) {
		String query = "SELECT DISTINCT ?subject WHERE {" + " <"
				+ record.getArtistURI() + "> dcterms:subject ?subject" + "}";
		return query;

	}

	public static void calculateGender(Record record) {
		double he = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " he ");
		double she = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " she ");
		double her = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " her ");
		double his = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " his ");
		double they = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " they ");
		double them = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " them ");

		double ratio = (she + her) / (he + his);
		record.setGenderRatio(ratio);
		if ((they + them) > (she + her) && (they + them) > (he + his))
			record.setGender("other");
		else if (ratio < 0.8)
			record.setGender("male");
		else if (ratio > 1.2)
			record.setGender("female");
		else
			record.setGender("other");

	}

	public void updateRecordInfo(DBManager manager) {
		manager.updateRecord(this.getRecordID(), this.getReleasedOn(),
				this.getArtistURI(), this.getSongURI(),
				this.getArtistComment(), this.getGender(),
				this.getGenderRatio(), this.getCategories_record().toString(),
				this.getCategories_artist().toString(), this.getBound());
	}

	public void addGenre(DBManager manager) {
		for (int j = 0; j < this.getGenreList().size(); j++) {
			int genreID = manager.exists("genreID", "genre", "name",
					StringEscape.escapeSql(this.getGenreList().get(j)));
			if (genreID > 0) {

				// id exists in db, then add FK for "genreOf"

				manager.addGenreOf(genreID, this.getRecordID());
			} else {
				// add category in the DB
				manager.addGenre(StringEscape.escapeSql(this.getGenreList()
						.get(j)));
				int var = manager.exists("genreID", "genre", "name",
						StringEscape.escapeSql(this.getGenreList().get(j)));
				if (var > 0) {
					manager.addGenreOf(genreID, this.getRecordID());
				} else
					System.out
							.println("Something, somwhere, went terribly WRONG! ");
			}
		}
	}

}