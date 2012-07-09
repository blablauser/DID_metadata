package didproject;

import java.util.ArrayList;

public class AddAgeOfCastaway {
	public static void run() {
		// connect to DB and alter all instances of Episode to keep the
		// corresponding date of birth

		DBManager manager = new DBManager();
		ArrayList<String> episodeIDs = new ArrayList<String>();
		// get all persons in DB - to query for them
		episodeIDs = manager.getIds("episode", "episodeID");
		Episode episode;

		for (int i = 0; i < episodeIDs.size(); i++) {
			// get date of broadcast and castawayID
			ArrayList<String> fields = new ArrayList<String>();
			fields = manager.getEpisode("episode", episodeIDs.get(i));
			episode = new Episode(Integer.parseInt(fields.get(0)),
					Integer.parseInt(fields.get(1)), Integer.parseInt(fields
							.get(2)), fields.get(3), Integer.parseInt(fields
							.get(4)), fields.get(5));

			int dob = manager.exists("dateOfBirth", "castaway", "castawayID",
					Integer.toString(episode.getCastawayID()));

			String dateOfBroadcast = episode.getDateOfBroadcast();
			int yearOfBroadcast = Integer.parseInt(dateOfBroadcast
					.substring(dateOfBroadcast.length() - 4));

			int age;
			if (dob == 0)
				age = 0;
			else
				age = yearOfBroadcast - dob;

			System.out.println("epID:" + episode.getEpisodeID()
					+ ", date of birth:" + dob);
			System.out.println("Year of Broadcast:" + yearOfBroadcast);
			System.out.println("Age:" + age);

			manager.updateEpisode(episode.getCastawayID(), "age",
					Integer.toString(age));
		}
	}
}
