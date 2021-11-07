/**
 * Modified SYST 17796 Project Base code.
 * Modified By Oscar Orccotoma October 30
 */
package ca.sheridancollege.project;
//import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class Player {

    private String name; //the unique name for this player
    public Deck userHand;
    //private Deck compHand;
    private int userBooks;

    public int getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(int userBooks) {
// anytime method is called, will add new arg value into old userBook value
        this.userBooks += userBooks;
    }

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name) {

        this.name = name;
        this.userHand=new Deck();
        this.userBooks=0;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Ensure that the playerID is unique
     *
     * @param name the player name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    


    /**
     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
     * with logic to play your game.
     */
//    public abstract void play();

}