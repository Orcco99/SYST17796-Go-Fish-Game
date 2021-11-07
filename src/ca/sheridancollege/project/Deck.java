/**
 * Modified SYST 17796 Project Base code.
 * Modified By Oscar Orccotoma October 30
 */
package ca.sheridancollege.project;
//import java.util.ArrayList;
//import java.util.Collections;
import java.util.Random;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author Paul Bonenfant Jan 2020
 */
public class Deck { 
    
    private Card[] deck;
    private int count;
    private static final int Size = 52; // decided to leave this as player hand size as well, sinc eno hand will be greater than 52
    private static Random random = new Random();
    
    public Deck() {
        
        deck = new Card[Size];
        count = 0; // acts as a counter for the deck 
        
    }
    
    public void fillDeck(){
        Card card;

        int[] card_ranks = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] card_suits = {1,2,3,4};

        for (int card_suit : card_suits) {
            for (int card_rank : card_ranks) {
                card = new Card(card_rank, card_suit);
                this.insertCardIntoDeck(card); // sets counter to 52 after every iteration
            }
        }
    }
    
    public void insertCardIntoDeck(Card card){
        deck[count] = card;
        count++;// when new value is added into deck count goes up 
    }
    
    public Card deleteRank(int rank){// deletes card of any input rank and returns it 
        Card tl;
        Card delete;
        
        for(int i = 0;i<count;i++){//replaces cards in deck[i] with card in last postion in the deck 
            tl = deck[i]; 
            if(tl.getRank()== rank){ // if deck[i] object is same as rank in arg, then card is placed to be deleted
                delete = tl;
                deck[i] = deck[count-1];//deck[i] object is replaced withthe last value in the deck
                count--;//deck size is reduced after card is copied in deck[i]
                return delete;
            }
        }
        return null; 
    }
    
    public Card deleteAnyCard(){ // selects random card form deck to present to player
        if (count == 0){ // if deck empty no card returned
            return null;
        }
        else {
            int rando = random.nextInt(count); // choosing random card within deck range
            Card temp = deck[rando];//placing randomly selected card from deck as card object
            
            if (rando != count-1){ // if rando value is not equal to size of deck -1 (the last card in deck)
                deck [rando] = deck[count-1];//replaces deck rando index, w/ the value of the last card in deck 
                count--; // reduces size of deck by one after substitution of previous deck[count-1] value into  deck[rando]
            }
            else {
                deck[count-1] = null;// if deck[rando] = deck[count-1], that means rnadomly selected value was the last card
                count --;//reduce size of deck after card deletion from deck 
            }
            return temp; //this is the card which is given to user
        }
    }
    
    public int checkInitialDeckForBooks(){
        for (int i =0; i<4; i++){
    // only checking first 4 cards in each deck, 
    //bc if no match is found there, then no book can be made of less than 4 cards
            if (getCount(deck[i].getRank()) == 4){
                return deck[i].getRank(); // returning rank of complete book
            }
        }
        return 0; // returns 0 if no matches found in starting hand
        //any other valu emeans that book was made in starting hand
    }
    
    public int getCount(int rank){ // returns number of occurences of a certain number, in a deck
        int occurences = 0;
        for(int i = 0; i<count;i++){
            if (deck[i].getRank()==rank){
                occurences++;
            } 
        }
        return occurences;
    }
    
    public int getSize(){
        return count;
    }
    
    public String toString(String name){
        if(count!=0){
            String result = name+"'s deck: \n";
            for (int i = 0; i<count;i++){
                result += deck[i] + "\n";
            }
            return result;   
        }
        return "Empty Hand";
    }
    
        
            
    
    
    
        

}