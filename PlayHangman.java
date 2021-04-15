
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alexandros Patsalides / 1028348
 * @author Christodoulos Mavrides / 1040660 
 * 
 * @version 1.0
 * 
 * This class contains the main method of the Hangman game and
 * controlls the behaviour of the Hangman class
 */
public class PlayHangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = 0;
        int guessNum = 0;
        ArrayList<String> dictionary = new ArrayList<String>();
        char guess;

        try(Scanner fileReader = new Scanner(new File(args[0]))){
            while(fileReader.hasNextLine()){
                dictionary.add(fileReader.nextLine());
            }    
        }
        catch(FileNotFoundException e){
                e.printStackTrace();
        }

        length = Integer.parseInt(args[1]);
        guessNum = Integer.parseInt(args[2]);
        
        System.out.println("Welcome to Hangman game");
        System.out.println("You choose to use length word of: "+ length);
        System.out.println("You choose to use maximum of tries = "+guessNum);

        Hangman game = new Hangman(length, guessNum, dictionary);       

        game.printSums();
    	
      
        while(!game.exitGame()){
                  
        	System.out.println("Your guess? ");
            guess = checkLetterInput(sc);
            if(game.checkDoublicate(guess)) {
            	System.out.println("You already guessed that!");
            	System.out.println();
            }
            else {
            if(game.playGame(guess)){
                System.out.println("Your guess was right!");
            }
            else{
                System.out.println("Sorry, there are no "+ guess+"'s");
            }

            System.out.println();
            }
           
            
           game.printSums();
            
        }
        

        if (game.Winner()){
            System.out.println("You won!!!");
        }
        else{
            System.out.println("Game Over. You lost.");
        }

        System.out.println("answer = ");
        System.out.println(game.getAnswer());
        sc.close();
        
    }

    /**
     * This is a checker method that checks the users input
     * @param sc The Scanner
     * @return It returns the character if valid or inputs an other char
     */
    private static char checkLetterInput(Scanner sc){
        while(true){
            if (sc.hasNext()){
                String temp = sc.next();
                if (temp.length()==1 && Character.isLetter(temp.charAt(0))){
                    System.out.println("Your letter is: " + temp.charAt(0));
                    return temp.charAt(0);
                }
            }
            System.out.println("Input is invalid. Please enter one letter: ");
        }
    }
}