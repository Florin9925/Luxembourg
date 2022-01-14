package Utility;

import Town.Town;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


import Town.*;

public class ReadMap {

    public static void read(Town town, String fileName) {
        try {

            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList zonList = doc.getElementsByTagName("node");
            int aux = zonList.getLength();
            for (int tempZon = 0; tempZon < aux; tempZon++) {
                Node zonNode = zonList.item(tempZon);

                if (zonNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) zonNode;
                    NodeTown node = new NodeTown();

                    node.setId(Integer.parseInt(eElement.getAttribute("id")));
                    node.setLatitude(Integer.parseInt(eElement.getAttribute("longitude")));
                    node.setLongitude(Integer.parseInt(eElement.getAttribute("latitude")));

                    town.addNode(node);
                }
            }

            town.initAdj_list();

            zonList = doc.getElementsByTagName("arc");
            aux = zonList.getLength();
            for (int tempZon = 0; tempZon < aux; tempZon++) {
                Node zonNode = zonList.item(tempZon);

                if (zonNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) zonNode;
                    int from = Integer.parseInt(eElement.getAttribute("from"));
                    int to = Integer.parseInt(eElement.getAttribute("to"));
                    int length = Integer.parseInt(eElement.getAttribute("length"));


                    town.addArc(from, to, length);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done read");
    }

}
