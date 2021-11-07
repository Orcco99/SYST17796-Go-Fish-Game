/**
 * Modified SYST 17796 Project Base code.
 * Modified By Oscar Orccotoma October 30
 */
package ca.sheridancollege.project;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GoFishGame {
    
    public Scanner scan = new Scanner(System.in);
    private Deck gameDeck;
    private boolean win; //boolean value determines whether game loops of not
    private Player p1, cpu;
    private Random rando = new Random();
    private int order;
    //private int playAgain;
    
    
    public GoFishGame(String name) {
        this.gameDeck = new Deck();
        gameDeck.fillDeck();
       p1 = new Player (name);
       cpu = new Player ("Computer");
    }
    
    public void menu(){
        System.out.println("Choose an option (1 or 2)"
                            + "\n(1) Play Go Fish "
                            + "\n(2) Rules of Go Fish");
        int menu = scan.nextInt();
        while (menu > 2 || menu < 1){
            System.out.println("Invalid input please choose between the two options");
            menu = scan.nextInt();
        }
        switch(menu){
            case 1: playGame();
            break;
            
            case 2: System.out.println("Go Fish Rules:\n"
                    + "You must first choose an order on which player will go first, CPU or the user\n"
                    + "choose a number between 1-3, and if you guess it right then you get to go first,\n"
                    + "otherwise the CPU player goes first.\n\n"
                    + "Both players are given a starting hand of 7 cards, the remainig deck is placed in the\n"
                    + "middle. The starting player will ask their opponent if they have any cards, matching the\n"
                    + "numeric rank on any of the cards in their own hand. If there is a match in the opponent's\n"
                    + "hand, then they must give all cards of the same rank to the asking player. The player who \n"
                    + "made the request may then go again as well. If a players request to an an opponents fails, \n"
                    + "then the opponent says Go Fish and the asking player must draw a card from the deck, and their\n"
                    + "turn ends. The process repeats for each players turn.\n"
                    + "The aim of the game is to score as many groupings of cards of the same rank, or as they are \n"
                    + "called in the game: Books. A book is made when you have a card rank in all four suits. At the \n"
                    + "end of the game the Player with the most Books wins."
          
                    + "\nRules for specific situations:\n"
                    + "If a player draws a card from the deck and it completes a Book in their hand, then it counts as a\n"
                    + "book for the player, and they get to go again."
                    + "If a player completes a book when during their turn, then they may go again (when a player \n"
                    + "completes a book while asking from an opponent)\n"
                    + "The game is finished when a player runs out of cards in their hand  \n"
                    + "To Begin a new Game Press Enter:");
                        scan.nextLine();
                        scan.nextLine();
                        playGame();
                    
            break;
        }       
    }
    
    
    public void playGame(){
        //do {
        initializePlayerHands();
        checkInitialDeal();
        
        decideOrder();
        
        
        if (order == 1){ // if user guesses right then they go first otherwise comp goes first
 
            do {
            //System.out.println("Cards Left: "+gameDeck.getSize());

            playerTurn();
            //checkForGameOverCondtion();
            System.out.println("Cards Left: "+gameDeck.getSize());
            
            cpuTurn();
            //checkForGameOverCondtion();
            
            System.out.println("Cards Left: "+gameDeck.getSize());
            }while(!win);
        }
        else if (order == 0) {
            do {
                
            cpuTurn();
            checkForGameOverCondtion();
            System.out.println("Cards Left: "+gameDeck.getSize());

                
            playerTurn();
            checkForGameOverCondtion();
            System.out.println("Cards Left: "+gameDeck.getSize());
            
            }while(!win);
        }
        System.out.println("Game Finished");
        if (gameDeck.getSize() == 0){//showing game ending condition which was met
            System.out.println("Deck is empty");
            System.out.println("Computers remaining hand: "+cpu.userHand.getSize()); 
            System.out.println("Players remaining hand: "+p1.userHand.getSize());
        }
        else if(p1.userHand.getSize()==0){
            System.out.println("Player's Hand is empty");
            System.out.println("Computers remaining hand: "+cpu.userHand.getSize());
        }
        else if(cpu.userHand.getSize()==0){
            System.out.println("Computers hand is empty");
            System.out.println("Players remaining hand: "+p1.userHand.getSize());
        }
        showWinner();
        //System.out.println(cpu.userHand.getSize());
        //System.out.println(p1.userHand.getSize()+cpu.userHand.getSize()+(p1.getUserBooks()*4 + cpu.getUserBooks()*4)+gameDeck.getSize());
//            System.out.println("Would you like to play again?\n"
//                    + "(1) Yes (2) No");
//            playAgain = scan.nextInt();
//            while (playAgain > 2 || playAgain < 1){
//            System.out.println("Invalid input please choose between the two options");
//            playAgain = scan.nextInt();
//        }
//        }while(playAgain==1);
//                System.out.println(p1.userHand.toString(p1.getName())); 
//                System.out.println(cpu.userHand.toString(cpu.getName()));
    }
    
    public void initializePlayerHands(){
        for(int i = 0; i<7;i++){// number here declaers size of starting hand
            p1.userHand.insertCardIntoDeck(gameDeck.deleteAnyCard());// inserting 7 cards into each users hand from gaem deck
            cpu.userHand.insertCardIntoDeck(gameDeck.deleteAnyCard());
        }  
    }
 
    public void checkInitialDeal(){// checking players starting hands for any books
       int initialUserHand = p1.userHand.checkInitialDeckForBooks();
       if (initialUserHand != 0){
           System.out.println("You are lucky "+ p1.getName() +" you already got a book " +
                            "of the rank " + initialUserHand + " in you rstarting hand." +
                            "\nYou currently have 1 book");
           p1.setUserBooks(1);
       }       
       int initialCompHand = cpu.userHand.checkInitialDeckForBooks();
       if (initialCompHand != 0){
           System.out.println("The computer is lucky and got a book " +
                            "of the rank " + initialCompHand + " in their starting hand "+
                            "\nThey currently have 1 book");
           cpu.setUserBooks(1);
       }  
    }
    public void decideOrder(){
        //int order; // if 1 then user starts, if 0 then comp starts
        System.out.println("To choose the order you will need to guess the right number between 1-3");
        int r = rando.nextInt(3)+1;
        System.out.println("Enter a number between 1-3");
        //System.out.println(r);
        int i = scan.nextInt();
        if ( i == r){
            System.out.println("Lucky Guess, User Goes First");
            order = 1;
        }
        else{
            System.out.println("Unlucky Guess, computer goes first");
            order = 0; 
        }   
    }
    
    public void playerTurn(){
        boolean userRetry = false;
        do{
            userRetry = false; // determines if player can go again true means can go again
            if (!win){
                Card drawnCard = null;
                System.out.println(p1.userHand.toString(p1.getName())); //presents players cards
                //System.out.println(cpu.userHand.toString(cpu.getName())); // shows computers hand
                //System.out.println(cpu.userHand.getSize());
                
                System.out.println("Which rank would you like to ask for?");
                int rank = scan.nextInt();
                while(p1.userHand.getCount(rank)==0){// if no matches between user input and their hand then msut retry
                    System.out.println("That Rank is not already in your deck, " 
                                        +"Enter another value");
                    rank = scan.nextInt();
                }
                int matches = cpu.userHand.getCount(rank);
                if (matches == 0){
                    System.out.println("Go Fish");
                    drawnCard = gameDeck.deleteAnyCard();
                    
                    if (drawnCard.getRank() == rank){//if drawn card matches asked rank value then player goes again 
                        userRetry = true;
                        p1.userHand.insertCardIntoDeck(drawnCard);
                        System.out.println("Drawn Card: "+ drawnCard);
                        System.out.println("Lucky draw go again");
                    }
                    else{
                        System.out.println("Drawn card: "+ drawnCard);
                        p1.userHand.insertCardIntoDeck(drawnCard);
                    }
                    int countAfterGoFish = p1.userHand.getCount(drawnCard.getRank());
                    if(countAfterGoFish == 4){//checking to see if a book was completed after drawign card
                        p1.setUserBooks(1);
                        System.out.println("You just completed a book "
                                        + "with the Rank "+drawnCard.getRank() +"\n"
                                        + "You now have: "+p1.getUserBooks()+" Books\n"
                                        + "The computer has: "+cpu.getUserBooks()+" Books"
                                        + "\nGo Again");
                                        userRetry = true;
                    
                        for(int i = 0;i<4;i++){
                            p1.userHand.deleteRank(drawnCard.getRank());
                        }
                        System.out.println("Cards Left: "+gameDeck.getSize());
                    }
                    if (!userRetry ||userRetry){
                        checkForGameOverCondtion();
                    }
                } else if ( matches >=1){ // inserting cpu card into user hand if match
                        for (int i = 0;i<matches;i++){
                            p1.userHand.insertCardIntoDeck(cpu.userHand.deleteRank(rank));
                        }
                        System.out.println("The computer has "+ matches+ " cards of that rank, go again.");
                        System.out.println("Cards Left: "+gameDeck.getSize());
                        userRetry = true;
                        
                        int countAfterTurn = p1.userHand.getCount(rank);
                        if (countAfterTurn ==4){
                            p1.setUserBooks(1);
                            System.out.println("You just completed a book"
                                                + " with the rank: "+rank+"\n"
                                                + "You now have: "+p1.getUserBooks()+" Books \n"
                                                + "The computer has: "+cpu.getUserBooks()+" Books"
                                                + "\nYou get to go again");
                            
                            for (int i =0;i<4;i++){
                                p1.userHand.deleteRank(rank); // deleting each matching ranked card
                            }
                        }
                    
                    checkForGameOverCondtion();}           
                }   
            }while(userRetry);
    }
    public void cpuTurn(){
        boolean cpuRetry = false;
        
        do {
            cpuRetry=false;
            if (!win){
                Card cpuDrawnCard = null;
                Card guess = cpu.userHand.deleteAnyCard();//randomly pulling a card from cpu hand
                cpu.userHand.insertCardIntoDeck(guess); // places card that was guessed back into the cpu hand
                //System.out.println(cpu.userHand.getSize());
                int rank = guess.getRank();
                int cpuMatches = p1.userHand.getCount(rank);// comparing cpu card to user card hand
                
                System.out.println("Computer player guess: "+guess);
                if(cpuMatches == 0){ // go fish condition
                    cpuDrawnCard = gameDeck.deleteAnyCard(); // adding card to cpu hand from deck 
                
                    if(cpuDrawnCard.getRank()==rank){ // if new drawn card is same rank as card asked by comp
                        cpu.userHand.insertCardIntoDeck(cpuDrawnCard);
                        cpuRetry = true;
                        System.out.println("Drawn Card: "+ cpuDrawnCard);
                        System.out.println("Lucky draw go again");   
                    }
                    else {
                        cpu.userHand.insertCardIntoDeck(cpuDrawnCard);
                        System.out.println("Computer guessed wrong, your turn");
                        System.out.println("Computer draws a card");
                    }
                    int cpuRankCountAfterGoFish = cpu.userHand.getCount(cpuDrawnCard.getRank()); //checking if drawn card completes a set for cpu hand
                    if(cpuRankCountAfterGoFish == 4){
                        cpu.setUserBooks(1);
                        System.out.println("Computer just completed a book from drawing a card "
                                        + "with the rank "+cpuDrawnCard.getRank()
                                        + "\nThe computer now has: "+cpu.getUserBooks()+" Books\n"
                                        +"You have: "+ p1.getUserBooks()+" Books\n"
                                        + "Computer gets to go again");
                                        cpuRetry = true;
                    
                    for(int i = 0;i<4;i++){
                        cpu.userHand.deleteRank(cpuDrawnCard.getRank());
                    }
                    
                    }
                    if(cpuRetry){
                        checkForGameOverCondtion();
                    }
                }else if (cpuMatches >= 1){// if cpu guess does match a card in user deck
                        for (int i =0;i<cpuMatches;i++){
                            cpu.userHand.insertCardIntoDeck(p1.userHand.deleteRank(rank));//inserting user card into cpu hand
                        }
                        System.out.println("The computer took "+cpuMatches+" of your cards"
                                            + "\nComputer gets to go again");
                        cpuRetry = true;
                        
                        int compCountAfterTurn = cpu.userHand.getCount(rank);
                        if (compCountAfterTurn == 4){
                            cpu.setUserBooks(1);
                            System.out.println("Computer just got a book from stealing you cards, with the rank: "+
                                                rank+"\nThe computer now has: "+cpu.getUserBooks()+" Books\n"
                                                 + "You have: "+p1.getUserBooks()+" Books"
                                                 + "\nComputer gets to go again");
                        
                            for (int i = 0;i<4;i++){
                                cpu.userHand.deleteRank(rank);
                            }
                        }
                    checkForGameOverCondtion();}   
                }
        } while(cpuRetry);
    }
        
    
    public void checkForGameOverCondtion(){
        win = (gameDeck.getSize() == 0 || p1.userHand.getSize() == 0 || cpu.userHand.getSize()==0);
        // if any of these conditions are met then win becomes true and game is over
    }
    
    public void showWinner(){
        if (p1.getUserBooks() > cpu.getUserBooks()){
            System.out.println("Congrats "+p1.getName() +  " you won!");
            System.out.println("User Books: "+p1.getUserBooks());
            System.out.println("Computer Books: "+cpu.getUserBooks());
        }
        else if (cpu.getUserBooks()> p1.getUserBooks()){
            System.out.println("Computer won!");
            System.out.println("User Books: "+p1.getUserBooks());
            System.out.println("Computer Books: "+cpu.getUserBooks());
        }
        else if(cpu.getUserBooks()==p1.getUserBooks()){
            System.out.println("The game was a tie");
            System.out.println("User Books: "+p1.getUserBooks());
            System.out.println("Computer Books: "+cpu.getUserBooks());
        }       
    }
    /**
     * @return the name
     */
    

    /**
     * @return the players of this game
     */
    

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    
}//end class
