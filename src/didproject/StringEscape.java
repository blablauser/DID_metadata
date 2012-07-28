package didproject;

import java.util.Random;

public class StringEscape {
	
	public static void generateRandom(int number, int max) {
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= number; ++idx) {
			int randomInt = randomGenerator.nextInt(max);
			System.out.print("," + randomInt);
		}

	}
	

	public static String escapeSql(String str) {
		str = str.replaceAll("\'", "%27");

		return str;
	}

	public static String unescapeSql(String str) {
		str = str.replaceAll("%27", "\'");

		return str;
	}

	public static String escapeUrl(String str) {

		str = str.replaceAll("\"", "%22");
		str = str.replaceAll("\'", "%27");
		str = str.replaceAll("\\(", "%28");
		str = str.replaceAll("\\)", "%29");
		str = str.replaceAll("\\*", "%2A");
		str = str.replaceAll("\\+", "%2B");
		//str = str.replaceAll("/", "%2F");
		// str = str.replaceAll(",", "%2C");
		// str = str.replaceAll("-", "%2D");
		return str;
	}
	
	public static String escapeSparqlURL(String str) {

		str = str.replaceAll("\"", "%22");
		str = str.replaceAll("\'", "%27");
//		str = str.replaceAll("\\(", "%28");
//		str = str.replaceAll("\\)", "%29");
//		str = str.replaceAll("\\*", "%2A");
//		str = str.replaceAll("\\+", "%2B");
		//str = str.replaceAll("/", "%2F");
		// str = str.replaceAll(",", "%2C");
		// str = str.replaceAll("-", "%2D");
		return str;
	}

	public static String unescapeUrl(String str) {
		str = str.replaceAll("%20", " ");
		str = str.replaceAll("%22", "\"");
		str = str.replaceAll("%27", "\'");
		str = str.replaceAll("%28", "\\(");
		str = str.replaceAll("%29", "\\)");
		str = str.replaceAll("%2A", "\\*");
		str = str.replaceAll("%2B", "\\+");
//		str = str.replaceAll("%2C", ",");
//		str = str.replaceAll("%2D", "-");
		//str = str.replaceAll("%2F", "/");
		return str;
	}

	public static int countOccurances(String initial, String toFind) {
		// from
		// http://stackoverflow.com/questions/767759/occurences-of-substring-in-a-string

		int lastIndex = 0;
		int count = 0;

		while (lastIndex != -1) {
			lastIndex = initial.indexOf(toFind, lastIndex);
			if (lastIndex != -1) {
				count++;
				lastIndex += toFind.length();
			}
		}
		return count;
	}
	
	public static String escapeBifContains(String str) {
		str = str.trim();

		if (str.startsWith("\'")) {
			str = str.substring(1, str.length());
		}
		if (str.endsWith("\'")) {
			str = str.substring(0, str.length()-1);
		}
		str = str.replaceAll("\'", "\' AND \'");
		str = str.replaceAll("\".+?\"", "");
		// if there are any other left, just delete them
		str = str.replaceAll("\"", "");
		str = str.replaceAll("\\(", "");
		str = str.replaceAll("\\)", "");
		str = str.replaceAll("\\+", "");
		str = str.replaceAll("\\*", "");
		return str;
	}
	
	public static String EncodeDBpediaResource(String str){
		str = str.replaceAll(" ", "_");
		str = "http://dbpedia.org/resource/"+str;
		return str;
	}

}
