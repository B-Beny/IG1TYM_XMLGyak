package domig1tym;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadIG1TYM
{
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException
    {
        File xmlFile = new File("usersIG1TYM.xml");

        javax.xml.parsers.DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //DOM fa előállítás
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        //normalizálás segít a helyes elemek elérésében

        //Gyökérelem kiírása
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("user");

        for(int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            System.out.println("\nCurrentElement: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;
                
                String uid = elem.getAttribute("id");

                Node nodel = elem.getElementsByTagName("firstname").item(0);
                String fname = nodel.getTextContent();

                Node node2 = elem.getElementsByTagName("lastname").item(0);
                String lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("profession").item(0);
                String pname = node3.getTextContent();

                System.out.println("User id: " + uid);
                System.out.println("Firstname: " + fname);
                System.out.println("Lastname: " + lname);
                System.out.println("Profession: " + pname);
            }
        }
    }
}
