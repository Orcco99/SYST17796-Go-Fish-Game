

package ca.sheridancollege.project;

//import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author oscar
 */
public class GameLauncher {

    public static void main(String[] args) {
        //int choice;
        
//        Deck deck = new Deck();
//        deck.fillDeck();
//        
//        System.out.println(deck.toString("osacr"));
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Go Fish Card Game\n"
                    + "This is a one player game against a Computer player");
        System.out.println("Please enter a Player name:");
        String name = scan.nextLine();
        
        
        GoFishGame game = new GoFishGame(name);
        game.menu();
        
        
        

    }
}
    

