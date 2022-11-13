package hu.domparse.IG1TYM;

import java.io.File;

// Egyed (Transformer) osztály, factory (TransformerFactory) ami létrehozza ezt az egyedet és kivételek, amiket dobhatnak importálása
// Mivel a transzformációnak mindig van egy forrása és eredménye, kell azaz osztály, ami szükséges ahhoz,
// hogy a DOM-ot használjuk forrásként (DOMSource) és egy kimeneti folyam az eredményeknek (StreamResult)
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DOMModifyIG1TYM
{
    public static void main(String argv[])
    {
        try
        {
            File inputFile = new File("XMLIG1TYM.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);

            // Ezek csak a kód szétválasztása miatt vannak, minden rész az adott kódfejezet rövid leírásával kezd
            // ------------------------------------------------------------------------------------------------------------------------------
            // ******************************************************************************************************************************
            // ------------------------------------------------------------------------------------------------------------------------------
            // A "supercar" példához nagyon hasonlóan attribútum értéket, illetve mező értéket változtat,
            // azzal a kivétellel, hogy mivel nekem az "auto" egyedből több is van,
            // bele van téve még egy for loop-ba az egész, ami addig megy, ahány ilyen elemem van
            NodeList autoList = doc.getElementsByTagName("auto");
            for(int i = 0; i < autoList.getLength(); i++)
            {
                // Mindig változtatom az aktuálisan lekért auto egyedem
                Node auto = doc.getElementsByTagName("auto").item(i);

                // auto attribútumának módosítása
                NamedNodeMap attr = auto.getAttributes();
                // Ha az attribútum az alvázszám
                Node nodeAttr = attr.getNamedItem("alvazszam");
                // Akkor változtassa meg "A0X"-re, ahol X az auto egyed jelenlegi indexe a sorban (+1 az indexelés miatt, mert az 0-tól indul,
                // de én azt akarom hogy 1-től)
                nodeAttr.setTextContent("A0" + (i+1));
                
                // auto gyerekelemeinek kilistázása
                NodeList list = auto.getChildNodes();     
                
                // for loop ami a gyerekelemek számáig megy
                for(int temp = 0; temp < list.getLength(); temp++)
                {
                    Node node = list.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element eElement = (Element) node;

                        // A név nevű gyerekelemnél teljesül
                        if ("nev".equals(eElement.getNodeName()))
                        {
                            // Ha a név egyenlő ezzel
                            if ("Sprinter Trueno GT-Apex".equals(eElement.getTextContent()))
                            {
                                // Változtassa meg erre
                                eElement.setTextContent("Sprinter Trueno GT-Apex AE86");
                            }
                            if ("206 Profil".equals(eElement.getTextContent()))
                            {
                                eElement.setTextContent("206 Profil 1.1");
                            }
                        }
                    }
                }
            }
            // ------------------------------------------------------------------------------------------------------------------------------
            // ******************************************************************************************************************************
            // ------------------------------------------------------------------------------------------------------------------------------
            // A luxurycars törléséhez hasonló, ugyanazzal a különbséggel, mint az előbbi módosításnál
            // Itt a "modell" elemek "ajtok_szama" gyerekelemét törlöm
            NodeList modellList = doc.getElementsByTagName("modell");
            for(int i = 0; i < modellList.getLength(); i++)
            {
                // Kilistázza a jelenlegi modell egyedet
                Node modell = doc.getElementsByTagName("modell").item(i);
            
                // Lekéri annak gyerekelemeit
                NodeList childNodes = modell.getChildNodes();

                // Végigmegy a gyerekelemeken
                for (int count = 0; count < childNodes.getLength(); count++)
                {
                    Node node = childNodes.item(count);
                    // Ha a gyerekelem neve "ajtok_szama"
                    if("ajtok_szama".equals(node.getNodeName()))
                    {
                        // Akkor törölje
                        modell.removeChild(node);
                    }
                }
            }
            // ------------------------------------------------------------------------------------------------------------------------------
            // ******************************************************************************************************************************
            // ------------------------------------------------------------------------------------------------------------------------------
            // Alaprajzon belül módosítja a hengerelrendezésbe írtakat
            NodeList alaprajzList = doc.getElementsByTagName("alaprajz");
            for(int i = 0; i < alaprajzList.getLength(); i++)
            {
                // Kilistázza az alaprajz egyedeket
                Node alaprajz = doc.getElementsByTagName("alaprajz").item(i);
            
                // Lekéri annak gyerekelemeit
                NodeList childNodes = alaprajz.getChildNodes();

                // for loop ami a gyerekelemek számáig megy
                for(int temp = 0; temp < childNodes.getLength(); temp++)
                {
                    Node node = childNodes.item(temp);

                    // Ellenőrzés hogy a kapott egyed elem-e
                    if(node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element eElement = (Element) node;

                        // A hengerelrendezés nevű gyerekelemnél teljesül
                        if("hengerelrendezes".equals(eElement.getNodeName()))
                        {
                            // Ha a hengerelrendezés egyenlő ezzel
                            if ("I".equals(eElement.getTextContent()))
                            {
                               // Változtassa meg erre
                               eElement.setTextContent("Inline");
                            }
                        }
                    }
                }
            }
            // ------------------------------------------------------------------------------------------------------------------------------
            // ******************************************************************************************************************************
            // ------------------------------------------------------------------------------------------------------------------------------
            // Új gyerekelemet vesz fel a gyártja egyedbe - "honap", majd az Aut_Gyart_Aut attribútum értéke alapján állít neki értéket
            NodeList gyartjaList = doc.getElementsByTagName("gyartja");
            for (int i = 0; i < gyartjaList.getLength(); i++)
            {
                Node gyartja = gyartjaList.item(i);

                // Lekéri az "Aut_Gyart_Aut" attribútum értékét és eltárolja az "id"-ben
                String id = gyartja.getAttributes().getNamedItem("Aut_Gyart_Aut").getTextContent();

                // Létrehozza az új "honap" elemet
                Element honap = doc.createElement("honap");
                gyartja.appendChild(honap);

                // Az "id" értéke alapján ad értéket az új "honap" elemnek
                if ("01".equals(id))
                {
                    honap.appendChild(doc.createTextNode("03"));
                }
                if ("02".equals(id))
                {
                    honap.appendChild(doc.createTextNode("01"));
                }
                if ("03".equals(id))
                {
                    honap.appendChild(doc.createTextNode("08"));
                }
            }
            // ------------------------------------------------------------------------------------------------------------------------------
            // ******************************************************************************************************************************
            // ------------------------------------------------------------------------------------------------------------------------------
            // Tratalom konzolra írása:
            // Csinálunk egy transzformációt ami ahhoz kell, hogy az XML fájlt a System.out-ra továbbítsuk
            // Létrehozunk egy transformer objektumot, használjuk a DOM-ot hogy létrehozzunk egy forrás objektumot és
            // használjuk a System.out-ot hogy építsünk egy eredmény objektumot
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);

            System.out.println("-----Módosított fájl-----");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
