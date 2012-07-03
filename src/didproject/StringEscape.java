package didproject;

public class StringEscape {

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
  		str = str.replaceAll(",", "%2C");
  		str = str.replaceAll("-", "%2D");
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
  		str = str.replaceAll("%2C", ",");
  		str = str.replaceAll("%2D", "-");
  		return str;
     }
     
}
