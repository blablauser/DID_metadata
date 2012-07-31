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
	private String key;
	private int found;
	private int dangerous;
	private boolean onDBpedia;

	// ##################################

	private String occupation;
	private String peopleFrom;
	private int alumni;
	private int school;
	private int high_school;
	private int masters;
	private int studies;

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPeopleFrom() {
		return peopleFrom;
	}

	public void setPeopleFrom(String peopleFrom) {
		this.peopleFrom = peopleFrom;
	}

	public int getAlumni() {
		return alumni;
	}

	public void setAlumni(int alumni) {
		this.alumni = alumni;
	}

	public int getSchool() {
		return school;
	}

	public void setSchool(int school) {
		this.school = school;
	}

	public int getHigh_school() {
		return high_school;
	}

	public void setHigh_school(int high_school) {
		this.high_school = high_school;
	}

	public int getMasters() {
		return masters;
	}

	public void setMasters(int masters) {
		this.masters = masters;
	}

	private ArrayList<String> categories;

	public Person(int id, String name, String link, String gender,
			String occupations, String dateOfBirth, int found, int dangerous) {
		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setGender(gender);
		this.setOccupations(occupations);
		this.setDateOfBirth(dateOfBirth);
		if (found == 1)
			this.setOnDBpedia(true);
		else
			this.setOnDBpedia(false);
		this.setKey("");
		this.setDangerous(dangerous);
		/**
		 * private String occupation; private String peopleFrom; private int
		 * alumni; private int school; private int high_school; private int
		 * masters;
		 */
		this.setAlumni(0);
		this.setSchool(0);
		this.setHigh_school(0);
		this.setMasters(0);
		this.setPeopleFrom("");
		this.setOccupation("");
		this.setCategories(new ArrayList<String>());

	}

	public Person(int id, String name, String link, String gender,
			String occupations) {
		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setGender(gender);
		this.setDateOfBirth("0");
		this.setKey("");
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

	public void setFound(int found) {
		this.found = found;
	}

	public int getFound() {
		return found;
	}

	public void setDangerous(int dangerous) {
		this.dangerous = dangerous;
	}

	public int getDangerous() {
		return dangerous;
	}

	public String createDBpediaLink(String wikiLink) {
		wikiLink = wikiLink.replaceAll("http://en.wikipedia.org/wiki/",
				"http://dbpedia.org/resource/");
		wikiLink = StringEscape.escapeUrl(wikiLink);
		return wikiLink;
	}

	public void addOccupations(DBManager manager) {
		String[] occupationsList = this.occupations.split(";");
		for (int j = 0; j < occupationsList.length; j++) {

			int occupationID = manager.exists("occupationID", "occupation",
					"name", StringEscape.escapeSql(occupationsList[j]));
			if (occupationID > 0) {
				// id exists in db, then add FK for "worksAs"

				manager.addWorksAs(this.getId(), occupationID);
			} else {
				// add occupation to the DB
				manager.addOccupation(StringEscape
						.escapeSql(occupationsList[j]));
				int var = manager.exists("occupationID", "occupation", "name",
						StringEscape.escapeSql(occupationsList[j]));
				if (var > 0) {

					manager.addWorksAs(this.getId(), var);
				} else
					System.out
							.println("Something, somwhere, went terribly WRONG! ");
			}
		}
	}

	public void flagCastaway(DBManager manager) {
		manager.flagCastaway(this.getId(), "found", this.getOnDBpedia());
		manager.flagCastaway(this.getId(), "dangerous", this.getDangerous());
	}

	public void addDateOfBirthToDatabase(DBManager manager) {
		manager.addDoBCastaway(this.getId(), "dateOfBirth",
				Integer.parseInt(this.getDateOfBirth()));
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

	public String getKey() {
		return key;
	}

	public void setKey(String description) {
		this.key = description;
	}

	public void mainOccupation() {
		String[] occupationsList = this.occupations.split(";");
		this.setOccupation(occupationsList[0]);
	}

	public void resolvePeopleFrom() {
		int i = 0;
		while (this.getPeopleFrom().isEmpty()
				&& i < this.getCategories().size()) {

			if (this.getCategories().get(i).startsWith("People_from_")) {
				this.setPeopleFrom(this.getCategories().get(i)
						.substring("People_from_".length()));
			}
			i++;
		}

	}

	public void resolveEducation() {
		int i = 0;
		while (this.getHigh_school() == 0 && i < this.getCategories().size()) {

			if (this.getCategories().get(i).endsWith("High_School_alumni")) {
				this.setHigh_school(1);
			}
			i++;
		}

		i = 0;
		while (this.getSchool() == 0 && i < this.getCategories().size()) {

			if (this.getCategories().get(i).startsWith("Former_pupils_of_")) {
				this.setSchool(1);
			} else {
				if (this.getCategories().get(i).endsWith("School_alumni")
						&& !this.getCategories().get(i)
								.endsWith("High_School_alumni")) {
					this.setSchool(1);
				} else {
					if (this.getCategories().get(i)
							.startsWith("People_educated_at")) {
						this.setSchool(1);
					}
				}
			}
			i++;
		}

		i = 0;
		while (this.getMasters() == 0 && i < this.getCategories().size()) {

			if (this.getCategories().get(i).startsWith("Masters_of_")) {
				this.setMasters(1);
			}
			i++;
		}

		i = 0;
		while (this.getAlumni() == 0 && i < this.getCategories().size()) {

			if (this.getCategories().get(i).endsWith("_alumni")
					&& !this.getCategories().get(i)
							.endsWith("High_School_alumni")
					&& !this.getCategories().get(i).endsWith("School_alumni")) {
				this.setAlumni(1);
			}

			if (this.getAlumni() == 0) {
				if (this.getCategories().get(i)
						.startsWith("Former_students_of_"))
					this.setAlumni(1);
			}
			i++;
		}

	}

	public void resolveAnyEducation() {
		int i = 0;
		while (this.getStudies() == 0 && i < this.getCategories().size()) {

			if (this.getCategories().get(i).endsWith("_alumni")
					|| this.getCategories().get(i).startsWith("Masters_of_")
					|| this.getCategories().get(i)
							.startsWith("Former_students_of_")
					|| this.getCategories().get(i)
							.startsWith("People_educated_at")
					|| this.getCategories().get(i)
							.startsWith("Former_pupils_of_")) {
				this.setStudies(1);
			}
			i++;
		}
	}

	public void normalizeCastaway(DBManager manager) {
		/**
		 * private String occupation; private String peopleFrom; private int
		 * alumni; private int school; private int high_school; private int
		 * masters;
		 */
		manager.updateCastaway(this.getId(), "main_occupation",
				this.getOccupation());
		manager.updateCastaway(this.getId(), "peopleFrom", this.getPeopleFrom());
		manager.updateCastaway(this.getId(), "alumni",
				Integer.toString(this.getAlumni()));
		manager.updateCastaway(this.getId(), "school",
				Integer.toString(this.getSchool()));
		manager.updateCastaway(this.getId(), "high_school",
				Integer.toString(this.getHigh_school()));
		manager.updateCastaway(this.getId(), "masters",
				Integer.toString(this.getMasters()));
	}

	public void setStudies(int studies) {
		this.studies = studies;
	}

	public int getStudies() {
		return studies;
	}

}
