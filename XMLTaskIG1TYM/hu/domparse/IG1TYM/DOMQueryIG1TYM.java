package hu.domparse.IG1TYM;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryIG1TYM
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
    {
		//Forrás file 
		File file = new File("XMLIG1TYM.xml");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		 
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();

		// Gyökér elem
		System.out.print("Gyökér elem: ");
        System.out.println(doc.getDocumentElement().getNodeName());

        // Autók kilistázása
        NodeList nList = doc.getElementsByTagName("auto");
         
        System.out.println("-----------------");
         
        // Végigfut az "auto"-nak a gyerekelemein, kihagyva a "szin"-t
        for(int i = 0; i < nList.getLength(); i++)
        {
        	Node node = nList.item(i);
            System.out.println("\nJelenlegi elem: " + node.getNodeName());

            if(node.getNodeType() == Node.ELEMENT_NODE)
            {
            	Element elem = (Element) node;

            	System.out.println("Alvázszám: " + elem.getAttribute("alvazszam"));

            	NodeList nList2 = elem.getChildNodes();

            	for(int j = 0; j < nList2.getLength(); j++)
                {
            		Node node2 = nList2.item(j);

					if(node2.getNodeType() == Node.ELEMENT_NODE)
                    {
						if(!node2.getNodeName().equals("szin"))
                        {
							System.out.println(node2.getNodeName() + " : " + node2.getTextContent());
						}
					}
				}
            }
		}
	}
}