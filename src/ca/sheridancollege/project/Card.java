/**
 * Modified SYST 17796 Project Base code.
 * Modified By Oscar Orccotoma October 30
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public class Card {
    //default modifier for child classes
    private int rank;
    private int suit;

    public Card(int rank, int suit){
        this.suit = suit;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    public String toString(){
        String r="";
        String s="";

        if (rank > 10){
            switch(rank){
                case 11:
                    r = "Jack of ";
                    break;
                case 12:
                    r = "Queen of ";
                    break;
                case 13:
                    r = "King of ";
                    break;  
            }
        }else if (rank == 1){
                    r = "Ace of ";
                    }
        else{
            r = rank+" of ";
        }

        switch (suit) {
            case 1:
                s = "Diamonds";
                break;
            case 2:
                s = "Clubs";
                break;
            case 3:
                s = "Hearts";
                break;
            case 4:
                s = "Spades";
                break;
            default:
                break;
        }
        return r + s;

    }

}
