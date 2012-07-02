package didproject;

import java.util.ArrayList;

public class Person {

	private String name;
	private String link;
	private String dateOfBirth;
	private ArrayList<String> categories;
	
	public Person(String name, String link) {
		this.setName(name);
		this.setLink(link);
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String createDBpediaLink (String wikiLink) {
		wikiLink = wikiLink.replaceAll("http://en.wikipedia.org/wiki/","http://dbpedia.org/resource/");
		wikiLink = wikiLink.replaceAll(" ","%20");
		wikiLink = wikiLink.replaceAll("\"","%22");
		wikiLink = wikiLink.replaceAll("\'","%27");
		wikiLink = wikiLink.replaceAll("\\(","%28");
		wikiLink = wikiLink.replaceAll("\\)","%29");
		wikiLink = wikiLink.replaceAll("\\*","%2A");
		wikiLink = wikiLink.replaceAll("\\+","%2B");
		wikiLink = wikiLink.replaceAll(",","%2C");
		wikiLink = wikiLink.replaceAll("-","%2D");
		return wikiLink;
	}
}
