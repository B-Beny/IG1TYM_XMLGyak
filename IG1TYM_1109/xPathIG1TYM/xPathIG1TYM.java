package xPathIG1TYM;

import java.beans.Expression;
import java.io.File;
import java.io.IOException;
import java.nio.channels.spi.AsynchronousChannelProvider;

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

public class xPathIG1TYM
{
    public static void main(String[] args)
    {
        try
        {
            // File xmlFile = new File("student.xml");
            // DocumentBuilder létrehozása

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("studentIG1TYM.xml");

            document.getDocumentElement().normalize();

            // az XPath készítése
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Meg kell adni az elérési út kifejezést és a csompópont listát
            //String expression = "/class/student";
            //String expression = "//student[@id=02]";
            //String expression = "//student";
            //String expression = "/class/student[2]";
            //String expression = "/class/student[last()]";
            //String expression = "/class/student[last()-1]";
            //String expression = "/class/";
            //String expression = "/class/[@>=1]";
            //String expression = "/descendant-or-self::";
            //String expression = "/class/student[20<kor]";
            String expression = "/class/student/keresztnev | /class/student/vezeteknev";

            // Készítsünk egy listát, majd a xPath kifejezést le kell fordítani és ki kell értékelni
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            // A for ciklus segítségével a NodeList csomópontajin végig kell iterálni
            for(int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);

                System.out.println("\nAktuális elem: " + node.getNodeName());

                // Meg kell vizsgálni a csomópontot, tesztelni kell a subelemet
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student"))
                {
                    Element element = (Element) node;

                    // az id attribútumot ad vissza
                    System.out.println("Hallgató ID: " + element.getAttribute("id"));

                    // keresztnevet ad vissza
                    System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());

                    // vezetéknevet ad vissza
                    System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());

                    // becenevet ad vissza
                    System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());

                    // kort ad vissza
                    System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
                }
            }
        }
        catch (ParserConfigurationException e) {e.printStackTrace();}
        catch (SAXException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        catch (XPathExpressionException e) {e.printStackTrace();}
    }
}
