package deadwood.board;

import deadwood.xml.ParseXML;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Stores info about the state of the current board.
 */
public class Board {

    public static Place trailers = new Place("Trailers");
    public static Set mainStreet = new Set("Main Street", 3);
    public static Set saloon = new Set("Saloon", 2);
    public static Place castingOffice = new Place("Casting Office");
    public static Set ranch = new Set("Ranch", 2);
    public static Set secretHideout = new Set("Secret Hideout", 3);
    public static Set bank = new Set("Bank", 1);
    public static Set church = new Set("Church", 2);
    public static Set hotel = new Set("Hotel", 3);
    public static Set trainStation = new Set("Train Station", 3);
    public static Set jail = new Set("Jail", 1);
    public static Set generalStore = new Set("General Store", 2);

    public static List<Set> sets = new ArrayList<>();


    private static Scene[] scenes;
    static { //generates cards
        try {
            scenes = ParseXML.readCardData(ParseXML.getDocFromFile("cards.xml"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static int numOfScenes;

    /**
     * Creates the board at the beginning of the game.
     * @throws ParserConfigurationException
     */
    public static void makeBoard() throws ParserConfigurationException {
        sets.add(mainStreet);
        sets.add(saloon);
        sets.add(ranch);
        sets.add(secretHideout);
        sets.add(bank);
        sets.add(church);
        sets.add(hotel);
        sets.add(trainStation);
        sets.add(jail);
        sets.add(generalStore);

        //create intra-board connections
        connect(trailers, mainStreet);
        connect(trailers, saloon);
        connect(mainStreet, saloon);

        connect(castingOffice, ranch);
        connect(castingOffice, secretHideout);
        connect(secretHideout, ranch);

        connect(bank, hotel);
        connect(bank, church);
        connect(church, hotel);

        connect(jail, generalStore);
        connect(jail, trainStation);
        connect(trainStation, generalStore);

        //create inter-board connections
        connect(saloon, bank);
        connect(trailers, hotel);

        connect(bank, ranch);
        connect(church, secretHideout);

        connect(ranch, generalStore);
        connect(castingOffice, trainStation);

        connect(generalStore, saloon);
        connect(jail, mainStreet);

        ParseXML.readBoardData(ParseXML.getDocFromFile("board.xml"));
    }

    /**
     * Helper method for makeBoard() to ease the creation of links between places
     */
    private static void connect(Place p1, Place p2) {
        p1.addNeighbor(p2);
        p2.addNeighbor(p1);
    }

    /**
     * Randomly allocates scenes among the sets.
     */
    public static void dealScenes() {
        List<Scene> cards = new ArrayList<>(scenes.length);
        cards.addAll(Arrays.asList(scenes));
        Random rand = new Random();
        for (Set set: sets) {
            int cardIndex = rand.nextInt(cards.size());
            set.setScene(cards.get(cardIndex));
            cards.remove(cards.get(cardIndex));
        }
        numOfScenes = sets.size();
    }

    public static int getNumOfScenes() {
        return numOfScenes;
    }

    public static int finishScene() {
       return --numOfScenes;
    }

    /**
     * Resets the cards at the end of the day.
     */
    public static void reset() {
        for (Set set: sets) {
            set.reset();
        }
    }
}
