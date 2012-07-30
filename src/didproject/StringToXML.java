package didproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class StringToXML {
	public static Document parse(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					xmlString)));
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String UrlToString(String urlString) {
		try {
			System.out.println(urlString);
			URL url = new URL(urlString);

			@SuppressWarnings("unused")
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			String inputLine;
			String xmlString = "";
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				xmlString += inputLine;
			}

			in.close();
			return xmlString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
