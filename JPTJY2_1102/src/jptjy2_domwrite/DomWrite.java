package jptjy2_domwrite;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


public class DomWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try (InputStream is = new FileInputStream("usersJPTJY2.xml")) {

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(is);

            NodeList userList = doc.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {
            	
                Node user = userList.item(i);
                if (user.getNodeType() == Node.ELEMENT_NODE) {
                    String id = user.getAttributes().getNamedItem("id").getTextContent();
                    

                        NodeList childNodes = user.getChildNodes();

                        Element neptunCode = doc.createElement("neptunkod");
                        CDATASection cdataSection = doc.createCDATASection("xmlns:Neptunkod");

                        neptunCode.appendChild(cdataSection);

                        user.appendChild(neptunCode);

                }

            }

            try (FileOutputStream output =
                         new FileOutputStream("user1JPTJY2.xml")) {
                writeXml(doc, output);
            }

        } catch (ParserConfigurationException | SAXException
                | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }

    // write doc to output stream
    private static void writeXml(Document doc, OutputStream output) throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
	}


