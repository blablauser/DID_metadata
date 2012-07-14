package didproject;

public class CollectMetadataAboutSongsDBpedia {
	public static void run() {
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
			+ "PREFIX category: <http://dbpedia.org/resource/Category:>" ;	
				

	String dbQuery = " SELECT * " + "WHERE { ?music rdf:type mo:Track ."
			+ "?music mo:musicbrainz ?uri . " + "?music dc:title ?title ."
			
			+ "?music rdfs:label ?label ."
			+ "FILTER regex(?label, \"Little Child\", \"i\") " + "}";
	
	SparqlQueryProcesser.getBoundArtistSongURIs(queryPrefix+dbQuery);
	}
}
