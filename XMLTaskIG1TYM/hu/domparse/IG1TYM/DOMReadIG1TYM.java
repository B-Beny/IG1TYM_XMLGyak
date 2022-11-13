package hu.domparse.IG1TYM;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMReadIG1TYM
{
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException
    {
        // XML illetve output TXT fájl létrehozása
        File xmlFile = new File("XMLIG1TYM.xml");
        File outputFile = new File("outputFile.txt");

        // Fájlíró létrehozása az output TXT-hez
        FileWriter fWriter = new FileWriter(outputFile, false);

        javax.xml.parsers.DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        // DOM fa előállítás
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        // Normalizálás segít a helyes elemek elérésében

        // Gyökérelem kiírása
        String root = doc.getDocumentElement().getNodeName();
        System.out.println("Gyökér elem: " + root);
        fWriter.write("Gyökér elem: " + root + "\n");

        // Adott nevű elem kilistázása
        NodeList nList = doc.getElementsByTagName("auto");

        for(int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            // Jelenlegi elem nevének kiolvasása, majd megjelenítése konzolon, kiírása a fájlba
            String currentNode = nNode.getNodeName();
            System.out.println("\nJelenlegi elem: " + currentNode);
            fWriter.write("\nJelenlegi elem: " + currentNode);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;
                
                // Alvázszámm attribútum kiolvasása 
                String alvaz = elem.getAttribute("alvazszam");

                // Név és tömeg elemek kiolvasása majd kiírása
                Node nodel = elem.getElementsByTagName("nev").item(0);
                String nev = nodel.getTextContent();

                Node node2 = elem.getElementsByTagName("tomeg").item(0);
                String tomeg = node2.getTextContent();

                System.out.println("Alvázszám: " + alvaz);
                System.out.println("Név: " + nev);
                System.out.println("Tömeg: " + tomeg);
                fWriter.write("\nAlvázszám: " + alvaz + "\nNév: " + nev + "\nTömeg: " + tomeg + "\n");

                // Mivel a szín elem többértékű, ezért egy for ciklussal olvasom ki mindegyiket,
                // amely olyan hosszú, ahány olyan nevű elem létezik 
                System.out.println("Elérhető színek: ");
                fWriter.write("Elérhető színek: ");
                for(int j = 0; j < elem.getElementsByTagName("szin").getLength(); j++)
                {
                Node node3 = elem.getElementsByTagName("szin").item(j);
                String szin = node3.getTextContent();
                System.out.println(" " + szin);
                fWriter.write("\n" + szin);
                }

                // Teljesítmény kiolvasása és kiírása
                Node node4 = elem.getElementsByTagName("teljesitmeny").item(0);
                String perf = node4.getTextContent();

                System.out.println("Teljesítmény: " + perf);
                fWriter.write("\nTeljesítmény: " + perf + "\n");
            }
        }

        // Nem hozok létre új változót, hanem csak a listamutatót változtatom
        nList = doc.getElementsByTagName("gyarto");

        for(int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            String currentNode = nNode.getNodeName();
            System.out.println("\nJelenlegi elem: " + currentNode);
            fWriter.write("\nJelenlegi elem: " + currentNode);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;
                
                String adoszam = elem.getAttribute("adoszam");

                Node nodel = elem.getElementsByTagName("nev").item(0);
                String nev = nodel.getTextContent();

                System.out.println("Adószám: " + adoszam);
                System.out.println("Név: " + nev);
                fWriter.write("\nAdószám: " + adoszam + "\nNév: " + nev);

                // Mivel a címnek több gyerekeleme is van, ezért csak fejlécként írom ki ezt az elemet,
                // a gyerekeit pedig egyesével alatta mint a többit
                System.out.println("Cím: ");
                fWriter.write("\nCím: ");
                Node node2 = elem.getElementsByTagName("orszag").item(0);
                String orszag = node2.getTextContent();
                System.out.println("Ország: " + orszag);

                Node node3 = elem.getElementsByTagName("iranyitoszam").item(0);
                String irszam = node3.getTextContent();
                System.out.println("Irányítószám: " + irszam);

                Node node4 = elem.getElementsByTagName("telepules").item(0);
                String telepules = node4.getTextContent();
                System.out.println("Település: " + telepules);

                fWriter.write("\nOrszág: " + orszag + "\nIrányítószám: " + irszam + "\nTelepülés:" + telepules + "\n");
            }
        }

        // Innentől kezdve a többi beolvasás is a fentebb említett módszereket követik

        nList = doc.getElementsByTagName("modell");

        for(int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            String currentNode = nNode.getNodeName();
            System.out.println("\nJelenlegi elem: " + currentNode);
            fWriter.write("\nJelenlegi elem: " + currentNode);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;
                
                String nev = elem.getAttribute("nev");
                System.out.println("Név: " + nev);
                fWriter.write("\nNév: " + nev);

                Node node1 = elem.getElementsByTagName("ajtok_szama").item(0);
                String ajtoksz = node1.getTextContent();
                System.out.println("Ajtók száma: " + ajtoksz);

                Node node2 = elem.getElementsByTagName("csomagtarto_meret").item(0);
                String csomagtartom = node2.getTextContent();
                System.out.println("Csomagtartó mérete: " + csomagtartom);

                Node node3 = elem.getElementsByTagName("lehajthato_teto").item(0);
                String teto = node3.getTextContent();
                System.out.println("Lehajtható tető: " + teto);

                fWriter.write("\nAjtók száma: " + ajtoksz + "\nCsomagtartó mérete: " + csomagtartom + "\nLehajtható tető:" + teto + "\n");
            }
        }

        nList = doc.getElementsByTagName("motor");

        for(int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            String currentNode = nNode.getNodeName();
            System.out.println("\nJelenlegi elem: " + currentNode);
            fWriter.write("\nJelenlegi elem: " + currentNode);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;
                
                String motorkod = elem.getAttribute("motorkod");
                System.out.println("Morokód: " + motorkod);
                fWriter.write("\nMotorkód: " + motorkod);

                System.out.println("Alaprajz: ");
                fWriter.write("\nAlaprajz: ");
                Node node1 = elem.getElementsByTagName("hengerurtartalom").item(0);
                String urtartalom = node1.getTextContent();
                System.out.println("Hengerűrtartalom: " + urtartalom);

                Node node2 = elem.getElementsByTagName("hengerelrendezes").item(0);
                String elrendezes = node2.getTextContent();
                System.out.println("Hengerelrendezés: " + elrendezes);

                Node node3 = elem.getElementsByTagName("hengerek_szama").item(0);
                String hengerszam = node3.getTextContent();
                System.out.println("Hengerek száma: " + hengerszam);

                fWriter.write("\nHengerűrtartalom: " + urtartalom + "\nHengerelrendezés: " + elrendezes + "\nHengerek száma:" + hengerszam + "\n");

                System.out.println("Üzemanyagok: " );
                fWriter.write("Üzemanyagok: ");
                for(int j = 0; j < elem.getElementsByTagName("uzemanyag").getLength(); j++)
                {
                Node node4 = elem.getElementsByTagName("uzemanyag").item(j);
                String uzemanyag = node4.getTextContent();
                System.out.println(" " + uzemanyag);
                fWriter.write("\n" + uzemanyag);
                }
                fWriter.write("\n");
            }
        }

        nList = doc.getElementsByTagName("gyartja");

        for(int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);

            String currentNode = nNode.getNodeName();
            System.out.println("\nJelenlegi elem: " + currentNode);
            fWriter.write("\nJelenlegi elem: " + currentNode);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elem = (Element) nNode;
                
                String AGYA = elem.getAttribute("Aut_Gyart_Aut");
                String AGYGY = elem.getAttribute("Aut_Gyart_Gyart");

                Node node = elem.getElementsByTagName("evjarat").item(0);
                String evjarat = node.getTextContent();

                System.out.println("Autó alvázszám: " + AGYA);
                System.out.println("Gyártó adószám: " + AGYGY);
                System.out.println("Évjárat: " + evjarat);
                fWriter.write("\nAutó alvázszám: " + AGYA + "\nGyártó adószám: " + AGYGY + "\nÉvjárat: " + evjarat + "\n");
            }
        }

        // Fájlíró bezárása
        fWriter.close();
    }
}