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
	private int genderRatio;
	private int bound;
	private ArrayList<String> genreList;

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

	public int getGenderRatio() {
		return genderRatio;
	}

	public void setGenderRatio(int genderRatio) {
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

	public Record(int recordID, String artist, String title, String part_of,
			String composer, String releasedOn, String artistURI,
			String songURI, String artistComment, int genderRatio, int bound) {
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
		String query = "SELECT DISTINCT *" + "WHERE " + "{ "
				+ record.getSongURI()
				+ " dbpedia-owl:releaseDate ?releaseDate} ";
		return query;
	}

	public static String getSongGenreQuery(Record record) {
		String query = "SELECT DISTINCT *"
				+ "WHERE {"
				+ "OPTIONAL {"
				+ record.getSongURI()
				+ " dbpedia-owl:genre ?genre} ."
				+ "  OPTIONAL {<http://dbpedia.org/resource/Like_a_Prayer_%28song%29> dbpedia2:genre ?genre} . "
				+ "}";
		return query;

	}
	public static String getArtistCommentQuery(Record record) {
		String query = "SELECT DISTINCT *"
				+ "WHERE "
				+ "{ "
				+ "OPTIONAL {"+record.getArtistURI()+" rdfs:comment ?artistComment ."
				+ "FILTER (LANG(?artistComment) = 'en') }" + "} ";
		return query;
	}
}
