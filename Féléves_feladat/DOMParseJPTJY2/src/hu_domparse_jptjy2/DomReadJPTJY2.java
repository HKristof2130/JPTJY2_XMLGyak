package hu_domparse_jptjy2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DomReadJPTJY2 {
    public static void main(String[] args) {
        DomReader();
    }

    public static void  DomReader(){
        readSzezon();
        readCsapat();
        readEdzo();
        readStabtag();
        readJatekos();
    }

    //kiolvasom az összes játékost
    private static void readJatekos(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("XMLJPTJY2.xml"));

            doc.getDocumentElement().normalize();

            NodeList jatekosList = doc.getElementsByTagName("jatekos");

            for (int temp = 0; temp < jatekosList.getLength(); temp++) {

                Node node = jatekosList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String id1 = element.getAttribute("j_id");
                    String id2 = element.getAttribute("cs_j");
                    String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                    String csapat = element.getElementsByTagName("csapat").item(0).getTextContent();
                    String kor = element.getElementsByTagName("kor").item(0).getTextContent();
                    String pozicio1 = element.getElementsByTagName("pozicio").item(0).getTextContent();
                    String pozicio2 = element.getElementsByTagName("pozicio").item(1).getTextContent();


                    System.out.println("Jelenlegi elem :" + node.getNodeName());
                    System.out.println("Játekos Id : " + id1);
                    System.out.println("Csapat Id : " + id2);
                    System.out.println("Név : " + nev);
                    System.out.println("Csapat : " + csapat);
                    System.out.println("Kor : " + kor);
                    System.out.println("Pozíció : " + "Elsődleges : " + pozicio1 + " Másodlagos : " + pozicio2);
                    System.out.println("------------------");

                    try (FileWriter fw = new FileWriter("DOMRead.txt", true);) {

                        // fajlba iras megkezdése
                        fw.write("Jatekos:------ \n");
                        fw.write("Jatekos id:" + id1 + "\n");
                        fw.write("Csapat id:" + id2 + "\n");
                        fw.write("Nev: \n" + nev + "\n");
                        fw.write("Csapat: \n" + csapat + "\n");
                        fw.write("Pozíció: \n" + pozicio1 + "\n" + pozicio2 + "\n");
                        fw.write("\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    //kiolvasom az összes stábtagot
    private static void readStabtag(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("XMLJPTJY2.xml"));

            doc.getDocumentElement().normalize();

            NodeList stabtagList = doc.getElementsByTagName("stabtag");

            for (int temp = 0; temp < stabtagList.getLength(); temp++) {

                Node node = stabtagList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String id1 = element.getAttribute("s_id");
                    String id2 = element.getAttribute("cs_s");
                    String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                    String csapat = element.getElementsByTagName("csapat").item(0).getTextContent();
                    String beosztas = element.getElementsByTagName("beosztas").item(0).getTextContent();


                    System.out.println("Jelenlegi elem :" + node.getNodeName());
                    System.out.println("Stabtag Id : " + id1);
                    System.out.println("Csapat Id : " + id2);
                    System.out.println("Név : " + nev);
                    System.out.println("Csapat : " + csapat);
                    System.out.println("Beosztás : " + beosztas);
                    System.out.println("------------------");

                    try (FileWriter fw = new FileWriter("DOMRead.txt", true);) {

                        // fajlba iras megkezdése
                        fw.write("Stabtag:------ \n");
                        fw.write("Stabtag id:" + id1 + "\n");
                        fw.write("Csapat id:" + id2 + "\n");
                        fw.write("Nev: \n" + nev + "\n");
                        fw.write("Csapat: \n" + csapat + "\n");
                        fw.write("Beosztás: \n" + beosztas + "\n");
                        fw.write("\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    //kiolvasom az összes edzőt
    private static void readEdzo(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("XMLJPTJY2.xml"));

            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList edzoList = doc.getElementsByTagName("edzo");

            for (int temp = 0; temp < edzoList.getLength(); temp++) {

                Node node = edzoList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String id1 = element.getAttribute("e_id");
                    String id2 = element.getAttribute("cs_e");
                    String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                    String csapat= element.getElementsByTagName("csapat").item(0).getTextContent();
                    String aktivSzezon= element.getElementsByTagName("aktiv_szezon").item(0).getTextContent();


                    System.out.println("Jelenlegi elem :" + node.getNodeName());
                    System.out.println("Edzo Id : " + id1);
                    System.out.println("Csapat Id : " + id2);
                    System.out.println("Név : " + nev);
                    System.out.println("Csapat : " + csapat);
                    System.out.println("Szezon aktív edzőként : " + aktivSzezon);
                    System.out.println("------------------");

                    try (FileWriter fw = new FileWriter("DOMRead.txt", true);) {

                        // fajlba iras megkezdése
                        fw.write("Edzok:------ \n");
                        fw.write("Edzo id:" + id1 + "\n");
                        fw.write("Csapat id:" + id2 + "\n");
                        fw.write("Nev: \n" + nev + "\n");
                        fw.write("Csapat: \n" + csapat + "\n");
                        fw.write("Szezon aktív edzőként: \n" + aktivSzezon + "\n");
                        fw.write("\n\n");
                       } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    //kiolvasom az összes csapatot
    private  static void readCsapat(){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("XMLJPTJY2.xml"));

            doc.getDocumentElement().normalize();

            NodeList csapatList = doc.getElementsByTagName("csapat");


            for (int temp = 0; temp < 3 ; temp++) {

                System.out.println(temp);
                Node node = csapatList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String id = element.getAttribute("cs_id");
                    String csapatNeve = element.getElementsByTagName("nev").item(0).getTextContent();
                    String arena = element.getElementsByTagName("arena").item(0).getTextContent();
                    String bajnokiCimek = element.getElementsByTagName("bajnoki_cimek").item(0).getTextContent();


                    System.out.println("Jelenlegi elem :" + node.getNodeName());
                    System.out.println("Csapat Id : " + id);
                    System.out.println("Csapat neve : " + csapatNeve);
                    System.out.println("Aréna : " + arena);
                    System.out.println("Bajnoki címek : " + bajnokiCimek);
                    System.out.println("------------------");

                    try (FileWriter fw = new FileWriter("DOMRead.txt", true);) {

                        // fajlba iras megkezdése
                        fw.write("Csapat:------ \n");
                        fw.write("Csapat id:" + id + "\n");
                        fw.write("Aréna: \n" + arena + "\n");
                        fw.write("Bajnoki címek: \n" + bajnokiCimek + "\n");
                        fw.write("\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    //kiolvasom az összes szezont
    private static void readSzezon(){

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("XMLJPTJY2.xml"));

            doc.getDocumentElement().normalize();


            NodeList szezonList = doc.getElementsByTagName("szezon");

            for (int temp = 0; temp < szezonList.getLength(); temp++) {

                Node node = szezonList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String id = element.getAttribute("sz_id");
                    String nevezettCsapatokSzama = element.getElementsByTagName("nevezettcsapatok_szama").item(0).getTextContent();
                    String teljesSzezon = element.getElementsByTagName("teljes_szezon").item(0).getTextContent();
                    String szezonIdotartama = element.getElementsByTagName("kezdet").item(0).getTextContent() + " - " + element.getElementsByTagName("kezdet").item(0).getTextContent();


                    System.out.println("Jelenlegi elem :" + node.getNodeName());
                    System.out.println("Szezon Id : " + id);
                    System.out.println("Nevezett csapatok szama : " + nevezettCsapatokSzama);
                    System.out.println("Teljes szezon : " + teljesSzezon);
                    System.out.println("Szezon idotartama : " + szezonIdotartama);
                    System.out.println("------------------");

                    try (FileWriter fw = new FileWriter("DOMRead.txt", true);) {

                        // fajlba iras megkezdése
                        fw.write("Szezon:------ \n");
                        fw.write("Szezon id:" + id + "\n");
                        fw.write("Nevezett csapatok száma: \n" + nevezettCsapatokSzama + "\n");
                        fw.write("Teljes szezon: \n" + teljesSzezon + "\n");
                        fw.write("Szezon időtartama: \n" + szezonIdotartama + "\n");
                        fw.write("\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
