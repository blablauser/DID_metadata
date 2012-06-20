package didproject;

 import java.io.StringReader;  
   
 import javax.xml.parsers.DocumentBuilder;  
 import javax.xml.parsers.DocumentBuilderFactory;  
 import org.w3c.dom.Document;  
 import org.xml.sax.InputSource;  
 
   
 public class StringToXML {  
     public static Document parse (String xmlString) {  
    	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
    	    DocumentBuilder builder;  
    	    try  
    	    {  
    	        builder = factory.newDocumentBuilder();  
    	        Document document = builder.parse( new InputSource( new StringReader( xmlString ) ) );
    	        return document;
    	    } catch (Exception e) {  
    	        e.printStackTrace();  
    	    }
			return null; 
     }  
 }  
   