package didproject;

//import java.util.ArrayList;

public class MainClass {
	public static void main(String args[]) {

		/**
		 * TODO Get metadata about people!!!
		 * 
		 * CollectMetadataAboutGuests.run();
		 * 
		 * */

		/**
		 * TODO calculate and add to DB the age of each castaway!
		 * 
		 * AddAgeOfCastaway.run();
		 * 
		 * */

		/**
		 * TODO RECORDS: Get metadata from DBpedia.
		 * 
		 * */

		/**
		 * TODO FIX records: Get metadata from DBpedia.
		 * 
		 * CollectMetadataAboutSongsDBpedia.run();
		 * */

		/**
		 * run again for people.
		 * 
		 * CollectMetadataAboutGuests.run(); // run again for BOUND=0 records
		 * (and Sergey Vasilievich Rachmaninov = // Sergei Vasilievich
		 * Rachmaninoff!!! ) - but use the fixed queries
		 * CollectMetadataAboutSongsDBpedia.run();
		 */

		/**
		 * Collect metadata from MusicBrainz :
		 * 
		 * getMetaFromMusicBrainz.run(); *
		 * */

		// GET THE UNIVERSITY FOR PEOPLE.
		NormalizeData.castaways();
		NormalizeData.records();
		//

	}
}
