package deadwood.xml;// Example Code for parsing XML file
// Dr. Moushumi Sharmin
// CSCI 345

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import deadwood.board.Board;
import deadwood.board.Place;
import deadwood.board.Role;
import deadwood.board.Scene;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ParseXML {

   
    // building a document from the XML file
    // returns a Document object after loading the book.xml file.
    public static Document getDocFromFile(String filename)
        throws ParserConfigurationException {
        {
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = null;

           try{
               doc = db.parse(filename);
           } catch (Exception ex){
               System.out.println("XML parse failure");
               ex.printStackTrace();
           }
           return doc;
        } // exception handling
    }

    public static Scene[] readCardData(Document d) {
        Element root = d.getDocumentElement();
        NodeList cards = root.getElementsByTagName("card");
        Scene[] scenes = new Scene[cards.getLength()];
        for (int i = 0; i < cards.getLength(); i++) {
            Node card = cards.item(i);
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            int budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
            NodeList children = card.getChildNodes();
            int sceneNumber = 0;
            String sceneDescription = null;
            List<Role> roles = new ArrayList<>();
            for (int j = 0; j < children.getLength(); j++) {
                Node sub = children.item(j);
                if (sub.getNodeName().equals("scene")) {
                    sceneNumber = Integer.parseInt(sub.getAttributes().getNamedItem("number").getNodeValue());
                    sceneDescription = sub.getTextContent();
                } else if (sub.getNodeName().equals("part")) {
                    String roleName = sub.getAttributes().getNamedItem("name").getNodeValue();
                    int roleLevel = Integer.parseInt(sub.getAttributes().getNamedItem("level").getNodeValue());
                    String line = "";
                    NodeList sceneSub = sub.getChildNodes();
                    for (int k = 0; k < sceneSub.getLength(); k++) {
                        Node sceneSubNode = sceneSub.item(k);
                        if (sceneSubNode.getNodeName().equals("line")) {
                            line = sceneSubNode.getTextContent();
                        }
                    }
                    roles.add(0, new Role(roleName, line, roleLevel));
                }
            }
            scenes[i] = new Scene(cardName, sceneDescription, sceneNumber, roles, budget);

        }
        return scenes;
    }

    public static void readBoardData(Document d) {
        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");
        for (int i = 0; i < sets.getLength(); i++) {
            Node set = sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();

            NodeList setChildren = set.getChildNodes();
            for (int j = 0; j < setChildren.getLength(); j++) {
                Node setChild = setChildren.item(j);

                if (setChild.getNodeName().equals("parts")) {
                    NodeList parts = setChild.getChildNodes();
                    List<Role> roles = new ArrayList<>();
                    for (int k = 0; k < parts.getLength(); k++) {
                        Node part = parts.item(k);
                        if (part.getNodeName().equals("part")) {
                            String roleName = part.getAttributes().getNamedItem("name").getNodeValue();
                            int roleLevel = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
                            NodeList partChildren = part.getChildNodes();
                            for (int l = 0; l < parts.getLength(); l++) {
                                Node partChild = partChildren.item(l);
                                System.out.println(partChild.getNodeName());
                                if (partChild.getNodeName().equals("line")) {
                                    String line = partChildren.item(l).getTextContent();
                                    roles.add(new Role(roleName, line, roleLevel));
                                    break;
                                }
                            }

                        }
                    }
                    switch (setName) {
                        case "Main Street":
                            Board.mainStreet.setRoles(roles);
                            break;
                        case "Saloon":
                            Board.saloon.setRoles(roles);
                            break;
                        case "Ranch":
                            Board.ranch.setRoles(roles);
                            break;
                        case "Secret Hideout":
                            Board.secretHideout.setRoles(roles);
                            break;
                        case "Bank":
                            Board.bank.setRoles(roles);
                            break;
                        case "Church":
                            Board.church.setRoles(roles);
                            break;
                        case "Hotel":
                            Board.hotel.setRoles(roles);
                            break;
                        case "Train Station":
                            Board.trainStation.setRoles(roles);
                            break;
                        case "Jail":
                            Board.jail.setRoles(roles);
                            break;
                        case "General Store":
                            Board.generalStore.setRoles(roles);
                            break;
                    }
                }
            }
        }
    }

    // reads data from XML file and prints data
    public void readBookData(Document d) {
        Element root = d.getDocumentElement();

        NodeList books = root.getElementsByTagName("book");

        for (int i=0; i<books.getLength();i++) {

            System.out.println("Printing information for book "+(i+1));

            //reads data from the nodes
            Node book = books.item(i);
            String bookCategory = book.getAttributes().getNamedItem("category").getNodeValue();
            System.out.println("Category = "+bookCategory);

            //reads data

            NodeList children = book.getChildNodes();
            for (int j=0; j < children.getLength(); j++) {

                Node sub = children.item(j);

                if ("title".equals(sub.getNodeName())) {
                    String bookLanguage = sub.getAttributes().getNamedItem("lang").getNodeValue();
                    System.out.println("Language = "+bookLanguage);
                    String title = sub.getTextContent();
                    System.out.println("Title = "+title);
                }

                else if ("author".equals(sub.getNodeName())) {
                    String authorName = sub.getTextContent();
                    System.out.println(" Author = "+authorName);
                }
                else if ("year".equals(sub.getNodeName())) {
                    String yearVal = sub.getTextContent();
                    System.out.println(" Publication Year = "+yearVal);
                }
                else if ("price".equals(sub.getNodeName())) {
                    String priceVal = sub.getTextContent();
                    System.out.println(" Price = "+priceVal);
                }


            } //for childnodes

            System.out.println("\n");

        }//for book nodes

    }// method

}//class