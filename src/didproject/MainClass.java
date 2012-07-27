package didproject;

import java.util.Random;

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
		 * CollectMetaSongsFixed.run();#
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
		CollectMetadataAboutSongsDBpedia.run();
		// GET THE UNIVERSITY FOR PEOPLE.
		//StringEscape.generateRandom(100, 13110);

	}
}
