package hu_domparse_jptjy2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Scanner;


public class DomQueryJPTJY2 {

    private static XPath xPath = XPathFactory.newInstance().newXPath();
    private static Document document;


    public static void main(String[] args) {


        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            document = dBuilder.parse("XMLJPTJY2.xml");

            document.getDocumentElement().normalize();

            getAllPlayers();

            System.out.println("Search a player by id");
            Scanner input = new Scanner(System.in);
            String id = input.nextLine();
            getPlayerById(id);

            System.out.println("Search team by name");
            String team = input.nextLine();
            getTeamByName(team);

            System.out.println("Search player by position");
            String position = input.nextLine();
            getPlayersByPosition(position);

            System.out.println("Search staff by role");
            String role = input.nextLine();
            getStaffByRole(role);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAllPlayers() {


        String expression = String.format("/nba/jatekos");
        try {

            System.out.printf("NBA játékosok lekerdezese: \n");

            NodeList playersList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < playersList.getLength(); i++) {

                Node node = playersList.item(i);

                System.out.println("Element: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) node;

                    String id1 = elem.getAttribute("j_id");
                    String id2 = elem.getAttribute("cs_j");
                    String jatekosNev = elem.getElementsByTagName("nev").item(0).getTextContent();
                    String jatekosCsapata = elem.getElementsByTagName("csapat").item(0).getTextContent();
                    String jatekosKora = elem.getElementsByTagName("kor").item(0).getTextContent();

                    System.out.println("Játekos Id : " + id1);
                    System.out.println("Csapat Id : " + id2);
                    System.out.println("Név : " + jatekosNev);
                    System.out.println("Csapat : " + jatekosCsapata);
                    System.out.println("Kor : " + jatekosKora);
                    System.out.println("------------------");

                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }

    public static void getPlayerById(String id) {

        String idAttribute = "j_id";
        String expression = String.format("/nba/jatekos[@%s = '%s']", idAttribute, id);

        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("Id alapjan lekerdezés: \n");

            NodeList playerList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < playerList.getLength(); i++) {

                Node node = playerList.item(i);

                System.out.println("Element: " + node.getNodeName());

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
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }

    private static void getTeamByName(String teamName) {

        String expression = String.format("/nba/csapat[nev='%s']", teamName);
        try {

            System.out.printf("Csapat a nev alapjan lekerdezés: \n");

            NodeList teamList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < teamList.getLength(); i++) {

                Node node = teamList.item(i);

                System.out.println("Element: " + node.getNodeName());

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

                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private static void getPlayersByPosition(String position) {

        String expression = String.format("/nba/jatekos[pozicio='%s']", position);
        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("Jatékosok pozíció alapján: \n");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node jatekosList = nodeList.item(i);

                System.out.println("\nElement: " + jatekosList.getNodeName());

                if (jatekosList.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) jatekosList;

                    String id1 = element.getAttribute("j_id");
                    String id2 = element.getAttribute("cs_j");

                    String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                    String csapat = element.getElementsByTagName("csapat").item(0).getTextContent();
                    String kor = element.getElementsByTagName("kor").item(0).getTextContent();
                    String pozicio1 = element.getElementsByTagName("pozicio").item(0).getTextContent();
                    String pozicio2 = element.getElementsByTagName("pozicio").item(1).getTextContent();


                    System.out.println("Jelenlegi elem :" + element.getNodeName());
                    System.out.println("Játekos Id : " + id1);
                    System.out.println("Csapat Id : " + id2);
                    System.out.println("Név : " + nev);
                    System.out.println("Csapat : " + csapat);
                    System.out.println("Kor : " + kor);
                    System.out.println("Pozíció : " + "Elsődleges : " + pozicio1 + " Másodlagos : " + pozicio2);
                    System.out.println("------------------");
                }
            }
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getStaffByRole(String role){

        String expression = String.format("/nba/stabtag[beosztas = '%s']", role);

        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("Stabtag beosztás alapján: \n");

            NodeList staffList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < staffList.getLength(); i++) {

                Node node = staffList.item(i);

                System.out.println("Element: " + node.getNodeName());

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
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}