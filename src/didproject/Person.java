package didproject;

public class Person {

	private String name;
	private String link;
	
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
	
}
