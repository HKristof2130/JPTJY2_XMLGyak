package hu_domparse_jptjy2;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DomModifyJPTJY2 {

    private static Document doc;

    public static void main(String argv[]){
        try {
            DomModify();
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void DomModify()  throws SAXException,
            IOException, ParserConfigurationException{

        File xmlFile = new File("XMLJPTJY2.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        doc = dBuilder.parse(xmlFile);

        modifyNo2SeasonsId();

        modifySeasonsToNotWhole();

        addChampionToNo2Season();

        modifyStaffMembersRoleToHeadCoach();

        try (FileOutputStream output = new FileOutputStream("ModifiedXMLJPTJY2.xml")) {
            writeXml(doc, output);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void deleteAgeElementFromEveryPlayer(){

        NodeList jatekosList = doc.getElementsByTagName("jatekos");

        for (int i = 0; i < jatekosList.getLength(); i++) {
            Node jatekos = jatekosList.item(i);

            NodeList childNodes = jatekos.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {

                Node element = childNodes.item(j);

                if (element.getNodeType() == Node.ELEMENT_NODE) {

                    if ("kor".equalsIgnoreCase(element.getNodeName())) {
                        jatekos.removeChild(element);
                    }
                }
            }

        }
    }

    private static void addChampionToNo2Season(){

        NodeList szezonList = doc.getElementsByTagName("szezon");
        Node szezon = szezonList.item(2);

        Element bajnok = doc.createElement("bajnok");
        bajnok.appendChild(doc.createTextNode("LosAngeles Lakers"));
        szezon.appendChild(bajnok);
    }

    private static void modifyNo2SeasonsId(){

        NodeList szezonokList = doc.getElementsByTagName("szezon");
        Node szezon = szezonokList.item(1);

        szezon.getAttributes().getNamedItem("sz_id").setTextContent("4");
    }

    private static void modifySeasonsToNotWhole() {
        NodeList szezonList = doc.getElementsByTagName("szezon");
        modifyElement(szezonList, "teljes_szezon", "Igen");
    }

    private static void modifyStaffMembersRoleToHeadCoach(){
        NodeList staffList = doc.getElementsByTagName("stabtag");
        modifyElement(staffList,"beosztas","Head Coach");

    }

    //Element tag értékek modositasahoz metódus
    public static void modifyElement(NodeList nodeList, String tagName, String newValue) {
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element elem = (Element) node;
                try {
                    elem.getElementsByTagName(tagName).item(0).setTextContent(newValue);
                } catch (DOMException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void writeXml(Document doc, FileOutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File("JPTJY2Style.xslt")));

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
