package didproject;

public class Episode {
	private int episodeID;
	private int castawayID;
	private int luxuryID;
	private String dateOfBroadcast;
	private int age;
	private String occupations;

	public int getEpisodeID() {
		return episodeID;
	}

	public void setEpisodeID(int episodeID) {
		this.episodeID = episodeID;
	}

	public int getCastawayID() {
		return castawayID;
	}

	public void setCastawayID(int castawayID) {
		this.castawayID = castawayID;
	}

	public int getLuxuryID() {
		return luxuryID;
	}

	public void setLuxuryID(int luxuryID) {
		this.luxuryID = luxuryID;
	}

	public String getDateOfBroadcast() {
		return dateOfBroadcast;
	}

	public void setDateOfBroadcast(String dateOfBroadcast) {
		this.dateOfBroadcast = dateOfBroadcast;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getOccupations() {
		return occupations;
	}

	public void setOccupations(String occupations) {
		this.occupations = occupations;
	}

	public Episode(int episodeID, int castawayID, int luxuryID,
			String dateOfBroadcast, int age, String occupations) {
		this.setEpisodeID(episodeID);
		this.setCastawayID(castawayID);
		this.setLuxuryID(luxuryID);
		this.setDateOfBroadcast(dateOfBroadcast);
		this.setAge(age);
		this.setOccupations(occupations);

	}

}
