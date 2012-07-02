package didproject;

import java.util.ArrayList;

public class Person {
	private int id;
	private String name;
	private String link;
	private String DBlink;
	private String gender;
	private String occupations;
	private String dateOfBirth;
	private boolean onDBpedia;

	private ArrayList<String> categories;

	public Person(int id, String name, String link, String gender,
			String occupations) {
		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setGender(gender);
		this.setOccupations(occupations);
		this.setOnDBpedia(true);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setOccupations(String occupations) {
		this.occupations = occupations;
	}

	public String getOccupations() {
		return occupations;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String createDBpediaLink(String wikiLink) {
		wikiLink = wikiLink.replaceAll("http://en.wikipedia.org/wiki/",
				"http://dbpedia.org/resource/");
		wikiLink = wikiLink.replaceAll(" ", "%20");
		wikiLink = wikiLink.replaceAll("\"", "%22");
		wikiLink = wikiLink.replaceAll("\'", "%27");
		wikiLink = wikiLink.replaceAll("\\(", "%28");
		wikiLink = wikiLink.replaceAll("\\)", "%29");
		wikiLink = wikiLink.replaceAll("\\*", "%2A");
		wikiLink = wikiLink.replaceAll("\\+", "%2B");
		wikiLink = wikiLink.replaceAll(",", "%2C");
		wikiLink = wikiLink.replaceAll("-", "%2D");
		return wikiLink;
	}

	public void addOccupations(DBManager manager) {
		String[] occupationsList = this.occupations.split(";");
		for (int j = 0; j < occupationsList.length; j++) {

			int occupationID = manager.exists("occupation", "name",
					occupationsList[j]);
			if (occupationID > 0) {
				// id exists in db, then add FK for "worksAs"

				manager.addWorksAs(this.getId(), occupationID);
			} else {
				// add occupation to the DB
				manager.addOccupation(occupationsList[j]);
				if (manager.exists("occupation", "name", occupationsList[j]) > 0) {
					manager.addWorksAs(this.getId(), manager.exists(
							"occupation", "name", occupationsList[j]));
				}
			}
		}
	}

	public void flagCastaway(DBManager manager) {
		manager.flagCastaway(this.getId(), "found", this.getOnDBpedia());
	}
	
	public void addDateOfBirthToDatabase(DBManager manager) {
		manager.addDoBCastaway(this.getId(), "dateOfBirth", Integer.parseInt(this.getDateOfBirth()));
	}

	public void setOnDBpedia(boolean isOnDBpedia) {
		this.onDBpedia = isOnDBpedia;
	}

	public boolean isOnDBpedia() {
		return onDBpedia;
	}

	public int getOnDBpedia() {
		if (this.onDBpedia)
			return 1;
		else
			return 0;
	}

	public void setDBlink(String dBlink) {
		DBlink = dBlink;
	}

	public String getDBlink() {
		return DBlink;
	}
}
