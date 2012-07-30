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
	private int classical;
	private int bound;
	private int timed_out;

	// MusicBrainz fields
	private String mbz_gender;
	private String mbz_type;
	private String mbz_country;
	private String mbz_disambiguation;
	private String mbz_life_begin;
	private String mbz_life_end;
	private String mbz_life_ended;
	private String mbz_arid;

	public String getMbz_gender() {
		return mbz_gender;
	}

	public void setMbz_gender(String mbz_gender) {
		this.mbz_gender = mbz_gender;
	}

	public String getMbz_type() {
		return mbz_type;
	}

	public void setMbz_type(String mbz_type) {
		this.mbz_type = mbz_type;
	}

	public String getMbz_country() {
		return mbz_country;
	}

	public void setMbz_country(String mbz_country) {
		this.mbz_country = mbz_country;
	}

	public String getMbz_disambiguation() {
		return mbz_disambiguation;
	}

	public void setMbz_disambiguation(String mbz_disambiguation) {
		this.mbz_disambiguation = mbz_disambiguation;
	}

	public String getMbz_life_begin() {
		return mbz_life_begin;
	}

	public void setMbz_life_begin(String mbz_life_begin) {
		this.mbz_life_begin = mbz_life_begin;
	}

	public String getMbz_life_end() {
		return mbz_life_end;
	}

	public void setMbz_life_end(String mbz_life_end) {
		this.mbz_life_end = mbz_life_end;
	}

	public String getMbz_life_ended() {
		return mbz_life_ended;
	}

	public void setMbz_life_ended(String mbz_life_ended) {
		this.mbz_life_ended = mbz_life_ended;
	}

	public String getMbz_arid() {
		return mbz_arid;
	}

	public void setMbz_arid(String mbz_arid) {
		this.mbz_arid = mbz_arid;
	}

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

	public void setTimed_out(int timed_out) {
		this.timed_out = timed_out;
	}

	public int getTimed_out() {
		return timed_out;
	}

	public Record(int recordID, String artist, String title, String part_of,
			String composer, String releasedOn, String artistURI,
			String songURI, String artistComment, double genderRatio,
			int bound, int timed_out) {
		this.setClassical(0);
		this.setRecordID(recordID);
		this.setArtist(artist);
		this.setTitle(title);
		this.setPart_of(part_of);
		this.setComposer(composer);
		this.setReleasedOn(releasedOn);
		this.setArtistURI(StringEscape.unescapeUrl(artistURI));
		this.setSongURI(StringEscape.unescapeUrl(songURI));
		this.setArtistComment(artistComment);
		this.setGender("");
		this.setGenderRatio(genderRatio);
		this.setBound(bound);
		this.setGenreList(new ArrayList<String>());
		this.setCategories_artist(new ArrayList<String>());
		this.setCategories_record(new ArrayList<String>());
		this.setTimed_out(timed_out);
	}

	/**
	 * Constructor with categories as well!!! For data mining!
	 * 
	 * */
	public Record(int recordID, String artist, String title, String part_of,
			String composer, String releasedOn, String artistURI,
			String songURI, String artistComment, double genderRatio,
			String gender, String categories_record, String categories_artist,
			int bound, int timed_out) {

		this.setClassical(0);
		this.setRecordID(recordID);
		this.setArtist(artist);
		this.setTitle(title);
		this.setPart_of(part_of);
		this.setComposer(composer);
		this.setReleasedOn(releasedOn);
		this.setArtistURI(StringEscape.unescapeUrl(artistURI));
		this.setSongURI(StringEscape.unescapeUrl(songURI));
		this.setArtistComment(artistComment);
		this.setGenderRatio(genderRatio);
		this.setGender(gender);
		this.setGenreList(new ArrayList<String>());

		categories_record = categories_record.replaceAll("\\[", "");
		categories_record = categories_record.replaceAll("\\]", "");

		String[] var = categories_record.split(",");
		ArrayList<String> varArray = new ArrayList<String>();
		for (String s : var) {
			varArray.add(s);
		}
		this.setCategories_record(varArray);

		categories_artist = categories_artist.replaceAll("\\[", "");
		categories_artist = categories_artist.replaceAll("\\]", "");
		var = categories_artist.split(",");
		varArray = new ArrayList<String>();
		for (String s : var) {
			varArray.add(s);
		}
		this.setCategories_artist(varArray);

		this.setBound(bound);
		this.setTimed_out(timed_out);
	}

	public Record(int recordID, String artist, String title, String part_of,
			String composer) {

		this.setRecordID(recordID);
		this.setArtist(artist);
		this.setTitle(title);
		this.setPart_of(part_of);
		this.setComposer(composer);

	}

	public static String getLinkedResourcesQuery(Record record) {
		String query = "SELECT DISTINCT * WHERE {" + "{ "
				+ "?song rdf:type <http://dbpedia.org/ontology/Single> ."
				+ "?song rdfs:label ?songTitle ."
				// + "FILTER (LANG(?songTitle) = 'en') ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ "?song dbpedia-owl:musicalArtist ?artist ."

				+ "?artist rdfs:label ?artistName ."
				// + "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "} "

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/MusicalWork> ."
				+ " ?song rdfs:label ?songTitle ."
				// + " FILTER (LANG(?songTitle) = 'en') ."
				+ " FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ " ?song dbpedia-owl:musicalArtist ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				// + " FILTER (LANG(?artistName) = 'en') ."
				+ " FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "} UNION"
				+ " {?song rdf:type <http://umbel.org/umbel/rc/MusicalComposition> ."
				+ " ?song rdfs:label ?songTitle ."
				// + " FILTER (LANG(?songTitle) = 'en') ."
				+ " FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ " ?song dbpedia-owl:musicalArtist ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				// + " FILTER (LANG(?artistName) = 'en') ."
				+ " FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "}"
				// ##
				// ## change the artist link to :musicalBand
				// ##
				+ "UNION { ?song rdf:type <http://dbpedia.org/ontology/Single> ."
				+ "?song rdfs:label ?songTitle ."
				// + "FILTER (LANG(?songTitle) = 'en') ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ "?song dbpedia-owl:musicalBand ?artist ."

				+ "?artist rdfs:label ?artistName ."
				// + "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "} "

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/MusicalWork> ."
				+ " ?song rdfs:label ?songTitle ."
				// + " FILTER (LANG(?songTitle) = 'en') ."
				+ " FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ " ?song dbpedia-owl:musicalBand ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				// + " FILTER (LANG(?artistName) = 'en') ."
				+ " FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ " } "

				+ "UNION {"
				+ " ?song rdf:type <http://umbel.org/umbel/rc/MusicalComposition> ."
				+ " ?song rdfs:label ?songTitle ."
				// + " FILTER (LANG(?songTitle) = 'en') ."
				+ " FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ " ?song dbpedia-owl:musicalBand ?artist ."

				+ " ?artist rdfs:label ?artistName ."
				// + " FILTER (LANG(?artistName) = 'en') ."
				+ " FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "}" + "} LIMIT 1";

		return query;
	}

	public static String getAnyLinkedResourcesQuery(Record record) {
		String query = "SELECT DISTINCT * WHERE {"
				+ "{ "

				+ "?song rdfs:label ?songTitle ."
				// + "FILTER (LANG(?songTitle) = 'en') ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ "?song dbpedia-owl:musicalArtist ?artist ."

				+ "?artist rdfs:label ?artistName ."
				// + "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "} "

				// ##
				// ## change the artist link to :musicalBand
				// ##
				+ "UNION { "
				+ "?song rdfs:label ?songTitle ."
				// + "FILTER (LANG(?songTitle) = 'en') ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ "?song dbpedia-owl:musicalBand ?artist ."

				+ "?artist rdfs:label ?artistName ."
				// + "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "} " + "} LIMIT 1";

		return query;
	}

	public static String getIndividualSongQuery(Record record) {
		// bound = 2, if found
		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?song rdf:type <http://dbpedia.org/ontology/Single> ."
				+ "?song rdfs:label ?songTitle ."
				// + "FILTER (LANG(?songTitle) = 'en') ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ "} "

				+ "UNION {"
				+ "?song rdf:type <http://dbpedia.org/ontology/MusicalWork> ."
				+ " ?song rdfs:label ?songTitle ."
				// + " FILTER (LANG(?songTitle) = 'en') ."
				+ " FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) .}"

				+ " UNION"
				+ " {?song rdf:type <http://umbel.org/umbel/rc/MusicalComposition> ."
				+ " ?song rdfs:label ?songTitle ."
				// + " FILTER (LANG(?songTitle) = 'en') ."
				+ " FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."

				+ "} " + "}LIMIT 1";
		// ##
		// ## change the artist link to :musicalBand
		// ##

		return query;
	}

	public static String getIndividualArtistQuery(Record record) {
		// bound = 3, if found
		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "

				+ "?artist rdf:type <http://dbpedia.org/ontology/Artist> ."
				+ "?artist rdfs:label ?artistName ."
				// + "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ "} "

				+ "UNION {"
				+ " ?artist rdf:type schema:MusicGroup ."
				+ " ?artist rdfs:label ?artistName ."
				// + " FILTER (LANG(?artistName) = 'en') ."
				+ " FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."

				+ " } "

				+ "UNION {"
				+ " ?artist rdf:type <http://umbel.org/umbel/rc/MusicalPerformer> ."
				+ " ?artist rdfs:label ?artistName ."
				// + " FILTER (LANG(?artistName) = 'en') ."
				+ " FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ." + "}" + "} LIMIT 1";

		return query;
	}

	public static String getIndividualClassicalSongQuery(Record record) {
		// bound = 5, if found
		String query = "SELECT DISTINCT * " + "WHERE {"
				+ "{ "
				+ "?song rdf:type [rdfs:suBClassOf ?genre] ."
				+ "?genre rdfs:suBClassOf yago:ClassicalMusic107025900 ."
				+ "?song rdfs:label ?songTitle ."
				// + "FILTER (LANG(?songTitle) = 'en') ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ." + "} " + "} LIMIT 1";
		return query;
	}

	public static String getIndividualClassicalArtistQuery(Record record) {

		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?artist dcterms:subject [skos:broader category:Classical_music_era] ."
				+ "?artist rdfs:label ?artistName ."
				// + "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ." + "} " + "} LIMIT 1";
		return query;
	}

	public static String getIndividualModernClassicalArtistQuery(Record record) {

		// TODO fix for modern!!!!

		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?artist dcterms:subject [skos:broader category:Classical_composers] ."
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ "} "
				+ "UNION { "
				+ "?artist dcterms:subject [skos:broader category:Opera_singers] ."
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ." + "}" + "} LIMIT 1";
		return query;
	}

	public static String getWritersQuery(Record record) {

		// TODO fix for modern!!!!

		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?artist dcterms:subject [skos:broader category:Poets] ."
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ "} UNION {?artist dcterms:subject [skos:broader category:English_writers] ."
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ." + "} " + "} LIMIT 1";
		return query;
	}

	public static String getAnyArtistQuery(Record record) {

		// TODO fix for modern!!!!

		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ "?artist foaf:page ?artistPage . } "
				+ "UNION { "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ "?artist foaf:isPrimaryTopicOf ?artistPage . }"
				+ " UNION { ?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "<"
				+ StringEscape.EncodeDBpediaResource(StringEscape
						.escapeSparqlURL(record.getArtist()))
				+ "> dbpedia-owl:wikiPageRedirects ?artist ." + "}"
				+ "} LIMIT 1";
		return query;
	}

	public static String getAnyArtistFIXQuery(Record record) {

		// TODO fix for modern!!!!

		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ " ?artist foaf:page ?artistPage . }"
				+ " UNION { "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ " ?artist foaf:isPrimaryTopicOf ?artistPage . }"
				+ " UNION { "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "<"
				+ StringEscape.EncodeDBpediaResource(StringEscape
						.escapeSparqlURL(record.getArtist()))
				+ "> dbpedia-owl:wikiPageRedirects ?artist ." + "}"
				+ "} LIMIT 1";
		return query;
	}

	public static String getAnyArtistRedirectQuery(Record record) {

		// TODO fix for modern!!!!

		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ " ?artist foaf:page ?artistPage . }"
				+ " UNION { "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ "FILTER ( <bif:contains>(?artistName, \"'"
				+ StringEscape.escapeBifContains(record.getArtist())
				+ "'\") ) ."
				+ " ?artist foaf:isPrimaryTopicOf ?artistPage . }"
				+ " UNION { "
				+ "?artist rdfs:label ?artistName ."
				+ "FILTER (LANG(?artistName) = 'en') ."
				+ " ?artist dbpedia-owl:wikiPageRedirects <"
				+ StringEscape.EncodeDBpediaResource(StringEscape
						.escapeSparqlURL(record.getArtist())) + "> ." + "}"
				+ "} LIMIT 1";
		return query;
	}

	public static String getAnySongQuery(Record record) {

		// TODO fix for modern!!!!
		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."
				+ "?song foaf:page ?songPage. } "
				+ "UNION { "
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "'\") ) ."
				+ "?song foaf:isPrimaryTopicOf ?songPage. }"
				+ " UNION "
				+ "{?song rdfs:label ?songTitle ."
				+ "<"
				+ StringEscape.EncodeDBpediaResource(StringEscape
						.escapeSparqlURL(record.getPart_of()))
				+ "> dbpedia-owl:wikiPageRedirects ?song ." + "}" + "} LIMIT 1";

		return query;
	}

	public static String getAnySongFIXQuery(Record record) {

		// TODO fix for modern!!!!
		String query = "SELECT DISTINCT * "
				+ "WHERE {"
				+ "{ "
				+ "?song rdfs:label ?songTitle ."
				+ "FILTER ( <bif:contains>(?songTitle, \"'"
				+ StringEscape.escapeBifContains(record.getPart_of())
				+ "' AND 'song'\") ) ."
				+ "?song foaf:page ?songPage. } "
				+ "UNION {"
				+ "?song rdfs:label ?songTitle ."
				+ " ?song foaf:page ?songPage ."
				+ "<"
				+ StringEscape.EncodeDBpediaResource(StringEscape
						.escapeSparqlURL(record.getPart_of()))
				+ "> dbpedia-owl:wikiPageRedirects ?song ."
				+ "} "
				+ "UNION {"
				+ "?song rdfs:label ?songTitle ."
				+ "?song foaf:isPrimaryTopicOf ?songPage ."
				+ "<"
				+ StringEscape.EncodeDBpediaResource(StringEscape
						.escapeSparqlURL(record.getPart_of()))
				+ "> dbpedia-owl:wikiPageRedirects ?song ." + "}" + "} LIMIT 1";

		return query;
	}

	public static String getReleaseDateQuery(Record record) {
		String query = "SELECT DISTINCT * " + "WHERE " + "{ " + "<"
				+ StringEscape.escapeSparqlURL(record.getSongURI()) + ">"
				+ " dbpedia-owl:releaseDate ?releaseDate" + "} ";
		return query;
	}

	public static String getSongGenreQuery(Record record) {
		String query = "SELECT DISTINCT * " + "WHERE {" + "OPTIONAL {" + "<"
				+ StringEscape.escapeSparqlURL(record.getSongURI()) + ">"

				+ " dbpedia-owl:genre ?genre} ." + "  OPTIONAL {<"
				+ StringEscape.escapeSparqlURL(record.getSongURI())
				+ "> dbpedia2:genre ?genre} . " + "}";
		return query;

	}

	public static String getArtistGenreQuery(Record record) {
		String query = "SELECT DISTINCT * " + "WHERE {" + "OPTIONAL {" + "<"
				+ StringEscape.escapeSparqlURL(record.getArtistURI()) + ">"

				+ " dbpedia-owl:genre ?genre} ." + "  OPTIONAL {<"
				+ StringEscape.escapeSparqlURL(record.getArtistURI())
				+ "> dbpedia2:genre ?genre} . " + "}";
		return query;

	}

	public static String getArtistCommentQuery(Record record) {
		String query = "SELECT DISTINCT * " + "WHERE " + "{ " + "OPTIONAL {"
				+ "<" + StringEscape.escapeSparqlURL(record.getArtistURI())
				+ "> dbpedia-owl:abstract ?artistComment ."
				+ "FILTER (LANG(?artistComment) = 'en') }" + "} ";
		return query;
	}

	public static String getSubjectOfSongQuery(Record record) {
		String query = "SELECT DISTINCT ?subject WHERE {" + " <"
				+ StringEscape.escapeSparqlURL(record.getSongURI())
				+ "> dcterms:subject ?subject" + "}";
		return query;

	}

	public static String getSubjectOfArtistQuery(Record record) {
		String query = "SELECT DISTINCT ?subject WHERE {" + " <"
				+ StringEscape.escapeSparqlURL(record.getArtistURI())
				+ "> dcterms:subject ?subject" + "}";
		return query;

	}

	public void setClassical(int classical) {
		this.classical = classical;
	}

	public int getClassical() {
		return classical;
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
		double their = StringEscape.countOccurances(record.getArtistComment()
				.toLowerCase(), " their ");

		double ratio = (she + her) / (he + his);
		if ((he + his) == 0)
			ratio = (she + her);

		record.setGenderRatio(ratio);
		if (((they + them + their) > (she + her))
				&& ((they + them + their) > (he + his)))
			record.setGender("band");
		else if (ratio < 0.8)
			record.setGender("male");
		else if (ratio > 1.2)
			record.setGender("female");
		else
			record.setGender("other");

	}

	public void updateRecordInfo(DBManager manager) {
		manager.updateRecord(this.getRecordID(), this.getReleasedOn(),
				StringEscape.escapeUrl(this.getArtistURI()),
				StringEscape.escapeUrl(this.getSongURI()),
				StringEscape.escapeSql(this.getArtistComment()),
				this.getGender(), this.getGenderRatio(),
				StringEscape.escapeSql(this.getCategories_record().toString()),
				StringEscape.escapeSql(this.getCategories_artist().toString()),
				this.getBound(), this.getTimed_out(), this.getClassical());
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
					manager.addGenreOf(var, this.getRecordID());
				} else
					System.out
							.println("Something, somwhere, went terribly WRONG! ");
			}
		}
	}

	public static void getReleaseDateFromSong(Record record) {
		int i = 0;

		while (record.getReleasedOn().equals("0")
				&& (i < record.getCategories_record().size())) {
			String category = record.getCategories_record().get(i);
			if (category.matches("^[0-9]{4}+_+.*+$")) {
				record.setReleasedOn(category.substring(0, 4));
			}
			i++;
		}

	}

	public static void getReleaseDateFromArtist(Record record) {
		int i = 0;
		while (record.getReleasedOn().equals("0")
				&& (i < record.getCategories_artist().size())) {
			String category = record.getCategories_artist().get(i);
			if (category.matches("^[0-9]{2}+th-century+.*+$")) {
				record.setReleasedOn(category.substring(0, 12));
			}
			i++;
		}
	}

	public void checkCategories(String category) {
		String[] list = { "opera", "operas", "ballet", "musical", "sonata",
				"concerto", "symphony", "orchestra", "ballets", "composers",
				"pianists", "classical" };
		int i = 0;
		while (this.getClassical() == 0 && i < list.length) {
			if (category.toLowerCase().contains(list[i])) {
				this.setClassical(1);
			}
			i++;
		}
	}

	public void updateMbzArtistinfo(DBManager manager) {
		manager.updateMbzArtistinfo(this.getRecordID(), this.getMbz_type(),
				this.getMbz_country(), this.getMbz_disambiguation(),
				this.getMbz_life_begin(), this.getMbz_life_end(),
				this.getMbz_life_ended(),this.getMbz_arid());
	}
}
