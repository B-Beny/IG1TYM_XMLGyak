package hu.domparse.IG1TYM;

import java.io.IOException;

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


public class XPathQueryIG1TYM
{
    public static void main(String[] args)
    {
        try
        {
            // DocumentBuilder létrehozása
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("XMLIG1TYM.xml");

            document.getDocumentElement().normalize();

            // az XPath készítése
            XPath xPath = XPathFactory.newInstance().newXPath();

            // --------------------------------------------------------------------------------------------------------------------------
            // LEKÉRDEZÉSEK
            // --------------------------------------------------------------------------------------------------------------------------
            // Kiválasztja az autó utolsó elemét
            //String expression = "/gepjarmu-felepites/auto[last()]";

            // Kiválasztja azon autókat, amelyeknek a tömege legfeljebb 1200
            //String expression = "//auto[tomeg<1200]";

            // Kiválasztja a gyártó illetve modell elemeket
            //String expression = "//gyarto | //modell";

            // Kiválasztja az első és harmadik motort
            //String expression = "//motor[1] | //motor[3]";

            // Kiválasztja azt a gyártót, akinek adószáma 123
            String expression = "//gyarto[@adoszam=123]";
            // --------------------------------------------------------------------------------------------------------------------------

            // Készítsünk egy listát, majd a xPath kifejezést le kell fordítani és ki kell értékelni
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            // A for ciklus segítségével a NodeList csomópontajin végig kell iterálni
            for(int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet

                // autó csomópont
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("auto"))
                {
                    Element element = (Element) node;

                    System.out.println("Alvázszám: " + element.getAttribute("alvazszam"));
                    System.out.println("Név: " + element.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Tömeg: " + element.getElementsByTagName("tomeg").item(0).getTextContent());

                    for(int j = 0; j < element.getElementsByTagName("szin").getLength(); j++)
                    {
                        System.out.println("Szín: " + element.getElementsByTagName("szin").item(j).getTextContent());
                    }

                    System.out.println("Teljesítmény: " + element.getElementsByTagName("teljesitmeny").item(0).getTextContent());
                }

                // gyártó csomópont
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("gyarto"))
                {
                    Element element = (Element) node;

                    System.out.println("Adószám: " + element.getAttribute("adoszam"));
                    System.out.println("Név: " + element.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Cím:");
                    System.out.println("Ország: " + element.getElementsByTagName("orszag").item(0).getTextContent());
                    System.out.println("Irányítószám: " + element.getElementsByTagName("iranyitoszam").item(0).getTextContent());
                    System.out.println("Település: " + element.getElementsByTagName("telepules").item(0).getTextContent());
                }

                // modell csomópont
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("modell"))
                {
                    Element element = (Element) node;

                    System.out.println("Név: " + element.getAttribute("nev"));
                    System.out.println("Ajtók száma: " + element.getElementsByTagName("ajtok_szama").item(0).getTextContent());
                    System.out.println("Csomagtartó méret: " + element.getElementsByTagName("csomagtarto_meret").item(0).getTextContent());
                    System.out.println("Lehajtható tető: " + element.getElementsByTagName("lehajthato_teto").item(0).getTextContent());
                }

                // motor csomópont
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("motor"))
                {
                    Element element = (Element) node;

                    System.out.println("Motorkód: " + element.getAttribute("motorkod"));
                    System.out.println("Alaprajz:");
                    System.out.println("Hengerűrtartalom: " + element.getElementsByTagName("hengerurtartalom").item(0).getTextContent());
                    System.out.println("Hengerelrendezés: " + element.getElementsByTagName("hengerelrendezes").item(0).getTextContent());
                    System.out.println("Hengerek száma: " + element.getElementsByTagName("hengerek_szama").item(0).getTextContent());

                    for(int j = 0; j < element.getElementsByTagName("uzemanyag").getLength(); j++)
                    {
                        System.out.println("Üzemanyag: " + element.getElementsByTagName("uzemanyag").item(j).getTextContent());
                    }

                    System.out.println("Nyomaték: " + element.getElementsByTagName("nyomatek").item(0).getTextContent());
                }

            }
        }
        catch (ParserConfigurationException e) {e.printStackTrace();}
        catch (SAXException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        catch (XPathExpressionException e) {e.printStackTrace();}
    }
}