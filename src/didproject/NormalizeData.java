package didproject;

import java.util.ArrayList;

public class NormalizeData {
	public static void castaways() {
		DBManager manager = new DBManager();
		Person castaway;
		ArrayList<String> castawayIDs = new ArrayList<String>();
		// get all persons in DB - to query for them
		castawayIDs = manager.getIds("castaway", "castawayID");

		for (int i = 0; i < castawayIDs.size(); i++) {
			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getCastaway("castaway", castawayIDs.get(i));
			castaway = new Person(Integer.parseInt(fields.get(0)),
					fields.get(1), fields.get(2), fields.get(3), fields.get(4),
					fields.get(5), Integer.parseInt(fields.get(6)),
					Integer.parseInt(fields.get(7)));

			// Set the main occupation
			castaway.mainOccupation();
			
			ArrayList<String> categories = new ArrayList<String>();
			ArrayList<String> cat_fields = new ArrayList<String>();
			// get all Categories for castaway.
			cat_fields = manager.getCategoriesforCastaway(Integer
					.toString(castaway.getId()));
			for (int j = 0; j < cat_fields.size(); j++) {
				categories.add(cat_fields.get(j));
			}
			castaway.setCategories(categories);
			// castaway.resolvePeopleFrom();
			// castaway.resolveEducation();
			// castaway.normalizeCastaway(manager);
			castaway.resolveAnyEducation();
			manager.flagCastaway(castaway.getId(), "studies",
					castaway.getStudies());

		}

	}

	public static void records() {
		DBManager manager = new DBManager();
		Record record;
		ArrayList<String> recordIDs = new ArrayList<String>();
		// get all songs in DB - to query for them
		recordIDs = manager.getIds("record", "recordID");
		int gender_err = 0;

		for (int i = 0; i < recordIDs.size(); i++) {
			/**
			 * int classical, String mbz_type, String mbz_gender, String
			 * mbz_country, String mbz_disambiguation, String mbz_life_begin,
			 * String mbz_life_end, String mbz_life_ended, String mbz_arid, int
			 * mbz_found
			 */
			// for each castawayID!!! get the rest of the fields
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getRecord("record", recordIDs.get(i));
			record = new Record(Integer.parseInt(fields.get(0)), fields.get(1),
					fields.get(2), fields.get(3), fields.get(4), fields.get(5),
					fields.get(6), fields.get(7), fields.get(8),
					Double.parseDouble(fields.get(9)), fields.get(10),
					fields.get(11), fields.get(12), Integer.parseInt(fields
							.get(13)), Integer.parseInt(fields.get(14)),
					Integer.parseInt(fields.get(15)), fields.get(16),
					fields.get(17), fields.get(18), fields.get(19),
					fields.get(20), fields.get(21), fields.get(22),
					fields.get(23), Integer.parseInt(fields.get(24)));

			//

			if (record.getGender() != record.getMbz_gender()) {
				if (record.getMbz_type() != record.getGender()) {
					gender_err++;
				}
			}
		}

		System.out.println("Gender err, DBpedia vs MBZ: (out of 13035) "
				+ gender_err);
	}
}
